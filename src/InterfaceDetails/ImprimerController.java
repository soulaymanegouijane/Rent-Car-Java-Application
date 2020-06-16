package InterfaceDetails;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import AbstactClasses.Abst;
import Entities.Contrat;
import Entities.Factures;
import Entities.Reservation;
import Test.H;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImprimerController implements Initializable {

	 @FXML
	    public Label nomClient;

	    @FXML
	    public Label prenomClient;

	    @FXML
	    public Label cinClient;

	    @FXML
	    public Label adresseClient;

	    @FXML
	    public Label matriculeVehicule;

	    @FXML
	    public Label typeVehicule;

	    @FXML
	    public Label marqueVehicule;
	    
	    @FXML
	    public AnchorPane prt;
	    
	    @FXML
	    public VBox vboxT;

	    @FXML
	    public Label carburantVehicule;
	    
	    @FXML
	    public Button imprimerBtn;
	    
	    @FXML
	    public Label dateFacture;
	    
	    @FXML
	    private JFXButton closeBtn;
	    
	    @FXML
	    private TableView<Factures> table;

	    @FXML
	    public TableColumn<Factures,Long> NumeroFacture;

	    @FXML
	    private TableColumn<Factures,Long> NbrJours;

	    @FXML
	    private TableColumn<Factures,Float> MTReservation;

	    @FXML
	    private TableColumn<Factures,Float> MTSanction;

	    @FXML
	    private TableColumn<Factures,Float> Avance;

	    @FXML
	    private TableColumn<Factures,Float> MontantTotal;
	    
	    ObservableList<Factures> list = FXCollections.observableArrayList();
	    
	    public static long id;
	    
	    public static Factures fcture = new Factures();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(fcture);
		filblanks();
		remplir_tableau();
		
	}
	
	@FXML
    void CloseBtnFct(ActionEvent event) {
		Stage st = (Stage) closeBtn.getScene().getWindow();
		st.close();
    }
	
	@FXML
    void ImprimerFacture(ActionEvent event) {
		closeBtn.setVisible(false);
		imprimerBtn.setVisible(false);
		  Label jobStatus = new Label("Status de l'impression : ");

		  //TextArea textArea = new TextArea();
		  
		  Stage st = (Stage) imprimerBtn.getScene().getWindow();

		  Button printButton = new Button("Imprimer");

		  // Creation d'un handler pour le bouton
		// Creation du printer job
		    PrinterJob job = PrinterJob.createPrinterJob();

		    // Montre la boite de dialogue
		    boolean proceed = job.showPrintDialog(st);

		    // Si l'utilisateur clique sur imprimer dans la boite de dialogue
		    if (proceed) {

		     job.jobStatusProperty().addListener((observable, oldValue, newValue) -> {

		      if (newValue == PrinterJob.JobStatus.PRINTING)
		       jobStatus.setText("Status de l'impression : Impression en cours...");

		      if (newValue == PrinterJob.JobStatus.DONE)
		       jobStatus.setText("Status de l'impression : Texte imprimer avec succs !");

		      if (newValue == PrinterJob.JobStatus.ERROR)
		       jobStatus.setText("Status de l'impression : Erreur lors de l'impression");

		     });

		     // Imprime la zone texte
		     boolean printed = job.printPage(vboxT);

		     if (printed) {
		      job.endJob();
		     }
		    }

		  /* Mise en page */
		  //VBox root = new VBox();

//		  prt.getChildren().addAll(prt, printButton, jobStatus);
//
//		  Scene scene = new Scene(prt);
//		  st.setScene(scene);
//		  /* Fin mise en page */
//
//		  st.show();
		  closeBtn.setVisible(true);
		  CloseBtnFct(event);
    }
	
	
//	public void remplir_tableau(long idFacture) {
//		String sql = "select * from facture where idFacture =?";
//		Connection con = Abst.getConnection();
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setLong(1, idFacture);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				Factures facture = new Factures();
//				facture.setIdFacture(rs.getLong(""));
//				facture.setContrat(H.contrat.getById(rs.getLong("")));
//				
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//			
//	}
	
	public void filblanks() {
		 	adresseClient.setText(fcture.getContrat().getReservation().getClient().getAdress());
	    	cinClient.setText(fcture.getContrat().getReservation().getClient().getIdClient());
	    	prenomClient.setText(fcture.getContrat().getReservation().getClient().getPrenom());
	    	nomClient.setText(fcture.getContrat().getReservation().getClient().getNom());
	    	carburantVehicule.setText(fcture.getContrat().getVehicule().getCarburant().getLibelle());
	    	matriculeVehicule.setText(fcture.getContrat().getVehicule().getIdVehicule());
	    	marqueVehicule.setText(fcture.getContrat().getVehicule().getType().getMarque().getLibelle());
	    	typeVehicule.setText(fcture.getContrat().getVehicule().getType().getLibelle()); 
	    	LocalDate now = LocalDate.now();
	    	dateFacture.setText("Le " + String.valueOf(now) + ", à Agadir");
	    	System.out.println("********************* ---- >"+fcture.getContrat());
	}
	
	public void remplir_tableau() {
		fcture.setMtReserv(Double.valueOf(fcture.getContrat().getReservation().getMontant()));
		fcture.setAvance(fcture.getContrat().getReservation().getAvance());
		list.add(fcture);
		NumeroFacture.setCellValueFactory(new PropertyValueFactory<>("idFacture"));
        NbrJours.setCellValueFactory(new PropertyValueFactory<>("nbr_jours"));
        MTReservation.setCellValueFactory(new PropertyValueFactory<>("mtReserv"));
        MTSanction.setCellValueFactory(new PropertyValueFactory<>("montantSanction"));
        MontantTotal.setCellValueFactory(new PropertyValueFactory<>("montantTTC"));
        Avance.setCellValueFactory(new PropertyValueFactory<>("avance"));
		
        table.setItems(list);
	}

}
