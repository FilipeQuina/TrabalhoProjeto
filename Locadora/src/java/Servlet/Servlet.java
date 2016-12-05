/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
import locadora.controller.ClienteJpaController;
import locadora.model.Cliente;

/**
 *
 * @author Guilherme
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet", "/cadastrarCliente.html", "/listarClientes.html"})
public class Servlet extends HttpServlet {
    @PersistenceUnit(unitName = "LocadoraPU")
    EntityManagerFactory emf;
    @Resource(name = "java:comp/UserTransaction")
    UserTransaction ut;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String uri = request.getRequestURI();
        if (uri.contains("cadastrarCliente.html")) {
            request.getRequestDispatcher("/WEB-INF/addCliente.jsp").forward(request, response);
        }
        else if (uri.contains("listarClientes.html")) {
            listAll(request, response);
        }
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
        if (request.getRequestURI().contains("cadastrarCliente.html")) {
            String nome = request.getParameter("nome");
            //String idade = request.getParameter("idade");
            //Boolean sexo = Boolean.parseBoolean(request.getParameter("sexo"));
            //Integer matricula = Integer.parseInt(request.getParameter("matricula"));
            //Date inscricao = new Date();
            //Integer grupo = Integer.parseInt(request.getParameter("grupo"));

            Cliente a = new Cliente();
            a.setNome(nome);
            //a.setGrupo();
            //a.setIdade(idade);
            ClienteJpaController daoCliente = new ClienteJpaController(ut, emf);
            try {
                //System.out.println("persistindo " + a);
                daoCliente.create(a);
                //System.out.println("persistido " + a);
                //List<Aluno> alunos = daoCliente.findAlunoEntities();
                //System.out.println(alunos);
            } catch (Exception ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("listarClientes.html");
        }
    }

    
    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteJpaController daoCliente = new ClienteJpaController(ut, emf);
        List<Cliente> clientes = daoCliente.findClienteEntities();
        System.out.println(clientes);
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/WEB-INF/listarClientes.jsp").forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
