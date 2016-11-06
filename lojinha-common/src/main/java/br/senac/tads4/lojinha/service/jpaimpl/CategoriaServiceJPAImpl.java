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
import br.senac.tads4.lojinha.service.CategoriaService;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author fernando.tsuda
 */
@Default
public class CategoriaServiceJPAImpl implements CategoriaService {

  private EntityManagerFactory emFactory
	  = Persistence.createEntityManagerFactory("LojinhaPU");

  @Override
  public List<Categoria> listar() {
    EntityManager em = emFactory.createEntityManager();
    try {
      // Query JPQL
      Query query = em.createQuery("SELECT c FROM Categoria c");
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public Categoria obter(int id) {
    EntityManager em = emFactory.createEntityManager();
    try {
      // Query JPQL
      Query query = em.createQuery(
	"SELECT c FROM Categoria c WHERE c.id = :idCategoria")
	      .setParameter("idCategoria", id);
      return (Categoria) query.getSingleResult();
    } finally {
      em.close();
    }
  }

}
