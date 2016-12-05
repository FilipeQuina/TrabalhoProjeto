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
import locadora.controller.ClienteJpaController;
import locadora.controller.CompraJpaController;
import locadora.controller.FilmeBluRayJpaController;
import locadora.controller.FilmeDVDJpaController;
import locadora.controller.FuncionarioJpaController;
import locadora.model.Cliente;
import locadora.model.Compra;
import locadora.model.FilmeBluRay;
import locadora.model.FilmeDVD;
import locadora.model.Funcionario;
import locadora.model.Pagamento;
import locadora.model.PagamentoBoleto;
import locadora.model.PagamentoCartaoCredito;

/**
 *
 * @author Guilherme
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet", "/cadastrarCliente.html", "/listarClientes.html", "/cadastrarFuncionario.html", "/listarFuncionarios.html", "/cadastrarCompra.html"})
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
        } else if (uri.contains("listarClientes.html")) {
            listAll(request, response);
        } else if (uri.contains("cadastrarFuncionario.html")) {
            request.getRequestDispatcher("/WEB-INF/addFuncionario.jsp").forward(request, response);
        } else if (uri.contains("listarFuncionarios.html")) {
            listAllF(request, response);
        } else if (uri.contains("cadastrarCompra.html")) {
            ClienteJpaController daoCliente = new ClienteJpaController(ut, emf);
            FilmeBluRayJpaController daoFilmeBD = new FilmeBluRayJpaController(ut, emf);
            FilmeDVDJpaController daoFilmeDVD = new FilmeDVDJpaController(ut, emf);
            List<Cliente> clientes = daoCliente.findClienteEntities();
            List<FilmeBluRay> bds = daoFilmeBD.findFilmeBluRayEntities();
            List<FilmeDVD> dvds = daoFilmeDVD.findFilmeDVDEntities();
            request.setAttribute("clientes", clientes);
            request.setAttribute("bds", bds);
            request.setAttribute("dvds", dvds);
            request.getRequestDispatcher("/WEB-INF/addCompra.jsp").forward(request, response);
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
        } else if (request.getRequestURI().contains("cadastrarFuncionario.html")) {
            String nome = request.getParameter("nome");
            String senha = request.getParameter("senha");
            double salariobase = Double.parseDouble(request.getParameter("salariobase"));
            Integer cargo = Integer.parseInt(request.getParameter("cargo"));
            Funcionario a = new Funcionario(cargo, salariobase, nome, senha);

            FuncionarioJpaController daoFuncionario = new FuncionarioJpaController(ut, emf);
            try {
                daoFuncionario.create(a);
            } catch (Exception ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("listarFuncionarios.html");
        } else if (request.getRequestURI().contains("cadastrarCompra.html")) {
            String nome = request.getParameter("nome");
            Long idCliente = Long.parseLong(request.getParameter("cliente"));
            Long idDVD = Long.parseLong(request.getParameter("dvd"));
            Long idBD = Long.parseLong(request.getParameter("bd"));
            Integer escolha = Integer.parseInt(request.getParameter("escolha"));
            ClienteJpaController daoCliente = new ClienteJpaController(ut, emf);
            FilmeBluRayJpaController daofilmeBD = new FilmeBluRayJpaController(ut, emf);
            FilmeDVDJpaController daofilmeDVD = new FilmeDVDJpaController(ut, emf);
            Compra a = new Compra(0f, daoCliente.findCliente(idCliente), daofilmeBD.findFilmeBluRay(idBD), daofilmeDVD.findFilmeDVD(idDVD), nome);

            CompraJpaController daoCompra = new CompraJpaController(ut, emf);
            try {
                daoCompra.create(a);
            } catch (Exception ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Pagamento pagamento;
            if(escolha == 1){
                pagamento = new PagamentoBoleto();
                pagamento.processarCompra(a);
                request.setAttribute("valor", pagamento.processarCompra(a));
                request.getRequestDispatcher("/WEB-INF/efetuarCompra.jsp").forward(request, response);
            }
            else if(escolha == 2){
                pagamento = new PagamentoCartaoCredito();
                pagamento.processarCompra(a);
                request.setAttribute("valor", pagamento.processarCompra(a));
                request.getRequestDispatcher("/WEB-INF/efetuarCompra.jsp").forward(request, response);
            }
            //response.sendRedirect("listarCompras.html");
        }

    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteJpaController daoCliente = new ClienteJpaController(ut, emf);
        List<Cliente> clientes = daoCliente.findClienteEntities();
        System.out.println(clientes);
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/WEB-INF/listarClientes.jsp").forward(request, response);
    }

    private void listAllF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FuncionarioJpaController daoFuncionario = new FuncionarioJpaController(ut, emf);
        List<Funcionario> Funcionarios = daoFuncionario.findFuncionarioEntities();
        System.out.println(Funcionarios);
        request.setAttribute("funcionarios", Funcionarios);
        request.getRequestDispatcher("/WEB-INF/listarFuncionarios.jsp").forward(request, response);
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
