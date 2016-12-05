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
public class PagamentoCartaoCredito implements Pagamento{
     /**
     *
     * @param compra
     */
    @Override
    public float processarCompra(Compra compra){
        float valor = compra.getValor()+2;
        //compra.setValor(compra.getValor()+2);
        //System.out.println("Forma de pagamento escolhida: Cartão de Crédito (O valor da compra é somado com 2)\nValor total da compra:"+compra.getValor());        
        return valor;
    }
}
