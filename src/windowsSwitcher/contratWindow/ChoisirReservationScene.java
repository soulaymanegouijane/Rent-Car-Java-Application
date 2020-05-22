package windowsSwitcher.contratWindow;

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

public class ChoisirReservationScene implements Initializable {
    public JFXButton choisirButton;
    public TableView reservationTableView;
    public TableColumn col_code_reservation;
    public TableColumn col_date_depart;
    public TableColumn col_date_retour;
    public TableColumn col_statut;
    public TableColumn col_nbr_contrats;
    public Label errorMessage;
    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill reservationTableView
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(reservationTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*reservation selectedReservation = reservationTableView.getSelectionModel().getSelectedItems().get(0);
            int codeReservationSelected = selectedClient.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contratWindow.fxml"));
            ContratWindow contratWindow = loader.getController();

            contratWindow.textFeildChoisirClient.setText(codeReservationSelected);
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
