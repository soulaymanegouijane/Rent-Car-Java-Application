package AjouterUnClient;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
    public JFXButton saveButton;
    public TextField prenomTextField;
    public TextField nomTextField;
    public TextField nationaliteTextField;
    public DatePicker dateNaissanceDatePicker;
    public TextField lieuNaissanceTextField;
    public TextField cinTextField;
    public TextField numPermisTextField;
    public TextField lieuDelivreTextField;
    public DatePicker dateDelivreDatePicker;
    public DatePicker dateExpireDatePicker;
    public TextField adresseTextField;
    public TextField codePostalTextField;
    public TextField paysTextField;
    public TextField telephoneTextField;
    public TextField emailTextField;
    public Label erreurMessage;
    @FXML
    private JFXButton closeButton ;
	@FXML
    public JFXComboBox<String> comboGender ;
    public JFXComboBox<String> comboIdType ;

    String prenom = null;
    String nom = null;
    String nationalite = null;
    LocalDate dateNaissance = null;
    String lieuNaissance = null;
    String cin = null;
    String numPermis = null;
    String lieuDelivre = null;
    LocalDate dateDelivre = null;
    LocalDate dateExpire = null;
    String adresse = null;
    String codePostal = null;
    String pays = null;
    String telephone = null;
    String email = null;

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
        dateNaissance = dateNaissanceDatePicker.getValue();
        lieuNaissance = lieuNaissanceTextField.getText();
        cin = cinTextField.getText();
        numPermis = numPermisTextField.getText();
        lieuDelivre = lieuDelivreTextField.getText();
        dateDelivre = dateDelivreDatePicker.getValue();
        dateExpire = dateExpireDatePicker.getValue();
        adresse = adresseTextField.getText();
        codePostal = codePostalTextField.getText();
        pays = paysTextField.getText();
        telephone = telephoneTextField.getText();
        email = emailTextField.getText();

        if(testEmpty()){
            erreurMessage.setVisible(true);
        }else{
            // Upload informations to dataBase

            Stage stage =(Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

    public boolean testEmpty(){
        if(prenom.isEmpty() || nom.isEmpty() || nationalite.isEmpty() || dateNaissance == null || lieuNaissance.isEmpty()
                || cin.isEmpty() || numPermis.isEmpty() || lieuDelivre.isEmpty() || dateDelivre == null
                || dateExpire == null || adresse.isEmpty() || codePostal.isEmpty() || pays.isEmpty() || telephone.isEmpty()
                || email.isEmpty()) return true;
        return false;
    }
}
