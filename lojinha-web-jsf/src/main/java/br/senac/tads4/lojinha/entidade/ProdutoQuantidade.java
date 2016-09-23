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
package br.senac.tads4.lojinha.entidade;

import br.senac.tads4.lojinha.entidade.Produto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author fernando.tsuda
 */
public class ProdutoQuantidade {
  
  private Produto produto;
  
  private int quantidade = 0;
  
  private Date dtInclusao;
  
  public ProdutoQuantidade(Produto produto, int quantidadeInicial) {
    this.produto = produto;
    quantidade = quantidadeInicial;
    dtInclusao = new Date();
  }

  public Produto getProduto() {
    return produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public Date getDtInclusao() {
    return dtInclusao;
  }
  
  public BigDecimal getPreco() {
    // Preco * quantidade
    return produto.getPreco().multiply(new BigDecimal(quantidade));
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.produto);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ProdutoQuantidade other = (ProdutoQuantidade) obj;
    if (!Objects.equals(this.produto, other.produto)) {
      return false;
    }
    return true;
  }
  
  
  
}
