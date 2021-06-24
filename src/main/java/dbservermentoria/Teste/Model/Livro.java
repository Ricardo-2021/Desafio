package dbservermentoria.Teste.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "book")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;
    @Column(name="title")
    @NotNull
    private String titulo;
    @Column(name="author")
    @NotNull
    private String autor;
    @Column(name="gender")
    @NotNull
    private String genero;

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
