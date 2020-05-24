package windowsSwitcher.reservationWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Reservation;
import Entities.Status;
import Entities.Utilisateur;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservationWindow implements Initializable {
    public HBox hBoxChoisirVehicule;
    public TextField textFeildChoisirVehicule;
    public JFXButton buttonChoisirVehicule;
    @FXML
	    private ComboBox<String> chercherComboBox;

	    @FXML
	    private AnchorPane inputSearchArea;

	    @FXML
	    private TextField idTextFeild;

	    @FXML
	    private ComboBox<String> typeComboBox;

	    @FXML
	    private DatePicker dateDatePicker;

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
	    private ComboBox<String> statutComboBox;

	    @FXML
	    private TextField nbrContratTextFeild;

	    @FXML
	    private Button chercherButton;

	    @FXML
	    private Label ErreurMessage;

	    @FXML
	    private TableView<Reservation> tableReservation;

	    @FXML
	    private TableColumn<Reservation,Long> col_id;

	    @FXML
	    private TableColumn<Reservation,String> col_type;

	    @FXML
	    private TableColumn<Reservation,String> col_date_depart;

	    @FXML
	    private TableColumn<Reservation,String> col_date_retour;

	    @FXML
	    private TableColumn<Reservation,Long> col_id_client;

	    @FXML
	    private TableColumn<Reservation,Long> col_id_utilisateur;

	    @FXML
	    private TableColumn<Reservation,String> col_status;

	    @FXML
	    private TableColumn<Reservation,Long> col_nbr_contrats;

	    @FXML
	    private Button ajouterReservationButton;

	    @FXML
        private Button detailReservationButton;
        
	

    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "Id", "Type", "Date", "Véhicule", "Client", "Utilisateur", "Status", "Nombres des contrats");
    ObservableList<String> typeTypeList = FXCollections.observableArrayList("Réservation local", "Réservation téléphonique");
    ObservableList<String> statutTypeList = FXCollections.observableArrayList("En cours", "Complété", "Annulé");

    ObservableList<Reservation> reservation_list = FXCollections.observableArrayList();
    ObservableList<Reservation> mono_reservation = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox();
        remplir_tableau();
    }

    public void comboBox(){
        chercherComboBox.setValue("Chercher par:");
        chercherComboBox.setItems(searchTypeList);
        typeComboBox.setValue("Select :");
        typeComboBox.setItems(typeTypeList);
        statutComboBox.setValue("Select :");
        statutComboBox.setItems(statutTypeList);
    }

    public void remplir_tableau(){
        ResultSet rs = null;
		try {
			String sql = "select * from reservation";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setIdReservation(rs.getLong("idReservation"));
				//res.setDatReservation(rs.getString("dateReservation"));
				res.setDate_depart(rs.getString("date_depart"));
				res.setDate_retour(rs.getString("date_retour"));
				res.setCinClient(H.reservation.getCinClient(rs.getLong("idReservation")));
				res.setCinUtilisateur(H.reservation.getCinUtilisateur(rs.getLong("idReservation")));
				Status s = H.status.getById(H.reservation.getStatus(rs.getLong("idStatus")));
				res.setStatusRes(s.getLibelle());
				res.setTypeVehicule(H.type.getById(H.vehicule.getType(rs.getString("idVehicule"))).getLibelle());
//				res.setVehicule(H.vehicule.getById(rs.getString("matricule")));
//				res.setClient(H.client.getById(rs.getLong("idClient")));
//				res.setStatus(H.status.getById(rs.getLong("idStatus")));
//				res.setTypeRes(H.typeres.getById(rs.getLong("idTypeRes")));
				reservation_list.add(res);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
        col_date_depart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        col_date_retour.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
        col_id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("statusRes"));
        
        tableReservation.setItems(reservation_list);
    }

    public void handleAjouterReservationButton(ActionEvent actionEvent) {
    }

    public void handleDetailReservationButton(ActionEvent actionEvent) {
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

    public void handleChercherComboBox(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Id")){

            enable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Type")){

            disable(idTextFeild);
            enable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Date")){

            disable(idTextFeild);
            disable(typeComboBox);
            enable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Véhicule")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            enable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Client")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            enable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Utilisateur")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            enable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Status")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            enable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Nombres des contrats")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            enable(nbrContratTextFeild);

        }
    }

    public void handleChercherButton(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);

        if(searchSection.equals("Id")){
            String idTaped = idTextFeild.getText();

            if(idTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by id
            }

        }else if(searchSection.equals("Type")){

            // Search résérvation by Type

        }else if(searchSection.equals("Date")){
            LocalDate dateTaped = dateDatePicker.getValue();

            // Search résérvation by date

        }else if(searchSection.equals("Véhicule")){
            String vehiculeTaped = textFeildChoisirVehicule.getText();

            if(vehiculeTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            }

        }else if(searchSection.equals("Client")){
            String clientTaped = textFeildChoisirClient.getText();

            if(clientTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by client
            }

        }else if(searchSection.equals("Utilisateur")){
            String utilisateurTaped = textFeildChoisirUtilisateur.getText();

            if(utilisateurTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by utilisateur
            }

        }else if(searchSection.equals("Status")){
            String statutTaped = statutComboBox.getValue();

            // Search résérvation by statut

        }else if(searchSection.equals("Nombres des contrats")){
            try {
                int nbrContratTaped = Integer.parseInt(nbrContratTextFeild.getText());
                // Search résérvation by client

            }catch (NumberFormatException e){
                enable(ErreurMessage);
            }

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
}
