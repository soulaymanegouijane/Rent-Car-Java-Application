package InterfaceDetails;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetailVehicule {
    @FXML
    public TextField idmatricule; // Matricule du vehicule
    @FXML
    public TextField nombrePlaceTextField; // nombre de place dans le vehicule
    @FXML
    public JFXComboBox<String> TypeCarburant; //type de carburant
    @FXML
    public JFXComboBox<String> marqueVoiture; // marque de vehicule
    @FXML
    public JFXColorPicker idcolor; // la couleur du vehicule
    @FXML
    public TextField idParking; // Le parking ou le véhicule est garé
    @FXML
    public JFXComboBox<String> dispoVehicule; //la disponibilité du Véhicule
    @FXML
    public JFXButton closeButton;// OK boutton
    @FXML
    public JFXButton deleteBtn; //supprimer la Véhicule
    @FXML
    public JFXButton editPhotoVehiculeBtn ; // changer  la photo de la vehicule
    @FXML
    public ImageView photoVehicule ; // la photo de la véhicule
    @FXML
    public Button choisirParkingBtn;// boutton pour parcourrir les park
    @FXML
    public JFXButton editBtn ;// le bouttons Modifier
    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    //deleteBtnAction()
}
