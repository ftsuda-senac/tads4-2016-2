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
package br.senac.tads4.lojinha.service.jpaimpl;

import br.senac.tads4.lojinha.entidade.Categoria;
import br.senac.tads4.lojinha.entidade.ImagemProduto;
import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.ProdutoService;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author fernando.tsuda
 */
public class ProdutoServiceJPAImpl implements ProdutoService {

  private EntityManagerFactory emFactory
	  = Persistence.createEntityManagerFactory("LojinhaPU");

  @Override
  public List<Produto> listar(int offset, int quantidade) {
    EntityManager em = emFactory.createEntityManager();
    try {
      // Query JPQL
      Query query = em.createQuery(
	      "SELECT DISTINCT p FROM Produto p "
	      + "LEFT JOIN FETCH p.categorias "
	      + "LEFT JOIN FETCH p.imagens")
	      .setFirstResult(offset)
	      .setMaxResults(quantidade);
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Produto obter(long idProduto) {
    EntityManager em = emFactory.createEntityManager();
    try {
      // Query JPQL
      Query query = em.createQuery(
	      "SELECT DISTINCT p FROM Produto p "
	      + "LEFT JOIN FETCH p.categorias "
	      + "LEFT JOIN FETCH p.imagens "
	      + "WHERE p.id = :idProd")
	      .setParameter("idProd", idProduto);
      return (Produto) query.getSingleResult();
    } finally {
      em.close();
    }
  }

  @Override
  public void incluir(Produto p) {
    EntityManager em = emFactory.createEntityManager();
    EntityTransaction transacao = em.getTransaction();
    try {
      transacao.begin();
      // Loop para fazer "attach" dos objetos Categoria no
      // EntityManager, para evitar a criação de categorias com 
      // nomes duplicados
      for (Categoria c : p.getCategorias()) {
	if (c.getId() != null) {
	  em.merge(c);
	} else {
	  em.persist(c);
	}
      }
      em.persist(p);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
    } finally {
      em.close();
    }
  }

  @Override
  public void alterar(Produto p) {
    EntityManager em = emFactory.createEntityManager();
    EntityTransaction transacao = em.getTransaction();
    try {
      transacao.begin();
      // Loop para fazer "attach" dos objetos Categoria no
      // EntityManager, para evitar a criação de categorias com 
      // nomes duplicados
      for (Categoria c : p.getCategorias()) {
	if (c.getId() != null) {
	  em.merge(c);
	} else {
	  em.persist(c);
	}
      }
      //Produto prodAlterado = (Produto) em.merge(p);
      em.merge(p);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
    } finally {
      em.close();
    }
  }

  @Override
  public void remover(long idProduto) {
    EntityManager em = emFactory.createEntityManager();
    EntityTransaction transacao = em.getTransaction();
    try {
      transacao.begin();
      Produto p = em.find(Produto.class, idProduto);
      List<ImagemProduto> imagens = p.getImagens();
      for (Iterator<ImagemProduto> i = imagens.iterator(); 
	      i.hasNext(); ) {
	ImagemProduto imagem = i.next();
	em.remove(imagem);
      }
      p.setCategorias(null);
      em.remove(p);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
    } finally {
      em.close();
    }
  }

}
