package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailGarage {
    @FXML
    public TextField idparkingTextField ; //champ de Réference Parking
    @FXML
    public TextField adressparkingTextField ; // champ de l'adresse du parking
    @FXML
    public TextField capaciteParkingTextField ; // champ de Capacité du Parking
    @FXML
    public TextField occupeParkingTextField; // champ du nombre des emplacement occuppé
    @FXML
    public JFXButton closeButton; // boutton OK
    @FXML
    public  JFXButton deletebtn ; // boutton supprimer
    @FXML
    public JFXButton editParkingBtn ; // Modifier boutton
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //deleteButtonAction
    // ModifierBtn

}

