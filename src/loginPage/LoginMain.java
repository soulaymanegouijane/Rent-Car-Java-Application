package loginPage;

import Entities.Utilisateur;
import Test.H;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import AbstactClasses.Abst;
import javafx.scene.Node;
import windowsSwitcher.windowsSwitcher;

public class LoginMain implements Initializable {
	
	@FXML
    public TextField username;
    
	@FXML
	public PasswordField password;
    
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

                if (password.getText().equals(loggedInUser.getPass())) {


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
                }
                con.close();
            } catch (SQLException e) {
                erreurMessage.setVisible(true);
            }

        }

    }

    
}
