package dbservermentoria.Teste.Repository;

import dbservermentoria.Teste.Model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

}
