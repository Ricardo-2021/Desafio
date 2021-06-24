package dbservermentoria.Teste.Service;


import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Emprestimo;
import dbservermentoria.Teste.Repository.EmprestimoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.stream.Stream;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo findByIdEmp(Integer id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoNoBancoDeDadosException(""));
    }

    public Emprestimo updateEmp(Emprestimo emprestimo, Integer id) {
        Emprestimo emprestimoBancoDeDados = findByIdEmp(id);
        BeanUtils.copyProperties(emprestimo, emprestimoBancoDeDados, "id");
        return emprestimoRepository.save(emprestimoBancoDeDados);
    }

    public Emprestimo updateByFieldEmp(Emprestimo emprestimoBody, Integer id) {
        Emprestimo emprestimoBancoDeDados = findByIdEmp(id);
        BeanUtils.copyProperties(fieldComplete(emprestimoBody, emprestimoBancoDeDados), emprestimoBancoDeDados, "id");
        return emprestimoRepository.save(emprestimoBancoDeDados);
    }

    public Emprestimo fieldComplete(Emprestimo emprestimo, Emprestimo emprestimoBancoDeDados) {
        Stream<Field> fields = Stream.of(emprestimoBancoDeDados.getClass().getDeclaredFields());
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                if (field.get(emprestimo) == null)
                    field.set(emprestimo, field.get(emprestimoBancoDeDados));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return emprestimo;
    }
}

