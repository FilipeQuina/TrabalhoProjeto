<%-- 
    Document   : listarFuncionarios
    Created on : 05/12/2016, 09:45:49
    Author     : Guilherme
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="cabecalho.jsp" %>
<h1>Lista de Alunos:</h1>
<c:forEach var="funcionario" items="${funcionarios}">
    <p><label>Nome: </label><c:out value="${funcionario.nome}" /></p>
    <p><label>Cargo: </label><c:out value="${funcionario.cargo}" /></p>
    <p><label>Salario base: </label><c:out value="${funcionario.salarioBase}" /></p>
    <p><label>Salario com imposto: </label><c:out value="${funcionario.calcularSalarioComImposto()}" /></p>    
    <hr />
</c:forEach>
</body>
</html>
