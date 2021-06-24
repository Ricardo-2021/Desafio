package dbservermentoria.Teste.Controller;

import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Emprestimo;
import dbservermentoria.Teste.Repository.EmprestimoRepository;
import dbservermentoria.Teste.Service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/emprestimo")
public class EmprestimoController {

    private EmprestimoRepository emprestimoRepository;
    private ArrayList<Emprestimo> emprestimoArraylist = new ArrayList<>();
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoRepository emprestimorepository, EmprestimoService emprestimoService) {
        this.emprestimoRepository = emprestimorepository;
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> emprestimo(@RequestBody @Valid Emprestimo emprestimo){
        emprestimoRepository.save(emprestimo);
        return new ResponseEntity<>(emprestimo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllEmp() {
        List<Emprestimo> livrosEmprestados = emprestimoRepository.findAll();
        if(livrosEmprestados.isEmpty()){
            return new ResponseEntity<>(livrosEmprestados, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Lista Vazia", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdEmp(@PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        Optional<Emprestimo> buscaEmprestimoId = emprestimoRepository.findById(id);
        if(buscaEmprestimoId.isPresent()){
            return new ResponseEntity<>(buscaEmprestimoId, HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @DeleteMapping("/devolucao/{id}")
    public ResponseEntity<?> devolucaoDoLivro (@PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        Optional<Emprestimo> livrodevolvido = emprestimoRepository.findById(id);
        if(livrodevolvido.isPresent()){
            emprestimoRepository.deleteById(id);
            return new ResponseEntity<>("Livro Devolvido", HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmp (@RequestBody @Valid Emprestimo emprestimo, @PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        return new ResponseEntity<>(emprestimoService.updateEmp(emprestimo, id), HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateByFieldEmp (@RequestBody Emprestimo emprestimoBody, @PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException{
        return new ResponseEntity<>(emprestimoService.updateByFieldEmp(emprestimoBody, id), HttpStatus.FOUND);
    }
}
