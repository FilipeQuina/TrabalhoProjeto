/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locadora.model.DAO.FilmeBluRayPrep;
import locadora.model.DAO.FilmeDVDPrep;
import locadora.model.FilmeBluRay;
import locadora.model.FilmeDVD;
import locadora.model.VO.FabricaBluRayPrep;
import locadora.model.VO.FabricaDVDPrep;
import locadora.model.VO.FabricaDeFilmePrep;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "FilmesController", urlPatterns = {"/FilmesController", "/ListarFilmes","/AddFilmeBR"})
public class FilmesController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     if (request.getRequestURI().contains("/FilmesController")) {
            request.getRequestDispatcher("/WEB-INF/addFilmeBR.jsp").forward(request, response);
        } 
     else if (request.getRequestURI().contains("/ListarFilmes")) {
            request.getRequestDispatcher("/WEB-INF/listFilmes.jsp").forward(request, response);
        } 
     else if (request.getRequestURI().contains("/ListarFilmes")) {
            List<FilmeBluRay> listaBR = new ArrayList<>();
            List<FilmeDVD> listaDVD = new ArrayList<>();

            try {
                FabricaDeFilmePrep daoBR = new FabricaBluRayPrep();
                daoBR.criarFilme().carregaPrep("FilmeBluRay");
               // FilmeBluRayPrep fbrp= new FilmeBluRayPrep();
                daoBR.criarFilme().listarFilmes();
                
                FabricaDeFilmePrep daoDVD = new FabricaDVDPrep();
                daoDVD.criarFilme().carregaPrep("FilmeDVD");
               // FilmeDVDPrep fbrp= new FilmeDVDPrep();
                daoDVD.criarFilme().listarFilmes();
                
                
                
            } catch (Exception ex) {
                Logger.getLogger(FilmesController.class.getName()).log(Level.SEVERE, null, ex);
               // lista = new ArrayList<FilmeBluRay>();
                request.setAttribute("erro", "Problema ao listar os filmes");
            }
            request.setAttribute("listaBr", listaBR);
            request.setAttribute("listaDVD", listaDVD);
            request.getRequestDispatcher("/WEB-INF/listFilmes.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("/AddFilmeBR")) {
            FilmeBluRay filmeBluRay = new FilmeBluRay();
            filmeBluRay.setNome(request.getParameter("nome"));
            filmeBluRay.setAutor(request.getParameter("autor"));
            filmeBluRay.setValor(Float.parseFloat(request.getParameter("valor")));


            try {
                FabricaDeFilmePrep daoBR = new FabricaBluRayPrep();
                daoBR.criarFilme().carregaPrep("FilmeBluRay");
                daoBR.criarFilme().inserirFilme(filmeBluRay);
                
            } catch (Exception ex) {
                Logger.getLogger(FilmesController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("listar.html?erro=Erro ao criar atividade");
                return;
            }
            response.sendRedirect("/ListarFilmes");
        } 
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
