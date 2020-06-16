package AjouterType;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import AbstactClasses.Abst;
import Entities.Type;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterType implements Initializable {
    
    @FXML
    private TextField libelleType;

    @FXML
    private JFXTextArea descriptionType;

    @FXML
    private JFXButton ajouterTypeBtn;

    @FXML
    private JFXButton closeButton;
    
    @FXML
    public JFXComboBox<String> mrqType;

    ObservableList<String> listMarque = FXCollections.observableArrayList();
    
    public String libelleTypeTaped;
    public String descriptionTypeTaped;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		marque_base_donnee();
		comboBox();
	}
    
    public void comboBox() {
    	mrqType.setItems(listMarque);
    }
    
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void ajouterTypeBtnAction(ActionEvent actionEvent) {
        if (!libelleType.getText().isEmpty()){
        	libelleTypeTaped = libelleType.getText();
            descriptionTypeTaped = descriptionType.getText();
            
            String typ = libelleType.getText();
        	String desc = descriptionType.getText();
        	String mrq = mrqType.getValue();
        	
        	Type type = new Type();
        	
        	type.setDescription(desc);
        	type.setLibelle(typ);
        	type.setMarque(H.marque.get(mrq));
        	
        	H.type.add(type);

            Stage stage =(Stage) ajouterTypeBtn.getScene().getWindow();
            stage.close();
        }else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Alert d'erreur");
        	alert.setHeaderText("can not add type");
        	if(libelleType.getText().isEmpty()) {
        		alert.setContentText("type field est vide");
        	}else if(descriptionType.getText().isEmpty()) {
        		alert.setContentText("description field est vide !");
        	}else {
        		alert.setContentText("tu dois remplir les deux champ");
        	}
        	alert.showAndWait();
        }
    }
    
    public void marque_base_donnee() {
    	Connection con = Abst.getConnection();
    	String sql = "select * from marque";
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String marq = rs.getString("libelle");
				listMarque.add(marq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }

	
}
