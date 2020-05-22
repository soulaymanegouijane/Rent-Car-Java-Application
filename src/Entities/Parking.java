package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parking")
public class Parking implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idParking", nullable=false)
	private long idParking;
	@Column(name="adress",nullable=false)
	private String adress;
	@Column(name="capacite",nullable=false)
	private long capacite;
	@Column(name="nbr_place_pleinne",nullable=false)
	private int nbr_place_pleinne; // ? Getters Setters ....
	
	/**
	 * @param idParking
	 * @param adress
	 * @param capacite
	 * @param utilisateur
	 */
	public Parking(long idParking, String adress, long capacite) {
		super();
		this.idParking = idParking;
		this.adress = adress;
		this.capacite = capacite;
	}
	
	/**
	 * 
	 */
	public Parking() {
		super();
	}


	public int getNbr_place_pleinne() {
		return this.nbr_place_pleinne;
	}

	public void setNbr_place_pleinne(int nbr_place_pleinne) {
		this.nbr_place_pleinne = nbr_place_pleinne;
	}

	
	/**
	 * @return the idParking
	 */
	public long getIdParking() {
		return idParking;
	}
	/**
	 * @param idParking the idParking to set
	 */
	public void setIdParking(long idParking) {
		this.idParking = idParking;
	}
	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @return the capacite
	 */
	public long getCapacite() {
		return capacite;
	}
	/**
	 * @param capacite the capacite to set
	 */
	public void setCapacite(long capacite) {
		this.capacite = capacite;
	}
	
	
}
