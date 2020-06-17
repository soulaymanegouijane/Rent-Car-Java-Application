package InterfaceDetails;

import Entities.Parking;
import Test.H;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailGarage implements Initializable {
    @FXML
    public TextField idparkingTextField ; //champ de Réference Parking
    @FXML
    public TextField adressparkingTextField ; // champ de l'adresse du parking
    @FXML
    public TextField capaciteParkingTextField ; // champ de Capacité du Parking
    @FXML
    public TextField occupeParkingTextField; // champ du nombre des emplacement occuppé
    @FXML
    public JFXButton closeButton; // boutton OK
    @FXML
    public  JFXButton deletebtn ; // boutton supprimer
    @FXML
    public JFXButton editParkingBtn ; // Modifier boutton
    public StackPane stackPane;
    public HBox nonEditHBox;
    public HBox editHBox;
    public JFXButton SaveeditParkingBtn;
    public JFXButton annulerBtn;

    public static Parking parking = new Parking();
    public Label erreurMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillBlanks();
    }

    public void fillBlanks(){
        adressparkingTextField.setText(parking.getAdress());
        capaciteParkingTextField.setText(String.valueOf(parking.getCapacite()));
        idparkingTextField.setText(String.valueOf(parking.getIdParking()));
        occupeParkingTextField.setText(String.valueOf(parking.getNbr_place_pleinne()));
    }

    public boolean rewriteParking() {
        erreurMessage.setVisible(false);
        if (Tester()){
            erreurMessage.setText("Remplissez tous les champs !!");
            erreurMessage.setVisible(true);
        }else if(!H.isNumeric(capaciteParkingTextField.getText())){
            erreurMessage.setText("Capacité doit étre entier !!");
            erreurMessage.setVisible(true);
        }else if(!H.isNumeric(occupeParkingTextField.getText())){
            erreurMessage.setText("Nombre de places doit étre entier !!");
            erreurMessage.setVisible(true);
        }else if((Integer.parseInt(capaciteParkingTextField.getText()) < Integer.parseInt(occupeParkingTextField.getText()))){
            erreurMessage.setText("Nombre de places doit étre moins de Capacité !!");
            erreurMessage.setVisible(true);
        }else {
            parking.setAdress(adressparkingTextField.getText());
            parking.setCapacite(Long.parseLong(capaciteParkingTextField.getText()));
            parking.setNbr_place_pleinne(Integer.parseInt(occupeParkingTextField.getText()));

            H.parking.edit(parking);
            return true;
        }
        return false;
    }

    @FXML
    public void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void editButtonAction(ActionEvent actionEvent) {
        enableEditing();
    }

    public void deleteBtnAction(ActionEvent actionEvent) {
        int result = H.parking.delete(parking);

        if (result == 0){
            AfficheErreur("Parking");
        }else {
            closeButtonAction();
        }
    }

    public void SaveeditParkingBtnAction(ActionEvent actionEvent) {
        if(rewriteParking())
            disableEditing();
    }

    public void annulerBtnAction(ActionEvent actionEvent) {
        fillBlanks();
        disableEditing();
    }

    public boolean Tester() {
        if(!adressparkingTextField.getText().isEmpty() && !capaciteParkingTextField.getText().isEmpty()
                && !occupeParkingTextField.getText().isEmpty() ) {
            return false;
        }
        return true;
    }

    public void enableEditing(){
        nonEditHBox.setVisible(false);
        editHBox.setVisible(true);

        adressparkingTextField.setEditable(true);
        capaciteParkingTextField.setEditable(true);
        occupeParkingTextField.setEditable(true);
    }

    public void disableEditing(){
        nonEditHBox.setVisible(true);
        editHBox.setVisible(false);

        adressparkingTextField.setEditable(false);
        capaciteParkingTextField.setEditable(false);
        occupeParkingTextField.setEditable(false);
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

