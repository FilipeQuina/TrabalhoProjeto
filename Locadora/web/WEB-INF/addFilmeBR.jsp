<%-- 
    Document   : addFilmeBD
    Created on : 05/12/2016, 05:32:01
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/AddFilmeBR" method="POST">
            <input type="text" name="nome" value="" />
            <input type="text" name="autor" value="" />
            <input type="text" name="valor" value="" />
            <input type="submit" value="Criar" />
        </form>
        
    </body>
</html>
