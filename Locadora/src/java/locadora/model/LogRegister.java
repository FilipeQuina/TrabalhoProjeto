/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model;

/**
 *
 * @author Filipe
 */
public class LogRegister {

    public static LogRegister log;

    protected LogRegister() {
    }

    public static LogRegister getInstancia() {
        if (log == null) {
            log = new LogRegister(); 
        }
        return log;
    }
    
    public static void InsertLog(String user, String dateLogRegister, String typeTransaction, String screenLog){
                
        /*typeTransaction: 
            -> (I) Inclusao do registro
            -> (C) Consulta ao abrir a tela, 
            -> (E) Exclui registro
            -> (A) Altera registro
            */
                
        /*
        
        string de conexão. conectar com o usuário específico para inserir o log
        
        abrir transação
        
        inserir registro
        executar commit
        
        fechar transação
        fechar conexao
        
        */
        
    }

}
