package agenda.eletronica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agenda.eletronica.exception.RecursoJaExistente;
import agenda.eletronica.exception.RecursoNaoEncontrado;
import agenda.eletronica.exception.ValidaDadosException;
import agenda.eletronica.model.Contato;
import agenda.eletronica.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	ContatoRepository repo;

    public List<Contato> consultarTodos(){
        return repo.findAll();
    }
	
	public Contato consultarPorId(Long idcontato) {
		Optional<Contato> opt = repo.findById(idcontato);
		Contato ct = opt.orElseThrow(() -> new RecursoNaoEncontrado("Contato não encontrado."));
		return ct;						
	}
	
	public void excluir(Long id) {
		Contato ct = consultarPorId(id);
		repo.delete(ct);
	}
	
	public Contato salvar(Contato contato) {
		validaCampos(contato);
		
		if (repo.findByEmail(contato.getEmail()) != null) {
			throw new RecursoJaExistente("Contato já cadastrado para esse email");
		}		
		return repo.save(contato);
	}
	
	public Contato alterar(Long idContato, Contato contato) {
		Contato ct = consultarPorId(idContato);
		validaCampos(contato);
		Contato fbE = repo.findByEmail(contato.getEmail());
		if (fbE != null || fbE.getEmail() != ct.getEmail()) {
			throw new RecursoJaExistente("Contato já cadastrado para esse email");
		}	
		
		return repo.save(ct);
	}
	
	private void validaCampos(Contato contato) {
		if(contato.getNome().equals("")) {
			throw new ValidaDadosException("O nome deve ser informado");
		}
		if(contato.getEmail().equals("")) {
			throw new ValidaDadosException("O email deve ser informado");
		}
		
		if(contato.getFone() == null ) {
			throw new ValidaDadosException("O fone deve ser informado");
		}
		else {
			if(contato.getFone().equals("")) {
				throw new ValidaDadosException("O fone deve ser informado");
			}
		}
	}
	
	
}
