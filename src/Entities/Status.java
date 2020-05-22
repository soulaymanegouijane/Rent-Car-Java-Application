package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="status")
public class Status implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idStatus", nullable=false)
	private long idStatus;
	@Column(name="libelle",nullable=false)
	private String libelle;
	@Column(name="description",nullable=false)
	private String description;
//	private List<Reservation> listReservation = new ArrayList<>();
	
	
	/**
	 * @param idStatus
	 * @param libelle
	 * @param description
	 * @param listReservation
	 */
	public Status(long idStatus, String libelle, String description) {
		super();
		this.idStatus = idStatus;
		this.libelle = libelle;
		this.description = description;
	}
	
	
	/**
	 * 
	 */
	public Status() {
		super();
	}



	/**
	 * @return the idStatus
	 */
	public long getIdStatus() {
		return idStatus;
	}
	/**
	 * @param idStatus the idStatus to set
	 */
	public void setIdStatus(long idStatus) {
		this.idStatus = idStatus;
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
//	/**
//	 * @return the listReservation
//	 */
//	public List<Reservation> getListReservation() {
//		return listReservation;
//	}
//	/**
//	 * @param listReservation the listReservation to set
//	 */
//	public void setListReservation(List<Reservation> listReservation) {
//		this.listReservation = listReservation;
//	}
//	
	
}
