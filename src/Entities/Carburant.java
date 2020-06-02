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
	
	/**
	 * @param idCarburant
	 * @param libelle
	 * @param description
	 */
	public Carburant(long idCarburant, String libelle, String description) {
		super();
		this.idCarburant = idCarburant;
		this.libelle = libelle;
		this.description = description;
	}

	/**
	 *  Constructeur Sans @param
	 */
	public Carburant() {
		super();
	}

	/**
	 * @return the idCarburant
	 */
	public long getIdCarburant() {
		return idCarburant;
	}

	/**
	 * @param idCarburant the idCarburant to set
	 */
	public void setIdCarburant(long idCarburant) {
		this.idCarburant = idCarburant;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Carburant [idCarburant=" + idCarburant + ", libelle=" + libelle + ", description=" + description + "]";
	}
	
	
	
	
	
	
	

}
