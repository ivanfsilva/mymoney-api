package br.com.ivanfsilva.mymoneyapi.service;

import br.com.ivanfsilva.mymoneyapi.model.Pessoa;
import br.com.ivanfsilva.mymoneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);

        if (!pessoaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        // BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo");
        pessoa.setCodigo(codigo);

        return pessoaRepository.save(pessoa);
    }
}
