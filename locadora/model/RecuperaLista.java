/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.model;


import java.util.ArrayList;


public class RecuperaLista {
    
    protected ArrayList<RecuperaMemento> estados;
    
    public RecuperaLista() {
        estados = new ArrayList<>();
    }

    
    public void somarLista(RecuperaMemento memento ){
        estados.add(memento);
    }
    
    

    public RecuperaMemento getUltimoEstadoSalvo() {
        if (estados.size() <= 0) {
            return new RecuperaMemento();
        }
        RecuperaMemento estadoSalvo = estados.get(estados.size() - 1);
        estados.remove(estados.size() - 1);
        return estadoSalvo;
    }
}
