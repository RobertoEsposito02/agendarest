package it.prova.agendarest.repository.agenda;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.agendarest.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long>{
	
	@Query("from Agenda a left join fetch a.utente where a.id = :id")
	Optional<Agenda> findByIdConUtente(Long id);
}
