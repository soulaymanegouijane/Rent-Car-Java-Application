package AjouterUtilisateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import Entities.Marque;
import Entities.Reservation;
import Entities.Role;
import Entities.Utilisateur;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerUtilisateur implements Initializable {
	@FXML
    public TextField UsernameTextField;
	
    public PasswordField PasswordTextField;
    @FXML
    private ImageView idview;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private JFXComboBox<String> comboRole;

    @FXML
    private JFXComboBox<String> comboGender;

    @FXML
    private TextField nationaliteTextField;

    @FXML
    private DatePicker date_naissance;

    @FXML
    private TextField lieuNaissanceTextField;

    @FXML
    private JFXComboBox<String> comboIdType;

    @FXML
    private TextField cinTextField;

    @FXML
    private TextField text;

    @FXML
    private JFXButton btn;

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

    ObservableList<String> civilite = FXCollections.observableArrayList("Homme","Femme");
    ObservableList<String> identification = FXCollections.observableArrayList("Passport","Carte Nationale");
    ObservableList<String> roleList = FXCollections.observableArrayList();
    

    Image image = null;
    String prenom = null;
    String nom = null;
    String role = null;
    String genre = null;
    String nationalite = null;
    String dateNaissance = null;
    String lieuNaissance = null;
    String idType = null;
    String cin = null;
    String adresse = null;
    String codePostal = null;
    String ville = null;
    String pays = null;
    String telephone = null;
    String email = null;
    String username = null;
    String pass = null;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	role_base_donnee();
    	comboBox();
	}
    
    public void comboBox() {
    	comboGender.setItems(civilite);
    	comboIdType.setItems(identification);
    	comboRole.setItems(roleList);
    	
    }

    FileChooser fc = new FileChooser();
    File selectedfile = null;
    File file = null;
    byte[] bFile = null;
    public void btnimageAction(){
        
        selectedfile = fc.showOpenDialog(null);
        if(selectedfile!=null){
            Image newimage = new Image(selectedfile.toURI().toString(),200,150,true,true);
            text.setText(selectedfile.getAbsolutePath());
            idview.setImage(newimage);
            file = new File(selectedfile.getAbsolutePath());
            bFile = new byte[(int) file.length()];
            System.out.println(selectedfile.getAbsolutePath());
        }
    }
    
    
    

    public void handleAnnulerButton(ActionEvent actionEvent) {
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);
        Utilisateur user = new Utilisateur();
        image = idview.getImage();
        prenom = prenomTextField.getText();
        nom = nomTextField.getText();
        role = comboRole.getValue();
        genre = comboGender.getSelectionModel().getSelectedItem();
        nationalite = nationaliteTextField.getText();
        dateNaissance = ((TextField)date_naissance.getEditor()).getText();
        lieuNaissance = lieuNaissanceTextField.getText();
        idType = comboIdType.getSelectionModel().getSelectedItem();
        cin = cinTextField.getText();
        adresse = adresseTextField.getText();
        codePostal = codePostalTextField.getText();
        ville = villeTextField.getText();
        pays = paysTextField.getText();
        telephone = telephoneTextField.getText();
        email = emailTextField.getText();
        username = UsernameTextField.getText();
        pass = PasswordTextField.getText();
        
        Role rol = H.role.get(role);
        boolean s = false;
        if(s){
            erreurMessage.setVisible(true);
        }else{
            // Upload informations to dataBase
        	user.setIdUtilisateur(cin);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setAdress(adresse);
			user.setTele(telephone);
			user.setEmail(email);
			user.setNationalite(nationalite);
			user.setNaissance(dateNaissance);
			user.setLieu_naissance(lieuNaissance);
			user.setEtat_compte("");
			user.setPays(pays);
			user.setCode_postale(codePostal);
			user.setCivilite(genre);
			user.setVille(ville);
			user.setRole(rol);
			user.setCarte_identifiant(idType);
			user.setUsername(username);
			user.setPass(pass);
			user.setEtat_compte("Activer");
			
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
			
			user.setImage(bFile);
			
			H.utilisateur.add(user);

            Stage stage =(Stage) saveButton.getScene().getWindow();
            stage.close();
        }
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

    public boolean testEmpty(){
        if(image == null || prenom.isEmpty() || nom.isEmpty() || role.isEmpty() || genre.isEmpty() || nationalite.isEmpty()
                || dateNaissance == null || lieuNaissance.isEmpty() || idType.isEmpty()|| cin.isEmpty() || adresse.isEmpty()
                || codePostal.isEmpty() || ville.isEmpty() || pays.isEmpty() || telephone.isEmpty() || email.isEmpty())
            return true;
        return false;
    }

	
}
