package AjouterReservation;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Client;
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

public class ChoisirClientScene implements Initializable {
    
	@FXML
    private JFXButton choisirButton;

    @FXML
    private TableView<Client> clientTableView;

    @FXML
    private TableColumn< Client,String > col_cin;

    @FXML
    private TableColumn< Client,String > col_nom;

    @FXML
    private TableColumn< Client,String > col_prenom;

    @FXML
    private TableColumn< Client,String > col_tele;

    @FXML
    private TableColumn< Client,String > col_adresse;

    @FXML
    private Label errorMessage;

    @FXML
    private Button closeButton;
	
    ObservableList<Client> client_list = FXCollections.observableArrayList();
    
    public String cinClientSelected = null;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill clientTableView
    	remplir_tableau();
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(clientTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row
        	
        	Client selectedClient= clientTableView.getSelectionModel().getSelectedItem();
            cinClientSelected = selectedClient.getIdClient();

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public void remplir_tableau() {
    	Connection con = Abst.getConnection();
    	String sql = "select * from client";
    	Client client= new Client();
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet tous_les_client = ps.executeQuery();
			while(tous_les_client.next()) {
				client.setIdClient(tous_les_client.getString("idClient"));
				client.setNom(tous_les_client.getString("nom"));
				client.setPrenom(tous_les_client.getString("prenom"));
				client.setAdress(tous_les_client.getString("adress"));
				client.setTelephone(tous_les_client.getString("telephone"));
				client_list.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	col_cin.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
        col_tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        clientTableView.setItems(client_list);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
