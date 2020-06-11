package windowsSwitcher.reservationWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Parking;
import Entities.Reservation;
import Entities.Status;
import Entities.Type;
import Entities.TypeReservation;
import InterfaceDetails.DetailReservation;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationWindow implements Initializable {
    public HBox hBoxChoisirVehicule;
    public TextField textFeildChoisirVehicule;
    public JFXButton buttonChoisirVehicule;
    
    String searchSection = null;
    @FXML
    private ComboBox<String> chercherComboBox;

    @FXML
    private AnchorPane inputSearchArea;

    @FXML
    private TextField idTextFeild;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private HBox hBoxChoisirClient;

    @FXML
    private TextField textFeildChoisirClient;

    @FXML
    private JFXButton buttonChoisirClient;

    @FXML
    private HBox hBoxChoisirUtilisateur;

    @FXML
    private TextField textFeildChoisirUtilisateur;

    @FXML
    private JFXButton buttonChoisirUtilisateur;

    @FXML
    private ComboBox<String> statutComboBox;

    @FXML
    private TextField nbrContratTextFeild;

    @FXML
    private Button chercherButton;

    @FXML
    private Label ErreurMessage;

    @FXML
    private TableView<Reservation> tableReservation;

    @FXML
    private TableColumn<Reservation,Long> col_id;

    @FXML
    private TableColumn<Reservation,String> col_type;

    @FXML
    private TableColumn<Reservation,String> col_date_depart;

    @FXML
    private TableColumn<Reservation,String> col_date_retour;

    @FXML
    private TableColumn<Reservation,Long> col_id_client;

    @FXML
    private TableColumn<Reservation,Long> col_id_utilisateur;

    @FXML
    private TableColumn<Reservation,String> col_status;

    @FXML
    private TableColumn<Reservation,Long> col_nbr_contrats;

    @FXML
    private Button ajouterReservationButton;

    @FXML
    private Button detailReservationButton;
        
    public Reservation reservationSelected;

    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "Id", "Type", "Date", "Vehicule", "Client", "Utilisateur", "Status");
    ObservableList<String> typeTypeList = FXCollections.observableArrayList("Reservation local", "Reservation telephonique");
    ObservableList<String> statutTypeList = FXCollections.observableArrayList("En cours", "Complete", "Annule");

    ObservableList<Reservation> reservation_list = FXCollections.observableArrayList();
    ObservableList<Reservation> mono_reservation = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detailReservationButton.setDisable(true);
        comboBox();
        mono_reservation.clear();
        reservation_list.clear();
        color_cell();
        remplir_tableau();
        color_cell();
        
    }
    
    public void color_cell() {
    	col_status.setCellFactory(column -> {
    	    return new TableCell<Reservation, String>() {
    	        @Override
    	        protected void updateItem(String item, boolean empty) {
    	            super.updateItem(item, empty);

    	            if (item == null || empty) {
    	                setText(null);
    	                setStyle("");
    	            } else {
    	            	
    	                if (item.equals("Terminer")) {
    	                	setText(item.toString());
    	                    setTextFill(Color.CHOCOLATE);
    	                    setStyle("-fx-font-weight: bold");
    	                } else {
    	                	setText(item.toString());
    	                    setTextFill(Color.GREEN);
    	                    //setStyle("-fx-background-color: yellow");
    	                }
    	            }
    	        }
    	    };
    	});
    }

    public void comboBox(){
        chercherComboBox.setValue("Chercher par:");
        chercherComboBox.setItems(searchTypeList);
        typeComboBox.setValue("Select :");
        typeComboBox.setItems(typeTypeList);
        statutComboBox.setValue("Select :");
        statutComboBox.setItems(statutTypeList);
    }

    public void remplir_tableau(){
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
				res.setCinClient(rs.getString("idClient"));
				res.setCinUtilisateur(rs.getString("idUtilisateur"));
				Status s = H.status.getById(H.reservation.getStatus(rs.getLong("idReservation")));
				res.setStatusRes(s.getLibelle());
				res.setTypeVehicule(H.type.getById(H.vehicule.getType(rs.getString("idVehicule"))).getLibelle());
				reservation_list.add(res);
				
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
        col_date_depart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        col_date_retour.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
        col_id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("statusRes"));
        
        tableReservation.setItems(reservation_list);
    }

    public void afficher_reservation(String valeur) {
    	searchSection = chercherComboBox.getValue();
    	ResultSet tous_les_reservation = null;
    	Connection con = Abst.getConnection();
		try {
	    	String sql = "";
	    	PreparedStatement ps = null;
	    	
			if(searchSection.equals("Client")) {
				sql = "select * from reservation where idClient=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Vehicule")) {
				sql = "select * from reservation where idVehicule=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Status")) {
				sql = "select * from reservation where idStatus=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Date")) {
				sql = "select * from reservation where dateReservation=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
				System.out.println("-*-*-*-*-*-*-*-> ???? " );
			}
			if(searchSection.equals("Utilisateur")) {
				sql = "select * from reservation where idUtilisateur=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Type")) {
				sql = "select * from reservation where idTypeRes=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.valueOf(valeur));
			}
			tous_les_reservation = ps.executeQuery();
			while(tous_les_reservation.next()) {
				System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*---->");
				Reservation res = new Reservation();
				res.setIdReservation(tous_les_reservation.getLong("idReservation"));
				res.setDate_depart(tous_les_reservation.getString("date_depart"));
				res.setDate_retour(tous_les_reservation.getString("date_retour"));
				res.setCinClient(tous_les_reservation.getString("idClient"));
				res.setCinUtilisateur(tous_les_reservation.getString("idUtilisateur"));
				Status s = H.status.getById(H.reservation.getStatus(tous_les_reservation.getLong("idReservation")));
				res.setStatusRes(s.getLibelle());
				res.setTypeVehicule(H.type.getById(H.vehicule.getType(tous_les_reservation.getString("idVehicule"))).getLibelle());
				mono_reservation.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
        col_date_depart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        col_date_retour.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
        col_id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("cinUtilisateur"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("statusRes"));
        
        tableReservation.setItems(mono_reservation);
    }
    
    public void handleAjouterReservationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterReservation/AjouterReservation.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        mono_reservation.clear();
        reservation_list.clear();
        remplir_tableau();
    }

    public void handleDetailReservationButton(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../InterfaceDetails/detailReservation.fxml"));
    	DetailReservation.reservation = reservationSelected;
        Parent root = loader.load();
        
        //FunctionAffiche(loader);
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        reservation_list.clear();
        //mono_reservation.clear();
        remplir_tableau();
    }
    
    public void FunctionAffiche(FXMLLoader loader) {
    	DetailReservation detail = loader.getController();
    	
    	detail.avance.setText(String.valueOf(reservationSelected.getAvance()));
    	detail.dateReservation.setValue(H.convert(reservationSelected.getDatReservation()));
    	detail.dateDepartDatePicker.setValue(H.convert(reservationSelected.getDate_depart()));
    	detail.dateRetourDatePicker.setValue(H.convert(reservationSelected.getDate_retour()));
    	detail.vehiculeTextField.setText(reservationSelected.getVehicule().getIdVehicule());
    	detail.client.setText(reservationSelected.getClient().getIdClient());
    	detail.typeRservation.setValue(reservationSelected.getTypeRes().getLibelle());
    	detail.idReservation.setText(String.valueOf(reservationSelected.getIdReservation()));
    	detail.etatReservation.setText(reservationSelected.getStatus().getLibelle());
    	
    }
    
    @FXML
    void clicked(MouseEvent event) {
    	TableViewSelectionModel<Reservation>  selectionModel = tableReservation.getSelectionModel ();
    	ObservableList <Integer> indice = selectionModel.getSelectedIndices ();
    	if (indice.isEmpty()) {
    		detailReservationButton.setDisable(true);
    	}else {
    		long idReservation = tableReservation.getSelectionModel().getSelectedItem().getIdReservation();
    		reservationSelected = H.reservation.getById(idReservation);
    		detailReservationButton.setDisable(false);
    	}
    }

    public void handleButtonChoisirVehicule(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirVehiculeScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirVehiculeScene choisirVehicule = loader.getController();
        textFeildChoisirVehicule.setText(choisirVehicule.matriculeVehicule);
    }

    public void handleButtonChoisirClient(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirClientScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirClientScene choisirClient = loader.getController();
        textFeildChoisirClient.setText(choisirClient.cinClientSelected);
    }

    public void handleButtonChoisirUtilisateur(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirUtilisateurScene.fxml"));
    	Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        ChoisirUtilisateurScene choisirUtilisateur = loader.getController();
        textFeildChoisirUtilisateur.setText(choisirUtilisateur.cinUtilisateurSelected);
    }

    public void handleChercherComboBox(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Id")){

            enable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Type")){

            disable(idTextFeild);
            enable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Date")){

            disable(idTextFeild);
            disable(typeComboBox);
            enable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Vehicule")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            enable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Client")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            enable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Utilisateur")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            enable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Status")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            enable(statutComboBox);
            disable(nbrContratTextFeild);

        }else if(searchSection.equals("Nombres des contrats")){

            disable(idTextFeild);
            disable(typeComboBox);
            disable(dateDatePicker);
            disable(hBoxChoisirVehicule);
            disable(hBoxChoisirClient);
            disable(hBoxChoisirUtilisateur);
            disable(statutComboBox);
            enable(nbrContratTextFeild);

        }
    }

    public void handleChercherButton(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);
        
        // on n'a pas besoin ce trucs la 
        if(searchSection.equals("Id")){
        	mono_reservation.clear();
            reservation_list.clear();
            String idTaped = idTextFeild.getText();

            if(idTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search résérvation by id
            	afficher_reservation(idTaped);
            }

        }else if(searchSection.equals("Type")){
        	mono_reservation.clear();
            reservation_list.clear();
            String typeSelected = typeComboBox.getValue();

            if(typeSelected.isEmpty()){
                enable(ErreurMessage);
            }else {
            	TypeReservation type = H.typeres.get(typeSelected);
            	afficher_reservation(String.valueOf(type.getIdTypeReservation()));
            }

        }else if(searchSection.equals("Date")){
        	mono_reservation.clear();
            reservation_list.clear();
            String dateTaped = ((TextField)dateDatePicker.getEditor()).getText();

            if(dateTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	afficher_reservation(dateTaped);
            }

        }else if(searchSection.equals("Vehicule")){
        	mono_reservation.clear();
            reservation_list.clear();
            String vehiculeTaped = textFeildChoisirVehicule.getText();

            if(vehiculeTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	afficher_reservation(vehiculeTaped);
            }

        }else if(searchSection.equals("Client")){
        	mono_reservation.clear();
            reservation_list.clear();
            String clientTaped = textFeildChoisirClient.getText();

            if(clientTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	afficher_reservation(clientTaped);
            }

        }else if(searchSection.equals("Utilisateur")){
        	mono_reservation.clear();
            reservation_list.clear();
            String utilisateurTaped = textFeildChoisirUtilisateur.getText();

            if(utilisateurTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	afficher_reservation(utilisateurTaped);
            }

        }else if(searchSection.equals("Status")){
        	mono_reservation.clear();
            reservation_list.clear();
            String statutTaped = statutComboBox.getValue();

            if(statutTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
            	Status status = H.status.get(statutTaped);
            	afficher_reservation(String.valueOf(status.getIdStatus()));
            }

        }
    }
    
