package agenda.eletronica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agenda.eletronica.exception.RecursoNaoEncontrado;
import agenda.eletronica.exception.ValidaDadosException;
import agenda.eletronica.model.Local;
import agenda.eletronica.repository.LocalRepository;

@Service
public class LocalService {
	
	@Autowired
	LocalRepository repo;

    public List<Local> consultarTodos(){
        return repo.findAll();
    }
	
	public Local consultarPorId(Long idLocal) {
		Optional<Local> opt = repo.findById(idLocal);
		Local ct = opt.orElseThrow(() -> new RecursoNaoEncontrado("Local não encontrado."));
		return ct;	
	}
	public Local salvar(Local local) {
		validaCampos(local);
		return repo.save(local);
	}
	public Local alterar(Long idLocal, Local local) {
		Local lc = consultarPorId(idLocal);
		validaCampos(local);
		lc = local;
		return repo.save(lc);
	}
	public void excluir(Long id) {
		Local lc = consultarPorId(id);
		repo.delete(lc);
	}
	private void validaCampos(Local local) {
		if(local.getNome().equals("")) {
			throw new ValidaDadosException("O nome deve ser informado");
		}
		if(local.getRua().equals("")) {
			throw new ValidaDadosException("A rua deve ser informado");
		}
		if(local.getBairro().equals("")) {
			throw new ValidaDadosException("O bairro deve ser informado");
		}
		if(local.getCidade().equals("")) {
			throw new ValidaDadosException("A idade deve ser informado");
		}
		if(local.getUf().equals("")) {
			throw new ValidaDadosException("O UF deve ser informado");
		}
		if(local.getCep().equals("")) {
			throw new ValidaDadosException("O Cep deve ser informado");
		}
	}
}
