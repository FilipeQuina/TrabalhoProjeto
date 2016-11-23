/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import locadora.model.FilmeBluRay;
import locadora.model.VO.Filme;

/**
 *
 * @author filipe
 */
public class FilmeBluRayPrep implements Filme {
  private PreparedStatement operacaoListarTodos;
    private PreparedStatement operacaoCriar;
    private PreparedStatement operacaoExcluir;
    @Override
    public void carregaPrep() throws Exception {
        try{
           operacaoListarTodos = ConexaoJDBC.getInstance().prepareStatement("SELECT * FROM atividade");
           operacaoCriar = ConexaoJDBC.getInstance().prepareStatement("INSERT INTO (funcionario, descricao, tipo, horas) VALUES(?,?,?,?)", new String[]{"id"});
           //operacaoExcluir=ConexaoJDBC.getInstance().prepareStatement("DELETE from  where usuario='abc'");
        }catch (SQLException ex){
            Logger.getLogger(FilmeBluRayPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);   
        }
    }

    @Override
    public void inserirFilme(FilmeBluRay filme) {
        try {
                   
            operacaoCriar.setString(1, filme.getFuncionario());
            operacaoCriar.setString(2, filme.getDescricao());
            operacaoCriar.setString(3, filme.getTipo());
            operacaoCriar.setInt(4, filme.getHoras());
            operacaoCriar.executeUpdate();
            ResultSet keys = operacaoCriar.getGeneratedKeys();
            if(keys.next()){
                filme.setId(keys.getLong(1));
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(FilmeBluRayPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    @Override
    public void listarFilme() {
    }


}
