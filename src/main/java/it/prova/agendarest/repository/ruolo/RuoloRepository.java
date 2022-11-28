package it.prova.agendarest.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.agendarest.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
