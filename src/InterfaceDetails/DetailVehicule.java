package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailVehicule {
    @FXML
    private TextField idmatricule; // Matricule du vehicule
    @FXML
    private TextField nombrePlaceTextField; // nombre de place dans le vehicule
    @FXML
    private TextField TypeCarburant; //type de carburant
    @FXML
    private TextField marqueVoiture; // marque de vehicule
    @FXML
    private TextField idcolor; // la couleur du vehicule
    @FXML
    private TextField idParking; // Le parking ou le véhicule est garé
    @FXML
    private TextField dispoVehicule; //la disponibilité du Véhicule
    @FXML
    private JFXButton closeButton;// OK boutton
    @FXML
    private JFXButton deleteBtn; //supprimer la Véhicule
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    //deleteBtnAction()
}
