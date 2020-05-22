package AbstactClasses;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Entities.Client;
import Entities.Reservation;
import Entities.Status;
import Entities.TypeReservation;
import Test.H;

public abstract class Abst extends H {

	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projet_location";
			con = DriverManager.getConnection(url, "root","");
			System.out.println("connected --- ?");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("don't");
		}
		
		return con;
	}
	
	public  int addR(Reservation arg,Client client, Status status, TypeReservation tr) {
		return 0;
	};
	
	public Timestamp toTimeStampp(Date date) {
		Timestamp ms = new Timestamp(date.getTime());
		return ms; 
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
