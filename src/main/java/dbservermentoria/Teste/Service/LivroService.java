package dbservermentoria.Teste.Service;

import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Livro;
import dbservermentoria.Teste.Repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.stream.Stream;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public Livro findByIdBook (Integer id) {
        return livroRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoNoBancoDeDadosException(""));
    }

    public Livro updateBook (Livro livro, Integer id){
        Livro livroBancoDeDados = findByIdBook(id);
        BeanUtils.copyProperties(livro, livroBancoDeDados, "id");
        return livroRepository.save(livroBancoDeDados);
    }

    public Livro updateByFieldBook(Livro livroBody, Integer id) {
        Livro livroBancoDeDados = findByIdBook(id);
        BeanUtils.copyProperties(fieldComplete(livroBody, livroBancoDeDados), livroBancoDeDados, "id");
        return livroRepository.save(livroBancoDeDados);
    }

    public Livro fieldComplete(Livro livro, Livro livroBancoDeDados) {
        Stream<Field> fields = Stream.of(livroBancoDeDados.getClass().getDeclaredFields());
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                if (field.get(livro) == null)
                    field.set(livro, field.get(livroBancoDeDados));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return livro;
    }
}