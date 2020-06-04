package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailReservation {
    @FXML
    public TextField idReservation; // Id de la reservation
    @FXML
    public DatePicker dateReservation; // date de la réservation
    @FXML
    public TextField vehicule; //Véhicule Concerné par la réservation
    @FXML
    public TextField client; // Client Concerné par la reservation
    @FXML
    public DatePicker dateDepartDatePicker ; //Date départ de la voiture
    @FXML
    public DatePicker dateRetourDatePicker; // Date retour de la voiture
    @FXML
    public TextField avance; // Avance Payé par le client
    @FXML
    public TextField typeRservation; // type de reservation (telephonique ou par presence)
    @FXML
    public TextField etatReservation; //etat de la reservation (validé ou annulé)
    @FXML
    public JFXButton closeButton;// OK boutton
    @FXML
    public JFXButton createContratBtn ; // boutton leading to interface ajouter contrat
    @FXML
    public JFXButton  deleteReservationBtn; //supprimer la reservation
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
