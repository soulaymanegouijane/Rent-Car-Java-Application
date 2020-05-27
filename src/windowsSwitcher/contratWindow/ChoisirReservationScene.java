package windowsSwitcher.contratWindow;

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
    private TableColumn<Reservation,String> col_date_depart;

    @FXML
    private TableColumn<Reservation,String> col_date_retour;

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
        // Fill reservationTableView
    	remplir_tableau();
    }

    public void handleChoisirButton(ActionEvent actionEvent) {
        errorMessage.setVisible(false);
        if(reservationTableView.getSelectionModel().getSelectedItem() == null){
            errorMessage.setVisible(true);
        }else{
            // Get selected row

            /*reservation selectedReservation = reservationTableView.getSelectionModel().getSelectedItems().get(0);
            int codeReservationSelected = selectedClient.toString().split(",")[0].substring(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contratWindow.fxml"));
            ContratWindow contratWindow = loader.getController();

            contratWindow.textFeildChoisirClient.setText(codeReservationSelected);
             */
        	
        	Reservation selectedReservation= reservationTableView.getSelectionModel().getSelectedItem();
            idReservationSelected = String.valueOf(selectedReservation.getIdReservation());

            Stage stage = (Stage) choisirButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public void remplir_tableau() {
    	ResultSet rs = null;
		try {
			String sql = "select * from reservation";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setIdReservation(rs.getLong("idReservation"));
				res.setDate_depart(rs.getString("date_depart"));
				res.setDate_retour(rs.getString("date_retour"));
				Status s = H.status.getById(H.reservation.getStatus(rs.getLong("idStatus")));
				res.setStatusRes(s.getLibelle());
				reservation_list.add(res);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		col_code_reservation.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
        col_date_depart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        col_date_retour.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
        col_nbr_contrats.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));//e dois cree un attribue pour ce champs
        col_statut.setCellValueFactory(new PropertyValueFactory<>("statusRes"));
        
        reservationTableView.setItems(reservation_list);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
