package Test;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ControllerImpl.CarburantImpl;
import ControllerImpl.ClientImp;
import ControllerImpl.ContratImpl;
import ControllerImpl.FactureImpl;
import ControllerImpl.MarqueImpl;
import ControllerImpl.ParkingImpl;
import ControllerImpl.ReservationImpl;
import ControllerImpl.RoleImpl;
import ControllerImpl.StatusImpl;
import ControllerImpl.TypeImpl;
import ControllerImpl.TypeReservationImpl;
import ControllerImpl.UtilisateurImpl;
import ControllerImpl.VehiculeImpl;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class H {
	public static ClientImp client = new ClientImp();
	public static CarburantImpl carburant = new CarburantImpl();
	public static ParkingImpl parking = new ParkingImpl();
	public static VehiculeImpl vehicule = new VehiculeImpl();
	public static TypeReservationImpl typeres = new TypeReservationImpl();
	public static StatusImpl status = new StatusImpl();
	public static ReservationImpl reservation = new ReservationImpl();
	public static UtilisateurImpl utilisateur = new UtilisateurImpl();
	public static TypeImpl type = new TypeImpl();
	public static RoleImpl role = new RoleImpl();
	public static MarqueImpl marque = new MarqueImpl();
	public static ContratImpl contrat = new ContratImpl();
	public static FactureImpl facture = new FactureImpl();
	
	public static LocalDate convert(String str) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse(str, formatter);
    	return date;
    }

    public static void setfrenchDatePicker(DatePicker datePicker){
		String pattern = "dd/MM/yyyy";
		datePicker.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		});
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isEmailValid(String email) {
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
