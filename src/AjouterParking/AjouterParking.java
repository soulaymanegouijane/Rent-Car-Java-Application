package AjouterParking;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterParking {
    public TextField idparkingTextField;
    public TextField adressparkingTextField;
    public TextField capaciteParkingTextField;
    public TextField occupeParkingTextField;
    public TextField idUtilisateurTextField;
    public JFXButton submitButton;
    public Label erreurMessage;
    @FXML
    private JFXButton closeButton;

    String idparking;
    String adressparking;
    int capaciteParking;
    int occupeParking;
    String idUtilisateur;

    @FXML
    private void closeButtonAction(){
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void submitButtonAction(ActionEvent actionEvent) {
        erreurMessage.setVisible(false);

        if(Erreur()){
            erreurMessage.setVisible(true);
        }else{
            idparking = idparkingTextField.getText();
            adressparking = adressparkingTextField.getText();
            capaciteParking = Integer.parseInt(capaciteParkingTextField.getText());
            occupeParking = Integer.parseInt(occupeParkingTextField.getText());
            idUtilisateur = idUtilisateurTextField.getText();

            // Upload informations to dataBase

            Stage stage =(Stage) submitButton.getScene().getWindow();
            stage.close();
        }

    }

    public boolean Erreur() {

        if(!isNumeric(capaciteParkingTextField.getText())){
            erreurMessage.setText("La capacité faut étre un entier !!");
            return true;
        }

        if(!isNumeric(occupeParkingTextField.getText())){
            erreurMessage.setText("Le nombre des places occupées faut étre un entier !!");
            return true;
        }

        if(idparking.isEmpty() || adressparking.isEmpty() || idUtilisateur.isEmpty()){
            erreurMessage.setText("Remplire tous les champs !!");
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
