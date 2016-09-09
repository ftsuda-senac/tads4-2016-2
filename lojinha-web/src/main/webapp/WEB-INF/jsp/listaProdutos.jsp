<%-- 
    Document   : listaProdutos
    Created on : 08/09/2016, 21:36:11
    Author     : fernando.tsuda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Exemplos Servlet e JSP</title>
  </head>
  <body>
    <h1>Lista de Produtos</h1>

  <c:choose>
    <c:when test="${contador == 1}">
      <p>PRIMEIRO ACESSO NESTA SESSÃO</p>
    </c:when>
    <c:otherwise>
      <p>Contador: ${contador}</p>
    </c:otherwise>
  </c:choose>

  <div>
    <c:forEach items="${xpto}" var="prod">
      <div>
	<p>Nome: <c:out value="${prod.nome}" /></p>
	<p>Descrição: <c:out value="${prod.descricao}" /></p>
	<p>Preço: <fmt:formatNumber type="currency" 
				    value="${prod.preco}" /></p>
	<p>Data de Cadastro: <fmt:formatDate value="${prod.dtCadastro}"
					     pattern="dd/MM/yyyy" /></p>
      </div>
    </c:forEach>
  </div>

</body>
</html>
