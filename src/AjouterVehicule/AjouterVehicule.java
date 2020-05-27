package AjouterVehicule;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AjouterVehicule {
    @FXML
    private JFXButton closeButton;
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    //Ajout de vos code (all IDs are Added)
}
