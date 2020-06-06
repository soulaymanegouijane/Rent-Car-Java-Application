package InterfaceDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import AjouterCarburant.AjouterCarburant;
import Entities.Carburant;
import Entities.Marque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.vehiculeWindow.ChoisirParkingScene;

public class DetailVehicule implements Initializable {
	@FXML
    public JFXButton closeButton;

    @FXML
    public ImageView photoVehicule;

    @FXML
    public JFXButton editPhotoVehiculeBtn;

    @FXML
    public TextField idmatricule;

    @FXML
    public TextField nombrePlaceTextField;

    @FXML
    public JFXComboBox<String> TypeCarburant;

    @FXML
    public JFXComboBox<String> marqueVoiture;

    @FXML
    public JFXColorPicker idcolor;

    @FXML
    public TextField idParking;

    @FXML
    public Button choisirParkingBtn;
    

    @FXML
    public JFXComboBox<String> dispoVehicule;

    @FXML
    public JFXButton deleteBtn;

    @FXML
    public JFXButton editBtn;
    
    ObservableList<String> carburantList = FXCollections.observableArrayList();
    ObservableList<String> marqueList = FXCollections.observableArrayList();
    ObservableList<String> dispoList = FXCollections.observableArrayList("Disponible","indisponible");
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	marque_base_donne();
		carburant_base_donnee();
    	comboBox();		
	}
    
    @FXML
    void EditParking(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/vehiculeWindow/choisirParkingScene.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		ChoisirParkingScene choisirParking = loader.getController();
		idParking.setText(choisirParking.adressParkingChoisi);
    }
    
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    public void comboBox() {
    	TypeCarburant.setItems(carburantList);
    	marqueVoiture.setItems(marqueList);
    	dispoVehicule.setItems(dispoList);
    }
    
    public void marque_base_donne() {
		ResultSet tous_les_marques = null;
		String sql = "select * from marque";
		Connection con = Abst.getConnection();
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_marques = ps.executeQuery();
			while(tous_les_marques.next()) {
				Marque marque = new Marque();
				marque.setLibelle(tous_les_marques.getString("libelle"));
				String mrq = marque.getLibelle();
				marqueList.add(mrq);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public void carburant_base_donnee() {
		ResultSet tous_les_carburant = null;
		try {
			String sql = "select * from carburant";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_carburant = ps.executeQuery();
			while(tous_les_carburant.next()) {
				Carburant carburant = new Carburant();
				carburant.setLibelle(tous_les_carburant.getString("libelle"));
				String carb = carburant.getLibelle();
				carburantList.add(carb);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
