package AjouterUnClient;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
    private JFXButton closeButton ;
	
	@FXML
    private void closeButtonAction(){
	    Stage stage =(Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
    public JFXComboBox<Integer> comboday1 ;
    public JFXComboBox<Integer> comboday2 ;
    public JFXComboBox<Integer> comboday3 ;
    public JFXComboBox<String> combomonth1 ;
    public JFXComboBox<String> combomonth2 ;
    public JFXComboBox<String> combomonth3 ;
    public JFXComboBox<Integer> comboyear1 ;
    public JFXComboBox<Integer> comboyear2 ;
    public JFXComboBox<Integer> comboyear3 ;
    public JFXComboBox<String> comboGender ;
    public JFXComboBox<String> comboIdType ;

    ObservableList<Integer> dayslist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
    ObservableList<Integer> yearslist = FXCollections.observableArrayList(1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,2032,2033,2034,2035,2036,2037,2038,2039,2040);
    ObservableList<String> monthslist = FXCollections.observableArrayList("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre");
    ObservableList<String> Genderlist = FXCollections.observableArrayList("Femme","Homme");
    ObservableList<String> Idtypelist = FXCollections.observableArrayList("Carte Nationale","Passport");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	comboBox();
    }
    
    public void comboBox() {
    	comboday1.setItems(dayslist);
        comboday2.setItems(dayslist);
        comboday3.setItems(dayslist);
        combomonth1.setItems(monthslist);
        combomonth2.setItems(monthslist);
        combomonth3.setItems(monthslist);
        comboyear1.setItems(yearslist);
        comboyear2.setItems(yearslist);
        comboyear3.setItems(yearslist);
        comboGender.setItems(Genderlist);
        comboIdType.setItems(Idtypelist);
    }
}
