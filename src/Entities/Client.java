package Entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client implements Serializable{
	
	@Id
	@Column(name="idClient", nullable=false)
	private String idClient;
	@Column(name="age",nullable=true)
	private int age;
	@Column(name="nom",nullable=true)
	private String nom;
	@Column(name="prenom",nullable=true)
	private String prenom;
	@Column(name="adress",nullable=true)
	private String adress;
	@Column(name="telephone",nullable=true)
	private String telephone;
	@Column(name="email",nullable=true)
	private String email;	
	@Column(name="civilite",nullable=true)
	private String civilite;
	@Column(name="date_naissance",nullable=false)
	private String date_naissance;
	@Column(name="lieu_naissance",nullable=true)
	private String lieu_naissance;
	@Column(name="n_permis",nullable=true)
	private String n_permis;
	@Column(name="delevre_le",nullable=true)
	private String delevre_le;
	@Column(name="validitePermis",nullable=true)
	private String validitePermis;
	@Column(name="delevre_a",nullable=true)
	private String delevre_a;
	@Column(name="type_identifiant",nullable=true)
	private String carte_identifiant;
	//  num carte c'est le cin du client meme chose que idClient
	@Column(name="num_carte",nullable=true)
	private String num_carte;
	@Column(name="validitePI",nullable=true)
	private String validitePI;
	@Column(name="ville",nullable=true)
	private String ville;
	@Column(name="pays",nullable=true)
	private String pays;
	@Column(name="code_postale",nullable=true)
	private String  code_postale;
	@Column(name="nationalite",nullable=true)
	private String nationalite;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getLieu_naissance() {
		return lieu_naissance;
	}
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}
	public String getN_permis() {
		return n_permis;
	}
	public void setN_permis(String n_permis) {
		this.n_permis = n_permis;
	}
	public String getDelevre_le() {
		return delevre_le;
	}
	public void setDelevre_le(String delevre_le) {
		this.delevre_le = delevre_le;
	}
	public String getValiditePermis() {
		return validitePermis;
	}
	public void setValiditePermis(String validitePermis) {
		this.validitePermis = validitePermis;
	}
	public String getDelevre_a() {
		return delevre_a;
	}
	public void setDelevre_a(String delevre_a) {
		this.delevre_a = delevre_a;
	}
	public String getCarte_identifiant() {
		return carte_identifiant;
	}
	public void setCarte_identifiant(String carte_identifiant) {
		this.carte_identifiant = carte_identifiant;
	}
	public String getNum_carte() {
		return num_carte;
	}
	public void setNum_carte(String num_carte) {
		this.num_carte = num_carte;
	}
	public String getValiditePI() {
		return validitePI;
	}
	public void setValiditePI(String validitePI) {
		this.validitePI = validitePI;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getCode_postale() {
		return code_postale;
	}
	public void setCode_postale(String code_postale) {
		this.code_postale = code_postale;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	
	
	
}