package Entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="carburant")
public class Carburant implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCarburant", nullable=false)
	private long idCarburant;
	@Column(name="libelle",nullable=false)
	private String libelle;
	@Column(name="description", nullable=false)
	private String description;
	
	
	public long getIdCarburant() {
		return idCarburant;
	}
	public void setIdCarburant(long idCarburant) {
		this.idCarburant = idCarburant;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
		

}
