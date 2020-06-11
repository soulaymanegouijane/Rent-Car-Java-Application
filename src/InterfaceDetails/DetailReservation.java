package InterfaceDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Contrat.AjouterUnContrat;
import Entities.Contrat;
import Entities.Reservation;
import Test.H;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.reservationWindow.ChoisirClientScene;
import windowsSwitcher.reservationWindow.ChoisirVehiculeScene;

public class DetailReservation implements Initializable {
	
	@FXML
    public JFXButton closeButton;

    @FXML
    public TextField idReservation;

    @FXML
    public DatePicker dateReservation;

    @FXML
    public TextField vehiculeTextField;

    @FXML
    public JFXButton choisirVehiculeBtn;

    @FXML
    public TextField client;

    @FXML
    public JFXButton choisirClientBtn;

    @FXML
    public DatePicker dateDepartDatePicker;

    @FXML
    public DatePicker dateRetourDatePicker;

    @FXML
    public TextField avance;

    @FXML
    public JFXComboBox<String> typeRservation;

    @FXML
    public TextField etatReservation;

    @FXML
    public JFXButton createContratBtn;

    @FXML
    public JFXButton deleteReservationBtn;
    
    @FXML
    public  JFXButton EditReservationBtn ;
    public StackPane primaryStackPane;
    public HBox nonEditHBox;
    public VBox TerminerResevationVBox;
    public JFXButton TerminerReservationBtn;
    public VBox ContratFactureVBox;
    public JFXButton AccederContratBtn;
    public JFXButton AccederFactureBtn;
    public VBox StatutVBox;
    public JFXButton AnnulerReservationBtn;
    public VBox EditVBox;
    public JFXButton saveEditsBtn;
    public JFXButton annulerEditsBtn;
    public TextField idUtilisateur;
    public TextField nombreJoursTextField;
    public TextField montantReservationTextField;

    ObservableList<String> TypeRes = FXCollections.observableArrayList();

    public static Reservation reservation = new Reservation();
    public static Contrat contrat = new Contrat();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	getTypeReservationn_BaseDonnee();
		comboBox();
        H.setfrenchDatePicker(dateReservation);
        H.setfrenchDatePicker(dateDepartDatePicker);
        H.setfrenchDatePicker(dateRetourDatePicker);
        visibiliteBoxByStatus(reservation.getStatus().getIdStatus());
        fillBlanks();

        nombreJoursTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            LocalDate dateDepat = null;
            LocalDate dateRetour = null;
            Double mantant = 0.0;
            if (!nombreJoursTextField.getText().isEmpty()){
                mantant = Long.parseLong(newValue) * 299.99 /*Double.parseDouble(reservation.getVehicule().getPrixJour())*/;
                montantReservationTextField.setText(String.valueOf(mantant));
                if (dateDepartDatePicker.getValue() != null){
                    dateDepat = H.convert(dateDepartDatePicker.getEditor().getText());
                    dateRetour = dateDepat.plusDays(Long.parseLong(newValue));
                    dateRetourDatePicker.setValue(dateRetour);
                }else {
                    dateRetourDatePicker.setValue(null);
                }
            }else{
                montantReservationTextField.setText(null);
                dateRetourDatePicker.setValue(null);
            }
        });

        vehiculeTextField.textProperty().addListener((observable, oldValue, newValue) -> {

        });

        dateDepartDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(!nombreJoursTextField.getText().isEmpty())
                dateRetourDatePicker.setValue(newValue.plusDays(Long.parseLong(nombreJoursTextField.getText())));
            else
                dateRetourDatePicker.setValue(null);
        });
	}
    
    private void comboBox() {
		typeRservation.setItems(TypeRes);
	}

	public void fillBlanks(){
        idReservation.setText(String.valueOf(reservation.getIdReservation()));
        dateReservation.setValue(H.convert(reservation.getDatReservation()));
        //idUtilisateur.setText(reservation.getUtilisateur().getIdUtilisateur());
        vehiculeTextField.setText(reservation.getVehicule().getIdVehicule());
        client.setText(reservation.getClient().getIdClient());
        dateDepartDatePicker.setValue(H.convert(reservation.getDate_depart()));
        //nombreJoursTextField.setText(reservation.getNombreJours());
        dateRetourDatePicker.setValue(H.convert(reservation.getDate_retour()));
        //montantReservationTextField.setText(reservation.getMontant());
        avance.setText(String.valueOf(reservation.getAvance()));
        typeRservation.setValue(reservation.getTypeRes().getLibelle());
        etatReservation.setText(reservation.getStatus().getLibelle());
    }

    public void rewriteReservationInfos (){
        reservation.setIdReservation(Long.parseLong(idReservation.getText()));
        reservation.setDatReservation(((TextField)dateReservation.getEditor()).getText());
        //reservation.setUtilisateur(H.utilisateur.getById(idUtilisateur.getText()));
        reservation.getVehicule().setIdVehicule(vehiculeTextField.getText());
        reservation.getClient().setIdClient(client.getText());
        reservation.setDate_depart(((TextField)dateDepartDatePicker.getEditor()).getText());
        //reservation.setNombreJours(Long.parseLong(nombreJoursTextField.getText()));
        reservation.setDate_retour(((TextField)dateRetourDatePicker.getEditor()).getText());
        //reservation.setMontant(Double.parseDouble(montantReservationTextField.getText()));
        reservation.setAvance(Float.parseFloat(avance.getText()));
        reservation.getTypeRes().setLibelle(typeRservation.getValue());
        reservation.getStatus().setLibelle(etatReservation.getText());
    }

	@FXML
    void choisirClientBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/reservationWindow/choisirClientScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirClientScene choisirClient = loader.getController();
		if(!choisirClient.cinClientSelected.isEmpty()) {
			client.setText(choisirClient.cinClientSelected);
		}
    }
    
    public void getTypeReservationn_BaseDonnee() {
    	Connection con = Abst.getConnection();
    	String sql = "select libelle from type_reservation";
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String libelle = rs.getString("libelle");
				TypeRes.add(libelle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void choisirVehiculeBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/reservationWindow/choisirVehiculeScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirVehiculeScene choisirVehicule = loader.getController();
		if(!choisirVehicule.matriculeVehicule.isEmpty()) {
            vehiculeTextField.setText(choisirVehicule.matriculeVehicule);
		}
    }

    @FXML
    void closeButtonAction(ActionEvent event) {
    	Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void handleEditReservationBtn(ActionEvent actionEvent) {
        enableFields();
        
        EditVBox.setVisible(true);
        nonEditHBox.setVisible(false);
        TerminerResevationVBox.setVisible(false);
        ContratFactureVBox.setVisible(false);
        StatutVBox.setVisible(false);
    }

    public void handleAnnulerEditsBtn(ActionEvent actionEvent) {
        disableFields();
        fillBlanks();
        visibiliteBoxByStatus(reservation.getStatus().getIdStatus());

        EditVBox.setVisible(false);
        nonEditHBox.setVisible(true);
    }

    public void handleSaveEditsBtn(ActionEvent actionEvent) {

        // If everything is OK
        disableFields();
        rewriteReservationInfos();
        try {
        	Reservation result = H.reservation.edit(reservation);
        	if(result == null) {
        		// alert Exception 
        		System.out.println("n'est ajouter");
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    public void handleAnnulerReservationBtn(ActionEvent actionEvent) {
        reservation.getStatus().setIdStatus(4);
        fillBlanks();
        visibiliteBoxByStatus(4);
    }

    public void handlecreateContratBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Contrat/AjouterUnContrat.fxml"));

        AjouterUnContrat.reservation = reservation;
        Parent root = loader.load();

        AjouterUnContrat ajouterUnContrat = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        contrat = ajouterUnContrat.contrat;

        reservation.setDate_depart(contrat.getDate_sortie());
        //reservation.setNombreJours(String.valueOf(contrat.getNbr_jour()));
        nombreJoursTextField.setText(String.valueOf(contrat.getNbr_jour())); //For testing
        reservation.setDate_retour(contrat.getDate_retour());
        //reservation.setMontant(Double.parseDouble(String.valueOf(contrat.getMontantTotal()));
        montantReservationTextField.setText(String.valueOf(contrat.getMontantTotal()));//For testing
        reservation.setAvance(contrat.getRemise());

        fillBlanks();

        if(ajouterUnContrat.addwithSucces){
            visibiliteBoxByStatus(2);
            fillBlanks();
        }
    }

    public void handleTerminerReservationBtn(ActionEvent actionEvent) {
        visibiliteBoxByStatus(3);
        fillBlanks();
        // ajouter la disponibilite la vehicule
        EditReservationBtn.setVisible(false);
    }

    public void handleAccederContratBtn(ActionEvent actionEvent) throws IOException {
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("detailContrat.fxml"));
         Parent root = loader.load();

         //AjouterUnContrat ajouterUnContrat = loader.getController();
          

         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.setResizable(false);
         stage.initStyle(StageStyle.UNDECORATED);
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.showAndWait();
    	
    }

    public void handleAccederFactureBtn(ActionEvent actionEvent) {
    }

    public void enableFields(){
        if (reservation.getStatus().getIdStatus() == 1){
            choisirVehiculeBtn.setVisible(true);
            choisirClientBtn.setVisible(true);
            nombreJoursTextField.setEditable(false);
            avance.setEditable(true);
            typeRservation.setDisable(false);
        } else if(reservation.getStatus().getIdStatus() == 2){
            choisirVehiculeBtn.setVisible(false);
            choisirClientBtn.setVisible(false);
            nombreJoursTextField.setEditable(true);
            avance.setEditable(true);
            typeRservation.setDisable(false);
        } else if (reservation.getStatus().getIdStatus() == 3 || reservation.getStatus().getIdStatus() == 4){
            enableFields();
            EditReservationBtn.setVisible(false);
        }
    }

    public void disableFields(){
        choisirVehiculeBtn.setVisible(false);
        choisirClientBtn.setVisible(false);
        nombreJoursTextField.setEditable(false);
        avance.setEditable(false);
        typeRservation.setDisable(true);
    }

    public void visibiliteBoxByStatus(long idStatus){

        reservation.setStatus(H.status.getById(idStatus));
        H.reservation.edit(reservation);

        if (idStatus == 1){ // en cours
            StatutVBox.setVisible(true);
            ContratFactureVBox.setVisible(false);
            TerminerResevationVBox.setVisible(false);
        }else if (idStatus == 2){ // Active
            StatutVBox.setVisible(false);
            ContratFactureVBox.setVisible(true);
            AccederFactureBtn.setVisible(false);
            TerminerResevationVBox.setVisible(true);
        }else if (idStatus == 3){ // Terminer
            StatutVBox.setVisible(false);
            ContratFactureVBox.setVisible(true);
            AccederFactureBtn.setVisible(true);
            TerminerResevationVBox.setVisible(false);
        }else if (idStatus == 4){ // Annuler
            StatutVBox.setVisible(false);
            ContratFactureVBox.setVisible(false);
            TerminerResevationVBox.setVisible(false);
        }
    }
}
