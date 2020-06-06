package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicule")
public class Vehicule implements Serializable{
	
	@Id
	@Column(name="idVehicule", nullable=false)
	private String idVehicule;
	@Column(name="nbr_place",nullable=true)
	private int nbr_place;
	@Column(name="color",nullable=true)
	private String color;
	@Column(name="dispo",nullable=true)
	private boolean dispo;
	
	@Lob
	@Column(name="photo", columnDefinition = "BLOB")
	private byte [] image;
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	private String typeVehicule;
	private String marqueLibelle;
	
	private String libelle_marque;
	private String libelle_parking;
	private String libelle_carburant;
	
	
	@Column(name="idCarburant",nullable=true)
	private long dCarburant;
	@Column(name="idType",nullable=true)
	private long idType;
	@Column(name="idParking",nullable=true)
	private long idParking;



	@ManyToOne
	@JoinColumn(name="idCarburant",referencedColumnName = "idCarburant",insertable = false,updatable = false)
	private Carburant carburant;
	@ManyToOne
	@JoinColumn(name="idType",referencedColumnName = "idType",insertable = false,updatable = false)
	private Type type;
	@ManyToOne
	@JoinColumn(name="idParking",referencedColumnName = "idParking",insertable = false,updatable = false)
	private Parking parking;
//	private List<Reservation> listReservation = new ArrayList<Reservation>();
	
	
	



	/**
	 * @return the libelle_marque
	 */
	public String getLibelle_marque() {
		return libelle_marque;
	}

	/**
	 * @param libelle_marque the libelle_marque to set
	 */
	public void setLibelle_marque(String libelle_marque) {
		this.libelle_marque = libelle_marque;
	}

	/**
	 * @return the libelle_carburant
	 */
	public String getLibelle_carburant() {
		return libelle_carburant;
	}

	/**
	 * @param libelle_carburant the libelle_carburant to set
	 */
	public void setLibelle_carburant(String libelle_carburant) {
		this.libelle_carburant = libelle_carburant;
	}

	/**
	 * @return the libelle_parkinglibelle_carburant
	 */
	public String getLibelle_parking() {
		return libelle_parking;
	}

	/**
	 * @param libelle_parking the libelle_parking to set
	 */
	public void setLibelle_parking(String libelle_parking) {
		this.libelle_parking = libelle_parking;
	}


	/**
	 * @return the idVehicule
	 */
	public String getIdVehicule() {
		return idVehicule;
	}

	/**
	 * @param idVehicule the idVehicule to set
	 */
	public void setIdVehicule(String idVehicule) {
		this.idVehicule = idVehicule;
	}

	

	public boolean isDispo() {
		return this.dispo;
	}

	public boolean getDispo() {
		return this.dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}


	/**
	 * 
	 */
	public Vehicule() {
		super();
	}


	


	/**
	 * @return the nbr_place
	 */
	public int getNbr_place() {
		return nbr_place;
	}


	/**
	 * @param nbr_place the nbr_place to set
	 */
	public void setNbr_place(int nbr_place) {
		this.nbr_place = nbr_place;
	}


	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}


	/**
	 * @return the carburant
	 */
	public Carburant getCarburant() {
		return carburant;
	}


	/**
	 * @param carburant the carburant to set
	 */
	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}


	/**
	 * @return the marque
	 */
	public Type getType() {
		return type;
	}


	/**
	 * @param marque the marque to set
	 */
	public void setType(Type type) {
		this.type = type;
	}


	/**
	 * @return the parking
	 */
	public Parking getParking() {
		return parking;
	}


	/**
	 * @param parking the parking to set
	 */
	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public String getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	public String getMarqueLibelle() {
		return marqueLibelle;
	}

	public void setMarqueLibelle(String marqueLibelle) {
		this.marqueLibelle = marqueLibelle;
	}


//	/**
//	 * @return the listReservation
//	 */
//	public List<Reservation> getListReservation() {
//		return listReservation;
//	}
//
//
//	/**
//	 * @param listReservation the listReservation to set
//	 */
//	public void setListReservation(List<Reservation> listReservation) {
//		this.listReservation = listReservation;
//	}
//	
	
	
	
	
	

}
