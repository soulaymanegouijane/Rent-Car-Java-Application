package InterfaceDetails;

import Entities.Contrat;
import Test.H;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsulterContrat implements Initializable {
    public AnchorPane contratAnchorPane;
    public TextField idContrat;
    public DatePicker dateContratDatePicker;
    public TextField reservationTextField;
    public TextField vehiculeTextField;
    public TextField prixParJoursTextFeild;
    public DatePicker dateDepartDatePicker;
    public TextField nombreDeJoursTextField;
    public DatePicker dateRetourDatePicker;
    public TextField montantReservationTextField;
    public TextField avanceTextField;
    public JFXButton fermerButton;
    public TextField UtilisateurTextField;
    public TextField clientTextField;


    public static Contrat contrat = new Contrat();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        H.setfrenchDatePicker(dateContratDatePicker);
        H.setfrenchDatePicker(dateDepartDatePicker);
        H.setfrenchDatePicker(dateRetourDatePicker);
        fillBlanks();
    }

    public void fillBlanks(){
        idContrat.setText(String.valueOf(contrat.getIdContrat()));
        dateContratDatePicker.setValue(H.convert(contrat.getDateContrat()));
        UtilisateurTextField.setText(contrat.getUtilisateur().getIdUtilisateur());
        reservationTextField.setText(String.valueOf(contrat.getReservation().getIdReservation()));
        clientTextField.setText(contrat.getReservation().getClient().getIdClient());
        vehiculeTextField.setText(contrat.getVehicule().getIdVehicule());
        prixParJoursTextFeild.setText(String.valueOf(contrat.getPrix_jour()));
        dateDepartDatePicker.setValue(H.convert(contrat.getDate_sortie()));
        nombreDeJoursTextField.setText(String.valueOf(contrat.getNbr_jour()));
        dateRetourDatePicker.setValue(H.convert(contrat.getDate_retour()));
        montantReservationTextField.setText(String.valueOf(contrat.getMontantTotal()));
        avanceTextField.setText(String.valueOf(contrat.getRemise()));
    }

}
