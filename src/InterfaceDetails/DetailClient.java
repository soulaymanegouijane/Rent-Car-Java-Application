package InterfaceDetails;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import Entities.Client;
import Test.H;
import com.jfoenix.controls.JFXComboBox;

import AbstactClasses.Abst;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetailClient implements Initializable{


    @FXML
    public TextField prenomTextField;

    @FXML
    public TextField nomTextField;

    @FXML
    public TextField nationaliteTextField;

    @FXML
    public DatePicker dateNaissanceDatePicker;

    @FXML
    public TextField lieuNaissanceTextField;

    @FXML
    public TextField cinTextField;

    @FXML
    public TextField numPermisTextField;

    @FXML
    public TextField lieuDelivreTextField;

    @FXML
    public DatePicker dateDelivreDatePicker;


    @FXML
    public TextField adresseTextField;

    @FXML
    public TextField codePostalTextField;

    @FXML
    public TextField villeTextField;

    @FXML
    public TextField paysTextField;

    @FXML
    public TextField telephoneTextField;

    @FXML
    public TextField emailTextField;
    public JFXComboBox<String> GenreComboBox;
    public HBox editHBox;
    public JFXButton saveEditsButton;
    public JFXButton annulerEditsButton;
    public HBox nonEditHBox;
    public JFXComboBox<String> typeCinCombo;
    public DatePicker dateValiditeDatePicker;
    public Label erreurMessage;
    public StackPane stackPane;

    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    public JFXButton editClientBtn;//boutton Modifier

    public static Client client = new Client();

    ObservableList<String> GenreList = FXCollections.observableArrayList("Femme", "Homme");
    ObservableList<String> Idtypelist = FXCollections.observableArrayList("Carte Nationale","Passport");
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBox();
		fillBlanks();
		H.setfrenchDatePicker(dateNaissanceDatePicker);
		H.setfrenchDatePicker(dateDelivreDatePicker);
		H.setfrenchDatePicker(dateValiditeDatePicker);
	}

    public void comboBox() {
        GenreComboBox.setItems(GenreList);
        typeCinCombo.setItems(Idtypelist);
    }
	
	@FXML
    public void closeButtonAction(){
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
    }
	
	@FXML
    void deleteBtnAction(ActionEvent event) {

	    //Delete Client
		int result = H.client.delete(client);

        if (result == 0){
            AfficheErreur("Client");
        }else {
            closeButtonAction();
        }
    }
	
	@FXML
    void modifierClient(ActionEvent event) {

	    nonEditHBox.setVisible(false);
	    editHBox.setVisible(true);
	    enableEditing();
    }
	
	public boolean testEmpty(){
        if(prenomTextField.getText().isEmpty() || nomTextField.getText().isEmpty() || nationaliteTextField.getText().isEmpty()
                || lieuNaissanceTextField.getText().isEmpty() || emailTextField.getText().isEmpty()
                || telephoneTextField.getText().isEmpty() || codePostalTextField.getText().isEmpty()
                || villeTextField.getText().isEmpty() || cinTextField.getText().isEmpty()
                || numPermisTextField.getText().isEmpty() || dateDelivreDatePicker.getValue() == null
                || dateValiditeDatePicker.getValue() == null || dateNaissanceDatePicker.getValue() == null
                || adresseTextField.getText().isEmpty() || typeCinCombo.getValue() == null
                || GenreComboBox.getValue() == null || paysTextField.getText().isEmpty() || lieuDelivreTextField.getText().isEmpty()
        ) return true;
        return false;
    }

    public void fillBlanks(){

        prenomTextField.setText(client.getPrenom());
        nomTextField.setText(client.getNom());
        nationaliteTextField.setText(client.getNationalite());
        lieuNaissanceTextField.setText(client.getLieu_naissance());
        emailTextField.setText(client.getEmail());
        telephoneTextField.setText(client.getTelephone());
        codePostalTextField.setText(client.getCode_postale());
        villeTextField.setText(client.getVille());
        cinTextField.setText(client.getIdClient());
        numPermisTextField.setText(client.getN_permis());
        dateDelivreDatePicker.setValue(H.convert(client.getDelevre_a()));
        dateValiditeDatePicker.setValue(H.convert(client.getValiditePermis()));
        dateNaissanceDatePicker.setValue(H.convert(client.getDate_naissance()));
        adresseTextField.setText(client.getAdress());
        typeCinCombo.setValue(client.getCarte_identifiant());
        GenreComboBox.setValue(client.getCivilite());
        paysTextField.setText(client.getPays());
        lieuDelivreTextField.setText(client.getDelevre_le());

    }

    public void rewriteClientInfos(){

        client.setPrenom(prenomTextField.getText());
        client.setNom(nomTextField.getText());
        client.setNationalite(nationaliteTextField.getText());
        client.setDate_naissance(((TextField)dateNaissanceDatePicker.getEditor()).getText());
        client.setLieu_naissance(lieuNaissanceTextField.getText());
        client.setIdClient(cinTextField.getText());
        client.setN_permis(numPermisTextField.getText());
        client.setDelevre_le(lieuDelivreTextField.getText());
        client.setDelevre_a(((TextField)dateDelivreDatePicker.getEditor()).getText());
        client.setValiditePermis(((TextField)dateValiditeDatePicker.getEditor()).getText());
        client.setAdress(adresseTextField.getText());
        client.setCode_postale(codePostalTextField.getText());
        client.setPays(paysTextField.getText());
        client.setTelephone(telephoneTextField.getText());
        client.setEmail(emailTextField.getText());
        client.setVille(villeTextField.getText());
        client.setCivilite(GenreComboBox.getValue());
        client.setCarte_identifiant(typeCinCombo.getValue());

    }

    public void handleSaveEditsButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);
        if(testEmpty()){
            erreurMessage.setText("Remplissez tous les champs !!");
            erreurMessage.setVisible(true);
        }else if(!H.isEmailValid(emailTextField.getText())){
            erreurMessage.setText("Ereur dans l'email !!");
            erreurMessage.setVisible(true);
        }else if (!H.isNumeric(codePostalTextField.getText())) {
            erreurMessage.setText("Erreur dans la saisie du Code Postal !!");
            erreurMessage.setVisible(true);
        } else if (!H.isNumeric(telephoneTextField.getText())) {
            erreurMessage.setText("Erreur dans la saisie du Téléphone !!");
            erreurMessage.setVisible(true);
        } else{
            nonEditHBox.setVisible(true);
            editHBox.setVisible(false);
            rewriteClientInfos();

            H.client.edit(client);
        }
    }

    public void handleAnnulerEditsButton(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);
        nonEditHBox.setVisible(true);
        editHBox.setVisible(false);
        fillBlanks();
        disableEditing();
    }

    public void enableEditing(){
        prenomTextField.setEditable(true);
        nomTextField.setEditable(true);
        nationaliteTextField.setEditable(true);
        lieuNaissanceTextField.setEditable(true);
        emailTextField.setEditable(true);
        telephoneTextField.setEditable(true);
        codePostalTextField.setEditable(true);
        villeTextField.setEditable(true);
        cinTextField.setEditable(true);
        numPermisTextField.setEditable(true);
        dateDelivreDatePicker.setDisable(false);
        dateValiditeDatePicker.setDisable(false);
        dateNaissanceDatePicker.setDisable(false);
        adresseTextField.setEditable(true);
        typeCinCombo.setDisable(false);
        GenreComboBox.setDisable(false);
        paysTextField.setEditable(true);
        lieuDelivreTextField.setEditable(true);
    }

    public void disableEditing(){
        prenomTextField.setEditable(false);
        nomTextField.setEditable(false);
        nationaliteTextField.setEditable(false);
        lieuNaissanceTextField.setEditable(false);
        emailTextField.setEditable(false);
        telephoneTextField.setEditable(false);
        codePostalTextField.setEditable(false);
        villeTextField.setEditable(false);
        cinTextField.setEditable(false);
        numPermisTextField.setEditable(false);
        dateDelivreDatePicker.setDisable(true);
        dateValiditeDatePicker.setDisable(true);
        dateNaissanceDatePicker.setDisable(true);
        adresseTextField.setEditable(false);
        typeCinCombo.setDisable(true);
        GenreComboBox.setDisable(true);
        paysTextField.setEditable(false);
        lieuDelivreTextField.setEditable(false);
    }

    boolean cannotDelete = false;
    public void clientexisted() {
    	Connection con = Abst.getConnection();
    	String sql = "select idClient from reservation";
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String cin = rs.getString("idClient");
				if(cinTextField.getText().equals(cin)) {
					cannotDelete = true;
					break;
				}
			}
			if(cannotDelete) {
				AfficheErreur("Client");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void AfficheErreur(String str) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setAlignment(Pos.CENTER);
        jfxDialogLayout.setHeading(new Text("Alert d'erreur"));
        JFXButton okay = new JFXButton("Close");
        jfxDialogLayout.setBody(new Text("Vous pouver pas supprimer ce " + str));
        okay.setPrefWidth(110);
        okay.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white;");
        okay.setButtonType(JFXButton.ButtonType.RAISED);
        JFXDialog j = new JFXDialog(stackPane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        okay.setOnAction(event -> {
            j.close();
        });

        jfxDialogLayout.setActions(okay);
        j.show();
    }
}
