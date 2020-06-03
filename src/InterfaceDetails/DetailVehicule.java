package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailVehicule {
    @FXML
    public TextField idmatricule; // Matricule du vehicule
    @FXML
    public TextField nombrePlaceTextField; // nombre de place dans le vehicule
    @FXML
    public TextField TypeCarburant; //type de carburant
    @FXML
    public TextField marqueVoiture; // marque de vehicule
    @FXML
    public TextField idcolor; // la couleur du vehicule
    @FXML
    public TextField idParking; // Le parking ou le véhicule est garé
    @FXML
    public TextField dispoVehicule; //la disponibilité du Véhicule
    @FXML
    public JFXButton closeButton;// OK boutton
    @FXML
    public JFXButton deleteBtn; //supprimer la Véhicule
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    //deleteBtnAction()
}
