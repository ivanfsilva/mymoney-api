package br.com.ivanfsilva.mymoneyapi.resource;

import br.com.ivanfsilva.mymoneyapi.event.RecursoCriadoEvent;
import br.com.ivanfsilva.mymoneyapi.model.Pessoa;
import br.com.ivanfsilva.mymoneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
        Optional pessoa = this.pessoaRepository.findById(codigo);
        return pessoa.isPresent() ?
                ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }
}
