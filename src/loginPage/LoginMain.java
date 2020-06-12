package loginPage;

import Entities.Reservation;
import Entities.Status;
import Entities.Utilisateur;
import Entities.Vehicule;
import Test.H;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import AbstactClasses.Abst;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import windowsSwitcher.windowsSwitcher;

public class LoginMain implements Initializable {
	
	@FXML
    public TextField username;
    
	@FXML
	public PasswordField password;
	
	@FXML
    private StackPane stackPane;
    
	public Label erreurMessage;
    private double xOffset = 0;
    private double yOffset = 0;

    public Button enterButton;
    public Button exitButton;
    public AnchorPane rootPane;

    public static Utilisateur loggedInUser = new Utilisateur();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    
    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void handleEnterButton(ActionEvent actionEvent) throws IOException {

        erreurMessage.setVisible(false);
        if (username.getText().isEmpty() || password.getText().isEmpty()){
            erreurMessage.setVisible(true);
        }else{
        	
        	Connection con = Abst.getConnection();
            
            try {
                String sql = "select * from utilisateur where username=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username.getText());
                ResultSet loggedUtilisateur = ps.executeQuery();

                if (loggedUtilisateur.next()){

                    loggedInUser.setIdUtilisateur(loggedUtilisateur.getString("idUtilisateur"));
                    loggedInUser.setNom(loggedUtilisateur.getString("nom"));
                    loggedInUser.setPrenom(loggedUtilisateur.getString("prenom"));
                    loggedInUser.setAdress(loggedUtilisateur.getString("adress"));
                    loggedInUser.setTele(loggedUtilisateur.getString("telephone"));
                    loggedInUser.setEmail(loggedUtilisateur.getString("email"));
                    loggedInUser.setNaissance(loggedUtilisateur.getString("naissance"));
                    loggedInUser.setEtat_compte(loggedUtilisateur.getString("etat_compte"));
                    loggedInUser.setCivilite(loggedUtilisateur.getString("civilite"));
                    loggedInUser.setLieu_naissance(loggedUtilisateur.getString("lieu_naissance"));
                    loggedInUser.setVille(loggedUtilisateur.getString("ville"));
                    loggedInUser.setPays(loggedUtilisateur.getString("pays"));
                    loggedInUser.setNationalite(loggedUtilisateur.getString("nationalite"));
                    loggedInUser.setCarte_identifiant(loggedUtilisateur.getString("type_identifiant"));

                    loggedInUser.setUsername(loggedUtilisateur.getString("username"));
                    loggedInUser.setPass(loggedUtilisateur.getString("pass"));
                    loggedInUser.setImage(loggedUtilisateur.getBytes("photo"));
                    loggedInUser.setRole(H.role.getById(loggedUtilisateur.getLong("idRole")));
                }else{
                    erreurMessage.setVisible(true);
                }

                if (password.getText().equals(loggedInUser.getPass()) && loggedInUser.getEtat_compte().equals("Activer")) {
                	System.out.println("------------- *** ------>");
                	ActualiserDispoVehicule();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../windowsSwitcher/windowsSwitcher.fxml"));
                    Parent root = loader.load();

                    windowsSwitcher switcher = loader.getController();
                    switcher.handleAcceuilButton(actionEvent);
                    windowsSwitcher.loggedInUser = loggedInUser;

                    Scene home = new Scene(root);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();
                        }
                    });
                    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            stage.setX(event.getScreenX() - xOffset);
                            stage.setY(event.getScreenY() - yOffset);
                        }
                    });

                    stage.setScene(home);
                    stage.show();
                }else{
                    erreurMessage.setVisible(true);
                    if(loggedInUser.getEtat_compte().equals("Disactiver")) {

                    	JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                        jfxDialogLayout.setAlignment(Pos.CENTER);
                        jfxDialogLayout.setHeading(new Text("test"));
                        JFXButton okay = new JFXButton("Close");
                        jfxDialogLayout.setBody(new Text("body text"));
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
                con.close();
            } catch (SQLException e) {
                erreurMessage.setVisible(true);
            }

        }

    }

    public void ActualiserDispoVehicule() {
    	String sql = "select * from reservation where idStatus=?";
    	Connection con = Abst.getConnection();
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, 1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LocalDate date = H.convert(rs.getString("dateReservation"));
				LocalDate now = LocalDate.now();
		    	LocalDate aide = now.minusDays(2);
		    	System.out.println("-**-**-**-**-->From new Function");
		    	if(date.isBefore(aide)) {
		    		Status s = H.status.getById(4);
		    		Reservation res = H.reservation.getById(rs.getLong("idReservation"));
		    		res.setStatus(s);
		    		H.reservation.edit(res);
		    		Vehicule veh = res.getVehicule();
		    		veh.setDispo("Disponible");
		    		H.vehicule.edit(veh);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
