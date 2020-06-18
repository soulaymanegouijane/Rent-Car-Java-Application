package AjouterParking;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Parking;
import Exceptions.AjoutExceptions;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterParking implements Initializable{
    public TextField idparkingTextField;
    public TextField adressparkingTextField;
    public TextField capaciteParkingTextField;
    public TextField occupeParkingTextField;
    public JFXButton submitButton;
    public Label erreurMessage;
    @FXML
    private JFXButton closeButton;

    String idparking;
    String adressparking;
    int capaciteParking;
    int occupeParking;
    public String parkingTaped = null;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		long parc = getIdParking() + 1;
		idparkingTextField.setText(String.valueOf(parc));
	}
    
    @FXML
    private void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void submitButtonAction(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);

        if (Tester()){
            erreurMessage.setText("Remplissez tous les champs !!");
            erreurMessage.setVisible(true);
        }else if(!H.isNumeric(capaciteParkingTextField.getText())){
            erreurMessage.setText("Capacité doit étre entier !!");
            erreurMessage.setVisible(true);
        }else if(!H.isNumeric(occupeParkingTextField.getText())){
            erreurMessage.setText("Nombre de places doit étre entier !!");
            erreurMessage.setVisible(true);
        }else if((Integer.parseInt(capaciteParkingTextField.getText()) < Integer.parseInt(occupeParkingTextField.getText()))){
            erreurMessage.setText("Nombre de places doit étre moins de Capacité !!");
            erreurMessage.setVisible(true);
        }else {
            idparking = idparkingTextField.getText();
            adressparking = adressparkingTextField.getText();

            capaciteParking = Integer.parseInt(capaciteParkingTextField.getText());
            occupeParking = Integer.parseInt(occupeParkingTextField.getText());

            Parking parking = new Parking();
            parking.setIdParking(Long.parseLong(idparking));
            parking.setAdress(adressparking);
            parking.setCapacite(capaciteParking);
            parking.setNbr_place_pleinne(occupeParking);

            H.parking.add(parking);

            Stage stage =(Stage) submitButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public boolean Tester() {
    	if(!adressparkingTextField.getText().isEmpty() && !capaciteParkingTextField.getText().isEmpty() 
    			&& !occupeParkingTextField.getText().isEmpty() ) {
        	return false;
        }
    	return true;
    }
    
    public long getIdParking() {
    	Connection con = Abst.getConnection();
    	String sql = "select max(idParking) as maxParc from parking";
    	long parc = 0;
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				parc = rs.getLong("maxParc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return parc;
    }

}
