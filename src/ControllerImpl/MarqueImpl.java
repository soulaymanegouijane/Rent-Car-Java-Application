package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Marque;
import Interfaces.MarqueInter;

public class MarqueImpl extends Abst implements MarqueInter {
	
	@Override
	public int add(Marque arg) {
		Connection con = Abst.getConnection();
		int status=0;
		try {
			
			String sql = "insert into marque (libelle,description) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2,arg.getDescription());
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
	public Marque edit(Marque arg) {
		Connection con = Abst.getConnection();
		Marque st = new Marque();
		try {
			
			String sql = "UPDATE marque set libelle=?, description=? where idMarque = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2, arg.getDescription());
			ps.setLong(3, arg.getIdMarque());
			ps.executeUpdate();
			st.setLibelle(arg.getLibelle());
			st.setDescription(arg.getDescription());
			st.setIdMarque(arg.getIdMarque());
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
		
		return st;
	}

	@Override
	public int delete(Marque arg) {
		Connection con = Abst.getConnection();
		int status = 0;
		try {
			String sql = "DELETE from marque where idMarque = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdMarque());
			status =ps.executeUpdate(); 
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
	public List<Marque> getAll() {
		Connection con = Abst.getConnection();
		List<Marque> list = new ArrayList<Marque>();
		try {
			
			String sql = "Select * from marque";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Marque st = new Marque();
				st.setIdMarque(rs.getInt(1));
				st.setLibelle(rs.getString(2));
				st.setDescription(rs.getString(3));
				list.add(st);
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
	public Marque get(String libelle) {
		Connection con = Abst.getConnection();
		Marque st = new Marque();
		try {
			String sql = "Select * from marque where libelle=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, libelle);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				st.setIdMarque(rs.getInt(1));
				st.setLibelle(rs.getString(2));
				st.setDescription(rs.getString(3));
				
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
		
		return st;
	}
	
	@Override
	public Marque getById(long id) {
		Marque marque = new Marque();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select idMarque,libelle,description from marque where idMarque=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				marque.setIdMarque(rs.getLong(1));
				marque.setDescription(rs.getString(3));
				marque.setLibelle(rs.getString(2));
			}else {
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
		
		return marque;
	}

}
