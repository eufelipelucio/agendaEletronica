package agenda.eletronica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agenda.eletronica.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long>{
	
}
