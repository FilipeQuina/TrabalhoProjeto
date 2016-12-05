/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model;

/**
 *
 * @author aluno
 */
public class PagamentoBoleto implements Pagamento {
    
    /**
     *
     * @param compra
     */
    @Override
    public float processarCompra(Compra compra){
        float valor = compra.getValor()+4;
        //System.out.println("Forma de pagamento escolhida: Boleto Bancário (O valor da compra é somado com 4)\nValor total da compra:"+compra.getValor()); 
        return valor;
    }
}
