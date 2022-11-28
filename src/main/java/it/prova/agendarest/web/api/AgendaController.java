package it.prova.agendarest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	public AgendaDTO inserisci(@Valid @RequestBody AgendaDTO agenda) {
		if(agenda.getId() != null)
			throw new RuntimeException("id non nullo impossibile inserire un nuovo record");
		
		Agenda result = agendaService.inserisciNuovo(agenda.buildAgendaModel());
		return AgendaDTO.buildAgendaDTOfromModel(result, true);
	}
}
