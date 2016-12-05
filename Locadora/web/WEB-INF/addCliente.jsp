<%-- 
    Document   : addCliente
    Created on : 05/12/2016, 05:21:20
    Author     : Guilherme
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h1>Formulário</h1>
<form action="cadastrarCliente.html" method="POST">
    <div>
        <label>Nome completo:</label>
        <input name="nome" />
    </div>
    <div>
        <input type="submit" value="Enviar" />
    </div>
</form>
</body>
</html>
