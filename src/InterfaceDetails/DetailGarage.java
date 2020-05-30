package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailGarage {
    @FXML
    private TextField idparkingTextField ; //champ de Réference Parking
    @FXML
    private TextField adressparkingTextField ; // champ de l'adresse du parking
    @FXML
    private TextField capaciteParkingTextField ; // champ de Capacité du Parking
    @FXML
    private TextField occupeParkingTextField; // champ du nombre des emplacement occuppé
    @FXML
    private TextField idUtilisateurTextField ;// champ d'utilisateur qui a enreegistré le park
    @FXML
    private JFXButton closeButton; // boutton OK
    @FXML
    private  JFXButton deletebtn ; // boutton supprimer
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //deleteButtonAction

}

