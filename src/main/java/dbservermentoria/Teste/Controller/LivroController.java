package dbservermentoria.Teste.Controller;



import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Livro;
import dbservermentoria.Teste.Repository.LivroRepository;
import dbservermentoria.Teste.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/livro")
public class LivroController {

    private LivroRepository livroRepository;
    private ArrayList<Livro> livroArraylist = new ArrayList<>();
    private LivroService livroService;

    public LivroController(LivroRepository livrorepository, LivroService livroService) {
        this.livroRepository = livrorepository;
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> saveBook(@RequestBody @Valid Livro livro){
        livroRepository.save(livro);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllBook() {
        List<Livro> listaDeLivros = livroRepository.findAll();
        if(listaDeLivros.isEmpty()){
            return new ResponseEntity<>(listaDeLivros, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Lista Vazia", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdBook(@PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException {
        Optional<Livro> buscaLivroPorId = livroRepository.findById(id);
        if(buscaLivroPorId.isPresent()){
            return new ResponseEntity<>(buscaLivroPorId, HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook (@PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        Optional<Livro> livrodeletado = livroRepository.findById(id);
        if(livrodeletado.isPresent()){
            livroRepository.deleteById(id);
            return new ResponseEntity<>("Livro Excluido com Sucesso", HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook (@RequestBody @Valid Livro livro, @PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        return new ResponseEntity<>(livroService.updateBook(livro, id), HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateByFieldBook (@RequestBody Livro livroBody, @PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        return new ResponseEntity<>(livroService.updateByFieldBook(livroBody, id), HttpStatus.FOUND);
    }
}
