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
	@Column(name="date_contrat",nullable=false)
	private Date dateContrat;
	@Column(name="montant_total",nullable=false)
	private double MontantTotal;
	@Column(name="idVehicule",nullable=false)
	private String idVehicule;
	@Column(name="idReservation",nullable=false)
	private Long idReservation;
	@Column(name="idSanction",nullable=false)
	private Long idSanction;
	@Column(name="date_sortie",nullable=false)
	private Date date_sortie;
	@Column(name="date_retour",nullable=false)
	private Date date_retour; 
	@Column(name="heure_retour",nullable=false)
	private Date heure_retour;
	@Column(name="heure_sortie",nullable=false)
	private Date heure_sortie;
	@Column(name="nbr_jour",nullable=false)
	private int nbr_jour;
	@Column(name="prix_jour",nullable=false)
	private float prix_jour;
	@Column(name="remise",nullable=false)
	private float remise;
	@Column(name="caution",nullable=false)
	private float caution;
	@Column(name="km_depart",nullable=false)
	private long km_depart;
	@Column(name="km_retour",nullable=false)
	private long km_retour;
	
	@ManyToOne
	@JoinColumn(name="idVehicule",referencedColumnName = "idVehicule",insertable = false,updatable = false)
	private Vehicule vehicule;
	@ManyToOne
	@JoinColumn(name="idReservation",referencedColumnName = "idReservation",insertable = false,updatable = false)
	private Reservation reservation;
	@OneToOne
	@JoinColumn(name="idSanction",referencedColumnName = "idSanction",insertable = false,updatable = false)
	private Sanction sanction;
	
	
	public Contrat(Date dateContrat, double montantTotal, Vehicule vehicule, Reservation reservation, Sanction sanction,
			Date date_sortie, Date date_retour, Date heure_retour, Date heure_sortie, int nbr_jour, float prix_jour,
			float remise, float caution, long km_depart, long km_retour) {
		super();
		this.dateContrat = dateContrat;
		MontantTotal = montantTotal;
		this.vehicule = vehicule;
		this.reservation = reservation;
		this.sanction = sanction;
		this.date_sortie = date_sortie;
		this.date_retour = date_retour;
		this.heure_retour = heure_retour;
		this.heure_sortie = heure_sortie;
		this.nbr_jour = nbr_jour;
		this.prix_jour = prix_jour;
		this.remise = remise;
		this.caution = caution;
		this.km_depart = km_depart;
		this.km_retour = km_retour;
	}

	/**
	 * @param codeContrat
	 * @param dateContrat
	 * @param montantTotal
	 * @param vehicule
	 * @param reservation
	 * @param sanction
	 * @param date_sortie
	 * @param date_retour
	 * @param heure_retour {@docRoot la date de retour de la voiture}
	 * @param heure_sortie
	 * @param nbr_jour
	 * @param prix_jour
	 * @param remise
	 * @param caution
	 */
	public Contrat( Date dateContrat, double montantTotal, Vehicule vehicule, Reservation reservation,
			Sanction sanction, Date date_sortie, Date date_retour, Date heure_retour, Date heure_sortie, int nbr_jour,
			float prix_jour, float remise, float caution) {
		super();
//		this.idContrat = idContrat;
		this.dateContrat = dateContrat;
		this.MontantTotal = montantTotal;
		this.vehicule = vehicule;
		this.reservation = reservation;
		this.sanction = sanction;
		this.date_sortie = date_sortie;
		this.date_retour = date_retour;
		this.heure_retour = heure_retour;
		this.heure_sortie = heure_sortie;
		this.nbr_jour = nbr_jour;
		this.prix_jour = prix_jour;
		this.remise = remise;
		this.caution = caution;
	}

	/**
	 * @param ConstructeruSansParametre
	 */
	public Contrat() {
		super();
	}

	/**
	 * @return the codeContrat
	 */
	public long getIdContrat() {
		return this.idContrat;
	}

	/**
	 * @param codeContrat the codeContrat to set
	 */
	public void setIdContrat(long idContrat) {
		this.idContrat = idContrat;
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
	 * @return the dateContrat
	 */
	public Date getDateContrat() {
		return dateContrat;
	}

	/**
	 * @param dateContrat the dateContrat to set
	 */
	public void setDateContrat(Date dateContrat) {
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

	/**
	 * @return the sanction
	 */
	public Sanction getSanction() {
		return sanction;
	}

	/**
	 * @param sanction the sanction to set
	 */
	public void setSanction(Sanction sanction) {
		this.sanction = sanction;
	}

	/**
	 * @return the date_sortie
	 */
	public Date getDate_sortie() {
		return date_sortie;
	}

	/**
	 * @param date_sortie the date_sortie to set
	 */
	public void setDate_sortie(Date date_sortie) {
		this.date_sortie = date_sortie;
	}

	/**
	 * @return the date_retour
	 */
	public Date getDate_retour() {
		return date_retour;
	}

	/**
	 * @param date_retour the date_retour to set
	 */
	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}

	/**
	 * @return the heure_retour
	 */
	public Date getHeure_retour() {
		return heure_retour;
	}

	/**
	 * @param heure_retour the heure_retour to set
	 */
	public void setHeure_retour(Date heure_retour) {
		this.heure_retour = heure_retour;
	}

	/**
	 * @return the heure_sortie
	 */
	public Date getHeure_sortie() {
		return heure_sortie;
	}

	/**
	 * @param heure_sortie the heure_sortie to set
	 */
	public void setHeure_sortie(Date heure_sortie) {
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
	
	
	

}
