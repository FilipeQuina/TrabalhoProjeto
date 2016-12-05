/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import locadora.model.FilmeBluRay;
import locadora.model.VO.FilmePrep;

/**
 *
 * @author filipe
 */
public class FilmeBluRayPrep implements FilmePrep {
  private PreparedStatement operacaoListarTodos;
    private PreparedStatement operacaoCriar;

    @Override
    public void carregaPrep(String tipoFilme) throws Exception {
        try{
           operacaoListarTodos = ConexaoJDBC.getInstance().prepareStatement("SELECT * FROM "+tipoFilme);
           operacaoCriar = ConexaoJDBC.getInstance().prepareStatement("INSERT INTO "+tipoFilme+" (nome, autor, valor) VALUES(?,?,?)", new String[]{"id"});
           //operacaoExcluir=ConexaoJDBC.getInstance().prepareStatement("DELETE from  where usuario='abc'");
        }catch (SQLException ex){
            Logger.getLogger(FilmeBluRayPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);   
        }
    }

    @Override
    public void inserirFilme(FilmeBluRay filme) throws Exception{
        try {
                   
            operacaoCriar.setString(1, filme.getNome());
            operacaoCriar.setString(2, filme.getAutor());
            operacaoCriar.setFloat(3,filme.getValor());
            operacaoCriar.executeUpdate();
            ResultSet keys = operacaoCriar.getGeneratedKeys();
            if(keys.next()){
                filme.setId(keys.getLong(1));
     
            }
            
        }catch (SQLException ex){
            Logger.getLogger(FilmeBluRayPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);   
        }
      
    }

    @Override
        public List<FilmeBluRay> listarFilmes() throws Exception {
          
        List<FilmeBluRay> todos = new ArrayList<>();
        
        try {
            ResultSet resultado = operacaoListarTodos.executeQuery();
            while (resultado.next()) {
                FilmeBluRay filmeBluRay = new FilmeBluRay();
                filmeBluRay.setId(resultado.getLong("id"));
                filmeBluRay.setNome(resultado.getString("nome"));
                filmeBluRay.setAutor(resultado.getString("autor"));
                filmeBluRay.setValor(resultado.getFloat("valor"));
                todos.add(filmeBluRay);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmeBluRayPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

        return todos;
    }
    }
