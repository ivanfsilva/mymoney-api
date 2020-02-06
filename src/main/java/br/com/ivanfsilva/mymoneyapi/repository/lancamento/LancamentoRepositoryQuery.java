package br.com.ivanfsilva.mymoneyapi.repository.lancamento;

import br.com.ivanfsilva.mymoneyapi.model.Lancamento;
import br.com.ivanfsilva.mymoneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
