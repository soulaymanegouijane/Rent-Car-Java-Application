package homePage;

import Entities.Utilisateur;
import javafx.scene.control.Label;
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
    public Label nameLabel;

    public static Utilisateur loggedInUser = new Utilisateur();
    public Button CompteHomeButton;
    public Button clientHomeButton;
    public Button VehiculeHomeButton;
    public Button ParkingHomeButton;
    public Button ReservationHomeButton;
    public Button ContratsHomeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (loggedInUser.getCivilite().equals("Homme")) {
            nameLabel.setText("Mr." + loggedInUser.getNom().toUpperCase());
        }else if (loggedInUser.getCivilite().equals("Femme")) {
            nameLabel.setText("Mme." + loggedInUser.getNom().toUpperCase());
        }

        if (loggedInUser.getRole().getRole().equals("employee")){
            utilisateurHomeButton.setDisable(true);
        }
    }

    public void handleCompteHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleCompteButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
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

    public void handleClientHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleClientButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

    public void handleVehiculeHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleVehiculeButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

    public void handleParkingHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleParkngButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

    public void handleReservationHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleReservationButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

    public void handleContratsHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
        Parent root = loader.load();

        windowsSwitcher switcher = loader.getController();
        switcher.handleContratButton(actionEvent);

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }
}
