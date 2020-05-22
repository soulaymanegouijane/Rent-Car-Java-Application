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
@Table(name="type_reservation")
public class TypeReservation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTypeRes", nullable=false)
	private long idTypeReservation;
	@Column(name="libelle",nullable=false)
	private String libelle;
	@Column(name="description",nullable=false)
	private String description;
//	private List<Reservation> listReservation = new ArrayList<>();
	/**
	 * @param idTypeReservation
	 * @param libelle
	 * @param description
	 * @param listReservation
	 */
	public TypeReservation(long idTypeReservation, String libelle, String description,
			List<Reservation> listReservation) {
		super();
		this.idTypeReservation = idTypeReservation;
		this.libelle = libelle;
		this.description = description;
		//this.listReservation = listReservation;
	}
	/**
	 * 
	 */
	public TypeReservation() {
		super();
	}
	/**
	 * @return the idTypeReservation
	 */
	public long getIdTypeReservation() {
		return idTypeReservation;
	}
	/**
	 * @param idTypeReservation the idTypeReservation to set
	 */
	public void setIdTypeReservation(long idTypeReservation) {
		this.idTypeReservation = idTypeReservation;
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
	 * @return the listReservation
	 */
//	public List<Reservation> getListReservation() {
//		return listReservation;
//	}
//	/**
//	 * @param listReservation the listReservation to set
//	 */
//	public void setListReservation(List<Reservation> listReservation) {
//		this.listReservation = listReservation;
//	}
	
}
