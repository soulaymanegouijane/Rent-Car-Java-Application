package InterfaceDetails;

import Entities.Role;
import Entities.Utilisateur;
import Test.H;
import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailUtilisateur implements Initializable{

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
    public JFXComboBox<String> GenreComboBox;
    public JFXComboBox<String> typeCinCombo;
    public JFXComboBox<String> etatCompteCombo;
    public JFXComboBox<String> roleComboBox;
    public Label erreurMessage;
    public StackPane stackPane;

    @FXML
    private TextField imgFieldText;
    
    @FXML
    public JFXButton editBtn; // Modifier Boutton
    @FXML

    public ImageView profilImage;
    public Button chooseProfilImageButton;
    public JFXButton saveChangesButton;
    public JFXButton cancelEditingButton;
    public VBox editVBox;
    public VBox nonEditVBox;

    
    FileChooser imageChooser = new FileChooser();
    File fileChosen = null;
    byte[] bFile = null;
    File file = null;

    ObservableList<String> roleList = FXCollections.observableArrayList();
    ObservableList<String> etatCompteList = FXCollections.observableArrayList("Activer", "Disactiver");
    ObservableList<String> GenreList = FXCollections.observableArrayList("Femme", "Homme");
    ObservableList<String> Idtypelist = FXCollections.observableArrayList("Carte Nationale","Passport");

    boolean imageChanged = false;

    public static Utilisateur User = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role_base_donnee();
        comboBox();
        H.setfrenchDatePicker(dateNaissanceDatePicker);
        fillBlanks();

        imgFieldText.textProperty().addListener((observable, oldValue, newValue) ->{
            imageChanged = true;
        });
    }

    public void comboBox(){
        etatCompteCombo.setItems(etatCompteList);
        GenreComboBox.setItems(GenreList);
        typeCinCombo.setItems(Idtypelist);
        roleComboBox.setItems(roleList);
    }

    public void role_base_donnee() {
        ResultSet tous_les_roles = null;
        String sql = "select * from role";
        Connection con = Abst.getConnection();
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            tous_les_roles = ps.executeQuery();
            while(tous_les_roles.next()) {
                Role role = new Role();
                role.setRole(tous_les_roles.getString("role"));
                String rl = role.getRole();
                roleList.add(rl);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillBlanks(){
        profilImage.setImage(new Image(new ByteArrayInputStream(User.getImage()), 142, 123, false, false));
        prenomTextField.setText(User.getPrenom());
        nomTextField.setText(User.getNom());
        nationaliteTextField.setText(User.getNationalite());
        lieuNaissanceTextField.setText(User.getLieu_naissance());
        emailTextField.setText(User.getEmail());
        codePostalTextField.setText(User.getCode_postale());
        villeTextField.setText(User.getVille());
        dateNaissanceDatePicker.setValue(H.convert(User.getNaissance()));
        adresseTextField.setText(User.getAdress());
        numeroTelephoneTextField.setText(User.getTele());
        GenreComboBox.setValue(User.getCivilite());
        numeroCinTextField.setText(User.getIdUtilisateur());
        paysTextField.setText(User.getPays());
        typeCinCombo.setValue(User.getCarte_identifiant());
        etatCompteCombo.setValue(User.getEtat_compte());
        roleComboBox.setValue(User.getRole().getRole());
    }

    public void rewriteUserInfos(){
        User.setPrenom(prenomTextField.getText());
        User.setNom(nomTextField.getText());
        User.setNationalite(nationaliteTextField.getText());
        User.setLieu_naissance(lieuNaissanceTextField.getText());
        User.setEmail(emailTextField.getText());
        User.setCode_postale(codePostalTextField.getText());
        User.setVille(villeTextField.getText());
        User.setNaissance(((TextField)dateNaissanceDatePicker.getEditor()).getText());
        User.setAdress(adresseTextField.getText());
        User.setTele(numeroTelephoneTextField.getText());
        User.setCarte_identifiant(typeCinCombo.getValue());
        User.setCarte_identifiant(GenreComboBox.getValue());
        User.setIdUtilisateur(numeroCinTextField.getText());
        User.setPays(paysTextField.getText());
        User.setEtat_compte(etatCompteCombo.getValue());
        User.setRole(H.role.get(roleComboBox.getValue()));
        if (imageChanged){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                try {
                    inputStream.read(bFile);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            User.setImage(bFile);
        }
    }

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
        fillBlanks();
        disableFields();
    }
    
    public void handleChooseProfilImageButton(ActionEvent actionEvent) {
    	FileChooser.ExtensionFilter extensionFilterpng = new FileChooser.ExtensionFilter("png files (*.png)","*.png");
        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)","*.JPG");
        FileChooser.ExtensionFilter extensionFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)","*.jpg");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)","*.PNG");
        
        imageChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterjpg, extensionFilterPNG, extensionFilterpng);

        //Show open file dialog
        fileChosen = imageChooser.showOpenDialog(null);
        
        
        if(fileChosen!=null){
        	Image imageChosen = new Image(fileChosen.toURI().toString(),142,123,false,false);
            imgFieldText.setText(fileChosen.getAbsolutePath());
            profilImage.setImage(imageChosen);
            file = new File(fileChosen.getAbsolutePath());
            bFile = new byte[(int) file.length()];
            System.out.println("-------------->  "+fileChosen.getAbsolutePath());
        }
    }

    public void handleSaveChangesButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);
        if (testEmpty()){
            erreurMessage.setText("Remplissez tous les champs!!");
            erreurMessage.setVisible(true);
        }else if (!H.isEmailValid(emailTextField.getText())){
            erreurMessage.setText("Erreur dans l'email!!");
            erreurMessage.setVisible(true);
        }else if (!H.isNumeric(codePostalTextField.getText())) {
            erreurMessage.setText("Erreur dans le Code Postal!!");
            erreurMessage.setVisible(true);
        } else if (!H.isNumeric(numeroTelephoneTextField.getText())) {
            erreurMessage.setText("Erreur dans le N° Téléphone!!");
            erreurMessage.setVisible(true);
        }else {
            nonEditVBox.setVisible(true);
            editVBox.setVisible(false);
            disableFields();

            rewriteUserInfos();
            try {
                Utilisateur utilisateur = H.utilisateur.edit(User);
                if(utilisateur == null) {
                    System.out.println("erreur");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void AfficheErreur(String str) {

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setAlignment(Pos.CENTER);
        jfxDialogLayout.setHeading(new Text("Alert d'erreur"));
        JFXButton okay = new JFXButton("Close");
        jfxDialogLayout.setBody(new Text("Vous pouver pas supprimer cet " + str));
        okay.setPrefWidth(110);
        okay.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white;");
        okay.setButtonType(JFXButton.ButtonType.RAISED);
        JFXDialog j = new JFXDialog(stackPane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        okay.setOnAction(event -> {
            j.close();
        });

        jfxDialogLayout.setActions(okay);
        j.show();
    }
    
    public void handleDeleteBtn(ActionEvent actionEvent) {
        int result = H.utilisateur.delete(User);

        if (result == 0){
            AfficheErreur("Utilisateur");
        }else {
            closeButtonAction();
        }

    }

    public void enableFields(){

        GenreComboBox.setDisable(false);
        prenomTextField.setEditable(true);
        nomTextField.setEditable(true);
        roleComboBox.setDisable(false);
        nationaliteTextField.setEditable(true);
        dateNaissanceDatePicker.setDisable(false);
        lieuNaissanceTextField.setEditable(true);
        typeCinCombo.setDisable(false);
        numeroCinTextField.setEditable(true);
        adresseTextField.setEditable(true);
        codePostalTextField.setEditable(true);
        villeTextField.setEditable(true);
        paysTextField.setEditable(true);
        numeroTelephoneTextField.setEditable(true);
        emailTextField.setEditable(true);
        etatCompteCombo.setDisable(false);

    }

    public void disableFields(){

        GenreComboBox.setDisable(true);
        prenomTextField.setEditable(false);
        nomTextField.setEditable(false);
        roleComboBox.setDisable(true);
        nationaliteTextField.setEditable(false);
        dateNaissanceDatePicker.setDisable(true);
        lieuNaissanceTextField.setEditable(false);
        typeCinCombo.setDisable(true);
        numeroCinTextField.setEditable(false);
        adresseTextField.setEditable(false);
        codePostalTextField.setEditable(false);
        villeTextField.setEditable(false);
        paysTextField.setEditable(false);
        numeroTelephoneTextField.setEditable(false);
        emailTextField.setEditable(false);
        etatCompteCombo.setDisable(true);

    }

    public boolean testEmpty(){
        if(GenreComboBox.getValue() == null || prenomTextField.getText().isEmpty() || nomTextField.getText().isEmpty()
                || nationaliteTextField.getText().isEmpty() || roleComboBox.getValue() == null
                || dateNaissanceDatePicker.getValue() == null || lieuNaissanceTextField.getText().isEmpty()
                || typeCinCombo.getValue() == null || numeroCinTextField.getText().isEmpty() || adresseTextField.getText().isEmpty()
                || codePostalTextField.getText().isEmpty() || villeTextField.getText().isEmpty() || paysTextField.getText().isEmpty()
                || numeroTelephoneTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || etatCompteCombo.getValue() == null)
            return true;
        return false;
    }
}
