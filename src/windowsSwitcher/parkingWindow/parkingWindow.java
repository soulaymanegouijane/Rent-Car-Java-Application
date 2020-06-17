package windowsSwitcher.parkingWindow;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

import AbstactClasses.Abst;
import Entities.Contrat;
import Entities.Parking;
import Entities.Utilisateur;
import Entities.Vehicule;
import InterfaceDetails.DetailGarage;
import Test.H;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.vehiculeWindow.ChoisirParkingScene;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class parkingWindow implements Initializable {

    public Button choisirParkingBtn;
    public ImageView editParkingBtn;
    @FXML
    private ComboBox<String> chercherComboBox;

    @FXML
    private AnchorPane inputSearchArea;

    @FXML
    private TextField dataTextFeild;

    @FXML
    private Button charcherButton;

    @FXML
    private Label ErreurMessage;

    @FXML
    private TableView<Parking> tableParking;

    @FXML
    private TableColumn<Parking,Long> col_id;

    @FXML
    private TableColumn<Parking,String> col_Nom;

    @FXML
    private TableColumn<Parking,Integer> col_capacite;

    @FXML
    private TableColumn<Parking , Integer> col_placesVides;

    @FXML
    private TableColumn<Parking,String> col_adresse;

    @FXML
    private Button ajouterParkingButton;

    @FXML
    private Button detailParkingButton;
    
    Parking parkingSelected = null;
    

    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "Adress", "Capacite", "places sature");
    ObservableList<Parking> parking_list = FXCollections.observableArrayList();
    ObservableList<Parking> mono_parking = FXCollections.observableArrayList();
    
    String searchSection = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detailParkingButton.setDisable(true);
        chercherComboBox.setPromptText("Chercher par:");
        chercherComboBox.setItems(searchTypeList);
        remplir_tableau();
    }

    public void handleAjouterParkingButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterParking/AjouterParking.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        parking_list.clear();
        mono_parking.clear();
        remplir_tableau();
    }

    public void handleDetailParkingButton(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../InterfaceDetails/detailGarage.fxml"));
    	DetailGarage.parking = parkingSelected;
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        mono_parking.clear();
        parking_list.clear();
        remplir_tableau();
    }
        
    @FXML
    void clicked(MouseEvent event) {
    	TableViewSelectionModel<Parking>  selectionModel = tableParking.getSelectionModel ();
    	ObservableList <Integer> indice = selectionModel.getSelectedIndices ();
    	if (indice.isEmpty()) {
    		detailParkingButton.setDisable(true);
    	}else {
    		long idParking = tableParking.getSelectionModel().getSelectedItem().getIdParking();
    		parkingSelected = H.parking.getById(idParking);
    		detailParkingButton.setDisable(false);
    	}
    }

    public void handleChercherComboBox(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        charcherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(dataTextFeild);
            choisirParkingBtn.setVisible(false);

        }else if(searchSection.equals("Adress") || searchSection.equals("Capacite") || searchSection.equals("places sature")){

            choisirParkingBtn.setVisible(false);
            dataTextFeild.setEditable(true);
            enable(dataTextFeild);

            if(searchSection.equals("Adress")){
                choisirParkingBtn.setVisible(true);
                dataTextFeild.setEditable(false);
            }

        }
    }

    public void handleCharcherButton(ActionEvent actionEvent) {
        searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);
        if (dataTextFeild.getText().isEmpty()){
            enable(ErreurMessage);
        } else{
            if(searchSection.equals("Adress")){
                parking_list.clear();
                mono_parking.clear();
                String nomTaped = dataTextFeild.getText();

                afficher_parking(nomTaped);


            }else if(searchSection.equals("Capacite")){
                if (H.isNumeric(dataTextFeild.getText())) {
                    parking_list.clear();
                    mono_parking.clear();

                    String capaciteTaped = dataTextFeild.getText();

                    afficher_parking(capaciteTaped);
                }else{
                    enable(ErreurMessage);
                }
            }else if(searchSection.equals("places sature")){
                if (H.isNumeric(dataTextFeild.getText())) {
                    parking_list.clear();
                    mono_parking.clear();
                    String placesTaped = dataTextFeild.getText();

                    afficher_parking(placesTaped);
                }else{
                    enable(ErreurMessage);
                }
            }else {
                parking_list.clear();
                mono_parking.clear();
                remplir_tableau();
            }
        }

    }
    
    public void afficher_parking(String valeur) {
    	searchSection = chercherComboBox.getValue();
    	ResultSet tous_les_parking = null;
    	Connection con = Abst.getConnection();
		try {
	    	String sql = "";
	    	PreparedStatement ps = null;
	    	
			if(searchSection.equals("Adress")) {
				sql = "select * from parking where adress=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Capacite")) {
				sql = "select * from parking where capacite=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.parseLong(valeur));
			}
			if(searchSection.equals("places sature")) {
				sql = "select * from parking where nbr_place_pleinne=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(valeur));
			}
			tous_les_parking = ps.executeQuery();
			while(tous_les_parking.next()) {
				Parking parking = new Parking();
 				parking.setIdParking(tous_les_parking.getLong("idParking"));
				parking.setAdress(tous_les_parking.getString("adress"));
				parking.setCapacite(tous_les_parking.getLong("capacite"));
				parking.setNbr_place_pleinne(tous_les_parking.getInt("nbr_place_pleinne"));
				mono_parking.add(parking);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		col_id.setCellValueFactory(new PropertyValueFactory<>("idParking"));
 	    col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
 	    col_placesVides.setCellValueFactory(new PropertyValueFactory<>("nbr_place_pleinne"));
 	    col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
 	    
 	   tableParking.setItems(mono_parking);
}

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

    public void remplir_tableau() {
    	ResultSet tous_les_parking = null;
 		try {
 			String sql = "select * from parking";
 			Connection con = Abst.getConnection();
 			PreparedStatement ps = con.prepareStatement(sql);
 			tous_les_parking = ps.executeQuery();
 			
 			while(tous_les_parking.next()) {
 				Parking parking = new Parking();
 				parking.setIdParking(tous_les_parking.getLong("idParking"));
				parking.setAdress(tous_les_parking.getString("adress"));
				parking.setCapacite(tous_les_parking.getLong("capacite"));
				parking.setNbr_place_pleinne(tous_les_parking.getInt("nbr_place_pleinne"));
 				parking_list.add(parking);
 			}
 			con.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		
 	    col_id.setCellValueFactory(new PropertyValueFactory<>("idParking"));
 	    col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
 	    col_placesVides.setCellValueFactory(new PropertyValueFactory<>("nbr_place_pleinne"));
 	    col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
         
 		   tableParking.setItems(parking_list);
    }

    public void EditParking(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vehiculeWindow/choisirParkingScene.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        ChoisirParkingScene choisirParking = loader.getController();
        dataTextFeild.setText(choisirParking.adressParkingChoisi);
    }
}
