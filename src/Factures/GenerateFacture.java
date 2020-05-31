package Factures;

import com.jfoenix.controls.JFXButton;

import Entities.Contrat;
import Entities.Factures;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class GenerateFacture {
	
	@FXML
    private TextField idFacture;

    @FXML
    private TextField idContrat;

    @FXML
    private DatePicker todayDate;

    @FXML
    private TextField nbrDays;

    @FXML
    private TextField montantHT;

    @FXML
    private TextField taxes;

    @FXML
    private TextField montantTTC;

    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXButton generateBtn;
    
    @FXML
    void addFacture(ActionEvent event) {
    	String idFac = idFacture.getText();
    	long idCon = Long.valueOf(idContrat.getText());
    	String today = ((TextField)todayDate.getEditor()).getText();
    	int nbrDay = Integer.valueOf(nbrDays.getText());
    	Double mtHT = Double.valueOf(montantHT.getText());
    	Double tax = Double.valueOf(taxes.getText());
    	Double mtTTC = Double.valueOf(montantTTC.getText());
    	Factures facture = new Factures();
    	Contrat contrat = H.contrat.getById(idCon);
    	facture.setDateFacture(today);
    	facture.setNbr_jours(nbrDay);
    	facture.setMontantTTC(mtTTC);
    	facture.setTVA(tax);
    	facture.setPrixHT(mtHT);
    	int result = H.facture.add(facture);
    	
    	if (result != 0) {
			System.out.println("la facture est bien ajouter");
		}else {
			System.out.println("il ya un prb dans l'ajout");
		}
    	
    }

    @FXML
    void closeFacture(ActionEvent event) {

    }
}
