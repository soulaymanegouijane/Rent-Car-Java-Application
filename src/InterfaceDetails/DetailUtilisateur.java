package InterfaceDetails;

import Entities.Utilisateur;
import Test.H;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    public JFXButton closeButton;

    @FXML
    public JFXButton deleteBtn;
    @FXML
    public JFXButton editBtn; // Modifier Boutton
    @FXML

    public ImageView profilImage;
    public Button chooseProfilImageButton;
    public JFXButton saveChangesButton;
    public JFXButton cancelEditingButton;
    public VBox editVBox;
    public VBox nonEditVBox;

    //public Utilisateur User = new Utilisateur();

//    public void fillBlanks(){
//        //profilImage.setImage(new Image(new ByteArrayInputStream(User.getImage()), 142, 123, false, false));
//        prenomTextField.setText(User.getPrenom());
//        nomTextField.setText(User.getNom());
//        nationaliteTextField.setText(User.getNationalite());
//        lieuNaissanceTextField.setText(User.getLieu_naissance());
//        emailTextField.setText(User.getEmail());
//        codePostalTextField.setText(User.getCode_postale());
//        villeTextField.setText(User.getVille());
//        dateNaissanceDatePicker.setValue(H.convert(User.getNaissance()));
//        adresseTextField.setText(User.getAdress());
//        numeroTelephoneTextField.setText(User.getTele());
//        cinTypeTextField.setText(User.getCarte_identifiant());
//        numeroCinTextField.setText(User.getIdUtilisateur());
//        paysTextField.setText(User.getPays());
//    }

//    public void rewriteUserInfos(){
//        User.setPrenom(prenomTextField.getText());
//        User.setNom(nomTextField.getText());
//        User.setNationalite(nationaliteTextField.getText());
//        User.setLieu_naissance(lieuNaissanceTextField.getText());
//        User.setEmail(emailTextField.getText());
//        User.setCode_postale(codePostalTextField.getText());
//        User.setVille(villeTextField.getText());
//        User.setNaissance(dateNaissanceDatePicker.getValue().getDayOfMonth() + "/" + dateNaissanceDatePicker.getValue().getMonthValue() + "/" + dateNaissanceDatePicker.getValue().getYear());
//        User.setAdress(adresseTextField.getText());
//        User.setTele(numeroTelephoneTextField.getText());
//        User.setCarte_identifiant(cinTypeTextField.getText());
//        User.setIdUtilisateur(numeroCinTextField.getText());
//        User.setPays(paysTextField.getText());
//    }

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void handleEditBtn(ActionEvent actionEvent) {
        nonEditVBox.setVisible(false);
        editVBox.setVisible(true);
        enableFields();
    }

    public void handleCancelEditingButton(ActionEvent actionEvent) {
        nonEditVBox.setVisible(true);
        editVBox.setVisible(false);
        //fillBlanks();
        disableFields();
    }

    public void handleChooseProfilImageButton(ActionEvent actionEvent) {
        FileChooser imageChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)","*.JPG");
        FileChooser.ExtensionFilter extensionFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)","*.jpg");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)","*.PNG");
        FileChooser.ExtensionFilter extensionFilterpng = new FileChooser.ExtensionFilter("png files (*.png)","*.png");
        imageChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterjpg, extensionFilterPNG, extensionFilterpng);

        //Show open file dialog
        File fileChosen = imageChooser.showOpenDialog(null);

        Image imageChosen = new Image(fileChosen.toURI().toString(),142,123,false,false);
        profilImage.setImage(imageChosen);
    }

    public void handleSaveChangesButton(ActionEvent actionEvent) {
        nonEditVBox.setVisible(true);
        editVBox.setVisible(false);
        disableFields();

        //rewriteUserInfos();
    }

    public void handleDeleteBtn(ActionEvent actionEvent) {
    }

    public void enableFields(){

        genreUtilisateurTextField.setEditable(true);
        prenomTextField.setEditable(true);
        nomTextField.setEditable(true);
        nationaliteTextField.setEditable(true);
        dateNaissanceDatePicker.setEditable(true);
        lieuNaissanceTextField.setEditable(true);
        cinTypeTextField.setEditable(true);
        numeroCinTextField.setEditable(true);
        adresseTextField.setEditable(true);
        codePostalTextField.setEditable(true);
        villeTextField.setEditable(true);
        paysTextField.setEditable(true);
        numeroTelephoneTextField.setEditable(true);
        emailTextField.setEditable(true);

    }

    public void disableFields(){

        genreUtilisateurTextField.setEditable(false);
        prenomTextField.setEditable(false);
        nomTextField.setEditable(false);
        nationaliteTextField.setEditable(false);
        dateNaissanceDatePicker.setEditable(false);
        lieuNaissanceTextField.setEditable(false);
        cinTypeTextField.setEditable(false);
        numeroCinTextField.setEditable(false);
        adresseTextField.setEditable(false);
        codePostalTextField.setEditable(false);
        villeTextField.setEditable(false);
        paysTextField.setEditable(false);
        numeroTelephoneTextField.setEditable(false);
        emailTextField.setEditable(false);

    }


}
