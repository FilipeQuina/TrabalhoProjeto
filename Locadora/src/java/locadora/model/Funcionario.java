/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Filipe
 */
@Entity
public class Funcionario implements Serializable {
    public static final int PADRAO = 1;
    public static final int GERENTE = 2;
    public static final int SECRETARIO = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String senha;
    protected double salarioBase;
    protected int cargo;
    protected CalculaImposto estrategiaDeCalculo;

    
    	public Funcionario(int cargo, double salarioBase, Long id, String nome, String senha) {
		this.salarioBase = salarioBase;
                this.nome = nome;
                this.id = id;
                this.senha = senha;
		switch (cargo) {
		case PADRAO:
			estrategiaDeCalculo = new CalculoImpostoQuinzeOuDez();
			cargo = PADRAO;
			break;
		case SECRETARIO:
			estrategiaDeCalculo = new CalculoImpostoQuinzeOuDez();
			cargo = SECRETARIO;
			break;
		case GERENTE:
			estrategiaDeCalculo = new CalculoImpostoVinteOuQuinze();
			cargo = GERENTE;
			break;
		default:
			throw new RuntimeException("Cargo nao encontrado!");
		}
	}

	public double calcularSalarioComImposto() {
		return estrategiaDeCalculo.calculaSalarioComImposto(this);
	}

	public double getSalarioBase() {
		return salarioBase;
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
