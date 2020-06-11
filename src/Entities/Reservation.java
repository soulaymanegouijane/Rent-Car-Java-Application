	package Entities;

	import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReservation", nullable=false)
	private long idReservation;
	@Column(name="dateReservation",nullable=false)
	private String datReservation;
	@Column(name="date_depart",nullable=true)
	private String date_depart;
	@Column(name="date_retour",nullable=true)
	private String date_retour;	
	@Column(name="avance",nullable=false)
	private float avance;
	@Column(name="idTypeRes",nullable=false)
	private long idTypeRes;
	@Column(name="idClient",nullable=false)
	private String idClient;
	@Column(name="idStatus",nullable=false)
	private long idStatus;
	@Column(name="idVehicule",nullable=false)
	private String idVehicule;
	@Column(name="idUtilisateur",nullable=true)
	private String idUtilisateur;
	@Column(name="montant",nullable=true)
	private float montant;
	@Column(name="nombreJours",nullable=true)
	private int nombreJours;
	

	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	private String cinClient;
	private String cinUtilisateur;
	private String statusRes;
	private  String typeVehicule;
	
	@ManyToOne
	@JoinColumn(name="idUtilisateur",referencedColumnName = "idUtilisateur",insertable = false,updatable = false)
	private Utilisateur utilisateur;
	
	
	
	
	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", datReservation=" + datReservation + ", date_depart="
				+ date_depart + ", date_retour=" + date_retour + ", avance=" + avance + ", idTypeRes=" + idTypeRes
				+ ", idClient=" + idClient + ", idStatus=" + idStatus + ", idVehicule=" + idVehicule
				+ ", idUtilisateur=" + idUtilisateur + ", montant=" + montant + ", cinClient=" + cinClient
				+ ", cinUtilisateur=" + cinUtilisateur + ", statusRes=" + statusRes + ", typeVehicule=" + typeVehicule
				+ ", utilisateur=" + utilisateur + ", client=" + client + ", typeRes=" + typeRes + ", status=" + status
				+ ", vehicule=" + vehicule + "]";
	}


	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	/**
	 * @return the typeVehicule
	 */
	public String getTypeVehicule() {
		return typeVehicule;
	}


	/**
	 * @param typeVehicule the typeVehicule to set
	 */
	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}


	public int getNombreJours() {
		return nombreJours;
	}

	public void setNombreJours(int nombreJours) {
		this.nombreJours = nombreJours;
	}

	/**
	 * @return the statusRes
	 */
	public String getStatusRes() {
		return statusRes;
	}






	/**
	 * @param statusRes the statusRes to set
	 */
	public void setStatusRes(String statusRes) {
		this.statusRes = statusRes;
	}






	/**
	 * @return the cinUtilisateur
	 */
	public String getCinUtilisateur() {
		return cinUtilisateur;
	}






	/**
	 * @param cinUtilisateur the cinUtilisateur to set
	 */
	public void setCinUtilisateur(String cinUtilisateur) {
		this.cinUtilisateur = cinUtilisateur;
	}


	@ManyToOne
	@JoinColumn(name="idClient",referencedColumnName = "idClient",insertable = false,updatable = false)
	private Client client;
	@ManyToOne
	@JoinColumn(name="idTypeRes",referencedColumnName = "idTypeRes",insertable = false,updatable = false)
	private TypeReservation typeRes;
	@ManyToOne
	@JoinColumn(name="idStatus",referencedColumnName = "idStatus",insertable = false,updatable = false)
	private Status status;
	@ManyToOne
	@JoinColumn(name="idVehicule",referencedColumnName = "idVehicule",insertable = false,updatable = false)
	private Vehicule vehicule;

	


	

	


	public Reservation() {
	}

	
	



	/**
	 * @return the cinClient
	 */
	public String getCinClient() {
		return cinClient;
	}






	/**
	 * @param cinClient the cinClient to set
	 */
	public void setCinClient(String cinClient) {
		this.cinClient = cinClient;
	}






	public long getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(long idReservation) {
		this.idReservation = idReservation;
	}


	public String getDatReservation() {
		return datReservation;
	}


	public void setDatReservation(String datReservation) {
		this.datReservation = datReservation;
	}


	public String getDate_depart() {
		return this.date_depart;
	}

	public void setDate_depart(String date_depart) {
		this.date_depart = date_depart;
	}

	public String getDate_retour() {
		return this.date_retour;
	}

	public void setDate_retour(String date_retour) {
		this.date_retour = date_retour;
	}


	public float getAvance() {
		return avance;
	}


	public void setAvance(float avance) {
		this.avance = avance;
	}


	public TypeReservation getTypeRes() {
		return typeRes;
	}


	public void setTypeRes(TypeReservation typeRes) {
		this.typeRes = typeRes;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Vehicule getVehicule() {
		return vehicule;
	}


	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	


	
	
}
