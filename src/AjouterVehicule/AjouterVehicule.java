package AjouterVehicule;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import Entities.Carburant;
import Entities.Parking;
import Entities.Vehicule;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterVehicule implements Initializable{
	
	 @FXML
	    private JFXButton closeButton;

	    @FXML
	    private TextField idmatricule;

	    @FXML
	    private TextField nombrePlaceTextField; // Nombre de place

	    @FXML
	    private JFXComboBox<String> idcarburant;

	    @FXML
	    private JFXComboBox<String> marqueComboBox; // le combobox de la marque

		@FXML
		private JFXComboBox<String> typeComboBox ; //type de la voiture
	    @FXML
	    private TextField idcolor;

	    @FXML
	    private JFXComboBox<String> idparking;

	    @FXML
	    private JFXButton submitButton;

	    ObservableList<String> carburantList = FXCollections.observableArrayList();
	    ObservableList<String> parkingList = FXCollections.observableArrayList();
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	comboBox();
		}

	    public void comboBox() {
	    	idcarburant.setItems(carburantList);
	    	idparking.setItems(parkingList);
	    }

	    @FXML
	    public void closeButtonAction(){
	        Stage stage =(Stage) closeButton.getScene().getWindow();
	        stage.close();
	    }
	    
	    @FXML
	    void addVehicule(ActionEvent event) {
	    	String matricule = idmatricule.getText();
	    	Integer nbrPlace = idplace.getValue();
	    	String carbutant = idcarburant.getValue();
	    	String parking = idparking.getValue();
	    	String color = idcolor.getText();
	    	String marque = idmark.getText();
	    	Vehicule vehicule = new Vehicule();
	    	vehicule.setIdVehicule(matricule);
	    	vehicule.setCarburant(H.carburant.get(carbutant));
	    	vehicule.setColor(color);
	    	vehicule.setNbr_place(nbrPlace);
	    	vehicule.setParking(H.parking.get(parking));
	    	vehicule.setType(null);
	    	H.vehicule.add(vehicule);
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
	    
	    public void parking_base_donnee() {
	    	ResultSet tous_les_parking = null;
			try {
				String sql = "select * from parking";
				Connection con = Abst.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				tous_les_parking = ps.executeQuery();
				while(tous_les_parking.next()) {
					Parking parking = new Parking();
					parking.setAdress(tous_les_parking.getString("adress"));
					String adr = parking.getAdress();
					parkingList.add(adr);
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }

		
		
		
	    
}
