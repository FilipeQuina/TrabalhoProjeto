/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aluno
 */
@Entity
public class Compra implements Serializable {
    //public static final int BOLETO = 1;
    //public static final int CARTAO = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float valor;
    private String nome;
    private Cliente cliente;
    private FilmeBluRay filmesBD;
    private FilmeDVD filmesDVD;
    protected Pagamento processar;
    //protected int escolha;
    
    public Compra(float valor, Cliente cliente, FilmeBluRay filmesBD, FilmeDVD filmesDVD, String nome/*, int escolha*/) {
		this.filmesBD = filmesBD;
                this.filmesDVD = filmesDVD;
                this.valor = valor+filmesBD.getValor()+filmesDVD.getValor();
                this.nome = nome;
                this.cliente = cliente;
		/*switch (escolha) {
		case BOLETO:
			processar = new PagamentoBoleto();
			escolha = BOLETO;
			break;
		case CARTAO:
			processar = new PagamentoCartaoCredito();
			escolha = CARTAO;
			break;
		default:
			throw new RuntimeException("Erro!");
		}*/
	}
    
    
    	//public float calcularValorComImposto() {
	//	return processar.processarCompra(this);
	//}

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FilmeBluRay getFilmesBD() {
        return filmesBD;
    }

    public void setFilmesBD(FilmeBluRay filmesBD) {
        this.filmesBD = filmesBD;
    }

    public FilmeDVD getFilmesDVD() {
        return filmesDVD;
    }

    public void setFilmesDVD(FilmeDVD filmesDVD) {
        this.filmesDVD = filmesDVD;
    }

    public Pagamento getProcessar() {
        return processar;
    }

    public void setProcessar(Pagamento processar) {
        this.processar = processar;
    }

    /*public int getEscolha() {
        return escolha;
    }

    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }*/
    
    
  
}
