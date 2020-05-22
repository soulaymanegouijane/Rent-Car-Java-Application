package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.TypeReservation;
import Interfaces.TypeReservationInter;

public class TypeReservationImpl extends Abst implements TypeReservationInter {

	@Override
	public int add(TypeReservation arg) {
		int status= 0;
		
		try {
			Connection con = Abst.getConnection();
			String sql = "insert into type_reservation (libelle,description) values(?,?)";
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
	public TypeReservation edit(TypeReservation arg) {
		TypeReservation tr = new TypeReservation();
		try {
			Connection con = Abst.getConnection();
			String sql = "UPDATE type_reservation set libelle=?, description=? where idTypeRes = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2, arg.getDescription());
			ps.setLong(3, arg.getIdTypeReservation());
			ps.executeUpdate();
			tr.setLibelle(arg.getLibelle());
			tr.setDescription(arg.getDescription());
			tr.setIdTypeReservation(arg.getIdTypeReservation());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tr;
	}

	@Override
	public int delete(TypeReservation arg) {
		int status = 0;
		try {
			String sql = "DELETE from type_reservation where idTypeRes = ?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdTypeReservation());
			status =ps.executeUpdate(); 
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<TypeReservation> getAll() {
		List<TypeReservation> list = new ArrayList<TypeReservation>();
		try {
			Connection con = Abst.getConnection();
			String sql = "Select * from type_reservation";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TypeReservation tr = new TypeReservation();
				tr.setIdTypeReservation(rs.getInt(1));
				tr.setLibelle(rs.getString(2));
				tr.setDescription(rs.getString(3));
				list.add(tr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public TypeReservation get(String libelle) {
		TypeReservation tr = new TypeReservation();
		try {
			String sql = "Select * from type_reservation where libelle=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, libelle);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				tr.setIdTypeReservation(rs.getInt(1));
				tr.setLibelle(rs.getString(2));
				tr.setDescription(rs.getString(3));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tr;
	}
	
	@Override
	public TypeReservation getById(long id) {
		TypeReservation tr = new TypeReservation();
		try {
			String sql = "Select * from type_reservation where idTypeRes=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				tr.setIdTypeReservation(rs.getInt(1));
				tr.setLibelle(rs.getString(2));
				tr.setDescription(rs.getString(3));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas --- classTypeReservation");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tr;
	}
	

}
