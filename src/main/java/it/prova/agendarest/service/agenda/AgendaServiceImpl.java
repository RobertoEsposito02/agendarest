package it.prova.agendarest.service.agenda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.model.Ruolo;
import it.prova.agendarest.model.Utente;
import it.prova.agendarest.repository.agenda.AgendaRepository;
import it.prova.agendarest.repository.utente.UtenteRepository;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository repository;

	@Autowired
	private UtenteRepository utenteRepository;

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
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		Agenda agendaInstance = repository.findByIdConUtente(id).orElse(null);

		if (utenteInSessione.getId() != agendaInstance.getUtente().getId() && !utenteInSessione.getRuoli().stream()
				.map(r -> r.getCodice().equals("ROLE_ADMIN")).findAny().orElse(null))
			throw new RuntimeException("impossibile cercare un campo non proprio");

		return agendaInstance;
	}

	@Override
	public Agenda aggiorna(Agenda agendaInstance) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		Agenda agendaDaModificare = repository.findByIdConUtente(agendaInstance.getId()).orElse(null);
		
		if (utenteInSessione.getId() != agendaDaModificare.getUtente().getId() && !utenteInSessione.getRuoli().stream()
				.map(r -> r.getCodice().equals("ROLE_ADMIN")).findAny().orElse(null))
			throw new RuntimeException("impossibile modificare un campo non proprio");

		return repository.save(agendaInstance);
	}

	@Override
	public Agenda inserisciNuovo(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		Agenda agendaInstance = repository.findByIdConUtente(idToRemove).orElse(null);

		if (utenteInSessione.getId() != agendaInstance.getUtente().getId() && !utenteInSessione.getRuoli().stream()
				.map(r -> r.getCodice().equals(Ruolo.ROLE_ADMIN)).findAny().orElse(null))
			throw new RuntimeException("impossibile modificare un campo non proprio");

		repository.deleteById(idToRemove);
	}

	@Override
	public List<Agenda> listAllEager() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);

		if (utenteInSessione.getRuoli().stream().map(r -> r.getCodice().equals(Ruolo.ROLE_ADMIN)).findAny()
				.orElse(null))
			return repository.listAllEager();
		
		return listAllEagerTuoi();
	}
	
	@Override
	public List<Agenda> listAllEagerTuoi(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		return repository.listAllEagerTuoi(utenteInSessione.getId());
	}

}
