package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Type")
@Table(name="type")
public class Type implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idType", nullable=false)
	private long idType;
	@Column(name="libelle",nullable=false)
	private String libelle;
	@Column(name="description",nullable=false)
	private String description;
	@Column(name="idMarque",nullable=false)
	private long idMarque;
	
	@ManyToOne
	@JoinColumn(name="idMarque",referencedColumnName = "idMarque",insertable = false,updatable = false)
	private Marque marque;
	/**
	 * @param idType
	 * @param libelle
	 * @param description
	 * @param marque
	 */
	public Type(long idType, String libelle, String description, Marque marque) {
		super();
		this.idType = idType;
		this.libelle = libelle;
		this.description = description;
		this.marque = marque;
	}

	/**
	 * 
	 */
	public Type() {
		super();
	}

	/**
	 * @return the idType
	 */
	public long getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(long idType) {
		this.idType = idType;
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

	/**
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	
	
	
	
	
}
