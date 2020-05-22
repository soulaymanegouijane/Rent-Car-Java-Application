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
	private Date datReservation;
	@Column(name="date_depart",nullable=false)
	private Date date_depart;
	@Column(name="date_retour",nullable=false)
	private Date date_retour;	
	@Column(name="avance",nullable=false)
	private float avance;
	@Column(name="idTypeRes",nullable=false)
	private long idTypeRes;
	@Column(name="idClient",nullable=false)
	private long idClient;
	@Column(name="idStatus",nullable=false)
	private long idStatus;
	@Column(name="idVehicule",nullable=false)
	private String idVehicule;
	
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

	


	

	public Reservation(long idReservation, Date datReservation, Date date_depart, Date date_retour, float avance, TypeReservation typeRes, Client client, Status status, Vehicule vehicule) {
		this.idReservation = idReservation;
		this.datReservation = datReservation;
		this.date_depart = date_depart;
		this.date_retour = date_retour;
		this.avance = avance;
		this.typeRes = typeRes;
		this.client = client;
		this.status = status;
		this.vehicule = vehicule;
	}


	public Reservation() {
	}

	




	public long getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(long idReservation) {
		this.idReservation = idReservation;
	}


	public Date getDatReservation() {
		return datReservation;
	}


	public void setDatReservation(Date datReservation) {
		this.datReservation = datReservation;
	}


	public Date getDate_depart() {
		return this.date_depart;
	}

	public void setDate_depart(Date date_depart) {
		this.date_depart = date_depart;
	}

	public Date getDate_retour() {
		return this.date_retour;
	}

	public void setDate_retour(Date date_retour) {
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
