package ControllerImpl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Marque;
import Entities.Parking;
import Entities.Reservation;
import Entities.Type;
import Entities.Vehicule;
import Interfaces.VehiculeInter;
import Test.H;

public class VehiculeImpl extends Abst implements VehiculeInter {
	
	@Override
	public int add(Vehicule arg) {
		int status = 0;
		try {
			String sql = "insert into vehicule (idVehicule,nbr_place,idParking,idCarburant,idType,color,dispo,photo) values (?,?,?,?,?,?,?,?)";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getIdVehicule());
			ps.setInt(2, arg.getNbr_place());
			ps.setLong(3, arg.getParking().getIdParking());
			ps.setLong(4, arg.getCarburant().getIdCarburant());
			ps.setLong(5, arg.getType().getIdType());
			ps.setString(6, arg.getColor());
			ps.setBoolean(7, arg.getDispo());
			ps.setBytes(8, arg.getImage());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Vehicule edit(Vehicule arg) {
		String sql = "UPDATE vehicule SET  nbr_place=?,idParking=?,idCarburant=?,idType=?,color=?,dispo=?,photo=? WHERE idVehicule = ? ";
		Connection con = Abst.getConnection();
		Vehicule vehicule = null;
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, arg.getNbr_place());
			ps.setLong(2, arg.getParking().getIdParking());
			ps.setLong(3, arg.getCarburant().getIdCarburant());
			ps.setLong(4, arg.getType().getIdType());
			ps.setString(5, arg.getColor());
			ps.setBoolean(6, arg.getDispo());
			ps.setBytes(7, arg.getImage());
			ps.setString(8, arg.getIdVehicule());
			ps.executeUpdate();
			vehicule = getById(arg.getIdVehicule());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				System.out.println("closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vehicule;
	}

	@Override
	public int delete(Vehicule arg) {
		int status = 0;
		try {
			String sql = "DELETE FROM vehicule where idVehicule=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setString(1,arg.getIdVehicule());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public List<Vehicule> getAll() {
		List<Vehicule> list = new ArrayList<Vehicule>();
		Connection con = Abst.getConnection();
		try {
			String sql = "select * from vehicule";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Vehicule vehicule = new Vehicule();
				vehicule.setIdVehicule(rs.getString("idVehicule"));
				vehicule.setNbr_place(rs.getInt("nbr_place"));
				vehicule.setParking(H.parking.getById(rs.getLong("idParking")));
				vehicule.setCarburant(H.carburant.getById(rs.getLong("idCarburant")));
				vehicule.setType(H.type.getById(rs.getLong("idType")));
				vehicule.setColor(rs.getString("color"));
				list.add(vehicule);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				System.out.println("closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Vehicule get(String matricule) {
		Vehicule vehicule = new Vehicule();
		try {
			String sql = "Select * from vehicule where idVehicule=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, matricule);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				vehicule.setIdVehicule(rs.getString("idVehicule"));
				vehicule.setNbr_place(rs.getInt("nbr_place"));
				vehicule.setParking(H.parking.getById(rs.getLong("idParking")));
				vehicule.setCarburant(H.carburant.getById(rs.getLong("idCarburant")));
				vehicule.setType(H.type.getById(rs.getLong("idType")));
				vehicule.setColor(rs.getString("color"));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicule;
	}
	
	/** �a Marche pas !! */ 
	@Override
	public Vehicule getById(long id) {
		return null;
	}

	@Override
	public Vehicule getById(String matricule) {
		Vehicule vehicule = new Vehicule();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select * from vehicule where idVehicule=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, matricule);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				vehicule.setIdVehicule(rs.getString("idVehicule"));
				vehicule.setNbr_place(rs.getInt("nbr_place"));
				vehicule.setColor(rs.getString("color"));
				vehicule.setParking(H.parking.getById(rs.getLong("idParking")));
				vehicule.setCarburant(H.carburant.getById(rs.getLong("idCarburant")));
				vehicule.setType(H.type.getById(rs.getLong("idType")));
				vehicule.setImage(rs.getBytes("photo"));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas");
				return null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				System.out.println("closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vehicule;
	}
	
	
	public long getType(String idVehicule) {
		Connection con = Abst.getConnection();
		String sql ="select idType from vehicule where idVehicule =?";
		long result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idVehicule);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getLong("idType");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String marque_libelle(String idVehicule) {
		Connection con = Abst.getConnection();
		long idType = getType(idVehicule);
		Type type = H.type.getById(idType);
		long idMarque = type.getMarque().getIdMarque();
		Marque marque = H.marque.getById(idMarque);
		String libelle_marque = marque.getLibelle();
		return libelle_marque;
	}

		// TODO ??????
//	public void disponible(){
//		List<Reservation> list = H.reservation.getAll();
//
//		Date date = new Date();
//		int Year = date.getYear() + 1900;
//		int Month = date.getMonth() + 1;
//		int Day = date.getDate();
//		Iterator<Reservation> iterator = list.iterator();
//		Date now = getDate(Year, Month, Day);
//		int i = 0;
//		int j = 0;
//		while(iterator.hasNext()){
//			Date d = iterator.next().getDate_retour();
//			if (now.compareTo(d) > 0) {
//				System.out.println("la voiture reserver ------> " +i );
//				Vehicule v = iterator.next().getVehicule();
//				v.setDispo(false);
//				Parking p = H.parking.getById(4);
//				v.setParking(p);
//				H.vehicule.edit(v);
//				i++;
//			} else if (now.compareTo(d) < 0) {
//				System.out.println("la voiture n'est pa  disponible ---- > " + j);
//				j++;
//			}
//		}
//	}

}
