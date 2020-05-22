package windowsSwitcher.contratWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Contrat;
import Entities.Parking;
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
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ContratWindow implements Initializable {

    public HBox hBoxChoisirVehicule;
    public TextField textFeildChoisirVehicule;
    public JFXButton buttonChoisirVehicule;
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
    private TextField textFeildChoisirClient;

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



    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "N° contrat", "Date", "Résérvation", "Véhicule", "Client", "Utilisateur");
    ObservableList<Contrat> contrat_list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	comboBox();
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
        String searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            disable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("N° contrat")){

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

        }else if(searchSection.equals("Résérvation")){

            disable(nContratTextFeild);
            disable(dateDatePicker);
            enable(hBoxChoisirReservation);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);

        }else if(searchSection.equals("Véhicule")){

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
        String searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);

        if(searchSection.equals("N° contrat")){

            try {
                int nContratTaped = Integer.parseInt(nContratTextFeild.getText());
                // Search contrat by N° contrat

            }catch (NumberFormatException e){
                enable(ErreurMessage);
            }

        }else if(searchSection.equals("Date")){

            try {
                LocalDate dateTaped = dateDatePicker.getValue();
            }catch (DateTimeException e){
                enable(ErreurMessage);
            }

        }else if(searchSection.equals("Résérvation")){
            String reservationTaped = textFeildChoisirReservation.getText();

            if(reservationTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            }

        }else if(searchSection.equals("Véhicule")){
            String vehiculeTaped = textFeildChoisirVehicule.getText();

            if(vehiculeTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            }

        }else if(searchSection.equals("CIN Client")){
            String clientTaped = textFeildChoisirClient.getText();

            if(clientTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            }

        }else if(searchSection.equals("CIN Utilisateur")){
            String utilisateurTaped = textFeildChoisirUtilisateur.getText();

            if(utilisateurTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            }

        }

    }

    public void handleButtonChoisirReservation(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("choisirReservationScene.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void handleButtonChoisirVehicule(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("choisirVehiculeScene.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void handleButtonChoisirClient(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("choisirClientScene.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


    public void handleButtonChoisirUtilisateur(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("choisirUtilisateurScene.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
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
    	ResultSet tous_les_contrat = null;
 		try {
 			String sql = "select * from contrat";
 			Connection con = Abst.getConnection();
 			PreparedStatement ps = con.prepareStatement(sql);
 			tous_les_contrat = ps.executeQuery();
 			
 			while(tous_les_contrat.next()) {
 				Contrat parking = new Contrat();
// 				parking.setIdParking(tous_les_parking.getLong(""));
//				parking.setAdress(tous_les_parking.getString(""));
//				parking.setCapacite(tous_les_parking.getLong(""));
//				parking.setNbr_place_pleinne(tous_les_parking.getInt(""));
 				contrat_list.add(parking);
 			}
 			con.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		
 		
 		
 	   col_nb_contrat.setCellValueFactory(new PropertyValueFactory<>(""));
 	   col_cin_client.setCellValueFactory(new PropertyValueFactory<>(""));
 	   col_date_contrat.setCellValueFactory(new PropertyValueFactory<>(""));
 	   col_date_echeance.setCellValueFactory(new PropertyValueFactory<>(""));
 	   col_reservation.setCellValueFactory(new PropertyValueFactory<>(""));
 	   col_cin_utilisateur.setCellValueFactory(new PropertyValueFactory<>(""));
 		    
 	
         
 		   tableContrat.setItems(contrat_list);
    }
}
