package it.prova.agendarest.dto.agenda;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.model.Utente;

public class AgendaDTO {
	private Long id;
	@NotBlank(message = "{agenda.descrizione.notBlank}")
	private String descrizione;
	@NotNull(message = "{agenda.dataOraInizio.notNull}")
	private LocalDate dataOraInizio;
	@NotNull(message = "{agenda.dataOraFine.notNull}")
	private LocalDate dataOraFine;

	@JsonIgnoreProperties(value = "{agende}")
	private Utente utente;

	public AgendaDTO() {
		// TODO Auto-generated constructor stub
	}

	public AgendaDTO(@NotBlank(message = "{agenda.descrizione.notBlank}") String descrizione,
			@NotNull(message = "{agenda.dataOraInizio.notNull}") LocalDate dataOraInizio,
			@NotNull(message = "{agenda.dataOraFine.notNull}") LocalDate dataOraFine, Utente utente) {
		super();
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utente = utente;
	}

	public AgendaDTO(Long id, @NotBlank(message = "{agenda.descrizione.notBlank}") String descrizione,
			@NotNull(message = "{agenda.dataOraInizio.notNull}") LocalDate dataOraInizio,
			@NotNull(message = "{agenda.dataOraFine.notNull}") LocalDate dataOraFine, Utente utente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(LocalDate dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public LocalDate getDataOraFine() {
		return dataOraFine;
	}

	public void setDataOraFine(LocalDate dataOraFine) {
		this.dataOraFine = dataOraFine;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Agenda buildAgendaModel() {
		Agenda result = new Agenda(this.id, this.descrizione, this.dataOraInizio, this.dataOraFine);
		if (this.utente != null)
			result.setUtente(utente);
		return result;
	}

	public static AgendaDTO buildAgendaDTOfromModel(Agenda agenda) {
		AgendaDTO result = new AgendaDTO(agenda.getId(), agenda.getDescrizione(), agenda.getDataOraInizio(),
				agenda.getDataOraFine(), agenda.getUtente());
		return result;
	}
	
	public static List<AgendaDTO> createListAgendaDTOFromModel(List<Agenda> agendaList){
		return agendaList.stream().map(agenda -> {
			return AgendaDTO.buildAgendaDTOfromModel(agenda);
		}).collect(Collectors.toList());
	}
	
}

