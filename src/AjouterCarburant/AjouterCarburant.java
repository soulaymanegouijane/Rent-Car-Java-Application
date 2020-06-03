package AjouterCarburant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import Entities.Carburant;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterCarburant {

    public JFXButton closeButton;
    public JFXButton ajouterCarburantBtn;
    public JFXTextArea descriptionCarburant;
    public TextField libelleCarburant;

    public String libelleCarburantTaped;
    public String descriptionCarburantTaped;

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void ajouterCarburantBtnAction(ActionEvent actionEvent) {
        if (!libelleCarburant.getText().isEmpty()){
        	libelleCarburantTaped = libelleCarburant.getText();
            descriptionCarburantTaped = descriptionCarburant.getText();
            
            String carb = libelleCarburant.getText();
        	String desc = descriptionCarburant.getText();
        	
        	Carburant carburant = new Carburant();
        	carburant.setDescription(desc);
        	carburant.setLibelle(carb);
        	H.carburant.add(carburant);
        	
        	
            Stage stage =(Stage) ajouterCarburantBtn.getScene().getWindow();
            stage.close();
        }
    }
}
