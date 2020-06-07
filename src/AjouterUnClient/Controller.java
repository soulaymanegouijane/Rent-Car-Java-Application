package AjouterUnClient;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Entities.Client;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public DatePicker dateValiditeDatePicker;
    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private JFXComboBox<String> comboGender;

    @FXML
    private TextField nationaliteTextField;

    @FXML
    private DatePicker dateNaissanceDatePicker;

    @FXML
    private TextField lieuNaissanceTextField;

    @FXML
    private JFXComboBox<String> comboIdType;

    @FXML
    private TextField cinTextField;

    @FXML
    private TextField numPermisTextField;

    @FXML
    private TextField lieuDelivreTextField;

    @FXML
    private DatePicker dateDelivreDatePicker;

    @FXML
    private DatePicker dateExpireDatePicker;

    @FXML
    private JFXButton closeButton;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField codePostalTextField;

    @FXML
    private TextField villeTextField;

    @FXML
    private TextField paysTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private Label erreurMessage;

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

    ObservableList<String> Genderlist = FXCollections.observableArrayList("Femme","Homme");
    ObservableList<String> Idtypelist = FXCollections.observableArrayList("Carte Nationale","Passport");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	comboBox();
    }
    
    
    
    public void comboBox() {
        comboGender.setItems(Genderlist);
        comboIdType.setItems(Idtypelist);
    }
    
    @FXML
    private void closeButtonAction(){
	    Stage stage =(Stage) closeButton.getScene().getWindow();
	    stage.close();
	}

    public void handleSaveButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);

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
        genre = comboGender.getValue();
        typeIdentifiant = comboIdType.getValue();
        Client client = new Client();
        
        if(testEmpty()){
            erreurMessage.setVisible(true);
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
        	
        	
        	int s = H.client.add(client);
        	if(s!=0) {
        		System.out.println("l'ajout est bien fait");
        	}else {
        		System.out.println("il y a un prb");
        	}

            Stage stage =(Stage) saveButton.getScene().getWindow();
            stage.close();
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
