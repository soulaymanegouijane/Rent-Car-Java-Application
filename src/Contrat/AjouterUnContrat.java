package Contrat;

import AbstactClasses.Abst;
import Entities.Reservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AjouterReservation.ChoisirUtilisateurScene;
import Entities.Contrat;
import Test.H;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.windowsSwitcher;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AjouterUnContrat implements Initializable {

    public DatePicker dateContratDatePicker;
    public TextField reservationTextField;
    public TextField vehiculeTextField;
    public TextField prixParJoursTextFeild;
    public DatePicker dateDepartDatePicker;
    public TextField nombreDeJoursTextField;
    public DatePicker dateRetourDatePicker;
    public TextField montantReservationTextField;
    public TextField avanceTextField;
    public JFXButton submitBtn;
    public Label erreurMessage;
    public TextField UtilisateurTextField;
    public TextField clientTextField;
    public Label erreurDate;

    @FXML
    private JFXButton closeButton;

    @FXML
    private TextField idContrat;

    @FXML
    private JFXButton parcourrirButton;

    public static Reservation reservation = null;

    public Contrat contrat = new Contrat();

    public boolean addwithSucces = false;
    
    public boolean showAlert = false;
	    
    @FXML
    private void closeButtonAction(){
        reservation = null;
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addwithSucces = false;
        H.setfrenchDatePicker(dateContratDatePicker);
        H.setfrenchDatePicker(dateDepartDatePicker);
        H.setfrenchDatePicker(dateRetourDatePicker);
        dateContratDatePicker.setValue(LocalDate.from(LocalDateTime.now()));
        idContrat.setText(String.valueOf(getIdContrat() + 1));
        UtilisateurTextField.setText(windowsSwitcher.loggedInUser.getIdUtilisateur());
        if(reservation != null){
            reservationTextField.setText(String.valueOf(reservation.getIdReservation()));
            parcourrirButton.setVisible(false);
            fillReservationRelatedFields();
        }

        nombreDeJoursTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            LocalDate dateDepat = null;
            LocalDate dateRetour = null;
            Double mantant = 0.0;
            if (!nombreDeJoursTextField.getText().isEmpty() && !prixParJoursTextFeild.getText().isEmpty()){
                mantant = Long.parseLong(newValue) * Double.parseDouble(prixParJoursTextFeild.getText());
                montantReservationTextField.setText(String.valueOf(mantant));
                if (dateDepartDatePicker.getValue() != null){
                    dateDepat = H.convert(dateDepartDatePicker.getEditor().getText());
                    dateRetour = dateDepat.plusDays(Long.parseLong(newValue));
                    dateRetourDatePicker.setValue(dateRetour);
                }else {
                    dateRetourDatePicker.setValue(null);
                }
            }else{
                montantReservationTextField.setText(null);
                dateRetourDatePicker.setValue(null);
            }
        });

        dateDepartDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null){
                    int days = Period.between(newValue,H.convert(dateContratDatePicker.getEditor().getText())).getDays();
                    if (days <= 0){
                        erreurDate.setVisible(false);
                        submitBtn.setDisable(false);
                    }else {
                        erreurDate.setVisible(true);
                        submitBtn.setDisable(true);
                    }

                    if(!nombreDeJoursTextField.getText().isEmpty()) {
                        dateRetourDatePicker.setValue(newValue.plusDays(Long.parseLong(nombreDeJoursTextField.getText())));
                    }else {
                        dateRetourDatePicker.setValue(null);
                    }
                }else {
                    submitBtn.setDisable(true);
                }
            }
        });

        /*dateDepartDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                int days = Period.between(H.convert(dateDepartDatePicker.getEditor().getText()),H.convert(dateContratDatePicker.getEditor().getText())).getDays();
                if (days <= 0){
                    erreurDate.setVisible(false);
                    submitBtn.setDisable(false);
                }else {
                    erreurDate.setVisible(true);
                    submitBtn.setDisable(true);
                }
            }else {
                submitBtn.setDisable(true);
            }

            if(!nombreDeJoursTextField.getText().isEmpty()) {
                dateRetourDatePicker.setValue(newValue.plusDays(Long.parseLong(nombreDeJoursTextField.getText())));
            }else {
                dateRetourDatePicker.setValue(null);
            }
        });*/
    }


    public long getIdContrat() {
        Connection con = Abst.getConnection();
        String sql = "select max(idContrat) as maxIdContrat from contrat";
        long maxIdContrat = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                maxIdContrat = rs.getLong("maxIdContrat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxIdContrat;
    }

    @FXML
    void btnReservation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoisirReservationScene.fxml"));
        Parent root = loader.load();;
        
        Stage stage = new Stage();
        assert root != null;
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    		
        ChoisirReservationScene choisirReservation = loader.getController();
        reservationTextField.setText(choisirReservation.idReservationSelected);
        reservationTextFieldAction(event);
    }

    public void fillReservationRelatedFields(){
        clientTextField.setText(reservation.getClient().getIdClient());
        vehiculeTextField.setText(reservation.getVehicule().getIdVehicule());
        prixParJoursTextFeild.setText(String.valueOf(reservation.getVehicule().getPrixJours()));
    }

    public void reservationTextFieldAction(ActionEvent actionEvent) {
        if(!reservationTextField.getText().isEmpty()){
            Long idReservationSelected = Long.parseLong(reservationTextField.getText());
            reservation = H.reservation.getById(idReservationSelected);
            fillReservationRelatedFields();
        }
    }

    public void handleSubmitBtn(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);
        if (testEmpty()){
            erreurMessage.setVisible(true);
        }else{
        	try {
        		contrat.setReservation(H.reservation.getById(Long.parseLong(reservationTextField.getText())));
        		contrat.setPrix_jour(Float.parseFloat(prixParJoursTextFeild.getText()));
        		contrat.setMontantTotal(Double.parseDouble(montantReservationTextField.getText()));
        		contrat.setRemise(Float.parseFloat(avanceTextField.getText()));
        		contrat.setNbr_jour(Integer.parseInt(nombreDeJoursTextField.getText()));
			} catch (Exception e) {
				showAlert = true;
			}
            contrat.setIdContrat(Long.parseLong(idContrat.getText()));
            contrat.setDateContrat(dateContratDatePicker.getEditor().getText());
            contrat.setUtilisateur(H.utilisateur.getById(UtilisateurTextField.getText()));
            contrat.setVehicule(H.vehicule.getById(vehiculeTextField.getText()));
            contrat.setDate_sortie(dateDepartDatePicker.getEditor().getText());
            contrat.setDate_retour(dateRetourDatePicker.getEditor().getText());


            int result = H.contrat.add(contrat);
            if (result == 0){
                contrat = null;
            }else {
                addwithSucces = true;
            }

            // regarder �a

            Stage stage =(Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

    public boolean testEmpty(){
        if (idContrat.getText().isEmpty() || dateContratDatePicker.getValue() == null || reservationTextField.getText().isEmpty()
                || vehiculeTextField.getText().isEmpty() || prixParJoursTextFeild.getText().isEmpty()
                || dateDepartDatePicker.getValue() == null || nombreDeJoursTextField.getText().isEmpty()
                || dateRetourDatePicker.getValue() == null || montantReservationTextField.getText().isEmpty()
                || avanceTextField.getText().isEmpty() || UtilisateurTextField.getText().isEmpty() || clientTextField.getText().isEmpty())
            return true;
        return false;
    }
}




