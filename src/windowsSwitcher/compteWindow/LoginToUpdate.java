package windowsSwitcher.compteWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import Entities.Utilisateur;

public class LoginToUpdate {
	
	@FXML
    private TextField usernameTestField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label erreurMessage;

    @FXML
    private Button EnterButton;

    @FXML
    private Button annulerButton;
	
    public boolean activateUpdate;
    
    public Utilisateur utilisateur = new Utilisateur();

    public void handleEnterButton(ActionEvent actionEvent) throws IOException {
        erreurMessage.setVisible(false);
        String usernameTaped = usernameTestField.getText();
        String passwordTaped = passwordTextField.getText();

        // Check if infos tapped are correct

        if(usernameTaped.isEmpty() && passwordTaped.isEmpty()){
            erreurMessage.setVisible(true);
        }else{
        	
            if(usernameTaped.equals(utilisateur.getUsername()) && passwordTaped.equals(utilisateur.getPass())) {
            	activateUpdate = true;
                Stage stage = (Stage) EnterButton.getScene().getWindow();
                stage.close();
            }else {
            	activateUpdate = false;
                erreurMessage.setVisible(true);
            }
        }
    }

    public void handleAnnulerButton(ActionEvent actionEvent) {
        activateUpdate = false;

        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
}
