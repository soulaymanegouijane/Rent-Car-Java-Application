package utilisateurPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import Entities.Utilisateur;

public class UtilisateurPage implements Initializable {

	@FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField searchTextFeild;

    @FXML
    private TableColumn<Utilisateur,String> col_cin;

    @FXML
    private TableColumn<Utilisateur,String> col_nom;

    @FXML
    private TableColumn<Utilisateur,String> col_prenom;

    @FXML
    private TableColumn<Utilisateur,String> col_date_naissance;

    @FXML
    private TableColumn<Utilisateur,String> col_tel;

    @FXML
    private TableColumn<Utilisateur,String> col_email;

    @FXML
    private TableColumn<Utilisateur,String> col_adresse;

    @FXML
    private TableColumn<Utilisateur,String> col_etat_compte;
	
   
    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Nom", "Prénom", "CIN");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox();
    }

    public void comboBox(){
        comboBox.setValue("Chercher par:");
        comboBox.setItems(searchTypeList);
    }

}
