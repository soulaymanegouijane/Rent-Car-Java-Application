package InterfaceDetails;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Test.H;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.contratWindow.ChoisirReservationScene;
import windowsSwitcher.contratWindow.ChoisirVehiculeScene;

public class DetailContrat implements Initializable {
	
	@FXML
    public JFXButton closeButton;

    @FXML
    public TextField idContrat;

    @FXML
    public DatePicker datecontratDatePicker;

    @FXML
    public TextField vehicule;

    @FXML
    public JFXButton choisirVehiculeBtn;

    @FXML
    public TextField reservation;

    @FXML
    public JFXButton choisirReservationBtn;

    @FXML
    public DatePicker datedepartDatePicker;

    @FXML
    public DatePicker dateretourDatePicker;

    @FXML
    public TextField remise;

    @FXML
    public TextField kmDepart;

    @FXML
    public TextField montantTotal;
    
    @FXML
    public JFXButton editContratBtn;
    public TextField UtilisateurTextField;
    public TextField clientTextField;
    public HBox nonEditableHBox;
    public HBox editableHBox;
    public JFXButton saveEditsContratBtn;
    public JFXButton annuerEditsContratBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        H.setfrenchDatePicker(datecontratDatePicker);
        H.setfrenchDatePicker(datedepartDatePicker);
        H.setfrenchDatePicker(dateretourDatePicker);
    }
    
    @FXML
    void choisirReservationBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/contratWindow/choisirReservationScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirReservationScene choisirReservation = loader.getController();
        if(!choisirReservation.idReservationSelected.isEmpty()) {
        	reservation.setText(choisirReservation.idReservationSelected);
        }
    }

    @FXML
    void choisirVehiculeBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/contratWindow/choisirVehiculeScene.fxml"));
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
    public void closeButtonAction(ActionEvent event){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void saveEditsContratBtnAction(ActionEvent actionEvent) {
    }

    public void annuerEditsContratBtnAction(ActionEvent actionEvent) {
    }
}