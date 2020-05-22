package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Type;
import Interfaces.TypeInter;

public class TypeImpl extends Abst implements TypeInter {

	MarqueImpl mari = new MarqueImpl();
	
	@Override
	public int add(Type arg) {
		int status=0;
		try {
			Connection con = Abst.getConnection();
			String sql = "insert into type (libelle,description) values(?,?)";
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
	public Type edit(Type arg) {
		Type st = new Type();
		try {
			Connection con = Abst.getConnection();
			String sql = "UPDATE type set libelle=?, description=? where idType = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2, arg.getDescription());
			ps.setLong(3, arg.getIdType());
			ps.executeUpdate();
			st.setLibelle(arg.getLibelle());
			st.setDescription(arg.getDescription());
			st.setIdType(arg.getIdType());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}

	@Override
	public int delete(Type arg) {
		int status = 0;
		try {
			String sql = "DELETE from type where idType = ?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdType());
			status =ps.executeUpdate(); 
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Type> getAll() {
		List<Type> list = new ArrayList<Type>();
		try {
			Connection con = Abst.getConnection();
			String sql = "Select * from type";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Type st = new Type();
				st.setIdType(rs.getLong("idType"));
				st.setLibelle(rs.getString("libelle"));
				st.setDescription(rs.getString("description"));
				st.setMarque(mari.getById(rs.getLong("idMarque")));
				list.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public Type get(String libelle) {
		Type st = new Type();
		try {
			String sql = "Select * from type where libelle=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, libelle);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				st.setIdType(rs.getLong("idType"));
				st.setLibelle(rs.getString("libelle"));
				st.setDescription(rs.getString("description"));
				st.setMarque(mari.getById(rs.getLong("idMarque")));
				
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

	@Override
	public Type getById(long id) {
		Type type = new Type();
		try {
			String sql ="select * from type where idType=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				type.setIdType(rs.getLong("idType"));
				type.setLibelle(rs.getString("libelle"));
				type.setDescription(rs.getString("description"));
				type.setMarque(mari.getById(rs.getLong("idMarque")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return type;
	}

}
