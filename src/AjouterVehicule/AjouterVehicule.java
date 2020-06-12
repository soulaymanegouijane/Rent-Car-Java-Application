package AjouterVehicule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import AjouterCarburant.AjouterCarburant;
import AjouterMarque.AjouterMarque;
import AjouterParking.AjouterParking;
import AjouterType.AjouterType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import Entities.Carburant;
import Entities.Marque;
import Entities.Parking;
import Entities.Type;
import Entities.Vehicule;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AjouterVehicule implements Initializable{

	public TextField prixJoursTextField;
	public TextField kilometrageTextField;
	@FXML
	private ImageView imageView;

	@FXML
	private TextField imagePath;

	@FXML
	private JFXButton parcourrirBtn;

	@FXML
	private TextField matriculeTextField;

	@FXML
	private TextField nombrePlaceTextField;

	@FXML
	private JFXComboBox<String> carburantComboBox;

	@FXML
	private JFXButton ajouterCarburantButton;

	@FXML
	private JFXComboBox<String> marqueComboBox;

	@FXML
	private JFXButton ajouterMarqueButton;

	@FXML
	private JFXComboBox<String> typeComboBox;

	@FXML
	private JFXButton ajouterTypeButton;

	@FXML
	private ColorPicker colorColorPicker;

	@FXML
	private JFXComboBox<String> parkingComboBox;

	@FXML
	private JFXButton ajouterParkingButton;

	@FXML
	private JFXButton closeButton;

	@FXML
	private JFXButton submitButton;

	@FXML
	private Label erreurMessage;

	ObservableList<String> carburantList = FXCollections.observableArrayList();
	ObservableList<String> marqueList = FXCollections.observableArrayList();
	ObservableList<String> TypeList = FXCollections.observableArrayList();
	ObservableList<String> ParkingList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carburant_base_donnee();
		parking_base_donnee();
		marque_base_donne();
		comboBox();
		marqueComboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(ObservableValue ov, String t, String t1) {
				TypeList.clear();
				typeComboBox.setItems(TypeList);
			}
		});


	}

	public void comboBox() {
		carburantComboBox.setItems(carburantList);
		parkingComboBox.setItems(ParkingList);
		marqueComboBox.setItems(marqueList);
	}
	
	@FXML
    void TypeOfTheMarque(ActionEvent event) {
		typeComboBox.setDisable(false);
		ajouterTypeButton.setDisable(false);
		String value = marqueComboBox.getValue() ;
		type_base_donne(value);
    }

	@FXML
    void handleAjouterParkingButton(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterParking/AjouterParking.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		AjouterParking parkingController = loader.getController();
		ParkingList.add(parkingController.parkingTaped);
		ParkingList.clear();
		parking_base_donnee();
		parkingComboBox.setItems(ParkingList);
		parkingComboBox.setValue(parkingController.parkingTaped);
    }
	
	public void type_base_donne(String str) {
		ResultSet tous_les_types = null;
		String sql = "select * from type where idMarque = ?";
		Connection con = Abst.getConnection();
		Marque marque = H.marque.get(str);
		System.out.println(marque.toString());
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, marque.getIdMarque());
			tous_les_types = ps.executeQuery();
			while(tous_les_types.next()) {
				Type type = new Type();
				type.setLibelle(tous_les_types.getString("libelle"));
				System.out.println("----- type -----> ! "+tous_les_types.getString("libelle"));
				String typ = type.getLibelle();
				TypeList.add(typ);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void marque_base_donne() {
		ResultSet tous_les_marques = null;
		String sql = "select * from marque";
		Connection con = Abst.getConnection();
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_marques = ps.executeQuery();
			while(tous_les_marques.next()) {
				Marque marque = new Marque();
				marque.setLibelle(tous_les_marques.getString("libelle"));
				String mrq = marque.getLibelle();
				marqueList.add(mrq);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void closeButtonAction(){
		Stage stage =(Stage) closeButton.getScene().getWindow();
		stage.close();
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
				ParkingList.add(adr);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void handleSubmitButton(ActionEvent actionEvent) {
		if (testEmpty()){
			erreurMessage.setVisible(true);
		}else{
			String matricule = matriculeTextField.getText();
			int nbrPlace = Integer.parseInt(nombrePlaceTextField.getText());
			String carbutant = (String) carburantComboBox.getValue();
			String parking = (String) parkingComboBox.getValue();
			String color = toRGBCode(colorColorPicker.getValue());
			String marque = marqueComboBox.getValue();
			String typ = typeComboBox.getValue();
			String prixJours = prixJoursTextField.getText();
			String kilometrage = kilometrageTextField.getText();

			Vehicule vehicule = new Vehicule();
			vehicule.setIdVehicule(matricule);
			vehicule.setCarburant(H.carburant.get(carbutant));
			vehicule.setColor(color);
			vehicule.setNbr_place(nbrPlace);
			vehicule.setParking(H.parking.get(parking));
			vehicule.setMarqueLibelle(marque);
			vehicule.setType(H.type.get(typ));
			vehicule.setDispo("Disponible");
			vehicule.setPrixJours(Float.parseFloat(prixJours));
			vehicule.setKilometrage(Long.parseLong(kilometrage));
			
			try {
				FileInputStream inputStream = new FileInputStream(file);
				try {
					inputStream.read(bFile);
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			vehicule.setImage(bFile);
			
			H.vehicule.add(vehicule);

			Stage stg = (Stage) closeButton.getScene().getWindow();
			stg.close();
		}
	}

	public void handleAjouterMarqueButton(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterMarque/AjouterMarque.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		AjouterMarque marqueController = loader.getController();
		marqueList.add(marqueController.libelleMarqueTaped);
		marqueList.clear();
		marque_base_donne();
		marqueComboBox.setItems(marqueList);
		marqueComboBox.setValue(marqueController.libelleMarqueTaped);
	}

	public void handleAjouterTypeButton(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterType/AjouterType.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		AjouterType typeController = loader.getController();
		TypeList.add(typeController.libelleTypeTaped);
		typeComboBox.getItems().removeAll(marqueComboBox.getItems());
		typeComboBox.setItems(TypeList);
		typeComboBox.setValue(typeController.libelleTypeTaped);
	}

	public void handleAjouterCarburantButton(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterCarburant/AjouterCarburant.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		AjouterCarburant CarburantController = loader.getController();
		carburantList.add(CarburantController.libelleCarburantTaped);
		carburantComboBox.getItems().removeAll(marqueComboBox.getItems());
		carburantComboBox.setItems(carburantList);
		carburantComboBox.setValue(CarburantController.libelleCarburantTaped);
	}

	public static String toRGBCode( Color color )
	{
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed() * 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue() * 255 ) );
	}

	public boolean testEmpty(){
		if(matriculeTextField.getText().isEmpty() || nombrePlaceTextField.getText().isEmpty() || carburantComboBox.getValue().isEmpty()
				|| marqueComboBox.getValue().isEmpty() || typeComboBox.getValue().isEmpty() ||  parkingComboBox.getValue() == null
				|| prixJoursTextField.getText().isEmpty() || kilometrageTextField.getText().isEmpty())
			return true;
		return false;
	}

	FileChooser fc = new FileChooser();
    File selectedfile = null;
    File file = null;
    byte[] bFile = null;
    public void btnimageAction(ActionEvent event){
        
        selectedfile = fc.showOpenDialog(null);
        if(selectedfile!=null){
            Image newimage = new Image(selectedfile.toURI().toString(),200,150,true,true);
            imagePath.setText(selectedfile.getAbsolutePath());
            imageView.setImage(newimage);
            file = new File(selectedfile.getAbsolutePath());
            bFile = new byte[(int) file.length()];
            System.out.println(selectedfile.getAbsolutePath());
        }
    }
}
