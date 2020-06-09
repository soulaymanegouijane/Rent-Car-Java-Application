package TerminerContrat;

import Test.H;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TerminerContrat implements Initializable {
    public TextField idContrat;
    public TextField idSanction;
    public DatePicker todayDate;
    public TextField nbrDaysRetard;
    public TextField resteFacture;
    public TextField sanction;
    public TextField montantTTC;
    public JFXButton closeButton;
    public JFXButton payerBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        H.setfrenchDatePicker(todayDate);
    }

    public void closeButtonAction(ActionEvent actionEvent) {
    }

    public void payerBtnAction(ActionEvent actionEvent) {
    }
}
