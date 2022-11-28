package it.prova.agendarest.repository.agenda;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.agendarest.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long>{
	
	@Query("from Agenda a left join fetch a.utente u where a.id = :id")
	Optional<Agenda> findByIdConUtente(Long id);
	
	@Query("select distinct a from Agenda a left join fetch a.utente u")
	List<Agenda> listAllEager();
	
	@Query("from Agenda a left join fetch a.utente u where u.id = :id")
	List<Agenda> listAllEagerTuoi(Long id);
}
