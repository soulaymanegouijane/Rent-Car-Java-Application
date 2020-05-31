package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetailUtilisateur {
    @FXML
    private TextField genreUtilisateurTextField; // Genre utilisateur (Mr->Massculin || Mme -> Feminin)
    @FXML
    private TextField prenomTextField; // Prenom de l'utilisateur
    @FXML
    private TextField nomTextField; //Nom d'utilisateur
    @FXML
    private TextField nationaliteTextField; // La nationalité de l'utilisateur
    @FXML
    private DatePicker dateNaissanceDatePicker ; //Date de naissance de l'utilisateur
    @FXML
    private TextField lieuNaissanceTextField; // Lieu de naissance
    @FXML
    private TextField cinTypeTextField; // type de la piece d'identité
    @FXML
    private TextField numeroCinTextField; // N° de la piéce d'identité
    @FXML
    private TextField adresseTextField; //Adresse de l'utilisateur
    @FXML
    private TextField codePostalTextField; //code postale
    @FXML
    private TextField villeTextField; //ville de l'utilisateur
    @FXML
    private TextField paysTextField; //pays de l'utilisateur
    @FXML
    private TextField numeroTelephoneTextField; //Télephone de l'utilisateur
    @FXML
    private TextField emailTextField; //email de l'utilisateur
    @FXML
    private ImageView idImageView; //Image de l'utilisateur
    @FXML
    private JFXButton closeButton;// OK boutton
    @FXML
    private JFXButton  deleteBtn; //supprimer la reservation
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    //deleteBtnAction()
}
