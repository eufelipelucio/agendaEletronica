package agenda.eletronica.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agenda.eletronica.exception.RecursoNaoEncontrado;
import agenda.eletronica.exception.ValidaDadosException;
import agenda.eletronica.model.Compromisso;
import agenda.eletronica.model.Contato;
import agenda.eletronica.repository.CompromissosRepository;

@Service
public class CompromissoService {
	@Autowired
	CompromissosRepository repo;

    public List<Compromisso> consultarTodos(){
        return repo.findAll();
    }
    public List<Compromisso> consultarPorData(Date data){
        return repo.findAllByData(data);
    }
	public List<Compromisso> consultarEntreDatas(Date dataInicio, Date dataFim){
        return repo.findByEntreDatas(dataInicio, dataFim);
    }
	public Compromisso consultarPorId(Long idcompromissos) {
		Optional<Compromisso> opt = repo.findById(idcompromissos);
		Compromisso comp = opt.get();
		if(comp == null){
            throw new RecursoNaoEncontrado("Compromisso não encontrado");
        }
        return comp;
	}
	public List<Compromisso> consultarPorContato(Contato contato) {
		List<Compromisso> listCompromissos = repo.findAllByContato(contato);
		if(listCompromissos.isEmpty()){
            throw new RecursoNaoEncontrado("Compromissos não encontrado");
        }
		return listCompromissos;
	}
	public Compromisso salvar(Compromisso compromisso) {
		validaCampos(compromisso);
		return repo.save(compromisso);
	}
	public Compromisso alterar(Long idCompromisso, Compromisso compromisso) {
		Compromisso comp = consultarPorId(idCompromisso);
		validaCampos(compromisso);
		comp = compromisso;
		return repo.save(comp);
	}
	public void excluir(Long id) {
		Compromisso comp = consultarPorId(id);
		repo.delete(comp);
	}
	private void validaCampos(Compromisso compromisso) {
		if(compromisso.getDescricao().equals("")) {
			throw new ValidaDadosException("A descrição deve ser informado");
		}
		if(compromisso.getData().equals("")) {
			throw new ValidaDadosException("A data deve ser informado");
		}
		
		if(compromisso.getHora() == null ) {
			throw new ValidaDadosException("A Hora deve ser informado");
		}
		else {
			if(compromisso.getHora().equals("")) {
				throw new ValidaDadosException("A Hora deve ser informado");
			}
		}
		if(compromisso.getContato().equals("")) {
			throw new ValidaDadosException("O id contato deve ser informado");
		}
		if(compromisso.getLocal().equals("") ) {
			throw new ValidaDadosException("O id local deve ser informado");
		}
	}
}
