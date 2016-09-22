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
package br.senac.tads4.lojinha.servlet;

import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.ProdutoService;
import br.senac.tads4.lojinha.service.fakeimpl.ProdutoServiceFakeImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "ListaProdutosServlet", urlPatterns = {"/ListaProdutosServlet"})
public class ListaProdutosServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ListaProdutosServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet ListaProdutosServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
    ProdutoService service = new ProdutoServiceFakeImpl();
    List<Produto> produtos = service.listar(0, 100);
    HttpSession sessao = request.getSession();
    Integer contador = 0;
    if (sessao.getAttribute("contador") != null) {
      contador = (Integer) sessao.getAttribute("contador");
    }
    contador ++;
    sessao.setAttribute("contador", contador);
    
    // Encaminha para jsp montar resposta
    request.setAttribute("contador", contador);
    request.setAttribute("xpto", produtos);
    RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("/WEB-INF/jsp/listaProdutos.jsp");
    dispatcher.forward(request, response);

    
    // Montar a resposta
//    response.setContentType("text/html;charset=UTF-8");
//    try (PrintWriter out = response.getWriter()) {
//      /* TODO output your page here. You may use following sample code. */
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet ListaProdutosServlet</title>");
//      out.println("</head>");
//      out.println("<body>");
//      out.println("<div>");
//      for (Produto p : produtos) {
//	out.println("<div>");
//	out.println("<p>Nome:" + p.getNome() + "</p>");
//	out.println("<p>Descrição:" + p.getDescricao() + "</p>");
//	out.println("<p>Preço:" + p.getPreco() + "</p>");
//	out.println("<p>Data cadastro:" + p.getDtCadastro() + "</p>");
//	out.println("</div>");
//      }
//      out.println("</div>");
//      out.println("</body>");
//      out.println("</html>");
//    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
