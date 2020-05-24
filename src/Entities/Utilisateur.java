package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class Utilisateur implements Serializable{
	
	@Id
	@Column(name="idUtilisateur", nullable=false)
	private String idUtilisateur;
	@Column(name="nom",nullable=false)
	private String nom;
	@Column(name="prenom",nullable=false)
	private String prenom;
	@Column(name="telephone",nullable=false)
	private String tele;
	@Column(name="adress",nullable=false)
	private String adress;
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="naissance",nullable=false)
	private String naissance;
	@Column(name="etat_compte",nullable=false)
	private String etat_compte;
	@Column(name="idRole",nullable=true)
	private long idRole;
	@Column(name="idReservation",nullable=false)
	private long idReservation;

	@ManyToOne
	@JoinColumn(name="idRole",referencedColumnName = "idRole",insertable = false,updatable = false)
	private Role role;
	@ManyToOne
	@JoinColumn(name="idReservation",referencedColumnName = "idReservation",insertable = false,updatable = false)
	private Reservation reservation;

	
	


	/**
	 * @return the etat_compte
	 */
	public String getEtat_compte() {
		return etat_compte;
	}


	/**
	 * @param etat_compte the etat_compte to set
	 */
	public void setEtat_compte(String etat_compte) {
		this.etat_compte = etat_compte;
	}


	public Utilisateur(String idUtilisateur, String nom, String prenom, String tele, String adress, String email,
			String naissance, String etat_compte, Role role, Reservation reservation) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.tele = tele;
		this.adress = adress;
		this.email = email;
		this.naissance = naissance;
		this.etat_compte = etat_compte;
		this.role = role;
		this.reservation = reservation;
	}


	/**
	 * @return the naissance
	 */
	public String getNaissance() {
		return naissance;
	}


	/**
	 * @param naissance the naissance to set
	 */
	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}


	/**
	 * 
	 */
	public Utilisateur() {
		super();
	}
	
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @return the idUtilisateur
	 */
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the tele
	 */
	public String getTele() {
		return tele;
	}
	/**
	 * @param tele the tele to set
	 */
	public void setTele(String tele) {
		this.tele = tele;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
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
