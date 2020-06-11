package AjouterReservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import Entities.Reservation;
import Entities.TypeReservation;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.contratWindow.ChoisirReservationScene;
import windowsSwitcher.windowsSwitcher;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AjouterReservation implements Initializable {

    public Label erreurMessage;
    @FXML
    private JFXButton closeButton;

    @FXML
    private TextField idReservation;

    @FXML
    private TextField idUtilisateur; //ce champ ajouté 

    @FXML
    private TextField vehicule;

    @FXML
    private JFXButton parcourrirButton1;

    @FXML
    private TextField client;

    @FXML
    private JFXButton parcourrirButton2;

    @FXML
    private TextField avanceTextField;

    @FXML
    private JFXComboBox<String> typeReservation;

    @FXML
    private JFXButton submit;
    
    @FXML
    private DatePicker dateReservation;

    public Reservation reservation = new Reservation();

    ObservableList<String> type_reservation = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	long nouveauIdReservation = getMaxReservation() + 1;
    	idReservation.setText(String.valueOf(nouveauIdReservation));
        getTypeReservation();
        H.setfrenchDatePicker(dateReservation);
        comboBox();
        dateReservation.setValue(LocalDate.from(LocalDateTime.now()));
        idUtilisateur.setText(windowsSwitcher.loggedInUser.getIdUtilisateur());
    }

    public void comboBox() {
        typeReservation.setItems(type_reservation);
    }

    String str = null;
    
    @FXML
    void btnChoisirClient(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirClientScene.fxml"));
    	Parent root = null;
    	try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		ChoisirClientScene choisirVehicule = loader.getController();
		client.setText(choisirVehicule.cinClientSelected);
		str = client.getText();
		System.out.println("----------------> "+str);
    }

    @FXML
    void btnChoisirVehicule(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirVehiculeScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirVehiculeScene choisirClient = loader.getController();
        vehicule.setText(choisirClient.matriculeVehicule);
    }

    @FXML
    void addReservation(ActionEvent event) {
    	erreurMessage.setVisible(false);

    	if (testEmpty()){
            erreurMessage.setVisible(true);
        }else{
            reservation.setIdReservation(Long.parseLong(idReservation.getText()));
            reservation.setDatReservation(dateReservation.getEditor().getText());
            reservation.setVehicule(H.vehicule.getById(vehicule.getText()));
            reservation.setClient(H.client.getById(str));
            reservation.setStatus(H.status.getById(1));
            reservation.setUtilisateur(H.utilisateur.getById(idUtilisateur.getText()));
            reservation.setAvance(Float.parseFloat(avanceTextField.getText()));
            reservation.setTypeRes(H.typeres.get(typeReservation.getValue()));

            int result = H.reservation.add(reservation);

            if (result != 0) {
                System.out.println("l'ajout d'une reservation est bien fait");
            } else {
                System.out.println("il y a un prb dans l'ajout d'une reservation");
            }

            Stage stage =(Stage) closeButton.getScene().getWindow();
            stage.close();
        }
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
                type_reservation.add(libelle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public long getMaxReservation() {
    	Connection con = Abst.getConnection();
    	String sql = "select max(idReservation) as Res from reservation";
    	long max = 0;
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				max = rs.getLong("Res");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return max;
    }

    public boolean testEmpty(){
        if (idReservation.getText().isEmpty() || dateReservation.getEditor().getText().isEmpty()
                || vehicule.getText().isEmpty() || client.getText().isEmpty() || idUtilisateur.getText().isEmpty()
                || avanceTextField.getText().isEmpty() || typeReservation.getValue() == null)return true;
        return false;
    }
}
