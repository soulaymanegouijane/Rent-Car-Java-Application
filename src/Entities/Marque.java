package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marque")
public class Marque implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMarque", nullable=false)
	private long idMarque;
	@Column(name="libelle",nullable=false)
	private String libelle;
	@Column(name="description",nullable=false)
	private String description;
	

	

	/**
	 * @param idMarque
	 * @param libelle
	 * @param description
	 */
	public Marque(long idMarque, String libelle, String description) {
		super();
		this.idMarque = idMarque;
		this.libelle = libelle;
		this.description = description;
	}

	/**
	 * @param ContructeurSansParam
	 */
	public Marque() {
		super();
	}

	/**
	 * @return the idMarque
	 */
	public long getIdMarque() {
		return idMarque;
	}

	/**
	 * @param idMarque the idMarque to set
	 */
	public void setIdMarque(long idMarque) {
		this.idMarque = idMarque;
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
	
	
	
	
}
