package windowsSwitcher.contratWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Client;
import Entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChoisirUtilisateurScene implements Initializable {

	@FXML
    private JFXButton choisirButton;

    @FXML
    private TableView<Utilisateur> utilisateurTableView;

    @FXML
    private TableColumn<Utilisateur,String> col_cin;

    @FXML
    private TableColumn<Utilisateur,String> col_nom;

    @FXML
    private TableColumn<Utilisateur,String> col_prenom;

    @FXML
    private TableColumn<Utilisateur,String> col_tele;

    @FXML
    private TableColumn<Utilisateur,String> col_etat_compte;

    @FXML
    private Label errorMessage;

    @FXML
    private Button closeButton;
    
    public String cinUtilisateurSelected = null;

    ObservableList<Utilisateur> utilisateur_list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill utilisateurTableView
    	remplir_tableau();
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(utilisateurTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*utilisateur selectedUtilisateur= utilisateurTableView.getSelectionModel().getSelectedItems().get(0);
            int cinUtilisateurSelected = selectedUtilisateur.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contratWindow.fxml"));
            ContratWindow contratWindow = loader.getController();

            contratWindow.textFeildChoisirUtilisateur.setText(cinUtilisateurSelected);
             */
        	
        	Utilisateur selectedClient= utilisateurTableView.getSelectionModel().getSelectedItem();
        	cinUtilisateurSelected = selectedClient.getIdUtilisateur();

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public void remplir_tableau() {
    	 ResultSet tous_les_utilisateur = null;
 		try {
 			String sql = "select * from utilisateur";
 			Connection con = Abst.getConnection();
 			PreparedStatement ps = con.prepareStatement(sql);
 			tous_les_utilisateur = ps.executeQuery();
 			
 			while(tous_les_utilisateur.next()) {
 				Utilisateur utilisateur = new Utilisateur();
 				utilisateur.setIdUtilisateur(tous_les_utilisateur.getString("idUtilisateur"));
 				utilisateur.setNom(tous_les_utilisateur.getString("nom"));
 				utilisateur.setPrenom(tous_les_utilisateur.getString("prenom"));
 				utilisateur.setTele(tous_les_utilisateur.getString("telephone"));
 				utilisateur.setEtat_compte(tous_les_utilisateur.getString("etat_compte"));
 				utilisateur_list.add(utilisateur);
 			}
 			con.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
         
         col_cin.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
         col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         col_tele.setCellValueFactory(new PropertyValueFactory<>("tele"));
         col_etat_compte.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));
         
         utilisateurTableView.setItems(utilisateur_list);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
