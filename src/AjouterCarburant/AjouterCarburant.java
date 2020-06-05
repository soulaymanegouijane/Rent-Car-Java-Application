package AjouterCarburant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import Entities.Carburant;
import Exceptions.AjoutExceptions;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterCarburant {
	
	@FXML
    private TextField libelleCarburant;

    @FXML
    private JFXTextArea descriptionCarburant;

    @FXML
    private JFXButton ajouterCarburantBtn;

    @FXML
    private JFXButton closeButton;

    public String libelleCarburantTaped;
    public String descriptionCarburantTaped;

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void ajouterCarburantBtnAction(ActionEvent actionEvent) throws AjoutExceptions {
        if (!libelleCarburant.getText().isEmpty() && !descriptionCarburant.getText().isEmpty()){
        	
            
            String carb = libelleCarburant.getText();
        	String desc = descriptionCarburant.getText();
        	
        	Carburant carburant = new Carburant();
        	carburant.setDescription(desc);
        	carburant.setLibelle(carb);
        	
        	try {
        		int y = H.carburant.add(carburant);
            	if(y==0) {
            		throw new AjoutExceptions("carburant");
            	}else {
            		libelleCarburantTaped = libelleCarburant.getText();
                    descriptionCarburantTaped = descriptionCarburant.getText();
            	}
			} catch (AjoutExceptions e) {
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Alert d'erreur");
	        	alert.setHeaderText("can not add Carburant");
	        	alert.setContentText("Carburant n'est pas Ajouter !!");
	        	alert.showAndWait();
			}
        	
        	
            Stage stage =(Stage) ajouterCarburantBtn.getScene().getWindow();
            stage.close();
        }else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Alert d'erreur");
        	alert.setHeaderText("can not add Carburant");
        	if(libelleCarburant.getText().isEmpty()) {
        		alert.setContentText("carburant field est vide");
        	}else if(descriptionCarburant.getText().isEmpty()) {
        		alert.setContentText("description field est vide !");
        	}else {
        		alert.setContentText("tu dois remplir les deux champ");
        	}
        	alert.showAndWait();
        }
    }
}
