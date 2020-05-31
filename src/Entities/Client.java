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
	@Column(name="date_naissance",nullable=true)
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
	//private List<Reservation> listReservation = new ArrayList<>();
	/**
	 * @return the idClient
	 */
	public String getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
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
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
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
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the date_naissance
	 */
	public String getDate_naissance() {
		return date_naissance;
	}
	/**
	 * @param date_naissance the date_naissance to set
	 */
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
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
	 * @return the n_permis
	 */
	public String getN_permis() {
		return n_permis;
	}
	/**
	 * @param n_permis the n_permis to set
	 */
	public void setN_permis(String n_permis) {
		this.n_permis = n_permis;
	}
	/**
	 * @return the delevre_le
	 */
	public String getDelevre_le() {
		return delevre_le;
	}
	/**
	 * @param delevre_le the delevre_le to set
	 */
	public void setDelevre_le(String delevre_le) {
		this.delevre_le = delevre_le;
	}
	/**
	 * @return the validitePermis
	 */
	public String getValiditePermis() {
		return validitePermis;
	}
	/**
	 * @param validitePermis the validitePermis to set
	 */
	public void setValiditePermis(String validitePermis) {
		this.validitePermis = validitePermis;
	}
	/**
	 * @return the delevre_a
	 */
	public String getDelevre_a() {
		return delevre_a;
	}
	/**
	 * @param delevre_a the delevre_a to set
	 */
	public void setDelevre_a(String delevre_a) {
		this.delevre_a = delevre_a;
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
	 * @return the num_carte
	 */
	public String getNum_carte() {
		return num_carte;
	}
	/**
	 * @param num_carte the num_carte to set
	 */
	public void setNum_carte(String num_carte) {
		this.num_carte = num_carte;
	}
	/**
	 * @return the validitePI
	 */
	public String getValiditePI() {
		return validitePI;
	}
	/**
	 * @param validitePI the validitePI to set
	 */
	public void setValiditePI(String validitePI) {
		this.validitePI = validitePI;
	}
	/**
	 * @return the listReservation
	 */
//	public List<Reservation> getListReservation() {
//		return listReservation;
//	}
//	/**
//	 * @param listReservation the listReservation to set
//	 */
//	public void setListReservation(List<Reservation> listReservation) {
//		this.listReservation = listReservation;
//	}
	
	
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
	public Client(String idClient, int age, String nom, String prenom, String adress, String telephone, String email,
			String civilite, String date_naissance, String lieu_naissance, String n_permis, String delevre_le,
			String validitePermis, String delevre_a, String carte_identifiant, String num_carte, String validitePI,
			String ville, String pays, String code_postale, String nationalite) {
		super();
		this.idClient = idClient;
		this.age = age;
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.civilite = civilite;
		this.date_naissance = date_naissance;
		this.lieu_naissance = lieu_naissance;
		this.n_permis = n_permis;
		this.delevre_le = delevre_le;
		this.validitePermis = validitePermis;
		this.delevre_a = delevre_a;
		this.carte_identifiant = carte_identifiant;
		this.num_carte = num_carte;
		this.validitePI = validitePI;
		this.ville = ville;
		this.pays = pays;
		this.code_postale = code_postale;
		this.nationalite = nationalite;
	}
	
	public Client() {
		super();
	}
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", age=" + age + ", nom=" + nom + ", prenom=" + prenom + ", adress="
				+ adress + ", telephone=" + telephone + ", email=" + email + ", civilite=" + civilite
				+ ", date_naissance=" + date_naissance + ", lieu_naissance=" + lieu_naissance + ", n_permis=" + n_permis
				+ ", delevre_le=" + delevre_le + ", validitePermis=" + validitePermis + ", delevre_a=" + delevre_a
				+ ", carte_identifiant=" + carte_identifiant + ", num_carte=" + num_carte + ", validitePI=" + validitePI
				+ ", ville=" + ville + ", pays=" + pays + ", code_postale=" + code_postale + ", nationalite="
				+ nationalite + "]";
	}
	
	
	
	
}
