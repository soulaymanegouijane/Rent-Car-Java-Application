package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Parking;
import Interfaces.ParkingInter;
import Test.H;

public class ParkingImpl extends Abst implements ParkingInter{

	@Override
	public int add(Parking arg) {
		int status=0;
		try {
			Connection con = Abst.getConnection();
			String sql = "insert into parking (adress,capacite,nbr_place_pleinne) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getAdress());
			ps.setLong(2,arg.getCapacite());
			ps.setLong(3, arg.getNbr_place_pleinne());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Parking edit(Parking arg) {
		Parking st = new Parking();
		try {
			Connection con = Abst.getConnection();
			String sql = "UPDATE parking set adress=?, capacite=? where idParking = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getAdress());
			ps.setLong(2, arg.getCapacite());
			ps.setLong(3, arg.getIdParking());
			ps.executeUpdate();
			st.setAdress(arg.getAdress());
			st.setCapacite(arg.getCapacite());
			st.setIdParking(arg.getIdParking());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}

	@Override
	public int delete(Parking arg) {
		int status = 0;
		try {
			String sql = "DELETE from parking where idParking = ?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdParking());
			status =ps.executeUpdate(); 
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	
	@Override
	public List<Parking> getAll() {
		List<Parking> list = new ArrayList<Parking>();
		try {
			Connection con = Abst.getConnection();
			String sql = "Select * from parking";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Parking parking = new Parking();
				parking.setIdParking(rs.getLong(1));
				parking.setAdress(rs.getString(2));
				parking.setCapacite(rs.getLong(3));
				list.add(parking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	///////////////////////*****************  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Override
	public Parking get(String adress) {
		Parking parking = new Parking();
		try {
			String sql = "Select * from parking where adress=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, adress);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				parking.setIdParking(rs.getLong("idParking"));
				parking.setAdress(rs.getString("adress"));
				parking.setCapacite(rs.getLong("capacite"));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return parking;
	}

	@Override
	public Parking getById(long id) {
		Parking parking = new Parking();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select idParking,adress,capacite from parking where idParking=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				parking.setIdParking(rs.getLong("idParking"));
				parking.setAdress(rs.getString("adress"));
				parking.setCapacite(rs.getLong("capacite"));
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				System.out.println("closed from parking ?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return parking;
	}

}
