<%-- 
    Document   : listarClientes
    Created on : 05/12/2016, 07:55:12
    Author     : Guilherme
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h1>Lista de Clientes:</h1>
<c:forEach var="cliente" items="${clientes}">
    <p><label>Nome: </label><c:out value="${cliente.nome}" /></p>
    <hr />
</c:forEach>
</body>
</html>
