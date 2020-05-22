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

public class ChoisirClientScene implements Initializable {
    public JFXButton choisirButton;
    public TableView clientTableView;
    public TableColumn col_cin;
    public TableColumn col_nom;
    public TableColumn col_prenom;
    public TableColumn col_tele;
    public TableColumn col_adresse;
    public Label errorMessage;
    public Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill clientTableView
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(clientTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*client selectedClient= clientTableView.getSelectionModel().getSelectedItems().get(0);
            int cinClientSelected = selectedClient.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contratWindow.fxml"));
            ContratWindow contratWindow = loader.getController();

            contratWindow.textFeildChoisirClient.setText(cinClientSelected);
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
