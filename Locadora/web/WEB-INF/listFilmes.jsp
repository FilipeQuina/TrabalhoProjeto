<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Filmes</title>

    </head>
    <body>

        <h1>Listar Filmes</h1>
        <table>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Autor</th>
                    <th>Duração</th>
                    <th>valor</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listaBr}" var="lbr">

                    <tr>
                        <td>${lbr.nome}</td>                    
                        <td>${lbr.tipo}</td>                    
                        <td>${lbr.valor}</td>                    
                                  
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
