package it.prova.agendarest.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrzione")
	private String descrizione;
	@Column(name = "dataorainizio")
	private LocalDate dataOraInizio;
	@Column(name = "dataorafine")
	private LocalDate dataOraFine;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;
	
	public Agenda() {
	}
	
	public Agenda(Long id,String descrizione, LocalDate dataOraInizio, LocalDate dataOraFine) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
	}

	public Agenda(String descrizione, LocalDate dataOraInizio, LocalDate dataOraFine, Utente utente) {
		super();
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utente = utente;
	}

	public Agenda(Long id, String descrizione, LocalDate dataOraInizio, LocalDate dataOraFine, Utente utente) {
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
	
	
}