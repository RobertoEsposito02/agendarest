package it.prova.agendarest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.agendarest.dto.agenda.AgendaDTO;
import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.service.agenda.AgendaService;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping
	public List<AgendaDTO> listAll(){
		return AgendaDTO.createListAgendaDTOFromModel(agendaService.listAllEager(),true);
	} 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AgendaDTO inserisci(@Valid @RequestBody AgendaDTO agenda) {
		if(agenda.getId() != null)
			throw new RuntimeException("id non nullo impossibile inserire un nuovo record");
		
		Agenda result = agendaService.inserisciNuovo(agenda.buildAgendaModel());
		return AgendaDTO.buildAgendaDTOfromModel(result, true);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public AgendaDTO aggiorn(@Valid @RequestBody AgendaDTO agenda) {
		if(agenda.getId() == null)
			throw new RuntimeException("bisogna specificare l'id per modificare un elemento");
		
		Agenda agendaDaAggiornare = agendaService.caricaSingoloElementoConUtente(agenda.getId());
		if(agendaDaAggiornare == null)
			throw new RuntimeException("elemento non trovato");
		
		Agenda result = agendaService.aggiorna(agenda.buildAgendaModel());
		return AgendaDTO.buildAgendaDTOfromModel(result, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void elimina(@PathVariable(name = "id") Long id) {
		agendaService.rimuovi(id);
	}
}
