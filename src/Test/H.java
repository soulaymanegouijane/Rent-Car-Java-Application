package Test;

import ControllerImpl.CarburantImpl;
import ControllerImpl.ClientImp;
import ControllerImpl.ContratImpl;
import ControllerImpl.FactureImpl;
import ControllerImpl.MarqueImpl;
import ControllerImpl.ParkingImpl;
import ControllerImpl.ReservationImpl;
import ControllerImpl.RoleImpl;
import ControllerImpl.SanctionImpl;
import ControllerImpl.StatusImpl;
import ControllerImpl.TypeImpl;
import ControllerImpl.TypeReservationImpl;
import ControllerImpl.UtilisateurImpl;
import ControllerImpl.VehiculeImpl;

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
	public static SanctionImpl sanction = new SanctionImpl();
}
