package windowsSwitcher.utilisateurWindow;

import com.jfoenix.controls.JFXComboBox;


import com.jfoenix.controls.JFXTextField;

import AbstactClasses.Abst;
import Entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UtilisateurWindow implements Initializable {
	@FXML
    private ComboBox<String> chercherComboBox;

    @FXML
    private AnchorPane inputSearchArea;

    @FXML
    private TextField dataTextFeild;

    @FXML
    private ComboBox<String> compteComboBox;

    @FXML
    private Button chercherButton;

    @FXML
    private Label ErreurMessage;

    @FXML
    private TableView<Utilisateur> tableauUtilisateurs;

    @FXML
    private TableColumn<Utilisateur,String> col_cin;

    @FXML
    private TableColumn<Utilisateur,String> col_nom;

    @FXML
    private TableColumn<Utilisateur,String> col_prenom;

    @FXML
    private TableColumn<Utilisateur,String> col_date_naissance;

    @FXML
    private TableColumn<Utilisateur,String> col_tel;

    @FXML
    private TableColumn<Utilisateur,String> col_email;

    @FXML
    private TableColumn<Utilisateur,String> col_adresse;

    @FXML
    private TableColumn<Utilisateur,String> col_etat_compte;

    @FXML
    private Button ajouterUtilisateurButton;

    @FXML
    private Button detailUtilisateurButton;

    String searchSection = null;
    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "CIN", "Nom", "Prenom", "Etat de compte");
    ObservableList<String> etatCompteList = FXCollections.observableArrayList("Activer", "Disactiver");
    
    ObservableList<Utilisateur> utilisateur_list = FXCollections.observableArrayList();
    ObservableList<Utilisateur> mono_Utilisateur = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	comboBox();
    	remplir_tableau();
    }
    
    public void comboBox() {
    	chercherComboBox.setPromptText("Chercher par:");
        chercherComboBox.setItems(searchTypeList);
        compteComboBox.setValue("Activer");
        compteComboBox.setItems(etatCompteList);
    }

    public void handleAjouterUtilisateurButton(ActionEvent actionEvent) {
    }

    public void handleDetailUtilisateurButton(ActionEvent actionEvent) {
    }

    public void handleChercherComboBox(ActionEvent actionEvent) {
        searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(dataTextFeild);
            disable(compteComboBox);

        }else if(searchSection.equals("CIN") || searchSection.equals("Nom") || searchSection.equals("Prenom")){

            enable(dataTextFeild);
            disable(compteComboBox);

        }else if(searchSection.equals("Etat de compte")){

            disable(dataTextFeild);
            enable(compteComboBox);

        }
    }

    public void handleChercherButton(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);

        if(searchSection.equals("CIN")){
            String CINTaped = dataTextFeild.getText();
            utilisateur_list.clear();
            mono_Utilisateur.clear();
            if(CINTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search User by CIN
            	afficher_utilisateur(CINTaped);
            }

        }else if(searchSection.equals("Nom")){
            String NomTaped = dataTextFeild.getText();
            utilisateur_list.clear();
            mono_Utilisateur.clear();

            if(NomTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search User by Nam
            	afficher_utilisateur(NomTaped);
            }

        }else if(searchSection.equals("Prenom")){
            String PrenomTaped = dataTextFeild.getText();
            utilisateur_list.clear();
            mono_Utilisateur.clear();

            if(PrenomTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search User by prenom
            	afficher_utilisateur(PrenomTaped);
            }

        }else if(searchSection.equals("Etat de compte")){
            String EtatTaped = compteComboBox.getValue();
            utilisateur_list.clear();
            mono_Utilisateur.clear();
            if(EtatTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	// Search User by Etat de compte
                afficher_utilisateur(EtatTaped);
            }
            
        }else {
        	remplir_tableau();
        }
    }

    public void disable(TextField TF){
        TF.setVisible(false);
        TF.setDisable(true);
    }

    public void enable(TextField TF){
        TF.setVisible(true);
        TF.setDisable(false);
    }

    public void disable(ComboBox<String> CB){
        CB.setVisible(false);
        CB.setDisable(true);
    }

    public void enable(ComboBox<String> CB){
        CB.setVisible(true);
        CB.setDisable(false);
    }

    public void disable(Label label){
        label.setVisible(false);
        label.setDisable(true);
    }

    public void enable(Label label){
        label.setVisible(true);
        label.setDisable(false);
    }
    
    public void remplir_tableau(){
        ResultSet tous_les_utilisateur = null;
		try {
			String sql = "select * from utilisateur";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_utilisateur = ps.executeQuery();
			
			while(tous_les_utilisateur.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(tous_les_utilisateur.getString("idUtilisateur"));
				utilisateur.setNom(tous_les_utilisateur.getString("nom"));
				utilisateur.setPrenom(tous_les_utilisateur.getString("prenom"));
				utilisateur.setAdress(tous_les_utilisateur.getString("adress"));
				utilisateur.setTele(tous_les_utilisateur.getString("telephone"));
				utilisateur.setEmail(tous_les_utilisateur.getString("email"));
				utilisateur.setNaissance(tous_les_utilisateur.getString("naissance"));
				utilisateur.setEtat_compte(tous_les_utilisateur.getString("etat_compte"));
				utilisateur_list.add(utilisateur);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        col_cin.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
        col_date_naissance.setCellValueFactory(new PropertyValueFactory<>("naissance"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tele"));
        col_etat_compte.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));
        
        tableauUtilisateurs.setItems(utilisateur_list);
	}

    public void afficher_utilisateur(String valeur) {
    	Utilisateur utilisateur = new Utilisateur();
    	ResultSet tous_les_utilisateur = null;
    	String sql = "";
		try {
			if(searchSection.equals("Prenom")) {
				sql = "select * from utilisateur where prenom=?";
			}
			if(searchSection.equals("Nom")) {
				sql = "select * from utilisateur where nom=?";		
			}
			if(searchSection.equals("CIN")) {
				sql = "select * from utilisateur where idUtilisateur=?";
			}
			if(searchSection.equals("Etat de compte")) {
				sql = "select * from utilisateur where etat_compte=?";
			}
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, valeur);
			tous_les_utilisateur = ps.executeQuery();
			if(tous_les_utilisateur.next()) {
				utilisateur.setIdUtilisateur(tous_les_utilisateur.getString("idUtilisateur"));
				utilisateur.setNom(tous_les_utilisateur.getString("nom"));
				utilisateur.setPrenom(tous_les_utilisateur.getString("prenom"));
				utilisateur.setAdress(tous_les_utilisateur.getString("adresse"));
				utilisateur.setTele(tous_les_utilisateur.getString("telephone"));
				utilisateur.setEmail(tous_les_utilisateur.getString("email"));
				utilisateur.setNaissance(tous_les_utilisateur.getString("date_naissance"));
				utilisateur.setEtat_compte(tous_les_utilisateur.getString("etat_compte"));
				//mono_Utilisateur.add(client);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mono_Utilisateur.add(utilisateur);
		col_cin.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
        col_date_naissance.setCellValueFactory(new PropertyValueFactory<>("naissance"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tele"));
        col_etat_compte.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));
        
        tableauUtilisateurs.setItems(mono_Utilisateur);
    }
}
