package AjouterMarque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import Entities.Marque;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AjouterMarque {

    @FXML
    private TextField libelleMarque;
    
    @FXML
    public JFXTextArea descriptionMarque;
    
    @FXML
    private JFXButton ajouterMarqueBtn;
    
    @FXML
    private JFXButton closeButton;

    public String libelleMarqueTaped;
    public String descriptionMarqueTaped;
    
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void ajouterMarqueBtnAction(ActionEvent actionEvent) {
        if (!libelleMarque.getText().isEmpty()){
        	libelleMarqueTaped = libelleMarque.getText();
            descriptionMarqueTaped = descriptionMarque.getText();
            
            String mrq = libelleMarque.getText();
        	String desc = descriptionMarque.getText();
        	
        	Marque marque = new Marque();
        	marque.setLibelle(mrq);
        	marque.setDescription(desc);
        	
        	H.marque.add(marque);
        	
//        	listMarque.clear();
//        	marque_base_donnee();

            Stage stage =(Stage) ajouterMarqueBtn.getScene().getWindow();
            stage.close();
        }
    }
}
