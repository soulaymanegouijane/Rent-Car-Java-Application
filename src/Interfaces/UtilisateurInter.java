package Interfaces;

import Entities.Utilisateur;
import Interfaces.GlobalInter;

public interface UtilisateurInter extends GlobalInter<Utilisateur> {

	public Utilisateur getById(String idUser);
}
