package it.prova.agendarest.service.agenda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.repository.agenda.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	private AgendaRepository repository;
	
	@Override
	public List<Agenda> listAll() {
		return (List<Agenda>) repository.findAll();
	}

	@Override
	public Agenda caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Agenda caricaSingoloElementoConUtente(Long id) {
		return repository.findByIdConUtente(id).orElse(null);
	}

	@Override
	public Agenda aggiorna(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	public Agenda inserisciNuovo(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

	@Override
	public List<Agenda> listAllEager() {
		return repository.listAllEager();
	}

}
