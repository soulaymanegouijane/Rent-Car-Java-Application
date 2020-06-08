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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.contratWindow.ChoisirReservationScene;

import java.io.IOException;
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
    private JFXButton parcourrirButtonUser;

    @FXML
    private TextField avance;

    @FXML
    private JFXComboBox<String> typeReservation;

    @FXML
    private JFXButton submit;
    
    @FXML
    private DatePicker dateReservation;
    
    @FXML
    private DatePicker dateDepart;
    
    @FXML
     private DatePicker dateRetour;

    //ObservableList<String> typelist = FXCollections.observableArrayList("Par Appel","A l'agence");
    ObservableList<String> type_reservation = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	long nouveauIdReservation = getMaxReservation() + 1;
    	idReservation.setText(String.valueOf(nouveauIdReservation));
        getTypeReservation();
        comboBox();
        
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
    void btnChoisirUser(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirUtilisateurScene.fxml"));
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
		
		ChoisirUtilisateurScene choisirUser = loader.getController();
		idUtilisateur.setText(choisirUser.cinUtilisateurSelected);
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
    	String rese = idReservation.getText();
    	String avc = avance.getText();
    	String dtReservation = ((TextField)dateReservation.getEditor()).getText();
    	String dtRetour = ((TextField)dateRetour.getEditor()).getText();
    	String dtDepart = ((TextField)dateDepart.getEditor()).getText();
    	String matricule = vehicule.getText();
    	String Client = client.getText();
    	String user = idUtilisateur.getText();
    	String type = typeReservation.getValue();
    	TypeReservation tpRes = H.typeres.get(type);
    	
    	Reservation reservation = new Reservation();
    	
    	reservation.setAvance(Float.valueOf(avc));
    	reservation.setDatReservation(dtReservation);
    	reservation.setDate_depart(dtDepart);
    	reservation.setDate_retour(dtRetour);
    	reservation.setVehicule(H.vehicule.getById(matricule));
    	reservation.setClient(H.client.getById(str));
    	reservation.setStatus(H.status.getById(1));
    	reservation.setUtilisateur(H.utilisateur.getById(user));
    	reservation.setTypeRes(tpRes);
    	
    	int result = H.reservation.add(reservation);
    	
    	if (result != 0) {
			System.out.println("l'ajout d'une reservation est bien fait");
		} else {
			System.out.println("il y a un prb dans l'ajout d'une reservation");
		}
    	
    	Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
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

    public void comboBox() {
        typeReservation.setItems(type_reservation);
    }
}
