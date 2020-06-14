package InterfaceDetails;

import AbstactClasses.Abst;
import Entities.Contrat;
import Entities.Factures;
import Entities.Parking;
import Entities.Reservation;
import Test.H;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class TerminerReservation implements Initializable {
    public AnchorPane factureAnchorPane;
    public TextField idFactureTextField;
    public DatePicker dateFactureDatePicker;
    public TextField ContratTextField;
    public TextField reservationTextField;
    public TextField clientTextField;
    public TextField vehiculeTextField;
    public TextField kilometrageTextField;
    public JFXComboBox<String> parkingComboBox;
    public TextField montantReservationTextField;
    public TextField avanceTextField;
    public TextField joursRetartTextField;
    public TextField montantSanctionTextField;
    public TextField MantantTotaleTextField;
    public JFXButton GenererButton;
    public JFXButton AnnulerButton;
    public Label kilometrageErreur;
    public Label erreurMessage;
    public TextField nombreDeJoursTextField;

    Factures facture = new Factures();
    public static Contrat contrat = new Contrat();
    Reservation reservation = new Reservation();

    public static boolean factureSeccusfullyAdd = false;

    ObservableList<String> ParkingList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        factureSeccusfullyAdd = false;
        reservation = contrat.getReservation();
        H.setfrenchDatePicker(dateFactureDatePicker);
        parking_base_donnee();
        parkingComboBox.setItems(ParkingList);
        fillBlanks();

        kilometrageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!kilometrageTextField.getText().isEmpty()){
                if(Integer.parseInt(kilometrageTextField.getText()) < reservation.getVehicule().getKilometrage()){
                    kilometrageErreur.setVisible(true);
                    GenererButton.setDisable(true);
                }else{
                    kilometrageErreur.setVisible(false);
                    GenererButton.setDisable(false);
                }
            }else {
                kilometrageErreur.setVisible(false);
                GenererButton.setDisable(true);
            }
        });
    }

    public long getIdFacture() {
        Connection con = Abst.getConnection();
        String sql = "select max(idFacture) as maxIdFacture from facture";
        long maxIdFacture = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                maxIdFacture = rs.getLong("maxIdFacture");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxIdFacture;
    }

    public void fillBlanks(){
        idFactureTextField.setText(String.valueOf(getIdFacture() + 1));
        dateFactureDatePicker.setValue(LocalDate.from(LocalDateTime.now()));
        ContratTextField.setText(String.valueOf(contrat.getIdContrat()));
        reservationTextField.setText(String.valueOf(reservation.getIdReservation()));
        clientTextField.setText(reservation.getClient().getIdClient());
        vehiculeTextField.setText(contrat.getVehicule().getIdVehicule());
        nombreDeJoursTextField.setText(String.valueOf(reservation.getNombreJours()));
        montantReservationTextField.setText(String.valueOf(reservation.getMontant()));
        avanceTextField.setText(String.valueOf(reservation.getAvance()));
        Long days = ChronoUnit.DAYS.between(H.convert(reservation.getDate_retour()),LocalDate.from(LocalDateTime.now()));
        joursRetartTextField.setText(String.valueOf(days));
        Long montantSanction = days * 2000;
        montantSanctionTextField.setText(String.valueOf(montantSanction));
        float montantTotal = reservation.getMontant() + montantSanction - reservation.getAvance();
        MantantTotaleTextField.setText(String.valueOf(montantTotal));
    }

    public void parking_base_donnee() {
        ResultSet tous_les_parking = null;
        try {
            String sql = "select * from parking where capacite>nbr_place_pleinne";
            Connection con = Abst.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            tous_les_parking = ps.executeQuery();
            while(tous_les_parking.next()) {
                Parking parking = new Parking();
                parking.setAdress(tous_les_parking.getString("adress"));
                String adr = parking.getAdress();
                ParkingList.add(adr);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleGenererButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);
        if (testEmpty()){
            erreurMessage.setVisible(true);
        }else{
            facture.setIdFacture(Long.parseLong(idFactureTextField.getText()));
            facture.setContrat(contrat);
            facture.setDateFacture(dateFactureDatePicker.getEditor().getText());
            facture.setMontantTTC(Double.parseDouble(MantantTotaleTextField.getText()));
            facture.setMontantSanction(Float.parseFloat(montantSanctionTextField.getText()));
            facture.setNbr_jours(Integer.parseInt(nombreDeJoursTextField.getText()));
            facture.setNbrJoursRetard(Integer.parseInt(joursRetartTextField.getText()));

            H.facture.add(facture);

            contrat.getVehicule().setDispo("Disponible");
            H.vehicule.edit(contrat.getVehicule());
            factureSeccusfullyAdd = true;

            Stage stage =(Stage) GenererButton.getScene().getWindow();
            stage.close();
        }
    }

    public void handleAnnulerBtn(ActionEvent actionEvent) {
        factureSeccusfullyAdd = false;
        Stage stage =(Stage) AnnulerButton.getScene().getWindow();
        stage.close();
    }

    public boolean testEmpty(){
        return kilometrageTextField.getText().isEmpty() || parkingComboBox.getValue() == null;
    }

}
