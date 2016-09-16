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
package br.senac.tads4.lojinha.service.fakeimpl;

import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.ProdutoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fernando.tsuda
 */
public class ProdutoServiceFakeImpl implements ProdutoService {

  @Override
  public List<Produto> listar() {
    List<Produto> produtos = new ArrayList<>();
    // Cria produto 1
    Produto p1 = new Produto();
    p1.setNome("Bolo de chocolate");
    p1.setDescricao("Descrição do bolo de chocolate");
    p1.setDtCadastro(new Date());
    p1.setPreco(new BigDecimal("9.99"));
    produtos.add(p1); // Adiciona na lista de produtos
    
    // Cria produto 2
    Produto p2 = new Produto();
    p2.setNome("Torta de maçã");
    p2.setDescricao("Descrição da torta de maçã");
    p2.setDtCadastro(new Date());
    p2.setPreco(new BigDecimal("12.99"));
    produtos.add(p2); // Adiciona na lista de produtos
    
    Produto p3 = new Produto();
    p3.setNome("Bolo de aniversário");
    p3.setDescricao("Descrição do bolo de aniversário");
    p3.setDtCadastro(new Date());
    p3.setPreco(new BigDecimal("59.99"));
    produtos.add(p3); // Adiciona na lista de produtos
    
    return produtos;
  }
  
}
