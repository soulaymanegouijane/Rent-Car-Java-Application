package AjouterMarque;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AjouterMarque {
    /* ------------- Les fx Ids ------------- */
    @FXML
    private TextField libelleMarque;
    @FXML
    private TextField descriptionMarque;
    @FXML
    private TextField libelleType;
    @FXML
    private TextField descriptionType;
    @FXML
    private TextField libelleStatus;
    @FXML
    private TextField descriptionStatus;
    @FXML
    private JFXButton ajouterTypeBtn;
    @FXML
    private JFXButton ajouterMarqueBtn;
    @FXML
    private JFXButton ajouterStatusBtn;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton ajouterToutBtn ; //ce bouttons est pour ajouter tout le field rempli si possible
    /* ------------- End of Les fx Ids ------------- */
    /*-------------Methods ------------- */
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    /*------------- End of Methods ------------- */
}
