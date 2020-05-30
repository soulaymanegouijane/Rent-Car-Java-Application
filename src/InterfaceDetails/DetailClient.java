package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailClient {

    @FXML
    private TextField GenreTextField  ; // le genre du client (Mr. if Masculin || Mme. if Feminin)

    @FXML
    private TextField  prenomTextField ; // champ du Prenom du client

    @FXML
    private TextField nomTextField  ; // champ du nom du client

    @FXML
    private TextField nationaliteTextField ; // champ de la nationalité

    @FXML
    private DatePicker dateNaissanceDatePicker; // date de naissance du client

    @FXML
    private TextField lieuNaissanceTextField  ; // lieu de naissance du client

    @FXML
    private TextField  typeCinTextField ; // type de carte d'identité (carte nationale ou Passport)

    @FXML
    private TextField  cinTextField ; // Numero de la piéce d'identite

    @FXML
    private TextField numPermisTextField ; // Numero du Permis de conduite

    @FXML
    private TextField lieuDelivreTextField  ; //Permis Délivré  A

    @FXML
    private DatePicker dateDelivreDatePicker ; //Permis Délivré  En

    @FXML
    private DatePicker dateExpireDatePicker ; //Permis Expire  En

    @FXML
    private TextField adresseTextField ; //Adresse du client

    @FXML
    private TextField codePostalTextField ; //Code Postale du client

    @FXML
    private TextField villeTextField ; //Ville Du client

    @FXML
    private TextField telephoneTextField ; //Numéro du telephone du Client

    @FXML
    private TextField emailTextField ; //Email du client
    @FXML
    private JFXButton closeButton ; //OK button
    @FXML
    private JFXButton deleteBtn ; //Boutton Supprimer
    @FXML
    public void closeButtonAction(){
    Stage stage =(Stage) closeButton.getScene().getWindow();
    stage.close();
}

// deleteBtnAction ()
}
