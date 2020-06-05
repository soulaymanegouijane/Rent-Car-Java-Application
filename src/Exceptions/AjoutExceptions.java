package Exceptions;

public class AjoutExceptions extends Exception{
	private String str;
	public AjoutExceptions(String str) {
		AjouterException(str);
	}
	
	public void AjouterException(String str) {
		if(str == "carburant") {
			System.out.println("prb Ajouter Carburant");
		}
	}

}
