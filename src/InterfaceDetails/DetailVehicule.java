package InterfaceDetails;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import AjouterMarque.AjouterMarque;
import AjouterType.AjouterType;
import Entities.Type;
import Entities.Vehicule;
import Test.H;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import AjouterCarburant.AjouterCarburant;
import Entities.Carburant;
import Entities.Marque;
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
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import windowsSwitcher.vehiculeWindow.ChoisirParkingScene;

public class DetailVehicule implements Initializable {
	@FXML
    public JFXButton closeButton;

    @FXML
    public ImageView photoVehicule;

    @FXML
    public JFXButton editPhotoVehiculeBtn;

    @FXML
    public TextField idmatricule;

    @FXML
    public TextField nombrePlaceTextField;

    @FXML
    public ColorPicker idcolor;

    @FXML
    public TextField idParking;

    @FXML
    public Button choisirParkingBtn;

    @FXML
    public JFXComboBox<String> dispoVehicule;

    @FXML
    public JFXButton deleteBtn;

    @FXML
    public JFXButton editBtn;
	public TextField kilometrageTextField;
	public TextField prixJoursTextField;
	public ImageView editParkingBtn;
	public Label erreurMessage;
	public HBox nonEditableHBox;
	public HBox editableHBox;
	public JFXButton annulerBtn;
	public JFXButton enregistrerBtn;
	public JFXComboBox<String> carburantComboBox;
	public JFXButton ajouterCarburantButton;
	public JFXComboBox<String> marqueComboBox;
	public JFXButton ajouterMarqueButton;
	public JFXComboBox<String> typeComboBox;
	public JFXButton ajouterTypeButton;
	public TextField imagePath;

	ObservableList<String> TypeList = FXCollections.observableArrayList();
	ObservableList<String> carburantList = FXCollections.observableArrayList();
    ObservableList<String> marqueList = FXCollections.observableArrayList();
    ObservableList<String> dispoList = FXCollections.observableArrayList("Disponible", "Reserver", "indisponible");

    boolean imageChanged = false;

