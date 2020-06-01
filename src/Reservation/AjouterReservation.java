package Reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterReservation implements Initializable {

    @FXML
    private JFXButton closeButton;

    @FXML
    private TextField idReservation;

    @FXML
    private TextField vehicule;

    @FXML
    private JFXButton parcourrirButton1;

    @FXML
    private TextField client;

    @FXML
    private JFXButton parcourrirButton2;

    @FXML
    private TextField avance;

    @FXML
    private JFXComboBox<String> typeReservation;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton contratCreate;
    @FXML
    private DatePicker dateReservation;
    @FXML
    private DatePicker dateDepart;
    @FXML
     private DatePicker dateRetour;

    ObservableList<String> typelist = FXCollections.observableArrayList("Par Appel","A l'agence");
    ObservableList<String> type = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getTypeReservation();
        comboBox();
    }

    @FXML
    void addReservation(ActionEvent event) {

    }

    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void getTypeReservation() {
        Connection con = Abst.getConnection();
        String sql = "select libelle from type_reservation";
        String libelle = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                libelle = rs.getString("libelle");
                type.add(libelle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void comboBox() {
        typeReservation.setItems(typelist);
    }
}
