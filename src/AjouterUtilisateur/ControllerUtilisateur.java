package AjouterUtilisateur;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class ControllerUtilisateur {
 @FXML
    private JFXButton btn;

    @FXML
    private ImageView idview;
    @FXML
    private TextField text;
    @FXML
    private Image image;
    @FXML
  public void btnimageAction(){
        FileChooser fc= new FileChooser();
        File selectedfile =fc.showOpenDialog(null);
        if(selectedfile!=null){
            image=new Image(selectedfile.toURI().toString(),200,150,true,true);
            //idview=new ImageView(image);
            text.setText(selectedfile.getAbsolutePath());
            idview.setImage(image);

        }
    }
    }
