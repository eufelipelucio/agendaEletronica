package agenda.eletronica.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import agenda.eletronica.model.Compromisso;

import agenda.eletronica.model.Contato;

@Repository
public interface CompromissosRepository extends JpaRepository<Compromisso, Long> {
	List<Compromisso> findAllByContato(Contato contato);

	List<Compromisso> findAllByData(Date data);
	
	//@Query("SELECT c FROM Compromisso c WHERE c.data between : dataInicio AND :dataFim")
	@Query(value = "select * from tb_compromissos where data between :dataInicio AND :dataFim", nativeQuery = true)
	List<Compromisso> findByEntreDatas(Date dataInicio, Date dataFim);
}
