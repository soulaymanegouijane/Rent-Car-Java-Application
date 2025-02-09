package Contrat;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Client;
import Entities.Reservation;
import Entities.Status;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChoisirReservationScene implements Initializable {
	
	@FXML
    private JFXButton choisirButton;

    @FXML
    private TableView<Reservation> reservationTableView;

    @FXML
    private TableColumn<Reservation,Integer> col_code_reservation;

    @FXML
    private TableColumn<Reservation,String> col_vehicule;

    @FXML
    private TableColumn<Reservation,String> col_client;

    @FXML
    private TableColumn<Reservation,String> col_statut;

    @FXML
    private TableColumn<Reservation,Integer> col_nbr_contrats;

    @FXML
    private Label errorMessage;

    @FXML
    private Button closeButton;
    
    ObservableList<Reservation> reservation_list = FXCollections.observableArrayList();
    
    public String idReservationSelected = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	remplir_tableau();
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(reservationTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
        	Reservation selectedReservation= reservationTableView.getSelectionModel().getSelectedItem();
            idReservationSelected = String.valueOf(selectedReservation.getIdReservation());

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public void remplir_tableau() {
    	ResultSet rs = null;
		try {
			String sql = "select * from reservation where idStatus=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, 1);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setIdReservation(rs.getLong("idReservation"));
				res.setMatriculeVehicule(H.vehicule.getById(rs.getString("idVehicule")).getIdVehicule());
				res.setCinClient(H.client.getById(rs.getString("idClient")).getIdClient());
				res.setStatusRes(H.status.getById(rs.getLong("idStatus")).getLibelle());
				reservation_list.add(res);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		col_code_reservation.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
        col_vehicule.setCellValueFactory(new PropertyValueFactory<>("matriculeVehicule"));
        col_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
        col_statut.setCellValueFactory(new PropertyValueFactory<>("statusRes"));
        
        reservationTableView.setItems(reservation_list);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
