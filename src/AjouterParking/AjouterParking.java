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
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        idparking = idparkingTextField.getText();
        adressparking = adressparkingTextField.getText();
        capaciteParking = Integer.parseInt(capaciteParkingTextField.getText());
        occupeParking = Integer.parseInt(occupeParkingTextField.getText());
        if(Erreur()){
            erreurMessage.setVisible(true);
        }else{
            // Upload informations to dataBase
            Parking parking = new Parking();
            parking.setIdParking(Long.valueOf(idparking));
            parking.setAdress(adressparking);
            parking.setCapacite(capaciteParking);
            parking.setNbr_place_pleinne(occupeParking);
            
            int result = H.parking.add(parking);  
            
            if (result != 0) {
				System.out.println("l'ajout d'un parking est bien fait");
			} else {
				System.out.println("il y a un prb dans l'ajout d'un parking");
			}

            Stage stage =(Stage) submitButton.getScene().getWindow();
            stage.close();
        }

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

    public boolean Erreur() {

        if(!isNumeric(capaciteParkingTextField.getText())){
            erreurMessage.setText("La capacité faut étre un entier !!");
            return true;
        }

        if(!isNumeric(occupeParkingTextField.getText())){
            erreurMessage.setText("Le nombre des places occupées faut étre un entier !!");
            return true;
        }

        if(idparking.isEmpty() || adressparking.isEmpty()){
            erreurMessage.setText("Remplire tous les champs !!");
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

	
}