    public static Vehicule voiture = new Vehicule();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	marque_base_donne();
		carburant_base_donnee();
    	comboBox();
    	fillBlanks();
		type_base_donne(marqueComboBox.getValue());
		typeComboBox.setItems(TypeList);
		marqueComboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				System.out.println("--------------------------> changer");
				TypeList.clear();
				typeComboBox.setItems(TypeList);
				typeComboBox.setDisable(false);
				ajouterTypeButton.setDisable(false);
			}
		});

		imagePath.textProperty().addListener((observable, oldValue, newValue) ->{
			imageChanged = true;
		});
	}

	public void fillBlanks(){
		photoVehicule.setImage(new Image(new ByteArrayInputStream(voiture.getImage()), 142, 123, false, false));
		idmatricule.setText(voiture.getIdVehicule());
		nombrePlaceTextField.setText(String.valueOf(voiture.getNbr_place()));
		carburantComboBox.setValue(voiture.getCarburant().getLibelle());
		marqueComboBox.setValue(voiture.getType().getMarque().getLibelle());
		typeComboBox.setValue(voiture.getType().getLibelle());// Attention
		idcolor.setValue(javafx.scene.paint.Color.valueOf((String) voiture.getColor()));
		kilometrageTextField.setText(String.valueOf(voiture.getKilometrage()));
		prixJoursTextField.setText(String.valueOf(voiture.getPrixJours()));
		idParking.setText(voiture.getParking().getAdress());
		dispoVehicule.setValue(voiture.getDispo());
	}

	public void rewriteVoitureInfos(){

		voiture.setIdVehicule(idmatricule.getText());
		voiture.setNbr_place(Integer.parseInt(nombrePlaceTextField.getText()));
		voiture.setCarburant(H.carburant.get(carburantComboBox.getValue()));
		voiture.setMarqueLibelle(marqueComboBox.getValue());
		voiture.setType(H.type.get(typeComboBox.getValue()));
		voiture.setColor(toRGBCode(idcolor.getValue()));
		voiture.setKilometrage(Long.parseLong(kilometrageTextField.getText()));
		voiture.setPrixJours(Float.parseFloat(prixJoursTextField.getText()));
		voiture.setParking(H.parking.get(idParking.getText()));
		voiture.setDispo(dispoVehicule.getValue());
		if (imageChanged){
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
			voiture.setImage(bFile);
		}
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
			photoVehicule.setImage(newimage);
			file = new File(selectedfile.getAbsolutePath());
			bFile = new byte[(int) file.length()];
			System.out.println(selectedfile.getAbsolutePath());
		}
	}

    
    @FXML
    void EditParking(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/vehiculeWindow/choisirParkingScene.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		ChoisirParkingScene choisirParking = loader.getController();
		idParking.setText(choisirParking.adressParkingChoisi);
    }
    
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    public void comboBox() {
		carburantComboBox.setItems(carburantList);
		marqueComboBox.setItems(marqueList);
    	dispoVehicule.setItems(dispoList);
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

	@FXML
	void TypeOfTheMarque(ActionEvent event) {
		typeComboBox.setDisable(false);
		ajouterTypeButton.setDisable(false);
		String value = marqueComboBox.getValue() ;
		type_base_donne(value);
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

		AjouterType typeController = loader.getController();
		typeController.mrqType.setValue(marqueComboBox.getValue());

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

		TypeList.add(typeController.libelleTypeTaped);
		typeComboBox.getItems().removeAll(marqueComboBox.getItems());
		typeComboBox.setItems(TypeList);
		typeComboBox.setValue(typeController.libelleTypeTaped);
	}

	public void handleDeleteBtn(ActionEvent actionEvent) {
	}

	public void handleEditBtn(ActionEvent actionEvent) {
		enableEditing();
	}

	public void handleAnnulerBtn(ActionEvent actionEvent) {
		fillBlanks();
		disableEditing();
	}

	public void handleEnregistrerBtn(ActionEvent actionEvent) {
		erreurMessage.setVisible(false);
		if (testEmpty()){
			erreurMessage.setVisible(true);
		}else {
			rewriteVoitureInfos();
			H.vehicule.edit(voiture);
			disableEditing();
		}
	}

	public void enableEditing(){
    	nonEditableHBox.setVisible(false);
		editableHBox.setVisible(true);

		editPhotoVehiculeBtn.setVisible(true);
		nombrePlaceTextField.setEditable(true);
		carburantComboBox.setDisable(false);
		ajouterCarburantButton.setVisible(true);
		marqueComboBox.setDisable(false);
		ajouterMarqueButton.setVisible(true);
		typeComboBox.setDisable(false);
		ajouterTypeButton.setVisible(true);//
		ajouterTypeButton.setDisable(false);
		idcolor.setDisable(false);
		kilometrageTextField.setEditable(true);
		prixJoursTextField.setEditable(true);
		choisirParkingBtn.setVisible(true);
		dispoVehicule.setDisable(false);
	}

	public void disableEditing(){
		nonEditableHBox.setVisible(true);
		editableHBox.setVisible(false);

		editPhotoVehiculeBtn.setVisible(false);
		nombrePlaceTextField.setEditable(false);
		carburantComboBox.setDisable(true);
		ajouterCarburantButton.setVisible(false);
		marqueComboBox.setDisable(true);
		ajouterMarqueButton.setVisible(false);
		typeComboBox.setDisable(true);
		ajouterTypeButton.setVisible(false);//
		ajouterTypeButton.setDisable(true);
		idcolor.setDisable(true);
		kilometrageTextField.setEditable(false);
		prixJoursTextField.setEditable(false);
		choisirParkingBtn.setVisible(false);
		dispoVehicule.setDisable(true);
	}

	public static String toRGBCode( Color color )
	{
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed() * 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue() * 255 ) );
	}

	public boolean testEmpty(){
		if(idmatricule.getText().isEmpty() || nombrePlaceTextField.getText().isEmpty() || carburantComboBox.getValue().isEmpty()
				|| marqueComboBox.getValue().isEmpty() || typeComboBox.getValue().isEmpty() ||  idParking.getText().isEmpty()
				|| prixJoursTextField.getText().isEmpty() || kilometrageTextField.getText().isEmpty())
			return true;
		return false;
	}
}
