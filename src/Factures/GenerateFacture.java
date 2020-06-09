package Factures;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Contrat;
import Entities.Factures;
import Test.H;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GenerateFacture implements Initializable {
	
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
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		long fact = getidFacture() + 1;
		idFacture.setText(String.valueOf(fact));
		H.setfrenchDatePicker(todayDate);
	}
    
    public long getidFacture() {
    	Connection con = Abst.getConnection();
    	String sql = "select max(idFacture) as maxFact from facture";
    	long fact = 0;
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				fact = rs.getLong("maxFact");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return fact;
    }
    
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
    	Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

	
}
