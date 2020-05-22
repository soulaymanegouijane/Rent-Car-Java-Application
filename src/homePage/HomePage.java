package homePage;

import windowsSwitcher.windowsSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    public Button deconnexionButton;
    public AnchorPane rootPane;
    public Button utilisateurHomeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleDeconnexionButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../loginPage/loginMain.fxml"));

        Scene home = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

    public void handleUtilisateurHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleUtilisateurButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }
}
