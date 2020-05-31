package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailReservation {
    @FXML
    private TextField idReservation; // Id de la reservation
    @FXML
    private DatePicker dateReservation; // date de la réservation
    @FXML
    private TextField vehicule; //Véhicule Concerné par la réservation
    @FXML
    private TextField client; // Client Concerné par la reservation
    @FXML
    private DatePicker dateDepartDatePicker ; //Date départ de la voiture
    @FXML
    private DatePicker dateRetourDatePicker; // Date retour de la voiture
    @FXML
    private TextField avance; // Avance Payé par le client
    @FXML
    private TextField typeRservation; // type de reservation (telephonique ou par presence)
    @FXML
    private TextField etatReservation; //etat de la reservation (validé ou annulé)
    @FXML
    private JFXButton closeButton;// OK boutton
    @FXML
    private JFXButton createContratBtn ; // boutton leading to interface ajouter contrat
    @FXML
    private JFXButton  deleteReservationBtn; //supprimer la reservation
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
