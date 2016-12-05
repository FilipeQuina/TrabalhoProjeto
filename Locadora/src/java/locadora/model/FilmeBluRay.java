package locadora.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class FilmeBluRay implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String autor;
    private float valor;
    private boolean estadoativo;
    
    RecuperaLista Listamemento ;
    public FilmeBluRay() {
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the estadoativo
     */
    public boolean isEstadoativo() {
        return estadoativo;
    }

    /**
     * @param estadoativo the estadoativo to set
     */
    public void setEstadoativo(boolean estadoativo) {
        this.estadoativo = estadoativo;
    }
    


   

    

}
