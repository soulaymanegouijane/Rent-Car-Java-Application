package AjouterReservation;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Utilisateur;
import Entities.Vehicule;
import Test.H;
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

public class ChoisirVehiculeScene implements Initializable {
    
    @FXML
    private JFXButton choisirButton;

    @FXML
    private TableView<Vehicule> vehiculeTableView;

    @FXML
    private TableColumn<Vehicule,String> col_matricule;

    @FXML
    private TableColumn<Vehicule,String> col_type;

    @FXML
    private TableColumn<Vehicule,String> col_marque;

    @FXML
    private TableColumn<Vehicule,String> col_kilometrage;

    @FXML
    private TableColumn<Vehicule,Boolean> col_disponibilite;

    @FXML
    private Label errorMessage;

    @FXML
    private Button closeButton;
    
    public String matriculeVehicule = null;

    ObservableList<Vehicule> vehicule_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill vehiculeTableView
    	remplir_tableau();
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(vehiculeTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

        	Vehicule selectedVehicule= vehiculeTableView.getSelectionModel().getSelectedItem();
        	matriculeVehicule = selectedVehicule.getIdVehicule();

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public void remplir_tableau() {
    	ResultSet tous_les_vehicule = null;
		try {
			String sql = "select * from vehicule";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_vehicule = ps.executeQuery();
			
			while(tous_les_vehicule.next()) {
				System.out.println("bien");
				Vehicule vehicule = new Vehicule();
				vehicule.setIdVehicule(tous_les_vehicule.getString("idVehicule"));
				// je dois ajouter disponibilite
				vehicule.setMarqueLibelle(H.vehicule.marque_libelle(tous_les_vehicule.getString("idVehicule")));
				vehicule.setTypeVehicule(H.type.getById(H.vehicule.getType(tous_les_vehicule.getString("idVehicule"))).getLibelle());
				vehicule.setKilometrage(tous_les_vehicule.getLong("kilometrage"));
				vehicule_list.add(vehicule);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			col_matricule.setCellValueFactory(new PropertyValueFactory<>("idVehicule"));
		    col_disponibilite.setCellValueFactory(new PropertyValueFactory<>("dispo"));
		    col_kilometrage.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
		    col_marque.setCellValueFactory(new PropertyValueFactory<>("marqueLibelle"));
		    col_type.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
        
		    vehiculeTableView.setItems(vehicule_list);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
