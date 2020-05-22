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

public class ChoisirUtilisateurScene implements Initializable {
    public JFXButton choisirButton;
    public TableView utilisateurTableView;
    public TableColumn col_cin;
    public TableColumn col_nom;
    public TableColumn col_prenom;
    public TableColumn col_tele;
    public TableColumn col_etat_compte;
    public Label errorMessage;
    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill utilisateurTableView
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(utilisateurTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*utilisateur selectedUtilisateur= utilisateurTableView.getSelectionModel().getSelectedItems().get(0);
            int cinUtilisateurSelected = selectedUtilisateur.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservationWindow.fxml"));
            ReservationWindow reservationWindow = loader.getController();

            reservationWindow.textFeildChoisirUtilisateur.setText(cinUtilisateurSelected);
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
