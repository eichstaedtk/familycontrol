package de.eichstaedt.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "ausgaben")
public class Ausgabe {
	
	@GeneratedValue
	@Id
	private int id;
	
	private LocalDateTime date;
	
	private Double amount;
	
	private String description;
	
	@OneToOne
	@JoinColumn(name = "AUSG_BENUTZER")
	private Benutzer benutzer;
	
	@OneToOne
	@JoinColumn(name = "AUSG_UNTERNEHMEN")
	private Unternehmen haendler;
	
	protected Ausgabe() {}
	
	public Ausgabe(LocalDateTime date,Double amount, String description, Unternehmen haendler, Benutzer benutzer)
	{
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.haendler = haendler;
		this.benutzer = benutzer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Unternehmen getHaendler() {
		return haendler;
	}

	public void setHaendler(Unternehmen haendler) {
		this.haendler = haendler;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	@Override
	public int hashCode() {
		return new Integer(date.hashCode() + amount.hashCode() + description.hashCode() + benutzer.hashCode());
	}
}