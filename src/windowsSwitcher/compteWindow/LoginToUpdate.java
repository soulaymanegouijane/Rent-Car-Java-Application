package windowsSwitcher.compteWindow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginToUpdate {
    public TextField usernameTestField;
    public PasswordField passwordTextField;
    public Button EnterButton;
    public Button annulerButton;
    public Label erreurMessage;
    public boolean activateUpdate;

    public void handleEnterButton(ActionEvent actionEvent) throws IOException {
        erreurMessage.setVisible(false);
        String usernameTaped = usernameTestField.getText();
        String passwordTaped = passwordTextField.getText();

        // Check if infos tapped are correct

        if(usernameTaped.isEmpty() && passwordTaped.isEmpty()){
            erreurMessage.setVisible(true);
        }else{
            activateUpdate = true;

            Stage stage = (Stage) EnterButton.getScene().getWindow();
            stage.close();
        }
    }

    public void handleAnnulerButton(ActionEvent actionEvent) {
        activateUpdate = false;

        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
}
