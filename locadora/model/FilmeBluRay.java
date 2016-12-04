package locadora.model;

public class FilmeBluRay {

    private Long Id;
    private String nome;
    private String autor;
   
    private boolean estadoativo;

  
    RecuperaLista Listamemento ;


    public void alteraEstado(String novoestado) {
        Listamemento.somarLista(new RecuperaMemento(novoestado));
        estadoativo += novoestado;
    }

    public void recuperarEstado() {
        estadoativo = Listamemento.getUltimoEstadoSalvo().getEstadoativo();
    }

    public void exibeEstadoativo() {
        System.out.println(estadoativo);
    }

    public boolean isEstadoativo() {
        return estadoativo;
    }

    public void setEstadoativo(boolean estadoativo) {
        this.estadoativo = estadoativo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
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

}
