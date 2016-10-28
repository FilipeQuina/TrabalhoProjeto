/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import locadora.model.DAO.FuncionarioJpaController;
import locadora.model.Funcionario;

@WebServlet(name = "Controlador", urlPatterns = {"/Logar", "/ValidarLogin","/Menu"})
public class Controlador extends HttpServlet {

    @PersistenceUnit(unitName = "LocadoraPU")
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")
    UserTransaction ut;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("/Logar")) {
            request.getRequestDispatcher("/WEB-INF/logar.jsp").forward(request, response);
        }
          if (request.getRequestURI().contains("/Menu")) {
            request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
        }
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FuncionarioJpaController fjpa = new FuncionarioJpaController(ut,emf);
        Funcionario f = new Funcionario();
//        
//        try {
//            fjpa.create(f);
//        } catch (Exception ex) {
//            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (request.getRequestURI().contains("/ValidarLogin")) {
            String loginServlet=request.getParameter("txtLogin");
            String senhaServlet=request.getParameter("txtSenha");
            boolean log=false; 
             System.out.println("estou aqui");
            List<Funcionario> lfun = fjpa.findFuncionarioEntities();
            for (Funcionario f1 : lfun) {
               
                 if(f1.getNome().equals(loginServlet)&& f1.getSenha().equals(senhaServlet)){
                     log=true;
        
          // response.sendRedirect("/Menu");
            }
                 
            }
           
            
        request.getRequestDispatcher("/WEB-INF/logar.jsp").forward(request, response);
        
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
