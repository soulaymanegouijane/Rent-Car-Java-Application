package windowsSwitcher.reservationWindow;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoisirVehiculeScene implements Initializable {
    public JFXButton choisirButton;
    public TableView vehiculeTableView;
    public TableColumn col_matricule;
    public TableColumn col_type;
    public TableColumn col_marque;
    public TableColumn col_kilometrage;
    public TableColumn col_disponibilite;
    public Label errorMessage;
    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill vehiculeTableView
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(vehiculeTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*vehicule selectedVehicule= vehiculeTableView.getSelectionModel().getSelectedItems().get(0);
            int matriculeSelected = selectedVehicule.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contratWindow.fxml"));
            ContratWindow contratWindow = loader.getController();

            contratWindow.textFeildChoisirVehicule.setText(matriculeSelected);
             */

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
