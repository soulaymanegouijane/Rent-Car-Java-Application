package windowsSwitcher.vehiculeWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Parking;
import Entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import windowsSwitcher.windowsSwitcher;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChoisirParkingScene implements Initializable {
    
	@FXML
    private JFXButton choisirButton;

    @FXML
    private TableView<Parking> parkingTableView;

    @FXML
    private TableColumn<Parking,String> col_nParking;

    @FXML
    private TableColumn< Parking , String > col_nom;

    @FXML
    private TableColumn<Parking,String> col_capacite;

    @FXML
    private TableColumn<Parking,String> col_places_vides;

    @FXML
    private TableColumn<Parking,String> col_adresse;

    @FXML
    private Label errorMessage;

    @FXML
    private Button closeButton;
	
	
    ObservableList<Parking> parking_list = FXCollections.observableArrayList();
    public String idParkingChoisi = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill parkingTableView
    	remplir_tabelau();
    	
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(parkingTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*parking selectedParking = parkingTableView.getSelectionModel().getSelectedItems().get(0);
            int nParkingSelected = selectedParking.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vehiculeWindow.fxml"));
            VehiculeWindow vehiculeWindow = loader.getController();

            vehiculeWindow.textFeildChoisirParking.setText(nParkingSelected);
             */
        	
        	Parking selectedParking= parkingTableView.getSelectionModel().getSelectedItem();
            idParkingChoisi = String.valueOf(selectedParking.getIdParking());

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }
    public void remplir_tabelau() {
    	ResultSet tous_les_parking = null;
 		try {
 			String sql = "select * from parking";
 			Connection con = Abst.getConnection();
 			PreparedStatement ps = con.prepareStatement(sql);
 			tous_les_parking = ps.executeQuery();
 			
 			while(tous_les_parking.next()) {
 				Parking parking = new Parking();
 				parking.setIdParking(tous_les_parking.getLong("idParking"));
				parking.setAdress(tous_les_parking.getString("adress"));
				parking.setCapacite(tous_les_parking.getLong("capacite"));
				parking.setNbr_place_pleinne(tous_les_parking.getInt("nbr_place_pleinne"));
 				parking_list.add(parking);
 			}
 			con.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		
 		col_nParking.setCellValueFactory(new PropertyValueFactory<>("idParking"));
 	    col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
 	    col_places_vides.setCellValueFactory(new PropertyValueFactory<>("nbr_place_pleinne"));
 	    col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
 		    
 	
         
 	   parkingTableView.setItems(parking_list);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
