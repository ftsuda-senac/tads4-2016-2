/*
 * The MIT License
 *
 * Copyright 2016 fernando.tsuda.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.senac.tads4.lojinha.managedbean;

import br.senac.tads4.lojinha.entidade.ProdutoQuantidade;
import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.ProdutoService;
import br.senac.tads4.lojinha.service.fakeimpl.ProdutoServiceFakeImpl;
import br.senac.tads4.lojinha.util.Mensagem;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author fernando.tsuda
 */
@Named
@SessionScoped
public class CompraBean implements Serializable {
  
  Set<ProdutoQuantidade> listaProdutos = 
	  new LinkedHashSet<ProdutoQuantidade>();

  public CompraBean() {
  }
  
  public String adicionarProduto(long idProduto, int quantidade) {
    ProdutoService service = new ProdutoServiceFakeImpl();
    Produto p = service.obter(idProduto);
    
    ProdutoQuantidade pq = null;
    for (ProdutoQuantidade item : listaProdutos) {
      if (item.getProduto().equals(p)) {
	pq = item;
	break;
      }
    }
    if (pq == null) {
      pq = new ProdutoQuantidade(p, quantidade);
      listaProdutos.add(pq);
    } else {
      pq.setQuantidade(pq.getQuantidade() + quantidade);
    }
    
    // Montar mensagem a ser apresentada para usuario
    Flash mensagem = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    mensagem.put("mensagem", new Mensagem("Produto " + p.getNome()  + " adicionado com sucesso", "success"));

    // Redirecionar para lista de produtos
    return "lista.xhtml?faces-redirect=true";
  }
  
  public int getQuantidadeItens() {
    return listaProdutos.size();
  }
  
}
