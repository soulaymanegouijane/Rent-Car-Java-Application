package Test;

import Entities.Utilisateur;

public class mainApp {

	public static void main(String[] args) {
		Utilisateur user = H.utilisateur.getByUsername("admin");
		System.out.println(user);

	}

}
