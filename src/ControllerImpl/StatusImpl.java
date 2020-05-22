package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Reservation;
import Entities.Status;
import Interfaces.StatusInter;

public class StatusImpl extends Abst implements StatusInter {
	ClientImp cli = new ClientImp();
	TypeReservationImpl tre = new TypeReservationImpl();
	
	@Override
	public int add(Status arg) {
		int status=0;
		try {
			Connection con = Abst.getConnection();
			String sql = "insert into status (libelle_status,description) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2,arg.getDescription());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Status edit(Status arg) {
		Status st = new Status();
		try {
			Connection con = Abst.getConnection();
			String sql = "UPDATE status set libelle_status=?, description=? where idStatus = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2, arg.getDescription());
			ps.setLong(3, arg.getIdStatus());
			ps.executeUpdate();
			st.setLibelle(arg.getLibelle());
			st.setDescription(arg.getDescription());
			st.setIdStatus(arg.getIdStatus());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}

	@Override
	public int delete(Status arg) {
		int status = 0;
		try {
			String sql = "DELETE from status where idStatus = ?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdStatus());
			status =ps.executeUpdate(); 
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Status> getAll() {
		List<Status> list = new ArrayList<Status>();
		try {
			Connection con = Abst.getConnection();
			String sql = "Select * from status";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Status st = new Status();
				st.setIdStatus(rs.getInt(1));
				st.setLibelle(rs.getString(2));
				st.setDescription(rs.getString(3));
				list.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public Status get(String libelle) {
		
		Status st = new Status();
		try {
			String sql = "Select * from status where libelle_status=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, libelle);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				st.setIdStatus(rs.getInt(1));
				st.setLibelle(rs.getString(2));
				st.setDescription(rs.getString(3));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}
	
	public long idStatus(String libelle) {
		long id=0;
		try {
			String sql = "select idStatus from status where libelle_status = ? ";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,libelle);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getLong("idStatus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
		
	//  ! get Les reservation par son libelle Affiche le reservation Valide Annule Non valide
	public List<Reservation> tousLesReservation(String libelle){
		List<Reservation> list = new ArrayList<Reservation>();
		long idStatus = idStatus(libelle);
		try {
			String sql = "select * from reservation where idStatus = ? ";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, idStatus);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setIdReservation(rs.getLong("idReservation"));
				reservation.setDatReservation(rs.getDate("dateReservation"));
				reservation.setClient(cli.getById(rs.getLong("idClient")));
				reservation.setStatus(getById(idStatus));
				reservation.setTypeRes(tre.getById(rs.getLong("idTypeRes")));
				list.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Status getById(long id) {
		Status st = new Status();
		try {
			String sql = "Select * from status where idStatus=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				st.setIdStatus(rs.getInt(1));
				st.setLibelle(rs.getString(2));
				st.setDescription(rs.getString(3));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas --- Class Status");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}

}
