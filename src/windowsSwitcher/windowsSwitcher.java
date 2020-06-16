package windowsSwitcher;

import Entities.Utilisateur;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import windowsSwitcher.compteWindow.CompteWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class windowsSwitcher implements Initializable {
	
	@FXML
    private JFXButton acceuilButton;

    @FXML
    private JFXButton utilisateurButton;

    @FXML
    private JFXButton clientButton;

    @FXML
    private JFXButton vehiculesButton;

    @FXML
    private JFXButton parkingButton;

    @FXML
    private JFXButton reservationsButton;

    @FXML
    private JFXButton contratsButton;

    @FXML
    private JFXButton compteButton;

    @FXML
    private JFXButton deconnexionButton;

    @FXML
    private AnchorPane windowSpace;

    public static Utilisateur loggedInUser = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (loggedInUser.getRole().getRole().equals("employee")){
            utilisateurButton.setDisable(true);
        }
    }

    public void handleAcceuilButton() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../homePage/homePage.fxml"));
        Parent root = loader.load();

        Scene home = new Scene(root);
        Stage stage = (Stage) acceuilButton.getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

    public void handleUtilisateurButton(ActionEvent actionEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("utilisateurWindow/utilisateurWindow.fxml"));
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(true);
        clientButton.setCancelButton(false);
        vehiculesButton.setCancelButton(false);
        parkingButton.setCancelButton(false);
        reservationsButton.setCancelButton(false);
        contratsButton.setCancelButton(false);
        compteButton.setCancelButton(false);
    }

    public void handleClientButton(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("clientWindow/clientWindow.fxml"));
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(false);
        clientButton.setCancelButton(true);
        vehiculesButton.setCancelButton(false);
        parkingButton.setCancelButton(false);
        reservationsButton.setCancelButton(false);
        contratsButton.setCancelButton(false);
        compteButton.setCancelButton(false);
    }

    public void handleVehiculeButton(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("vehiculeWindow/vehiculeWindow.fxml"));
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(false);
        clientButton.setCancelButton(false);
        vehiculesButton.setCancelButton(true);
        parkingButton.setCancelButton(false);
        reservationsButton.setCancelButton(false);
        contratsButton.setCancelButton(false);
        compteButton.setCancelButton(false);
    }

    public void handleParkngButton(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("parkingWindow/parkingWindow.fxml"));
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(false);
        clientButton.setCancelButton(false);
        vehiculesButton.setCancelButton(false);
        parkingButton.setCancelButton(true);
        reservationsButton.setCancelButton(false);
        contratsButton.setCancelButton(false);
        compteButton.setCancelButton(false);
    }

    public void handleReservationButton(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("reservationWindow/reservationWindow.fxml"));
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(false);
        clientButton.setCancelButton(false);
        vehiculesButton.setCancelButton(false);
        parkingButton.setCancelButton(false);
        reservationsButton.setCancelButton(true);
        contratsButton.setCancelButton(false);
        compteButton.setCancelButton(false);
    }

    public void handleContratButton(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("contratWindow/contratWindow.fxml"));
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(false);
        clientButton.setCancelButton(false);
        vehiculesButton.setCancelButton(false);
        parkingButton.setCancelButton(false);
        reservationsButton.setCancelButton(false);
        contratsButton.setCancelButton(true);
        compteButton.setCancelButton(false);
    }

    public void handleCompteButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("compteWindow/compteWindow.fxml"));

        CompteWindow.User = loggedInUser;

        AnchorPane pane = loader.load();
        windowSpace.getChildren().setAll(pane);

        acceuilButton.setCancelButton(false);
        utilisateurButton.setCancelButton(false);
        clientButton.setCancelButton(false);
        vehiculesButton.setCancelButton(false);
        parkingButton.setCancelButton(false);
        reservationsButton.setCancelButton(false);
        contratsButton.setCancelButton(false);
        compteButton.setCancelButton(true);
    }

    public void handleDeconnexionButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../loginPage/loginMain.fxml"));
        Parent root = loader.load();

        Scene home = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(home);
        stage.show();
    }

}
