package Exceptions;

public class AjoutExceptions extends Exception{
	private String str;
	private boolean bool;
	private String erreur;
	
	
	
	public AjoutExceptions(String str, boolean bool) {
		AjouterException(str,bool);
		this.bool = bool;
	}



	public void AjouterException(String str,Boolean bool) {
		execFunction(str,bool);
	}
	
	private void messageErreur(String str) {
		System.out.println("il y un prb d'ajout d'un(e) nouveau "+ str);
	}
	
	private void execFunction(String str,Boolean bool) {
		switch(str) {
		case "carburant":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "client":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "contrat":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "facture":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "marque":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "parking":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "reservation":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "role":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "sanction":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "status":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "type":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "typeReservation":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "utilisateur":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;
		case "vehicule":
			messageErreur(str);
			if(bool) {
				erreur = "erreur d'ajout du "+ str;
			}
			break;		
		}
	}
	
	
	public boolean isBool() {
		return bool;
	}
	
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	public void erreur() {
		System.out.println("prb");
	}
	
	
	
	
	
}
