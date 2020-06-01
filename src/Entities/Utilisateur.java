package Entities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class Utilisateur implements Serializable{
	
	@Id
	@Column(name="idUtilisateur", nullable=false)
	private String idUtilisateur;
	@Column(name="nom",nullable=true)
	private String nom;
	@Column(name="prenom",nullable=true)
	private String prenom;
	@Column(name="telephone",nullable=true)
	private String tele;
	@Column(name="adress",nullable=true)
	private String adress;
	@Column(name="email",nullable=true)
	private String email;
	@Column(name="naissance",nullable=true)
	private String naissance;
	@Column(name="etat_compte",nullable=true)
	private String etat_compte;
	@Column(name="idRole",nullable=false)
	private long idRole;
//	@Column(name="idReservation",nullable=false)
//	private long idReservation;
	@Column(name="civilite",nullable=true)
	private String civilite;
	@Column(name="lieu_naissance",nullable=true)
	private String lieu_naissance;
	@Column(name="ville",nullable=true)
	private String ville;
	@Column(name="pays",nullable=true)
	private String pays;
	@Column(name="nationalite",nullable=true)
	private String nationalite;
	@Column(name="type_identifiant",nullable=true)
	private String carte_identifiant;
	
	@Lob
	@Column(name="photo", columnDefinition = "BLOB")
	private byte [] image;
	
	@ManyToOne
	@JoinColumn(name="idRole",referencedColumnName = "idRole",insertable = false,updatable = false)
	private Role role;
	
	
	
	
	
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}


	/**
	 * @return the carte_identifiant
	 */
	public String getCarte_identifiant() {
		return carte_identifiant;
	}


	/**
	 * @param carte_identifiant the carte_identifiant to set
	 */
	public void setCarte_identifiant(String carte_identifiant) {
		this.carte_identifiant = carte_identifiant;
	}


	/**
	 * @return the nationalite
	 */
	public String getNationalite() {
		return nationalite;
	}


	/**
	 * @param nationalite the nationalite to set
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}


	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}


	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	@Column(name="code_postale",nullable=true)
	private String  code_postale;

	

	
	


	/**
	 * @return the civilite
	 */
	public String getCivilite() {
		return civilite;
	}


	/**
	 * @param civilite the civilite to set
	 */
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


	/**
	 * @return the lieu_naissance
	 */
	public String getLieu_naissance() {
		return lieu_naissance;
	}


	/**
	 * @param lieu_naissance the lieu_naissance to set
	 */
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}


	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}


	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}


	/**
	 * @return the code_postale
	 */
	public String getCode_postale() {
		return code_postale;
	}


	/**
	 * @param code_postale the code_postale to set
	 */
	public void setCode_postale(String code_postale) {
		this.code_postale = code_postale;
	}


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
	
	
	
	
	
	

}
