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
     
        Boolean b = false;
        Boolean E = false;
        if (!Tester()) {
    	   idparking = idparkingTextField.getText();
           adressparking = adressparkingTextField.getText();
           
           try {
        	   capaciteParking = Integer.parseInt(capaciteParkingTextField.getText());
               occupeParking = Integer.parseInt(occupeParkingTextField.getText());
               
			} catch (Exception e) {
				b = true;
				E = true;
			}
        	Parking parking = new Parking();
            parking.setIdParking(Long.valueOf(idparking));
            parking.setAdress(adressparking);
            parking.setCapacite(capaciteParking);
            parking.setNbr_place_pleinne(occupeParking);
            
            if(!E) {
            	H.parking.add(parking); 
            }
            
            if(b) {
            	AfficheErreur(true,"parking");
            }

            Stage stage =(Stage) submitButton.getScene().getWindow();
            stage.close();
		} else {
			erreurMessage.setVisible(true);
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Alert d'erreur");
        	alert.setHeaderText("can not add parking");
        	if(occupeParkingTextField.getText().isEmpty()) {
        		alert.setContentText("tu dois remplir les places occupee dans le parking");
        	}else if(adressparkingTextField.getText().isEmpty()) {
        		alert.setContentText("tu dois entrer l'adress du parking !");
        	}else if(capaciteParkingTextField.getText().isEmpty()) {
        		alert.setContentText("tu dois remplir la capacite du parking !");
        	}else {
        		alert.setContentText("tu dois remplir les deux champ");
        	}
        	alert.showAndWait();
		}
    }
    
    public void AfficheErreur(Boolean bool,String str) {
    	if(bool) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Alert d'erreur");
        	alert.setHeaderText("can not add "+str);
        	alert.setContentText(str+" n'est pas Ajouter !!");
        	alert.showAndWait();
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

    public boolean Erreur() {

        if(!isNumeric(capaciteParkingTextField.getText())){
            erreurMessage.setText("La capacite faut etre un entier !!");
            return true;
        }

        if(!isNumeric(occupeParkingTextField.getText())){
            erreurMessage.setText("Le nombre des places occupees faut etre un entier !!");
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
