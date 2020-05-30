package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailContrat {
    @FXML
    private TextField idContrat; // id du contrat
    @FXML
    private DatePicker datecontratDatePicker; //date d'enregistrement du contrat
    @FXML
    private TextField vehicule; // Voiture concerné par le contrat
    @FXML
    private TextField reservation; // la reservation concerné par le contrat
    @FXML
    private DatePicker datedepartDatePicker; // date depart du voiture
    @FXML
    private DatePicker dateretourDatePicker; // date retour de la voiture
    @FXML
    private TextField remise; // text field de remise ;
    @FXML
    private TextField kmDepart; // kilométrage du depart de la voiture
    @FXML
    private TextField montantTotal;// Montant Total du contrat
    @FXML
    private TextField sanction;// Montant des sanction du contrat
    @FXML
    private JFXButton closeButton;// OK button

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}