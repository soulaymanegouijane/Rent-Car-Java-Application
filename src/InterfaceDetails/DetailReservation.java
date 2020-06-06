package InterfaceDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.reservationWindow.ChoisirClientScene;
import windowsSwitcher.reservationWindow.ChoisirVehiculeScene;

public class DetailReservation implements Initializable {
	
	@FXML
    public JFXButton closeButton;

    @FXML
    public TextField idReservation;

    @FXML
    public DatePicker dateReservation;

    @FXML
    public TextField vehicule;

    @FXML
    public JFXButton choisirVehiculeBtn;

    @FXML
    public TextField client;

    @FXML
    public JFXButton choisirClientBtn;

    @FXML
    public DatePicker dateDepartDatePicker;

    @FXML
    public DatePicker dateRetourDatePicker;

    @FXML
    public TextField avance;

    @FXML
    public JFXComboBox<String> typeRservation;

    @FXML
    public TextField etatReservation;

    @FXML
    public JFXButton createContratBtn;

    @FXML
    public JFXButton EditReservation;

    @FXML
    public JFXButton deleteReservationBtn;
    
    @FXML
    public  JFXButton EditReservationBtn ;
    
    ObservableList<String> TypeRes = FXCollections.observableArrayList();

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	getTypeReservationn_BaseDonnee();
		comboBox();
		
	}
    
    private void comboBox() {
		typeRservation.setItems(TypeRes);
		
	}

	@FXML
    void choisirClientBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/reservationWindow/choisirClientScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirClientScene choisirClient = loader.getController();
		if(!choisirClient.cinClientSelected.isEmpty()) {
			client.setText(choisirClient.cinClientSelected);
		}
    }
    
    public void getTypeReservationn_BaseDonnee() {
    	Connection con = Abst.getConnection();
    	String sql = "select libelle from type_reservation";
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String libelle = rs.getString("libelle");
				TypeRes.add(libelle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void choisirVehiculeBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/reservationWindow/choisirVehiculeScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirVehiculeScene choisirVehicule = loader.getController();
		if(!choisirVehicule.matriculeVehicule.isEmpty()) {
			vehicule.setText(choisirVehicule.matriculeVehicule);
		}
    }

    @FXML
    void closeButtonAction(ActionEvent event) {
    	Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

	
    
    
    
 
}
