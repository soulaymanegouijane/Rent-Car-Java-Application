package Test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import AbstactClasses.Abst;
import ControllerImpl.CarburantImpl;
import Entities.Carburant;


public class TestApp extends Abst {

	public static void main(String[] args) {
		
		Carburant carburant =  new Carburant(1,"total","total");
		CarburantImpl ar = new CarburantImpl();
		ar.add(carburant);
		System.out.println("bien");
	}

	public static Date date_now(){
		Calendar cal = Calendar.getInstance();
		System.out.println("date ----" + cal.getTime());
		Date now = cal.getTime();
		return now;
	}

	public static Date getDate(int	 year, int month, int day){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		String str = day + "/" + month + "/" + year;
		try {
			date = simpleDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		return date;
	}

}