package Entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="contrat")
public class Contrat implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idContrat", nullable=false)
	private long idContrat;
	@Column(name="date_contrat",nullable=true)
	private String dateContrat;
	@Column(name="montant_total",nullable=true)
	private double MontantTotal;
	@Column(name="idVehicule",nullable=true)
	private String idVehicule;
	@Column(name="idReservation",nullable=true)
	private Long idReservation;
	@Column(name="date_sortie",nullable=true)
	private String date_sortie;
	@Column(name="date_retour",nullable=true)
	private String date_retour; 
	@Column(name="heure_retour",nullable=true)
	private String heure_retour;
	@Column(name="heure_sortie",nullable=true)
	private String heure_sortie;
	@Column(name="nbr_jour",nullable=true)
	private int nbr_jour;
	@Column(name="prix_jour",nullable=true)
	private float prix_jour;
	@Column(name="remise",nullable=true)
	private float remise;
	@Column(name="caution",nullable=true)
	private float caution;
	@Column(name="km_depart",nullable=true)
	private long km_depart;
	@Column(name="km_retour",nullable=true)
	private long km_retour;
	@Column(name="idUtilisateur",nullable=true)
	private String idUtilisateur;
	
	private String matricule;
	private String cinClient;
	private String cinUtilisateur;
	@Column(name="codeReservation",nullable=true)
	private long codeReservation;
	
	@ManyToOne
	@JoinColumn(name="idVehicule",referencedColumnName = "idVehicule",insertable = false,updatable = false)
	private Vehicule vehicule;
	@OneToOne
	@JoinColumn(name="idReservation",referencedColumnName = "idReservation",insertable = false,updatable = false)
	private Reservation reservation;
	@ManyToOne
	@JoinColumn(name="idUtilisateur",referencedColumnName = "idUtilisateur",insertable = false,updatable = false)
	private Utilisateur utilisateur;

	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	public Contrat() {
		super();
	}
	
	

	@Override
	public String toString() {
		return "Contrat [idContrat=" + idContrat + ", dateContrat=" + dateContrat + ", MontantTotal=" + MontantTotal
				+ ", idVehicule=" + idVehicule + ", idReservation=" + idReservation + ", date_sortie=" + date_sortie
				+ ", date_retour=" + date_retour + ", heure_retour=" + heure_retour + ", heure_sortie=" + heure_sortie
				+ ", nbr_jour=" + nbr_jour + ", prix_jour=" + prix_jour + ", remise=" + remise + ", caution=" + caution
				+ ", km_depart=" + km_depart + ", km_retour=" + km_retour + ", idUtilisateur=" + idUtilisateur
				+ ", matricule=" + matricule + ", cinClient=" + cinClient + ", cinUtilisateur=" + cinUtilisateur
				+ ", codeReservation=" + codeReservation + ", vehicule=" + vehicule + ", reservation=" + reservation
				+ ", utilisateur=" + utilisateur + "]";
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
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

	/**
	 * @return the codeReservation
	 */
	public long getCodeReservation() {
		return codeReservation;
	}

	/**
	 * @param codeReservation the codeReservation to set
	 */
	public void setCodeReservation(long codeReservation) {
		this.codeReservation = codeReservation;
	}

	/**
	 * @return the idContrat
	 */
	public long getIdContrat() {
		return idContrat;
	}

	/**
	 * @param idContrat the idContrat to set
	 */
	public void setIdContrat(long idContrat) {
		this.idContrat = idContrat;
	}

	/**
	 * @return the dateContrat
	 */
	public String getDateContrat() {
		return dateContrat;
	}

	/**
	 * @param dateContrat the dateContrat to set
	 */
	public void setDateContrat(String dateContrat) {
		this.dateContrat = dateContrat;
	}

	/**
	 * @return the montantTotal
	 */
	public double getMontantTotal() {
		return MontantTotal;
	}

	/**
	 * @param montantTotal the montantTotal to set
	 */
	public void setMontantTotal(double montantTotal) {
		MontantTotal = montantTotal;
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

	/**
	 * @return the idReservation
	 */
	public Long getIdReservation() {
		return idReservation;
	}

	/**
	 * @param idReservation the idReservation to set
	 */
	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}


	/**
	 * @return the date_sortie
	 */
	public String getDate_sortie() {
		return date_sortie;
	}

	/**
	 * @param date_sortie the date_sortie to set
	 */
	public void setDate_sortie(String date_sortie) {
		this.date_sortie = date_sortie;
	}

	/**
	 * @return the date_retour
	 */
	public String getDate_retour() {
		return date_retour;
	}

	/**
	 * @param date_retour the date_retour to set
	 */
	public void setDate_retour(String date_retour) {
		this.date_retour = date_retour;
	}

	/**
	 * @return the heure_retour
	 */
	public String getHeure_retour() {
		return heure_retour;
	}

	/**
	 * @param heure_retour the heure_retour to set
	 */
	public void setHeure_retour(String heure_retour) {
		this.heure_retour = heure_retour;
	}

	/**
	 * @return the heure_sortie
	 */
	public String getHeure_sortie() {
		return heure_sortie;
	}

	/**
	 * @param heure_sortie the heure_sortie to set
	 */
	public void setHeure_sortie(String heure_sortie) {
		this.heure_sortie = heure_sortie;
	}

	/**
	 * @return the nbr_jour
	 */
	public int getNbr_jour() {
		return nbr_jour;
	}

	/**
	 * @param nbr_jour the nbr_jour to set
	 */
	public void setNbr_jour(int nbr_jour) {
		this.nbr_jour = nbr_jour;
	}

	/**
	 * @return the prix_jour
	 */
	public float getPrix_jour() {
		return prix_jour;
	}

	/**
	 * @param prix_jour the prix_jour to set
	 */
	public void setPrix_jour(float prix_jour) {
		this.prix_jour = prix_jour;
	}

	/**
	 * @return the remise
	 */
	public float getRemise() {
		return remise;
	}

	/**
	 * @param remise the remise to set
	 */
	public void setRemise(float remise) {
		this.remise = remise;
	}

	/**
	 * @return the caution
	 */
	public float getCaution() {
		return caution;
	}

	/**
	 * @param caution the caution to set
	 */
	public void setCaution(float caution) {
		this.caution = caution;
	}

	/**
	 * @return the km_depart
	 */
	public long getKm_depart() {
		return km_depart;
	}

	/**
	 * @param km_depart the km_depart to set
	 */
	public void setKm_depart(long km_depart) {
		this.km_depart = km_depart;
	}

	/**
	 * @return the km_retour
	 */
	public long getKm_retour() {
		return km_retour;
	}

	/**
	 * @param km_retour the km_retour to set
	 */
	public void setKm_retour(long km_retour) {
		this.km_retour = km_retour;
	}

	/**
	 * @return the vehicule
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}

	/**
	 * @param vehicule the vehicule to set
	 */
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	/**
	 * @return the reservation
	 */
	public Reservation getReservation() {
		return reservation;
	}

	/**
	 * @param reservation the reservation to set
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	
	
	
	

}