//    public long getReservation(String idUser) {
//    	Connection con = Abst.getConnection();
//    	String sql = "select idReservation from reservation where idUtilisateur = ?";
//    	long idReservation = 0;
//    	try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, idUser);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				idReservation = rs.getLong("idReservation");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    	return idReservation;
//    }

    public void disable(TextField TF){
        TF.setVisible(false);
        TF.setDisable(true);
    }

    public void enable(TextField TF){
        TF.setVisible(true);
        TF.setDisable(false);
    }

    public void disable(ComboBox<String> CB){
        CB.setVisible(false);
        CB.setDisable(true);
    }

    public void enable(ComboBox<String> CB){
        CB.setVisible(true);
        CB.setDisable(false);
    }

    public void disable(Label label){
        label.setVisible(false);
        label.setDisable(true);
    }

    public void enable(Label label){
        label.setVisible(true);
        label.setDisable(false);
    }

    public void disable(DatePicker DP){
        DP.setVisible(false);
        DP.setDisable(true);
    }

    public void enable(DatePicker DP){
        DP.setVisible(true);
        DP.setDisable(false);
    }

    public void disable(HBox HB){
        HB.setVisible(false);
        HB.setDisable(true);
    }

    public void enable(HBox HB){
        HB.setVisible(true);
        HB.setDisable(false);
    }
}
