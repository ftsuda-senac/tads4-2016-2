/*
 * The MIT License
 *
 * Copyright 2016 Fernando.
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
package br.senac.tads4.lojinha.rest.resource;

import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.ProdutoService;
import br.senac.tads4.lojinha.service.jpaimpl.ProdutoServiceJPAImpl;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Fernando
 */
@Path("produto")
public class ProdutoResource {

  @Context
  private UriInfo context;
  
  //@Inject
  //private ProdutoService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listar(
          @DefaultValue("0") @QueryParam("offset") Integer offset,
          @DefaultValue("100") @QueryParam("quantidade") Integer quantidade) {
    ProdutoService service = new ProdutoServiceJPAImpl();
    // Para retornar listas de resultados, precisa usar o GenericEntity.
    List<Produto> lista = service.listar(offset, quantidade);
    GenericEntity<List<Produto>> listaRest = new GenericEntity<List<Produto>>(lista) {
    };
    return Response.ok().entity(listaRest).build();
    // ou Response.status(200).entity(service.listar(offset, quantidade).toArray()).build();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response obter(@PathParam("id") Long id) {
    ProdutoService service = new ProdutoServiceJPAImpl();
    Produto produto = service.obter(id);
    return Response.ok().entity(produto).build();
    // ou Response.status(200).entity(service.obter(id)).build();
  }
}
