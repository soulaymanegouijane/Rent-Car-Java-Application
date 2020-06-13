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
import javax.persistence.Table;

@Entity
@Table(name="facture")
public class Factures implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idFacture", nullable=false)
	private long idFacture;
	@Column(name="date_facture",nullable=true)
	private String dateFacture;
	@Column(name="nbr_jours",nullable=true)
	private int nbr_jours;
	@Column(name="prix_ht",nullable=true)
	private double prixHT;
	@Column(name="tva",nullable=true)
	private double TVA;
	@Column(name="montant_ttc",nullable=true)
	private double montantTTC;
	@Column(name="idContrat",nullable=true)
	private Long idContrat;
	@Column(name="nbrJoursRetard",nullable=true)
	private int nbrJoursRetard;
	@Column(name="montantSanction",nullable=true)
	private float montantSanction;
	

	@ManyToOne
	@JoinColumn(name="idContrat",referencedColumnName = "idContrat",insertable = false,updatable = false)
	private Contrat contrat;
	
	public int getNbrJoursRetard() {
		return nbrJoursRetard;
	}
	
	public void setNbrJoursRetard(int nbrJoursRetard) {
		this.nbrJoursRetard = nbrJoursRetard;
	}
	
	public float getMontantSanction() {
		return montantSanction;
	}
	
	public void setMontantSanction(float montantSanction) {
		this.montantSanction = montantSanction;
	}
	
	/**
	 * 
	 */
	public Factures() {
	}
	/**
	 * @param idFacture
	 * @param dateFacture
	 * @param nbr_jours
	 * @param prixHT
	 * @param tVA
	 * @param montantTTC
	 * @param contrat
	 */
	public Factures(long idFacture, String dateFacture, int nbr_jours, double prixHT, double tVA, double montantTTC,
			Contrat contrat) {
		super();
		this.idFacture = idFacture;
		this.dateFacture = dateFacture;
		this.nbr_jours = nbr_jours;
		this.prixHT = prixHT;
		TVA = tVA;
		this.montantTTC = montantTTC;
		this.contrat = contrat;
	}
	/**
	 * @return the idFacture
	 */
	public long getIdFacture() {
		return idFacture;
	}
	/**
	 * @param idFacture the idFacture to set
	 */
	public void setIdFacture(long idFacture) {
		this.idFacture = idFacture;
	}
	/**
	 * @return the dateFacture
	 */
	public String getDateFacture() {
		return dateFacture;
	}
	/**
	 * @param dateFacture the dateFacture to set
	 */
	public void setDateFacture(String dateFacture) {
		this.dateFacture = dateFacture;
	}
	/**
	 * @return the nbr_jours
	 */
	public int getNbr_jours() {
		return nbr_jours;
	}
	/**
	 * @param nbr_jours the nbr_jours to set
	 */
	public void setNbr_jours(int nbr_jours) {
		this.nbr_jours = nbr_jours;
	}
	/**
	 * @return the prixHT
	 */
	public double getPrixHT() {
		return prixHT;
	}
	/**
	 * @param prixHT the prixHT to set
	 */
	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}
	/**
	 * @return the tVA
	 */
	public double getTVA() {
		return TVA;
	}
	/**
	 * @param tVA the tVA to set
	 */
	public void setTVA(double tVA) {
		TVA = tVA;
	}
	/**
	 * @return the montantTTC
	 */
	public double getMontantTTC() {
		return montantTTC;
	}
	/**
	 * @param montantTTC the montantTTC to set
	 */
	public void setMontantTTC(double montantTTC) {
		this.montantTTC = montantTTC;
	}
	/**
	 * @return the contrat
	 */
	public Contrat getContrat() {
		return contrat;
	}
	/**
	 * @param contrat the contrat to set
	 */
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	
	
	
	

}
