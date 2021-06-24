package dbservermentoria.Teste.Model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    @JoinColumn(name = "user_id")
    @NotNull
    private Usuario idUsuario;
    @Column(name = "available")
    @NotNull
    private boolean disponivel;
    @Column(name = "dateofloan")
    @NotNull
    private LocalDate dataDeEmprestimo;
    @Column(name = "dateofreturn")
    @NotNull
    private LocalDate dateDevolucao;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotNull
    private Livro idLivro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
   }

    public void setusuarioId(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public LocalDate getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(LocalDate dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
    }

    public LocalDate getDateDevolucao() {
        return dateDevolucao;
    }

    public void setDateDevolucao(LocalDate dateDevolucao) {
        this.dateDevolucao = dateDevolucao;
    }

    public Livro getidLivro() {
        return idLivro;
    }

    public void setidLivro(Livro idLivro) { this.idLivro = idLivro;
    }
}
