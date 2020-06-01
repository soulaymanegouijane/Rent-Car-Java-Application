package windowsSwitcher.contratWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Contrat;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContratWindow implements Initializable {
    
	
    @FXML
    private TextField textFeildChoisirVehicule;

    @FXML
    private JFXButton buttonChoisirVehicule;

    @FXML
    private HBox hBoxChoisirVehicule;
    
    @FXML
    private ComboBox<String> chercherComboBox;

    @FXML
    private AnchorPane inputSearchArea;

    @FXML
    private TextField nContratTextFeild;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private HBox hBoxChoisirReservation;

    @FXML
    private TextField textFeildChoisirReservation;

    @FXML
    private JFXButton buttonChoisirReservation;

    @FXML
    private HBox hBoxChoisirClient;

    @FXML
    public TextField textFeildChoisirClient;

    @FXML
    private JFXButton buttonChoisirClient;

    @FXML
    private HBox hBoxChoisirUtilisateur;

    @FXML
    private TextField textFeildChoisirUtilisateur;

    @FXML
    private JFXButton buttonChoisirUtilisateur;

    @FXML
    private Button chercherButton;

    @FXML
    private Label ErreurMessage;

    @FXML
    private TableView<Contrat> tableContrat;

    @FXML
    private TableColumn<Contrat,Integer> col_nb_contrat;

    @FXML
    private TableColumn<Contrat,String> col_date_contrat;
    
    @FXML
    private TableColumn<Contrat,String> col_vehicule;

    @FXML
    private TableColumn<Contrat,String> col_date_echeance;

    @FXML
    private TableColumn<Contrat,String> col_reservation;

    @FXML
    private TableColumn<Contrat,String> col_cin_client;

    @FXML
    private TableColumn<Contrat,String> col_cin_utilisateur;

    @FXML
    private Button ajouterContratButton;

    @FXML
    private Button detailContratButton;

    String searchSection = null;


    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "Num contrat", "Date", "AjouterReservation", "Vehicule", "Client", "Utilisateur");
    ObservableList<Contrat> contrat_list = FXCollections.observableArrayList();
    ObservableList<Contrat> mono_contrat = FXCollections.observableArrayList();
    ObservableList<Contrat> contrats = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	comboBox();
    	remplir_tableau();
    }
    
    public void comboBox() {
    	chercherComboBox.setPromptText("Chercher par :");
        chercherComboBox.setItems(searchTypeList);
    }
    
    public void handleAjouterContratButton(ActionEvent actionEvent) {
    }

    public void handleDetailContratButton(ActionEvent actionEvent) {
    }

    public void handleChercherComboBox(ActionEvent actionEvent) {
        searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            disable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("Num contrat")){

            enable(nContratTextFeild);
            disable(dateDatePicker);
            disable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("Date")){

            disable(nContratTextFeild);
            enable(dateDatePicker);
            disable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("AjouterReservation")){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            enable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("Vehicule")){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            disable(hBoxChoisirReservation);
            enable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("Client")){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            disable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            enable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("Utilisateur")){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            disable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            enable(hBoxChoisirUtilisateur);

        }
    }

    public void handleChercherButton(ActionEvent actionEvent) {
    	contrat_list.clear();
        mono_contrat.clear();
        String searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);

        if(searchSection.equals("Num contrat")){
            
            String nContratTaped = nContratTextFeild.getText();

            if(nContratTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            	afficher_contrat(nContratTaped);
            }

        }else if(searchSection.equals("Date")){
        	contrat_list.clear();
            mono_contrat.clear();
            String dateTaped = ((TextField)dateDatePicker.getEditor()).getText();

            if(dateTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            	afficher_contrat(dateTaped);
            }

        }else if(searchSection.equals("AjouterReservation")){
        	contrat_list.clear();
            mono_contrat.clear();
            String reservationTaped = textFeildChoisirReservation.getText();

            if(reservationTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            	afficher_contrat(reservationTaped);
            }

        }else if(searchSection.equals("Vehicule")){
        	contrat_list.clear();
            mono_contrat.clear();
            String vehiculeTaped = textFeildChoisirVehicule.getText();

            if(vehiculeTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            	afficher_contrat(vehiculeTaped);
            }

        }else if(searchSection.equals("Client")){
        	contrat_list.clear();
            mono_contrat.clear();
            String clientTaped = textFeildChoisirClient.getText();

            if(clientTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	selectReservations(clientTaped);
            }

        }else if(searchSection.equals("CIN Utilisateur")){
        	contrat_list.clear();
            mono_contrat.clear();
            String utilisateurTaped = textFeildChoisirUtilisateur.getText();

            if(utilisateurTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                long reservation_numero = afficherUser(utilisateurTaped);
                returnContrat(reservation_numero);
            }

        }else {
        	contrat_list.clear();
            mono_contrat.clear();
        	remplir_tableau();
        }

    }

    public void handleButtonChoisirReservation(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirReservationScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirReservationScene choisirReservation = loader.getController();
        textFeildChoisirReservation.setText(choisirReservation.idReservationSelected);
    }

    public void handleButtonChoisirVehicule(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirVehiculeScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirVehiculeScene choisirVehicule = loader.getController();
        textFeildChoisirVehicule.setText(choisirVehicule.matriculeVehicule);
        
    }

    public void handleButtonChoisirClient(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirClientScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirClientScene choisirClient = loader.getController();
        textFeildChoisirClient.setText(choisirClient.cinClientSelected);
    }

    public void handleButtonChoisirUtilisateur(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirUtilisateurScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirUtilisateurScene choisirUser = loader.getController();
        textFeildChoisirUtilisateur.setText(choisirUser.cinUtilisateurSelected);
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

    public void disable(DatePicker DP){
        DP.setVisible(false);
        DP.setDisable(true);
    }

    public void enable(DatePicker DP){
        DP.setVisible(true);
        DP.setDisable(false);
    }

    public void disable(HBox HB){
        HB.setVisible(false);
        HB.setDisable(true);
    }

    public void enable(HBox HB){
        HB.setVisible(true);
        HB.setDisable(false);
    }
    
    public void remplir_tableau() {
    	
		try {
			String sql = "SELECT * FROM contrat";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet tous_les_contrat = ps.executeQuery();
			while(tous_les_contrat.next()) {
				System.out.println("---> bien");
			    Contrat contrat = new Contrat();
			    contrat.setIdContrat(tous_les_contrat.getLong("idContrat"));
			    contrat.setReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")));
			    //contrat.setVehicule(H.vehicule.getById(tous_les_contrat.getString("idVehicule")));
			    contrat.setCinUtilisateur(H.reservation.getCinUtilisateur(tous_les_contrat.getLong("idReservation")));
			    contrat.setCinClient(H.reservation.getCinClient(tous_les_contrat.getLong("idReservation")));
			    contrat.setMatricule(H.vehicule.getById(tous_les_contrat.getString("idVehicule")).getIdVehicule());
			    contrat.setCodeReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")).getIdReservation());
			    contrat.setDate_retour(tous_les_contrat.getString("date_retour"));
			    contrat.setDateContrat(tous_les_contrat.getString("date_Contrat"));
			    contrat_list.add(contrat);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		
 		
 	   col_nb_contrat.setCellValueFactory(new PropertyValueFactory<>("idContrat"));
 	   col_cin_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
 	   col_date_contrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
 	   col_date_echeance.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
 	   col_reservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
 	   col_cin_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
 	  col_vehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
 	
         
 		   tableContrat.setItems(contrat_list);
    }
    
    public void afficher_contrat(String valeur) {
    	searchSection = chercherComboBox.getValue();
    	Contrat contrat = new Contrat();
    	ResultSet tous_les_contrat = null;
    	Connection con = Abst.getConnection();
		try {
	    	String sql = "";
	    	PreparedStatement ps = null;
	    	
			if(searchSection.equals("Vehicule")) {
				sql = "select * from contrat where idVehicule=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("AjouterReservation")) {
				sql = "select * from contrat where idReservation=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.valueOf(valeur));
			}
			if(searchSection.equals("Date")) {
				sql = "select * from contrat where date_contrat=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Num contrat")) {
				sql = "select * from contrat where idContrat=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.valueOf(valeur));
			}
			tous_les_contrat = ps.executeQuery();
			if(tous_les_contrat.next()) {
				contrat.setIdContrat(tous_les_contrat.getLong("idContrat"));
			    contrat.setReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")));
			    //contrat.setVehicule(H.vehicule.getById(tous_les_contrat.getString("idVehicule")));
			    contrat.setCinUtilisateur(H.reservation.getCinUtilisateur(tous_les_contrat.getLong("idReservation")));
			    contrat.setCinClient(H.reservation.getCinClient(tous_les_contrat.getLong("idReservation")));
			    contrat.setMatricule(H.vehicule.getById(tous_les_contrat.getString("idVehicule")).getIdVehicule());
			    contrat.setCodeReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")).getIdReservation());
			    contrat.setDate_retour(tous_les_contrat.getString("date_retour"));
			    contrat.setDateContrat(tous_les_contrat.getString("date_Contrat"));
				mono_contrat.add(contrat);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   col_nb_contrat.setCellValueFactory(new PropertyValueFactory<>("idContrat"));
	 	   col_cin_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
	 	   col_date_contrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
	 	   col_date_echeance.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
	 	   col_reservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
	 	   col_cin_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
		   col_vehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
	   
    
	    tableContrat.setItems(mono_contrat);
    }
    
    public void selectReservations(String idClient) {
		Connection con = Abst.getConnection();
		String sql = "select * from reservation where idClient =?";
		long idReservation = 0;
		Contrat contrat= null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idClient);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				idReservation = rs.getLong("idReservation");
				contrat = selectContrats(idReservation);
				mono_contrat.add(contrat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   col_nb_contrat.setCellValueFactory(new PropertyValueFactory<>("idContrat"));
	 	   col_cin_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
	 	   col_date_contrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
	 	   col_date_echeance.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
	 	   col_reservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
	 	   col_cin_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
		   col_vehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
	   
 
	       tableContrat.setItems(mono_contrat);
	}
	
    public Contrat selectContrats(long idReservation) {
		Connection con = Abst.getConnection();
		String sql = "select * from contrat where idReservation =?";
		Contrat contrat = new Contrat();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, idReservation);
			ResultSet tous_les_contrat = ps.executeQuery();
			while(tous_les_contrat.next()) {
				contrat.setIdContrat(tous_les_contrat.getLong("idContrat"));
			    contrat.setReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")));
			    contrat.setCinUtilisateur(H.reservation.getCinUtilisateur(tous_les_contrat.getLong("idReservation")));
			    contrat.setCinClient(H.reservation.getCinClient(tous_les_contrat.getLong("idReservation")));
			    contrat.setMatricule(H.vehicule.getById(tous_les_contrat.getString("idVehicule")).getIdVehicule());
			    contrat.setCodeReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")).getIdReservation());
			    contrat.setDate_retour(tous_les_contrat.getString("date_retour"));
			    contrat.setDateContrat(tous_les_contrat.getString("date_Contrat"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contrat;
	}

	public void returnContrat(long idReservation) {
		Connection con = Abst.getConnection();
		String sql = "select * from contrat where idReservation = ?";
		Contrat contrat = new Contrat();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, idReservation);
			ResultSet tous_les_contrat = ps.executeQuery();
			if(tous_les_contrat.next()) {
				contrat.setIdContrat(tous_les_contrat.getLong("idContrat"));
			    contrat.setReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")));
			    contrat.setCinUtilisateur(H.reservation.getCinUtilisateur(tous_les_contrat.getLong("idReservation")));
			    contrat.setCinClient(H.reservation.getCinClient(tous_les_contrat.getLong("idReservation")));
			    contrat.setMatricule(H.vehicule.getById(tous_les_contrat.getString("idVehicule")).getIdVehicule());
			    contrat.setCodeReservation(H.reservation.getById(tous_les_contrat.getLong("idReservation")).getIdReservation());
			    contrat.setDate_retour(tous_les_contrat.getString("date_retour"));
			    contrat.setDateContrat(tous_les_contrat.getString("date_Contrat"));
			    mono_contrat.add(contrat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		   col_nb_contrat.setCellValueFactory(new PropertyValueFactory<>("idContrat"));
	 	   col_cin_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
	 	   col_date_contrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
	 	   col_date_echeance.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
	 	   col_reservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
	 	   col_cin_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
		   col_vehicule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
	   
 
		   tableContrat.setItems(mono_contrat);
	}
	
	public long afficherUser(String idUtilisateur) {
		Connection con = Abst.getConnection();
		String sql = "select idReservation from Utilisateur where idUtilisateur = ?";
		long idReservation = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idUtilisateur);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				idReservation = rs.getLong("idReservation");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idReservation;
	}
	
	
}
