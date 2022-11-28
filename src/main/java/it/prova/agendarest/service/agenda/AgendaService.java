package it.prova.agendarest.service.agenda;

import java.util.List;

import it.prova.agendarest.model.Agenda;

public interface AgendaService {
	public List<Agenda> listAll();
	
	public List<Agenda> listAllEager();

	public Agenda caricaSingoloElemento(Long id);
	
	public Agenda caricaSingoloElementoConUtente(Long id);

	public Agenda aggiorna(Agenda agendaInstance);

	public Agenda inserisciNuovo(Agenda agendaInstance);

	public void rimuovi(Long idToRemove);

}
