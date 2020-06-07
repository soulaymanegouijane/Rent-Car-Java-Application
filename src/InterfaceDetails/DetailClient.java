package InterfaceDetails;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Entities.Client;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    @FXML
    public JFXButton editClientBtn;//boutton Modifier
    
    String prenom = null;
    String nom = null;
    String nationalite = null;
    String dateNaissance = null;
    String lieuNaissance = null;
    String cinClient = null;
    String numPermis = null;
    String lieuDelivre = null;
    String dateDelivre = null;
    String dateExpire = null;
    String adresse = null;
    String codePostal = null;
    String pays = null;
    String telephone = null;
    String email = null;
    String ville = null;
    String genre = null;
    String typeIdentifiant = null;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
    public void closeButtonAction(){
	    Stage stage =(Stage) closeButton.getScene().getWindow();
	    stage.close();
    }
	
	@FXML
    void AnnulerBtnAction(ActionEvent event) {
		
    }
	
	@FXML
    void modifierClient(ActionEvent event) {
		System.out.println("--------------->  * modification vehicule");
		prenom = prenomTextField.getText();
        nom = nomTextField.getText();
        nationalite = nationaliteTextField.getText();
        dateNaissance = ((TextField)dateNaissanceDatePicker.getEditor()).getText();
        lieuNaissance = lieuNaissanceTextField.getText();
        cinClient = cinTextField.getText();
        numPermis = numPermisTextField.getText();
        lieuDelivre = lieuDelivreTextField.getText();
        dateDelivre = ((TextField)dateDelivreDatePicker.getEditor()).getText();
        dateExpire = ((TextField)dateExpireDatePicker.getEditor()).getText();
        adresse = adresseTextField.getText();
        codePostal = codePostalTextField.getText();
        pays = paysTextField.getText();
        telephone = telephoneTextField.getText();
        email = emailTextField.getText();
        ville = villeTextField.getText();
//        genre = comboGender.getValue();
//        typeIdentifiant = comboIdType.getValue();
        Client client = new Client();
        
        if(testEmpty()){
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Alert d'erreur");
        	alert.setHeaderText("can not add Client");
        	alert.setContentText("Client n'est pas Modifier !!");
        	alert.showAndWait();
        }else{
        	client.setAdress(adresse);
        	client.setNom(nom);
        	client.setPrenom(prenom);
        	client.setNationalite(nationalite);
        	client.setDate_naissance(dateNaissance);
        	client.setLieu_naissance(lieuNaissance);
        	client.setIdClient(cinClient);
        	client.setN_permis(numPermis);
        	client.setDelevre_le(lieuDelivre);
        	client.setDelevre_a(dateDelivre);
        	client.setPays(pays);
        	client.setTelephone(telephone);
        	client.setEmail(email);
        	client.setCode_postale(codePostal);
        	client.setCivilite(genre);
        	client.setCarte_identifiant(typeIdentifiant);
        	client.setVille(ville);
        	
        	H.client.edit(client);
        }
    }
	
	public boolean testEmpty(){
        if(prenom.isEmpty() || nom.isEmpty() || nationalite.isEmpty() || dateNaissance == null || lieuNaissance.isEmpty()
                || cinClient.isEmpty() || numPermis.isEmpty() || lieuDelivre.isEmpty() || dateDelivre == null
                || dateExpire == null || adresse.isEmpty() || codePostal.isEmpty() || pays.isEmpty() || telephone.isEmpty()
                || email.isEmpty()) return true;
        return false;
    }

}
