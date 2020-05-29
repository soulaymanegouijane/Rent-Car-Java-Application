package AjouterUtilisateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class ControllerUtilisateur {
    public TextField prenomTextField;
    public TextField nomTextField;
    public JFXComboBox comboRole;
    public JFXComboBox comboGender;
    public TextField nationaliteTextField;
    public DatePicker date_naissance;
    public TextField lieuNaissanceTextField;
    public JFXComboBox comboIdType;
    public TextField cinTextField;
    public JFXButton closeButton;
    public TextField adresseTextField;
    public TextField codePostalTextField;
    public TextField villeTextField;
    public TextField paysTextField;
    public TextField telephoneTextField;
    public TextField emailTextField;
    public JFXButton saveButton;
    public Label erreurMessage;
    @FXML
    private JFXButton btn;
    @FXML
    private ImageView idview;
    @FXML
    private TextField text;

    Image image = null;
    String prenom = null;
    String nom = null;
    String role = null;
    String genre = null;
    String nationalite = null;
    LocalDate dateNaissance = null;
    String lieuNaissance = null;
    String idType = null;
    String cin = null;
    String adresse = null;
    String codePostal = null;
    String ville = null;
    String pays = null;
    String telephone = null;
    String email = null;

    public void btnimageAction(){
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if(selectedfile!=null){
            Image newimage = new Image(selectedfile.toURI().toString(),200,150,true,true);
            //idview=new ImageView(image);
            text.setText(selectedfile.getAbsolutePath());
            idview.setImage(newimage);
        }
    }

    public void handleAnnulerButton(ActionEvent actionEvent) {
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);

        image = idview.getImage();
        prenom = prenomTextField.getText();
        nom = nomTextField.getText();
        role = comboRole.getSelectionModel().getSelectedItem().toString();
        genre = comboGender.getSelectionModel().getSelectedItem().toString();
        nationalite = nationaliteTextField.getText();
        dateNaissance = date_naissance.getValue();
        lieuNaissance = lieuNaissanceTextField.getText();
        idType = comboIdType.getSelectionModel().getSelectedItem().toString();
        cin = cinTextField.getText();
        adresse = adresseTextField.getText();
        codePostal = codePostalTextField.getText();
        ville = villeTextField.getText();
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
        if(image == null || prenom.isEmpty() || nom.isEmpty() || role.isEmpty() || genre.isEmpty() || nationalite.isEmpty()
                || dateNaissance == null || lieuNaissance.isEmpty() || idType.isEmpty()|| cin.isEmpty() || adresse.isEmpty()
                || codePostal.isEmpty() || ville.isEmpty() || pays.isEmpty() || telephone.isEmpty() || email.isEmpty())
            return true;
        return false;
    }
}
