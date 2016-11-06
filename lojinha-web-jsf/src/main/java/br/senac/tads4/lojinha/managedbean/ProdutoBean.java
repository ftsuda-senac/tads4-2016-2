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

import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.ProdutoService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author fernando.tsuda
 */
@Named
@RequestScoped
public class ProdutoBean implements Serializable {

  @Inject
  private ProdutoService service;

  private Long idProduto;

  private Produto produto = null;

  public ProdutoBean() {
  }

  public List<Produto> getLista() {
    return service.listar(0, 100);
  }

  public Produto getProduto() {
    if (produto == null) {
      // FacesContext: objeto que contém todas as informações relacionadas ao
      // processamento da requisição e geração da resposta dentro do ciclo do
      // JSF
      // http://docs.oracle.com/javaee/6/api/javax/faces/context/FacesContext.html
      FacesContext fc = FacesContext.getCurrentInstance();
      produto = obter(getIdParam(fc));
    }
    return produto;
  }

  private Produto obter(long idProduto) {
    return service.obter(idProduto);
  }

  private Long getIdParam(FacesContext fc) {
    Map<String, String> params = fc.getExternalContext()
            .getRequestParameterMap();
    return Long.parseLong(params.get("id"));
  }

  public Long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(Long idProduto) {
    this.idProduto = idProduto;
  }

  public String remover(Long idProd) {
    System.out.println("idProd: " + idProd);
    service.remover(idProd);
    return "/lista.xhtml?faces-redirect=true";
  }

}
