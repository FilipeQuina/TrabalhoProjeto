package locadora.model;

public class FilmeDVD {

    private Long Id;
    private String nome;
    private String autor;
    private boolean ativo;
    private boolean estadoativo;

    public boolean isEstadoativo() {
        return estadoativo;
    }

    public void setEstadoativo(boolean estadoativo) {
        this.estadoativo = estadoativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
