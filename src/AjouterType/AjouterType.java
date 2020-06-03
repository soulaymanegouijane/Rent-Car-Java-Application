package AjouterType;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterType {

    public JFXButton closeButton;
    public TextField libelleType;
    public JFXTextArea descriptionType;
    public JFXButton ajouterTypeBtn;

    public String libelleTypeTaped;
    public String descriptionTypeTaped;

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void ajouterTypeBtnAction(ActionEvent actionEvent) {
        if (!libelleType.getText().isEmpty()){
            libelleTypeTaped = libelleType.getText();
            descriptionTypeTaped = descriptionType.getText();

            //Send Values To DataBase

            Stage stage =(Stage) ajouterTypeBtn.getScene().getWindow();
            stage.close();
        }
    }
    /*------------- End of Methods ------------- */
}
