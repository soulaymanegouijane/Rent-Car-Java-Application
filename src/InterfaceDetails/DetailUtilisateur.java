package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetailUtilisateur {
	
	@FXML
    public TextField genreUtilisateurTextField;

    @FXML
    public TextField prenomTextField;

    @FXML
    public TextField nomTextField;

    @FXML
    public TextField nationaliteTextField;

    @FXML
    public DatePicker dateNaissanceDatePicker;

    @FXML
    public TextField lieuNaissanceTextField;

    @FXML
    public TextField cinTypeTextField;

    @FXML
    public TextField numeroCinTextField;

    @FXML
    public TextField adresseTextField;

    @FXML
    public TextField codePostalTextField;

    @FXML
    public TextField villeTextField;

    @FXML
    public TextField paysTextField;

    @FXML
    public TextField numeroTelephoneTextField;

    @FXML
    public TextField emailTextField;

    @FXML
    public ImageView idImageView;

    @FXML
    public JFXButton closeButton;

    @FXML
    public JFXButton deleteBtn;
    
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
