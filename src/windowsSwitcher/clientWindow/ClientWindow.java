package windowsSwitcher.clientWindow;

import AjouterUnClient.Controller;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import AbstactClasses.Abst;
import AjouterReservation.ChoisirUtilisateurScene;
import Entities.Client;
import InterfaceDetails.DetailClient;
import Test.H;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientWindow implements Initializable {
	@FXML
    private ComboBox<String> chercherComboBox;

    @FXML
    private AnchorPane inputSearchArea;

    @FXML
    private TextField dataTextFeild;

    @FXML
    private Button chercherButton;

    @FXML
    private Label ErreurMessage;

    @FXML
    private TableView<Client> tableClient;

    @FXML
    private TableColumn<Client,String> col_cin;

    @FXML
    private TableColumn<Client,String> col_nom;

    @FXML
    private TableColumn<Client,String> col_prenom;

    @FXML
    private TableColumn<Client,String> col_date_naissance;

    @FXML
    private TableColumn<Client,String> col_tel;

    @FXML
    private TableColumn<Client,String> col_email;

    @FXML
    private TableColumn<Client,String> col_adresse;

    @FXML
    private Button ajouterClientButton;

    @FXML
    private Button detailClientButton;

    ObservableList<String> searchTypeList = FXCollections.observableArrayList("Tous", "CIN", "Nom", "Prenom");
    ObservableList<Client> client_list = FXCollections.observableArrayList();
    ObservableList<Client> mono_Client = FXCollections.observableArrayList();
    String searchSection = null;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	detailClientButton.setDisable(true);
    	comboBox();
        remplir_tableau();
        Client c = H.client.getById("JB3066");
        System.out.println(c.getDate_naissance());
    }
    
    public void comboBox() {
    	chercherComboBox.setPromptText("Chercher par:");
        chercherComboBox.setItems(searchTypeList);
    }
    
    Client clientSelected = new Client();
    
    @FXML
    void clicked(MouseEvent event) {
    	TableViewSelectionModel<Client>  selectionModel = tableClient.getSelectionModel ();
    	ObservableList <Integer> indice = selectionModel.getSelectedIndices ();
    	if (indice.isEmpty()) {
    		detailClientButton.setDisable(true);
    	}else {
    		String cin = tableClient.getSelectionModel().getSelectedItem().getIdClient();
    		clientSelected = H.client.getById(cin);
    		System.out.println("------------->"+clientSelected.getCarte_identifiant());
    		detailClientButton.setDisable(false);
    	}
    }

    public void handleAjouterClientButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterUnClient/sample.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        client_list.clear();
        remplir_tableau();
    }

    public void handleDetailClientButton(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../InterfaceDetails/detailClient.fxml"));

        DetailClient.client = clientSelected;
        Parent root = loader.load();
        //detailClient.comboBox();
        // fonction pour rmplir les champs du detailClient interface
        //FunctionAffiche(loader);
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        client_list.clear();
        remplir_tableau();
    }
    
    /*public void FunctionAffiche(FXMLLoader loader) {
    	DetailClient clt = loader.getController();
        clt.prenomTextField.setText(clientSelected.getPrenom());
        clt.nomTextField.setText(clientSelected.getNom());
        clt.nationaliteTextField.setText(clientSelected.getNationalite());
        clt.lieuNaissanceTextField.setText(clientSelected.getLieu_naissance());
        clt.emailTextField.setText(clientSelected.getEmail());
        clt.telephoneTextField.setText(clientSelected.getTelephone());
        clt.codePostalTextField.setText(clientSelected.getCode_postale());
        clt.villeTextField.setText(clientSelected.getVille());
        clt.cinTextField.setText(clientSelected.getIdClient());
        clt.numPermisTextField.setText(clientSelected.getN_permis());
        clt.dateDelivreDatePicker.setValue(H.convert(clientSelected.getDelevre_a()));
        clt.dateValiditeDatePicker.setValue(H.convert(clientSelected.getValiditePermis()));
        clt.dateNaissanceDatePicker.setValue(H.convert(clientSelected.getDate_naissance()));
        clt.adresseTextField.setText(clientSelected.getAdress());
        clt.typeCinCombo.setValue(clientSelected.getCarte_identifiant());
        clt.paysTextField.setText(clientSelected.getPays());
        clt.lieuDelivreTextField.setText(clientSelected.getDelevre_le());
    }*/

    
    
    public void handleChercherComboBox(ActionEvent actionEvent) {
        String searchSection = chercherComboBox.getValue();
        chercherButton.setDisable(false);

        if(searchSection.equals("Tous") || searchSection.isEmpty()){

            disable(dataTextFeild);

        }else if(searchSection.equals("CIN") || searchSection.equals("Nom") || searchSection.equals("Prenom")){

            enable(dataTextFeild);

        }
    }

    public void handleChercherButton(ActionEvent actionEvent) {
    	searchSection = chercherComboBox.getValue();
        disable(ErreurMessage);

        if(searchSection.equals("CIN")){
        	client_list.clear();
        	mono_Client.clear();
            String CINTaped = dataTextFeild.getText();

            if(CINTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search client by CIN
            	afficher_client(CINTaped);
            }

        }else if(searchSection.equals("Nom")){
        	client_list.clear();
        	mono_Client.clear();
            String NomTaped = dataTextFeild.getText();

            if(NomTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search client by Nam
            	afficher_client(NomTaped);
            }

        }else if(searchSection.equals("Prenom")){
        	client_list.clear();
        	mono_Client.clear();
            String PrenomTaped = dataTextFeild.getText();

            if(PrenomTaped.isEmpty()){
                enable(ErreurMessage);
            }else {
                // Search client by prenom
            	afficher_client(PrenomTaped);
            }

        }else {
        	remplir_tableau();
        }
    }
    
    public void afficher_client(String valeur) {
    	searchSection = chercherComboBox.getValue();
    	Client client = new Client();
    	ResultSet tous_les_client = null;
    	String sql = "";
		try {
			if(searchSection.equals("Prenom")) {
				sql = "select * from client where prenom=?";
			}
			if(searchSection.equals("Nom")) {
				sql = "select * from client where nom=?";		
			}
			if(searchSection.equals("CIN")) {
				sql = "select * from client where idClient=?";
			}
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, valeur);
			tous_les_client = ps.executeQuery();
			if(tous_les_client.next()) {
				client.setIdClient(tous_les_client.getString("idClient"));
				client.setNom(tous_les_client.getString("nom"));
				client.setPrenom(tous_les_client.getString("prenom"));
				client.setAdress(tous_les_client.getString("adress"));
				client.setTelephone(tous_les_client.getString("telephone"));
				client.setEmail(tous_les_client.getString("email"));
				client.setDate_naissance(tous_les_client.getString("date_naissance"));
				//client_list.add(client);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	mono_Client.add(client);
    	col_cin.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
        col_date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        
        tableClient.setItems(mono_Client);
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
    
    // remplir le tableau par tous les client existe dans la base de donn�es
    public void remplir_tableau(){
        ResultSet tous_les_client = null;
		try {
			String sql = "select * from Client";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			tous_les_client = ps.executeQuery();
			
			while(tous_les_client.next()) {
				Client client = new Client();
				client.setIdClient(tous_les_client.getString("idClient"));
				client.setNom(tous_les_client.getString("nom"));
				client.setPrenom(tous_les_client.getString("prenom"));
				client.setAdress(tous_les_client.getString("adress"));
				client.setTelephone(tous_les_client.getString("telephone"));
				client.setEmail(tous_les_client.getString("email"));
				client.setDate_naissance(tous_les_client.getString("date_naissance"));
				client_list.add(client);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        col_cin.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
        col_date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        
        tableClient.setItems(client_list);
	}

}
