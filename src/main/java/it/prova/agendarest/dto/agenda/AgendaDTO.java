package it.prova.agendarest.dto.agenda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.agendarest.dto.utente.UtenteDTO;
import it.prova.agendarest.model.Agenda;

public class AgendaDTO {
	private Long id;
	@NotBlank(message = "{agenda.descrizione.notBlank}")
	private String descrizione;
	@NotNull(message = "{agenda.dataOraInizio.notNull}")
	private LocalDateTime dataOraInizio;
	@NotNull(message = "{agenda.dataOraFine.notNull}")
	private LocalDateTime dataOraFine;

	@JsonIgnoreProperties(value = "agende")
	private UtenteDTO utente;

	public AgendaDTO() {
		// TODO Auto-generated constructor stub
	}

	public AgendaDTO(@NotBlank(message = "{agenda.descrizione.notBlank}") String descrizione,
			@NotNull(message = "{agenda.dataOraInizio.notNull}") LocalDateTime dataOraInizio,
			@NotNull(message = "{agenda.dataOraFine.notNull}") LocalDateTime dataOraFine, UtenteDTO utente) {
		super();
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utente = utente;
	}
	
	public AgendaDTO(Long id,@NotBlank(message = "{agenda.descrizione.notBlank}") String descrizione,
			@NotNull(message = "{agenda.dataOraInizio.notNull}") LocalDateTime dataOraInizio,
			@NotNull(message = "{agenda.dataOraFine.notNull}") LocalDateTime dataOraFine) {
		super();
		this.id =id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
	}

	public AgendaDTO(Long id, @NotBlank(message = "{agenda.descrizione.notBlank}") String descrizione,
			@NotNull(message = "{agenda.dataOraInizio.notNull}") LocalDateTime dataOraInizio,
			@NotNull(message = "{agenda.dataOraFine.notNull}") LocalDateTime dataOraFine, UtenteDTO utente) {
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

	public LocalDateTime getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(LocalDateTime dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public LocalDateTime getDataOraFine() {
		return dataOraFine;
	}

	public void setDataOraFine(LocalDateTime dataOraFine) {
		this.dataOraFine = dataOraFine;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public Agenda buildAgendaModel() {
		Agenda result = new Agenda(this.id, this.descrizione, this.dataOraInizio, this.dataOraFine);
		if (this.utente != null)
			result.setUtente(utente.buildUtenteModel(false));
		return result;
	}

	public static AgendaDTO buildAgendaDTOfromModel(Agenda agenda, boolean includeUtenti) {
		AgendaDTO result = new AgendaDTO(agenda.getId(), agenda.getDescrizione(), agenda.getDataOraInizio(),
				agenda.getDataOraFine());
		
		if(includeUtenti)
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(agenda.getUtente(), false));
		
		return result;
	}
	
	public static List<AgendaDTO> createListAgendaDTOFromModel(List<Agenda> agendaList, boolean includeUtenti){
		return agendaList.stream().map(agenda -> {
			return AgendaDTO.buildAgendaDTOfromModel(agenda,includeUtenti);
		}).collect(Collectors.toList());
	}
	
}

