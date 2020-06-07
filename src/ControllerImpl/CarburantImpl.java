package ControllerImpl;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.HibernateUtil;
import org.hibernate.Session;
import AbstactClasses.Abst;
import Entities.Carburant;
import Exceptions.AjoutExceptions;
import Interfaces.CarburantInter;


public class CarburantImpl extends Abst implements CarburantInter {

	HibernateUtil hibernatUtil = new HibernateUtil();
	
	@Override	
	public int add(Carburant arg) throws AjoutExceptions{
		Connection con = Abst.getConnection();
		int status=0;
		try {
			
			String sql = "insert into carburant (libelle,description) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2,arg.getDescription());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new AjoutExceptions("carburant");
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

	@Override	// TODO ça marche bien
	public Carburant edit(Carburant arg) {
		
		Connection con = Abst.getConnection();
		Carburant st = new Carburant();
		try {
			
			String sql = "UPDATE carburant set libelle=?, description=? where idCarburant = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getLibelle());
			ps.setString(2, arg.getDescription());
			ps.setLong(3, arg.getIdCarburant());
			ps.executeUpdate();
			st.setLibelle(arg.getLibelle());
			st.setDescription(arg.getDescription());
			st.setIdCarburant(arg.getIdCarburant());
			
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
	
	@Override	// TODO ça marche bien
	public int delete(Carburant arg) {
		
		Connection con = Abst.getConnection();
		int status = 0;
		try {
			String sql = "DELETE from carburant where idCarburant = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, arg.getIdCarburant());
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

	@Override	// TODO ça marche bien
	public List<Carburant> getAll() {
		
		Connection con = Abst.getConnection();
		List<Carburant> list = new ArrayList<Carburant>();
		try {
			String sql = "Select * from carburant";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Carburant st = new Carburant();
				st.setIdCarburant(rs.getInt(1));
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

	@Override  		// TODO ça marche bien
	public Carburant get(String libelle) {
		
		Connection con = Abst.getConnection();
		Carburant st = new Carburant();
		try {
			String sql = "Select * from carburant where libelle=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, libelle);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				st.setIdCarburant(rs.getInt("idCarburant"));
				st.setLibelle(rs.getString("libelle"));
				st.setDescription(rs.getString("description"));
				
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

	
	@Override	// TODO ça marche bien
	public Carburant getById(long id) {
		
		Carburant c = new Carburant();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select idCarburant,libelle,description from carburant where idCarburant=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				c.setIdCarburant(rs.getLong("idCarburant"));
				c.setDescription(rs.getString("description"));
				c.setLibelle(rs.getString("libelle"));
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
		
		return c;
	}

}
