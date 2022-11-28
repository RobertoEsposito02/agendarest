package it.prova.agendarest.service.agenda;

import java.util.List;

import it.prova.agendarest.model.Agenda;

public interface AgendaService {
	public List<Agenda> listAll();

	public Agenda caricaSingoloElemento(Long id);
	
	public Agenda caricaSingoloElementoConUtente(Long id);

	public void aggiorna(Agenda agendaInstance);

	public void inserisciNuovo(Agenda agendaInstance);

	public void rimuovi(Long idToRemove);

}
