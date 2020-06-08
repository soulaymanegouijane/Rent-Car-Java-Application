package windowsSwitcher.vehiculeWindow;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Carburant;
import Entities.Client;
import Entities.Marque;
import Entities.Parking;
import Entities.Utilisateur;
import Entities.Vehicule;
import InterfaceDetails.DetailVehicule;
import Test.H;
import com.sun.istack.NotNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.contratWindow.ChoisirVehiculeScene;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VehiculeWindow implements Initializable {
	 @FXML
	    private ComboBox<String> chercherComboBox;

	    @FXML
	    private AnchorPane inputSearchArea;

	    @FXML
	    private TextField matriculeTextFeild;

	    @FXML
	    private ComboBox<String> typeComboBox;

	    @FXML
	    private ComboBox<String> marqueComboBox;

	    @FXML
	    private TextField kilometrageTextFeild;

	    @FXML
	    private ComboBox<String> carburantComboBox;

	    @FXML
	    private ComboBox<String> disponibiliteComboBox;

	    @FXML
	    private HBox hBoxChoisirParking;

	    @FXML
	    private TextField textFeildChoisirParking;

	    @FXML
	    private JFXButton buttonChoisirParking;

	    @FXML
	    private Button chercherButton;

	    @FXML
	    private Label ErreurMessage;

	    @FXML
	    private TableView<Vehicule> tableVehicule;

	    @FXML
	    private TableColumn<Vehicule,String> col_matricule;

	    @FXML
	    private TableColumn<Vehicule,String> col_type;

	    @FXML
	    private TableColumn<Vehicule,String> col_marque;

	    @FXML
	    private TableColumn<Vehicule,String> col_kilometrage;

	    @FXML
	    private TableColumn<Vehicule,String> col_carburant;

	    @FXML
	    private TableColumn<Vehicule,String> col_disponibilite;

	    @FXML
	    private TableColumn<Vehicule,String> col_Emplacement;

	    @FXML
	    private Button ajouterVehiculeButton;

	    @FXML
	    private Button detailVehiculeButton;
	    
	    Vehicule vehiculeSelected = null;

    String searchSection = null;
    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "Matricule", "Type", "Marque", "Carburant", "Disponibilite", "Emplacement");
    ObservableList<String> typeList = FXCollections.observableArrayList("Voiture", "Motocycle", "Camion", "Camionnette", "Caravane");
    ObservableList<String> marqueList = FXCollections.observableArrayList();
    ObservableList<String> carburantList = FXCollections.observableArrayList();//"Essence", "Gazole", "Hybride"
    ObservableList<String> disponibiliteList = FXCollections.observableArrayList("Disponible", "Reserver", "Indisponible");

    ObservableList<Vehicule> vehicule_list = FXCollections.observableArrayList();
    ObservableList<Vehicule> mono_vehicule = FXCollections.observableArrayList();
    
    // Je dois ecrire la fonction qui me permet de chercher une vehicule par son type
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		detailVehiculeButton.setDisable(true);
    	marque_base_donnee();
    	carburant_base_donnee();
    	comboBox();
        remplir_tableau();
        
    }
    
    public void comboBox() {
    	chercherComboBox.setPromptText("Chercher par:");
        chercherComboBox.setItems(searchTypeList);
        typeComboBox.setPromptText("Select :");
        typeComboBox.setItems(typeList);
        marqueComboBox.setPromptText("Select :");
        marqueComboBox.setItems(marqueList);
        carburantComboBox.setPromptText("Select :");
        carburantComboBox.setItems(carburantList);
        disponibiliteComboBox.setPromptText("Select :");
        disponibiliteComboBox.setItems(disponibiliteList);
    }
    
    public void marque_base_donnee() {
    	ResultSet tous_les_marque = null;
		try {
			String sql = "select * from marque";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_marque = ps.executeQuery();
			while(tous_les_marque.next()) {
				Marque marque = new Marque();
				marque.setLibelle(tous_les_marque.getString("libelle"));
				String mrq = marque.getLibelle();
				marqueList.add(mrq);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    // je dois faire des autres modifications sur cette fonction
    public void parking_base_donnee() {
    	ResultSet tous_les_parking = null;
		try {
			String sql = "select * from parking";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_parking = ps.executeQuery();
			while(tous_les_parking.next()) {
				Parking parking = new Parking();
				parking.setAdress(tous_les_parking.getString("adress"));
				String adr = parking.getAdress();
				carburantList.add(adr);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void carburant_base_donnee() {
    	ResultSet tous_les_carburant = null;
		try {
			String sql = "select * from carburant";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_carburant = ps.executeQuery();
			while(tous_les_carburant.next()) {
				Carburant carburant = new Carburant();
				carburant.setLibelle(tous_les_carburant.getString("libelle"));
				String carb = carburant.getLibelle();
				carburantList.add(carb);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void handleChercherComboBox(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if (searchSection.equals("Tous") || searchSection.isEmpty()) {

            disable(matriculeTextFeild);
            disable(typeComboBox);
            disable(marqueComboBox);
            disable(kilometrageTextFeild);
            disable(carburantComboBox);
            disable(disponibiliteComboBox);
            disable(hBoxChoisirParking);

        } else if (searchSection.equals("Matricule")) {

            enable(matriculeTextFeild);
            disable(typeComboBox);
            disable(marqueComboBox);
            disable(kilometrageTextFeild);
            disable(carburantComboBox);
            disable(disponibiliteComboBox);
            disable(hBoxChoisirParking);

        } else if (searchSection.equals("Type")) {

            disable(matriculeTextFeild);
            enable(typeComboBox);
            disable(marqueComboBox);
            disable(kilometrageTextFeild);
            disable(carburantComboBox);
            disable(disponibiliteComboBox);
            disable(hBoxChoisirParking);

        } else if (searchSection.equals("Marque")) {

            disable(matriculeTextFeild);
            disable(typeComboBox);
            enable(marqueComboBox);
            disable(kilometrageTextFeild);
            disable(carburantComboBox);
            disable(disponibiliteComboBox);
            disable(hBoxChoisirParking);

        } else if (searchSection.equals("Carburant")) {

            disable(matriculeTextFeild);
            disable(typeComboBox);
            disable(marqueComboBox);
            disable(kilometrageTextFeild);
            enable(carburantComboBox);
            disable(disponibiliteComboBox);
            disable(hBoxChoisirParking);

        } else if (searchSection.equals("Disponibilite")) {

            disable(matriculeTextFeild);
            disable(typeComboBox);
            disable(marqueComboBox);
            disable(kilometrageTextFeild);
            disable(carburantComboBox);
            enable(disponibiliteComboBox);
            disable(hBoxChoisirParking);

        } else if (searchSection.equals("Emplacement")) {

            disable(matriculeTextFeild);
            disable(typeComboBox);
            disable(marqueComboBox);
            disable(kilometrageTextFeild);
            disable(carburantComboBox);
            disable(disponibiliteComboBox);
            enable(hBoxChoisirParking);

        }
    }
    
    // Je dois faire le traitement que me donner la disponibilit� du vehicule
    public void handleChercherButton(ActionEvent actionEvent) {
        searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);

        if (searchSection.equals("Matricule")) {
        	vehicule_list.clear();
        	mono_vehicule.clear();
            String matriculeTaped = matriculeTextFeild.getText();
            if (matriculeTaped.isEmpty()) {
                enable(ErreurMessage);
            } else {
                // Search vehicule by matricule
            	afficher_vehicule(matriculeTaped);
            }

        } else if (searchSection.equals("Type")) {
        	vehicule_list.clear();
        	mono_vehicule.clear();
            String typeTaped = typeComboBox.getValue();

            // Search vehicule by Type
            if (typeTaped.isEmpty()) {
                enable(ErreurMessage);
            } else {
                // Search vehicule by emplacement
            	afficher_vehicule(typeTaped);
            }

        } else if (searchSection.equals("Marque")) {
        	vehicule_list.clear();
        	mono_vehicule.clear();
            String marqueTaped = marqueComboBox.getValue();
            System.out.println("----->" +marqueTaped );
            // Search vehicule by Marque
            if (marqueTaped.isEmpty()) {
                enable(ErreurMessage);
            } else {
                // Search vehicule by emplacement
            	mrq_type_Vehicule(marqueTaped);
            }

        }else if (searchSection.equals("Carburant")) {
        	vehicule_list.clear();
        	mono_vehicule.clear();
            String carburantTaped = carburantComboBox.getValue();

            // Search vehicule by carburant
            if (carburantTaped.isEmpty()) {
                enable(ErreurMessage);
            } else {
            	
            	afficher_vehicule(carburantTaped);
            }

        } else if (searchSection.equals("Disponibilite")) {
        	vehicule_list.clear();
        	mono_vehicule.clear();
            String disponibiliteTaped = disponibiliteComboBox.getValue();

            // Search vehicule by disponibilite
            if (disponibiliteTaped.isEmpty()) {
                enable(ErreurMessage);
            } else {
                // Search vehicule by emplacement
            	afficher_vehicule(disponibiliteTaped);
            }

        } else if (searchSection.equals("Emplacement")) {
        	vehicule_list.clear();
        	mono_vehicule.clear();
            String emplacementTaped = textFeildChoisirParking.getText();

            if (emplacementTaped.isEmpty()) {
                enable(ErreurMessage);
            } else {
                // Search vehicule by emplacement
            	afficher_vehicule(emplacementTaped);
            }

        }else {
        	vehicule_list.clear();
        	mono_vehicule.clear();
        	remplir_tableau();
        }
    }
    
    public void handleAjouterVehiculeButton(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterVehicule/AjouterVehicule.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        mono_vehicule.clear();
        vehicule_list.clear();
        remplir_tableau();
    }

    public void handleDetailVehiculeButton(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../InterfaceDetails/detailVehicule.fxml"));
        Parent root = loader.load();
        
        FunctionAffiche(loader);
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    void clicked(MouseEvent event) {
    	TableViewSelectionModel<Vehicule>  selectionModel = tableVehicule.getSelectionModel ();
    	ObservableList <Integer> indice = selectionModel.getSelectedIndices ();
    	if (indice.isEmpty()) {
    		detailVehiculeButton.setDisable(true);
    	}else {
    		String idVehicule = tableVehicule.getSelectionModel().getSelectedItem().getIdVehicule();
    		vehiculeSelected = H.vehicule.getById(idVehicule);
    		detailVehiculeButton.setDisable(false);
    	}
    }
    
    public void FunctionAffiche(FXMLLoader loader) {
    	DetailVehicule detail = loader.getController();
    	
    	detail.idmatricule.setText(vehiculeSelected.getIdVehicule());
    	detail.idcolor.setValue(javafx.scene.paint.Color.valueOf((String) vehiculeSelected.getColor()));
    	detail.nombrePlaceTextField.setText(String.valueOf(vehiculeSelected.getNbr_place()));
    	
    	if(vehiculeSelected.getDispo()) {
    		detail.dispoVehicule.setValue("disponible");
    	}else {
    		detail.dispoVehicule.setValue("indisponible");
    	}
    	detail.TypeCarburant.setValue(vehiculeSelected.getCarburant().getLibelle());
    	detail.marqueVoiture.setValue(vehiculeSelected.getType().getMarque().getLibelle());
    	detail.idParking.setText(vehiculeSelected.getParking().getAdress());
    	detail.photoVehicule.setImage(new Image(new ByteArrayInputStream(vehiculeSelected.getImage()), 142, 123, false, false));
    }

	public void handleButtonChoisirParking(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("choisirParkingScene.fxml"));
    	Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		ChoisirParkingScene choisirParking = loader.getController();
		textFeildChoisirParking.setText(choisirParking.idParkingChoisi);
		
	}

    public void disable(TextField TF) {
        TF.setVisible(false);
        TF.setDisable(true);
    }

    public void enable(TextField TF) {
        TF.setVisible(true);
        TF.setDisable(false);
    }

    public void disable(ComboBox<String> CB) {
        CB.setVisible(false);
        CB.setDisable(true);
    }

    public void enable(ComboBox<String> CB) {
        CB.setVisible(true);
        CB.setDisable(false);
    }

    public void disable(Label label) {
        label.setVisible(false);
        label.setDisable(true);
    }

    public void enable(Label label) {
        label.setVisible(true);
        label.setDisable(false);
    }

    public void disable(HBox HB){
        HB.setVisible(false);
        HB.setDisable(true);
    }

    public void enable(HBox HB){
        HB.setVisible(true);
        HB.setDisable(false);
    }
    
    public void mrq_type_Vehicule(String marque){
    	String sql = "select idType from type where idMarque = ?";
    	Connection con = Abst.getConnection();
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			Marque mrq = H.marque.get(marque);
			ps.setLong(1, mrq.getIdMarque());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				long idType = rs.getLong("idType");
				System.out.println("**************************** $ " + idType);
				afficher_vehicule(String.valueOf(idType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void afficher_vehicule(String valeur) {
    	searchSection = chercherComboBox.getValue();
    	
    	
		try {
			ResultSet tous_les_vehicule = null;
	    	PreparedStatement ps = null;
	    	Connection con = null;
			if(searchSection.equals("Matricule")) {
				String sql = "select * from vehicule where idVehicule=?";
				con = Abst.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, valeur);
			}
			if(searchSection.equals("Marque")) {
				String sql = "select * from vehicule where idType=?";
				con = Abst.getConnection();
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.valueOf(valeur));
			}
			if(searchSection.equals("Type")) {
				String sql = "select * from vehicule where idType=?";
				con = Abst.getConnection();
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.valueOf(valeur));
			}
			if(searchSection.equals("Carburant")) {
				String sql = "select * from vehicule where idCarburant=?";
				con = Abst.getConnection();
				ps = con.prepareStatement(sql);
				Carburant carburant = H.carburant.get(valeur);
				ps.setLong(1, carburant.getIdCarburant());
			}
			if(searchSection.equals("Disponibilite")) {
				String sql = "select * from vehicule where dispo=?";
				con = Abst.getConnection();
				ps = con.prepareStatement(sql);
				ps.setBoolean(1, Boolean.valueOf(valeur));
			}
			if(searchSection.equals("Emplacement")) {
				String sql = "select * from vehicule where idParking=?";
				con = Abst.getConnection();
				ps = con.prepareStatement(sql);
				ps.setLong(1, Long.valueOf(valeur));
			}
			
			tous_les_vehicule = ps.executeQuery();
			while(tous_les_vehicule.next()) {
				Vehicule vehicule = new Vehicule();
				vehicule.setIdVehicule(tous_les_vehicule.getString("idVehicule"));
				vehicule.setNbr_place(tous_les_vehicule.getInt("nbr_place"));
				vehicule.setLibelle_parking(H.parking.getById(tous_les_vehicule.getLong("idParking")).getAdress());
				vehicule.setLibelle_carburant(H.carburant.getById(tous_les_vehicule.getLong("idCarburant")).getLibelle());
				//vehicule.setLibelle_marque(H.marque.getById(tous_les_vehicule.getLong("idMarque")).getLibelle());
				vehicule.setTypeVehicule(H.type.getById(tous_les_vehicule.getLong("idType")).getLibelle());
				vehicule.setLibelle_marque(H.type.getById(tous_les_vehicule.getLong("idType")).getMarque().getLibelle());
				vehicule.setColor(tous_les_vehicule.getString("color"));
				// vehicule_list.add(vehicule); prb ?
				mono_vehicule.add(vehicule);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//mono_vehicule.add(vehicule); ** prb ?
    	col_matricule.setCellValueFactory(new PropertyValueFactory<>("idVehicule"));
	    col_disponibilite.setCellValueFactory(new PropertyValueFactory<>("dispo"));
	    col_carburant.setCellValueFactory(new PropertyValueFactory<>("libelle_carburant"));
	    col_kilometrage.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
	    col_marque.setCellValueFactory(new PropertyValueFactory<>("libelle_marque"));
	    
	    col_type.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
	    col_Emplacement.setCellValueFactory(new PropertyValueFactory<>("libelle_parking"));
    
	    tableVehicule.setItems(mono_vehicule);
    }
    
    public void remplir_tableau(){
        ResultSet tous_les_vehicule = null;
		try {
			String sql = "select * from vehicule";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_vehicule = ps.executeQuery();
			
			while(tous_les_vehicule.next()) {
				Vehicule vehicule = new Vehicule();
				vehicule.setIdVehicule(tous_les_vehicule.getString("idVehicule"));
				vehicule.setNbr_place(tous_les_vehicule.getInt("nbr_place"));
				vehicule.setLibelle_parking(H.parking.getById(tous_les_vehicule.getLong("idParking")).getAdress());
				vehicule.setLibelle_carburant(H.carburant.getById(tous_les_vehicule.getLong("idCarburant")).getLibelle());
				vehicule.setMarqueLibelle(H.vehicule.marque_libelle(tous_les_vehicule.getString("idVehicule")));
				vehicule.setTypeVehicule(H.type.getById(tous_les_vehicule.getLong("idType")).getLibelle());
				vehicule.setColor(tous_les_vehicule.getString("color"));
				vehicule_list.add(vehicule);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			col_matricule.setCellValueFactory(new PropertyValueFactory<>("idVehicule"));
		    col_disponibilite.setCellValueFactory(new PropertyValueFactory<>("dispo"));
		    col_carburant.setCellValueFactory(new PropertyValueFactory<>("libelle_carburant"));
		    col_kilometrage.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
		    col_marque.setCellValueFactory(new PropertyValueFactory<>("marqueLibelle"));
		    col_type.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
		    col_Emplacement.setCellValueFactory(new PropertyValueFactory<>("libelle_parking"));
        
		    tableVehicule.setItems(vehicule_list);
	}
}
