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
public class Gerente {

    public static Gerente gerente;
    private String login = "master";
    private String senha = "master";

    protected Gerente() {
    }

    public static Gerente getInstancia() {
        if (gerente == null) {
            gerente = new Gerente(); 
        }
        return gerente;
    }

    public void CadastrarFuncionarios() {

    }

    public void EditarFuncionarios() {

    }

    public void ExcluirFuncionarios() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
