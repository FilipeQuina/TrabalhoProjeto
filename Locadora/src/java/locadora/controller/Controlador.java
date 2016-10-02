/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import locadora.model.Gerente;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Logar", "/ValidarLogin"})
public class Controlador extends HttpServlet {

    @PersistenceUnit(unitName = "LocadoraPU")
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")
    UserTransaction ut;

    Gerente g = Gerente.getInstancia();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("/Logar")) {
            request.getRequestDispatcher("/WEB-INF/logar.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/ValidarLogin")) {
            String loginServlet=request.getParameter("txtLogin");
            String senhaServlet=request.getParameter("txtSenha");
            if(loginServlet.equals(g.getLogin()) && senhaServlet.equals(g.getSenha())){
            request.getRequestDispatcher("/WEB-INF/bemVindoGerente.jsp").forward(request, response);
            }
            else{
        request.getRequestDispatcher("/WEB-INF/logar.jsp").forward(request, response);
        }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
