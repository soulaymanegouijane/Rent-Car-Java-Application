package InterfaceDetails;

import Entities.Contrat;
import Entities.Factures;
import Entities.Reservation;
import Test.H;
import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ConsulterFacture implements Initializable {
    public AnchorPane factureAnchorPane;
    public TextField idFactureTextField;
    public DatePicker dateFactureDatePicker;
    public TextField ContratTextField;
    public TextField reservationTextField;
    public TextField clientTextField;
    public TextField vehiculeTextField;
    public TextField nombreDeJoursTextField;
    public TextField montantReservationTextField;
    public TextField avanceTextField;
    public TextField joursRetartTextField;
    public TextField montantSanctionTextField;
    public TextField MantantTotaleTextField;
    public Label erreurMessage;
    public JFXButton imprimerButton;
    public JFXButton FermerButton;

    public static Factures facture = new Factures();
    public Contrat contrat = new Contrat();
    public Reservation reservation = new Reservation();
    private long x = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	System.out.println("***" + facture);
        contrat = facture.getContrat();
        x= contrat.getIdContrat();
        reservation = contrat.getReservation();
        H.setfrenchDatePicker(dateFactureDatePicker);
        fillBlanks();
    }

    public void fillBlanks(){
        idFactureTextField.setText(String.valueOf(facture.getIdFacture()));
        dateFactureDatePicker.setValue(H.convert(facture.getDateFacture()));
        ContratTextField.setText(String.valueOf(contrat.getIdContrat()));
        reservationTextField.setText(String.valueOf(facture.getContrat().getReservation().getIdReservation()));
        clientTextField.setText(reservation.getClient().getIdClient());
        vehiculeTextField.setText(contrat.getVehicule().getIdVehicule());
        nombreDeJoursTextField.setText(String.valueOf(facture.getNbr_jours()));
        montantReservationTextField.setText(String.valueOf(reservation.getMontant()));
        avanceTextField.setText(String.valueOf(reservation.getAvance()));
        joursRetartTextField.setText(String.valueOf(facture.getNbrJoursRetard()));
        montantSanctionTextField.setText(String.valueOf(facture.getMontantSanction()));
        MantantTotaleTextField.setText(String.valueOf(facture.getMontantTTC()));
    }
    
    public Factures getFacturebyidContrat(Long idContrat){
        Connection con = Abst.getConnection();
        try {
            String sql = "select * from facture where idContrat=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,idContrat);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Factures factures = new Factures();
                factures.setIdFacture(rs.getLong("idFacture"));
                factures.setContrat(H.contrat.getById(rs.getLong("idContrat")));
                factures.setDateFacture(rs.getString("date_facture"));
                factures.setMontantTTC(rs.getDouble("montant_ttc"));
                factures.setMontantSanction(rs.getFloat("montantSanction"));
                factures.setNbr_jours(rs.getInt("nbr_jours"));
                factures.setNbrJoursRetard(rs.getInt("nbrJoursRetard"));
                return factures;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                System.out.println("closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void handleImprimerButton(ActionEvent actionEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Imprimer.fxml"));
    	ImprimerController.fcture = facture;
    	Parent root = loader.load();
    	
    	ImprimerController imprimerCon = loader.getController();
    	
    	Stage stage = new Stage(); 	
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
       
	   }
    	  
    
}
