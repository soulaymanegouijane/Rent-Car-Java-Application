package ControllerImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Reservation;
import Interfaces.ReservationInter;
import Test.H;

public class ReservationImpl extends Abst implements ReservationInter {
	
	// ! C ----- R ----- U ----- D

	@Override
	public int add(Reservation arg) {
		int status=0;
		Connection con = Abst.getConnection();
		try {
			
			String sql = "insert into reservation (idReservation,dateReservation,idClient,idTypeRes,idStatus,idVehicule,avance,idUtilisateur,montant) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdReservation());
			ps.setString(2, arg.getDatReservation());
			ps.setString(3, arg.getClient().getIdClient());
			ps.setLong(4, arg.getTypeRes().getIdTypeReservation());
			ps.setLong(5, arg.getStatus().getIdStatus());
			ps.setString(6,arg.getVehicule().getIdVehicule());
			ps.setFloat(7, arg.getAvance());
			ps.setString(8, arg.getUtilisateur().getIdUtilisateur());
			ps.setFloat(9, arg.getMontant());
			status = ps.executeUpdate();
			
			
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
		return status;
	}

	@Override
	public Reservation edit(Reservation arg) {
		Connection con = Abst.getConnection();
		try {
			String sql = "UPDATE reservation SET dateReservation=?,idClient=?,idTypeRes=?,idStatus=?,idVehicule=?, avance=? , date_depart = ? , date_retour = ?,montant=?, nombreJours=? where idReservation=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1,arg.getDatReservation());
			ps.setString(2,arg.getClient().getIdClient());
			ps.setLong(3,arg.getTypeRes().getIdTypeReservation());
			ps.setLong(4,arg.getStatus().getIdStatus());
			ps.setString(5, arg.getVehicule().getIdVehicule());
			ps.setFloat(6, arg.getAvance());
			ps.setString(7, arg.getDate_depart());
			ps.setString(8, arg.getDate_retour());
			ps.setFloat(9, arg.getMontant());
			ps.setLong(10, arg.getNombreJours());
			ps.setLong(11, arg.getIdReservation());
			ps.executeUpdate();
			arg.setDatReservation(arg.getDatReservation());
			arg.setAvance(arg.getAvance());
			arg.setDate_depart(arg.getDate_depart());
			arg.setDate_retour(arg.getDate_retour());
			arg.setClient(arg.getClient());
			arg.setStatus(arg.getStatus());
			arg.setTypeRes(arg.getTypeRes());
			arg.setVehicule(arg.getVehicule());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
				System.out.println("closed !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return arg;
	}

	@Override
	public int delete(Reservation arg) {
		int status = 0;
		try {
			String sql = "DELETE FROM reservation where idReservation=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setLong(1,arg.getIdReservation());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			status = 0;
		}
		
		
		return status;
	}

	@Override  	// TODO ça marche bien 
	public List<Reservation> getAll() {
		List<Reservation> list = new ArrayList<Reservation>();
		Connection con = Abst.getConnection();
		try {
			String sql = "select * from reservation";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setIdReservation(rs.getLong("idReservation"));
				res.setDatReservation(rs.getString("dateReservation"));
				res.setAvance(rs.getFloat("avance"));
				res.setDate_depart(rs.getString("date_depart"));
				res.setDate_retour(rs.getString("date_retour"));
				res.setVehicule(H.vehicule.getById(rs.getString("idVehicule")));
				res.setClient(H.client.getById(rs.getString("idClient")));
				res.setStatus(H.status.getById(rs.getLong("idStatus")));
				res.setTypeRes(H.typeres.getById(rs.getLong("idTypeRes")));
				list.add(res);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				System.out.println("closed from reservation ?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Reservation get(String nom) {
		return null;
	}

	@Override	// TODO ça marche bien
	public Reservation getById(long id) {
		Reservation r = new Reservation();
		try {
			String sql ="select * from reservation where idReservation=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				r.setIdReservation(rs.getLong("idReservation"));
				r.setDatReservation(rs.getString("dateReservation"));
				r.setDate_depart(rs.getString("date_depart"));
				r.setNombreJours(rs.getInt("nombreJours"));
				r.setDate_retour(rs.getString("date_retour"));
				r.setClient(H.client.getById(rs.getString("idClient")));
				r.setTypeRes(H.typeres.getById(rs.getLong("idTypeRes")));
				r.setStatus(H.status.getById(rs.getLong("idStatus")));
				r.setVehicule(H.vehicule.getById(rs.getString("idVehicule")));
				r.setUtilisateur(H.utilisateur.getById(rs.getString("idUtilisateur")));
				r.setMontant(rs.getFloat("montant"));
				r.setAvance(rs.getFloat("avance"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}

	// je dois supprimer cette fonction
	public String getCinClient(long idReservation) {
		String id = null;
		Connection con = Abst.getConnection();
		String sql ="select idClient from reservation where idReservation = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, idReservation);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getString("idClient");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	// mais est ce que cette fonction logique ou non ---------> je dois supprimer cette fonction 
	public String getCinUtilisateur(long idReservation) {
		String id = null;
		Connection con = Abst.getConnection();
		String sql ="select idUtilisateur from reservation where idReservation = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, idReservation);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getString("idUtilisateur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public Long getStatus(long idReservation) {
		long result = 0;
		Connection con = Abst.getConnection();
		String sql = "select idStatus from reservation where idReservation = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, idReservation);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getLong("idStatus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	

}
