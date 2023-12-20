package agenda.eletronica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agenda.eletronica.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	Contato findByEmail(String email);
	
	
}