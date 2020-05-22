package windowsSwitcher.vehiculeWindow;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import windowsSwitcher.windowsSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoisirParkingScene implements Initializable {
    public JFXButton choisirButton;
    public TableView parkingTableView;
    public TableColumn col_nParking;
    public TableColumn col_nom;
    public TableColumn col_capacite;
    public TableColumn col_places_vides;
    public TableColumn col_adresse;
    public Button closeButton;
    public Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill parkingTableView
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(parkingTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*parking selectedParking = parkingTableView.getSelectionModel().getSelectedItems().get(0);
            int nParkingSelected = selectedParking.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vehiculeWindow.fxml"));
            VehiculeWindow vehiculeWindow = loader.getController();

            vehiculeWindow.textFeildChoisirParking.setText(nParkingSelected);
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
