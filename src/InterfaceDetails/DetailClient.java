package InterfaceDetails;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailClient implements Initializable{

	@FXML
	public TextField GenreTextField;

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
    public TextField typeCinTextField;

    @FXML
    public TextField cinTextField;

    @FXML
    public TextField numPermisTextField;

    @FXML
    public TextField lieuDelivreTextField;

    @FXML
    public DatePicker dateDelivreDatePicker;

    @FXML
    public DatePicker dateExpireDatePicker;

    @FXML
    public TextField adresseTextField;

    @FXML
    public TextField codePostalTextField;

    @FXML
    public TextField villeTextField;

    @FXML
    public TextField paysTextField;

    @FXML
    public TextField telephoneTextField;

    @FXML
    public TextField emailTextField;

    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXButton deleteBtn;
    
    
    
    //Boutton Supprimer
    @FXML
    public void closeButtonAction(){
    Stage stage =(Stage) closeButton.getScene().getWindow();
    stage.close();
}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String str = prenomTextField.getText();
		System.out.println("-----------> "+str);
		
	}

// deleteBtnAction ()
}
