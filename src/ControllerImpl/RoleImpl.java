package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Role;
import Interfaces.RoleInter;

public class RoleImpl extends Abst implements RoleInter {

	@Override
	public int add(Role arg) {
		int status=0;
		try {
			Connection con = Abst.getConnection();
			String sql = "insert into role (role,description) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getRole());
			ps.setString(2,arg.getDescription());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Role edit(Role arg) {
		Role ro = new Role();
		try {
			Connection con = Abst.getConnection();
			String sql = "UPDATE role set role=?, description=? where idRole = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getRole());
			ps.setString(2, arg.getDescription());
			ps.setLong(3, arg.getIdRole());
			ps.executeUpdate();
			ro.setRole(arg.getRole());
			ro.setDescription(arg.getDescription());
			ro.setIdRole(arg.getIdRole());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ro;
	}

	@Override
	public int delete(Role arg) {
		int status = 0;
		try {
			String sql = "DELETE from role where idRole = ?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdRole());
			status =ps.executeUpdate(); 
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Role> getAll() {
		List<Role> list = new ArrayList<Role>();
		try {
			Connection con = Abst.getConnection();
			String sql = "Select * from role";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Role ro = new Role();
				ro.setIdRole(rs.getInt(1));
				ro.setRole(rs.getString(2));
				ro.setDescription(rs.getString(3));
				list.add(ro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public Role get(String role) {
		Role ro = new Role();
		try {
			String sql = "Select * from role where role=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, role);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				ro.setIdRole(rs.getInt(1));
				ro.setRole(rs.getString(2));
				ro.setDescription(rs.getString(3));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ro;
	
	}

	@Override
	public Role getById(long id) {
		Role ro = new Role();
		try {
			String sql = "Select * from role where idRole=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				ro.setIdRole(rs.getInt(1));
				ro.setRole(rs.getString(2));
				ro.setDescription(rs.getString(3));
				
			}else {
				System.out.println("il y a quelque chose qui ne va pas --- classRole");
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ro;
	}

}
