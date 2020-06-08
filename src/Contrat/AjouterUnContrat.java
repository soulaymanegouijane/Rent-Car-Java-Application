package Contrat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AjouterReservation.ChoisirUtilisateurScene;
import Entities.Contrat;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterUnContrat implements Initializable {
	
	 @FXML
	    private JFXButton closeButton;

	    @FXML
	    private TextField idContrat;

	    @FXML
	    private DatePicker dateContrat;

	    @FXML
	    private TextField vehicule;

	    @FXML
	    private JFXButton parcourrirButton1;

	    @FXML
	    private TextField reservation;

	    @FXML
	    private JFXButton parcourrirButton2;

	    @FXML
	    private DatePicker dateDepart;

	    @FXML
	    private DatePicker dateRetour;

	    @FXML
	    private TextField remise;

	    @FXML
	    private TextField kmDepart;

	    @FXML
	    private TextField montantTotal;

	    @FXML
	    private TextField avance;

	    @FXML
	    private TextField montantTotal1;

	    @FXML
	    private JFXButton submit;

	    @FXML
	    private JFXButton finish;

	    public boolean addwithSucces = false;
	    
        @FXML
        private void closeButtonAction(){
            Stage stage =(Stage) closeButton.getScene().getWindow();
            stage.close();
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        	
        }
        
        @FXML
        void addContrat(ActionEvent event) {

        	//String idCnrt = idContrat.getText();
        	String dtContrat = ((TextField)dateContrat.getEditor()).getText();
        	String dtRetour = ((TextField)dateRetour.getEditor()).getText();
        	String dtDepart = ((TextField)dateDepart.getEditor()).getText();
        	String voiture = vehicule.getText();
        	String res = reservation.getText();
        	//String rmse = remise.getText();
        	String kmDprt = kmDepart.getText();
        	//String mtTotal = montantTotal.getText();
        	//String sction = avance.getText();
        	
        	Contrat contrat = new Contrat();
        	
        	contrat.setDateContrat(dtContrat);
        	contrat.setDate_retour(dtRetour);
        	contrat.setDate_sortie(dtDepart);
        	//contrat.setMontantTotal(Double.valueOf(mtTotal));
        	contrat.setKm_depart(Long.valueOf(kmDprt));
        	//contrat.setRemise(Float.valueOf(rmse));
        	contrat.setReservation(H.reservation.getById(Long.valueOf(res)));
        	contrat.setVehicule(H.vehicule.getById(String.valueOf(voiture)));
        	
        	H.contrat.add(contrat);
        	
        	Stage stage =(Stage) closeButton.getScene().getWindow();
            stage.close();
        }

        @FXML
        void btnReservation(ActionEvent event) {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoisirReservationScene.fxml"));
        	Parent root = null;
        	try {
    			root = loader.load();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	Stage stage = new Stage();
        	stage.setScene(new Scene(root));
    		stage.setResizable(false);
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.showAndWait();
    		
    		ChoisirReservationScene choisirReservation = loader.getController();
    		reservation.setText(choisirReservation.idReservationSelected);
        }

        @FXML
        void btnVehicule(ActionEvent event) {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoisirVehiculeScene.fxml"));
        	Parent root = null;
        	try {
    			root = loader.load();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	Stage stage = new Stage();
        	stage.setScene(new Scene(root));
    		stage.setResizable(false);
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.showAndWait();
    		
    		ChoisirVehiculeScene choisirVehicule = loader.getController();
    		vehicule.setText(choisirVehicule.matriculeVehicule);
        }
        
        
    }




