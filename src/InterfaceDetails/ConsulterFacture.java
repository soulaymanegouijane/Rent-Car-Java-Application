package InterfaceDetails;

import Entities.Contrat;
import Entities.Factures;
import Entities.Reservation;
import Test.H;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ConsulterFacture implements Initializable {
    public AnchorPane factureAnchorPane;
    public TextField idFactureTextField;
    public DatePicker dateFactureDatePicker;
    public TextField ContratTextField;
    public TextField reservationTextField;
    public TextField clientTextField;
    public TextField vehiculeTextField;
    public TextField nombreDeJoursTextField;
    public TextField montantReservationTextField;
    public TextField avanceTextField;
    public TextField joursRetartTextField;
    public TextField montantSanctionTextField;
    public TextField MantantTotaleTextField;
    public Label erreurMessage;
    public JFXButton imprimerButton;
    public JFXButton FermerButton;

    public static Factures facture = new Factures();
    Contrat contrat = new Contrat();
    Reservation reservation = new Reservation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contrat = facture.getContrat();
        reservation = contrat.getReservation();
        H.setfrenchDatePicker(dateFactureDatePicker);
        fillBlanks();
    }

    public void fillBlanks(){
        idFactureTextField.setText(String.valueOf(facture.getIdFacture()));
        dateFactureDatePicker.setValue(H.convert(facture.getDateFacture()));
        ContratTextField.setText(String.valueOf(contrat.getIdContrat()));
        reservationTextField.setText(String.valueOf(reservation.getIdReservation()));
        clientTextField.setText(reservation.getClient().getIdClient());
        vehiculeTextField.setText(contrat.getVehicule().getIdVehicule());
        nombreDeJoursTextField.setText(String.valueOf(facture.getNbr_jours()));
        montantReservationTextField.setText(String.valueOf(reservation.getMontant()));
        avanceTextField.setText(String.valueOf(reservation.getAvance()));
        joursRetartTextField.setText(String.valueOf(facture.getNbrJoursRetard()));
        montantSanctionTextField.setText(String.valueOf(facture.getMontantSanction()));
        MantantTotaleTextField.setText(String.valueOf(facture.getMontantTTC()));
    }

    public void handleImprimerButton(ActionEvent actionEvent) {
    }
}
