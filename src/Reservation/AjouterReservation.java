package Reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterReservation implements Initializable {
           /* ---------- Les fx Ids ---------*/
            @FXML
            private JFXButton closeButton ; // boutton Annuler
            @FXML
            private DatePicker dateReservation ;//datepicker de la date de reservation
            @FXML
            private DatePicker dateDepart;//datepicker de la date de depart
            @FXML
            private DatePicker dateRetour;//datepicker de la date de retour de la voiture
            @FXML
            private TextField idReservation ; //id de la reservation auto-increment
            @FXML
            private TextField vehicule ; //vehicule concerné par la reservation
            @FXML
            private JFXButton parcourrirButton1 ;// boutton du parcourrir de la voiture
            @FXML
            private TextField client;//client qui a placé la reservation
            @FXML
            private JFXButton parcourrirButton2;/* bouton pour parcourrir les clients (à discuter!!!!!!!!) */
            //TODO discuter le cas si la personne veut placer la reservation sans devenir client
            @FXML
            private TextField avance;// Avance payé par le client
            @FXML
            private JFXButton submit ;// Boutton d'enregistrement
            @FXML
            private JFXButton contratCreate ;// Boutton pour créer un contrat sur place
             @FXML
             public JFXComboBox<String> typeReservation ;
            /* ---------- end of Les fx Ids ---------*/
            /*---------- Methods ---------*/
        ObservableList<String> typelist = FXCollections.observableArrayList("Par Appel","A l'agence");
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            typeReservation.setItems(typelist);
        }
        @FXML
    public  void  closeButtonAction(){
            Stage stage =(Stage) closeButton.getScene().getWindow();
            stage.close();
        }
        //les codes des autres bouttons




       /*---------- End of Methods ---------*/
    }


