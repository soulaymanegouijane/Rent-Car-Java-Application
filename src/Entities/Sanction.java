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
@Table(name="sanction")
public class Sanction implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSanction", nullable=false)
	private long idSanction;
	@Column(name="nbr_jrs_retard",nullable=false)
	private int nbrJrsRetard;
	@Column(name="date_sanction",nullable=false)
	private Date dateSanction;
	@Column(name="montant",nullable=false)
	private Double montant;
	@Column(name="idContrat",nullable=false)
	private long idContrat;
	
	
	@OneToOne
	@JoinColumn(name="idContrat",referencedColumnName = "idContrat",insertable = false,updatable = false)
	private Contrat contrat;
	
	
	/**
	 * @param idSanction
	 * @param nbrJrsRetard
	 * @param dateSanction
	 * @param montant
	 * @param contrat
	 */
	public Sanction(long idSanction, int nbrJrsRetard, Date dateSanction, Double montant, Contrat contrat) {
		super();
		this.idSanction = idSanction;
		this.nbrJrsRetard = nbrJrsRetard;
		this.dateSanction = dateSanction;
		this.montant = montant;
		this.contrat = contrat;
	}



	/**
	 * 
	 */
	public Sanction() {
		super();
	}

	
	
	/**
	 * @return the nbrJrsRetard
	 */
	public int getNbrJrsRetard() {
		return nbrJrsRetard;
	}



	/**
	 * @param nbrJrsRetard the nbrJrsRetard to set
	 */
	public void setNbrJrsRetard(int nbrJrsRetard) {
		this.nbrJrsRetard = nbrJrsRetard;
	}



	/**
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}


	/**
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}


	/**
	 * @return the idSanction
	 */
	public long getIdSanction() {
		return idSanction;
	}
	/**
	 * @param idSanction the idSanction to set
	 */
	public void setIdSanction(long idSanction) {
		this.idSanction = idSanction;
	}
	
	
	/**
	 * @return the dateSanction
	 */
	public Date getDateSanction() {
		return dateSanction;
	}
	/**
	 * @param dateSanction the dateSanction to set
	 */
	public void setDateSanction(Date dateSanction) {
		this.dateSanction = dateSanction;
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
