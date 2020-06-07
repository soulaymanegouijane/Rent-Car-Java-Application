package Test;



import AbstactClasses.Abst;
import Entities.Carburant;
import Entities.Utilisateur;


public class TestApp extends Abst {

	public static void main(String[] args) {
		
		System.out.println("test");
		
//		Carburant carb = H.carburant.getById(1);
//		System.out.println(carb.toString());
//		
//		carb.setLibelle("Hybride");
//		H.carburant.edit(carb);
//		System.out.println(carb);
		
		Utilisateur user = H.utilisateur.getById("JM63689");
		System.out.println(user.toString());
		user.setPrenom("zakaria");
		H.utilisateur.edit(user);
	}

//	public static Date date_now(){
//		Calendar cal = Calendar.getInstance();
//		System.out.println("date ----" + cal.getTime());
//		Date now = cal.getTime();
//		return now;
//	}
//
//	public static Date getDate(int	 year, int month, int day){
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = null;
//		String str = day + "/" + month + "/" + year;
//		try {
//			date = simpleDateFormat.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		System.out.println(date);
//		return date;
//	}

}