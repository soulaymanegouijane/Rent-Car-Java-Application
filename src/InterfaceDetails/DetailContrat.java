package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailContrat {
    @FXML
    public TextField idContrat; // id du contrat
    @FXML
    public DatePicker datecontratDatePicker; //date d'enregistrement du contrat
    @FXML
    public TextField vehicule; // Voiture concerné par le contrat
    @FXML
    public TextField reservation; // la reservation concerné par le contrat
    @FXML
    public DatePicker datedepartDatePicker; // date depart du voiture
    @FXML
    public DatePicker dateretourDatePicker; // date retour de la voiture
    @FXML
    public TextField remise; // text field de remise ;
    @FXML
    public TextField kmDepart; // kilométrage du depart de la voiture
    @FXML
    public TextField montantTotal;// Montant Total du contrat
    @FXML
    private JFXButton closeButton;// OK button
    @FXML
    public JFXButton choisirVehiculeBtn; //parcourrir les vehicules
    @FXML
    public JFXButton choisirReservationBtn;// parcourrir les reservations
    @FXML
    public JFXButton editContratBtn;//Boutton Modifier

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}