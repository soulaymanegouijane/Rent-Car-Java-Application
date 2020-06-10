package windowsSwitcher.compteWindow;

import Entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CompteWindow implements Initializable {
	
	@FXML
    private ImageView profilImage;

    @FXML
    private Button chooseProfilImageButton;

    @FXML
    private VBox grandTextVBox;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label typeCompteLabel;

    @FXML
    private TextField cinTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField compteTypeTextField;

    @FXML
    private TextField teleTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField etatCompteTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button saveCompte;

    @FXML
    private Button annulerButton;

    @FXML
    private Button updateCompteButton;

    public static Utilisateur User;


    private Image oldImage;
    private String oldUsername;
    private String oldPassword;
    private String oldCin;
    private String oldNom;
    private String oldPrenom;
    private String oldCompteType;
    private String oldtele;
    private String oldAdresse;
    private String oldemail;
    private String oldEtatCompte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        oldCin = User.getIdUtilisateur();
        oldNom = User.getNom();
        oldPrenom = User.getPrenom();
        oldCompteType = User.getRole().getRole();
        oldtele = User.getTele();
        oldAdresse = User.getAdress();
        oldemail = User.getEmail();
        oldEtatCompte = User.getEtat_compte();

        oldUsername = User.getUsername();
        oldPassword = User.getPass();
        oldImage = new Image(new ByteArrayInputStream(User.getImage()), 142, 123, false, false);

        fillBlanks();
    }

    public void fillBlanks(){

        profilImage.setImage(oldImage);
        usernameTextField.setText(oldUsername);
        passwordTextField.setText(oldPassword);
        cinTextField.setText(oldCin);
        nomTextField.setText(oldNom);
        prenomTextField.setText(oldPrenom);
        compteTypeTextField.setText(oldCompteType);
        teleTextField.setText(oldtele);
        adresseTextField.setText(oldAdresse);
        emailTextField.setText(oldemail);
        etatCompteTextField.setText(oldEtatCompte);

        fullNameLabel.setText(nomTextField.getText() + " " + prenomTextField.getText());
        typeCompteLabel.setText(compteTypeTextField.getText());
    }

    public void handleUpdateCompteButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginToUpdate.fxml"));
        Parent root = loader.load();
        LoginToUpdate access = loader.getController();
        
        access.utilisateur = User;

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        
        if(access.activateUpdate) activateModifications();
    }

    public void handleChooseProfilImageButton(ActionEvent actionEvent) throws IOException {
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

    public void handleSaveCompte(ActionEvent actionEvent) {
        // Verification des informations saisie
        oldImage = profilImage.getImage();
        oldUsername = usernameTextField.getText();
        oldPassword = passwordTextField.getText();
        oldCin = cinTextField.getText();
        oldNom = nomTextField.getText();
        oldPrenom = prenomTextField.getText();
        oldCompteType = compteTypeTextField.getText();
        oldtele = teleTextField.getText();
        oldAdresse = adresseTextField.getText();
        oldemail = emailTextField.getText();
        oldEtatCompte = etatCompteTextField.getText();

        disactivateModifications();
    }

    public void handleAnnulerButton(ActionEvent actionEvent) {
        profilImage.setImage(oldImage);
        usernameTextField.setText(oldUsername);
        passwordTextField.setText(oldPassword);
        cinTextField.setText(oldCin);
        nomTextField.setText(oldNom);
        prenomTextField.setText(oldPrenom);
        compteTypeTextField.setText(oldCompteType);
        teleTextField.setText(oldtele);
        adresseTextField.setText(oldAdresse);
        emailTextField.setText(oldemail);
        etatCompteTextField.setText(oldEtatCompte);

        disactivateModifications();
    }

    public void activateModifications(){
        grandTextVBox.setVisible(false);
        disable(updateCompteButton);
        enable(saveCompte);
        enable(annulerButton);
        enable(chooseProfilImageButton);

        usernameTextField.setDisable(false);
        passwordTextField.setDisable(false);
        cinTextField.setDisable(false);
        nomTextField.setDisable(false);
        prenomTextField.setDisable(false);
        teleTextField.setDisable(false);
        adresseTextField.setDisable(false);
        emailTextField.setDisable(false);
    }

    public void disactivateModifications(){
        grandTextVBox.setVisible(true);
        enable(updateCompteButton);
        disable(saveCompte);
        disable(annulerButton);
        disable(chooseProfilImageButton);

        usernameTextField.setDisable(true);
        passwordTextField.setDisable(true);
        cinTextField.setDisable(true);
        nomTextField.setDisable(true);
        prenomTextField.setDisable(true);
        teleTextField.setDisable(true);
        adresseTextField.setDisable(true);
        emailTextField.setDisable(true);
    }

    public void disable(Button Btn){
        Btn.setVisible(false);
        Btn.setDisable(true);
    }

    public void enable(Button Btn){
        Btn.setVisible(true);
        Btn.setDisable(false);
    }
}
