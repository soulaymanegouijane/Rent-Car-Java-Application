package InterfaceDetails;

import Entities.Utilisateur;
import Test.H;
import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
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
    
    
    public static Utilisateur User = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillBlanks();
        H.setfrenchDatePicker(dateNaissanceDatePicker);
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
        cinTypeTextField.setText(User.getCarte_identifiant());
        numeroCinTextField.setText(User.getIdUtilisateur());
        paysTextField.setText(User.getPays());
        genreUtilisateurTextField.setText(Civilite(User.getIdUtilisateur()));
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
        User.setCarte_identifiant(cinTypeTextField.getText());
        User.setIdUtilisateur(numeroCinTextField.getText());
        User.setPays(paysTextField.getText());
        
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
    
    public String Civilite(String idUser) {
    	String sql ="select * from utilisateur where idUtilisateur = ?";
    	Connection con = Abst.getConnection();
    	String str = null;
    	System.out.println("---------------------------------------> from civilite");
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idUser);
			ResultSet rs = ps.executeQuery();
			System.out.println("bien------------->civilite");
			if(rs.next()) {
				System.out.println(rs.getString("civilite"));
				if(rs.getString("civilite").equals("Homme")) {
					str=  "Mr";
					System.out.println("-------------> Homme");
				}else if(rs.getString("civilite").equals("Femme")) {
					str = "Mlle";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return str;
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

    public void handleDeleteBtn(ActionEvent actionEvent) {

        //Delete Utilisateur

        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
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
