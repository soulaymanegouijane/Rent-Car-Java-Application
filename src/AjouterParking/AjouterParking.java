package AjouterParking;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AjouterParking {
    @FXML
    private JFXButton closeButton;
    @FXML
    private void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
