package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Utilisateur;
import Interfaces.UtilisateurInter;
import Test.H;

public class UtilisateurImpl extends Abst implements UtilisateurInter {

	@Override
	public int add(Utilisateur arg) {
		int status =0;
		String sql = "insert into utilisateur (nom,prenom,adress,telephone,email,idUtilisateur,civilite,lieu_naissance,ville,code_postale,pays,nationalite,etat_compte,idRole,naissance,type_identifiant,photo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try {
			conn = Abst.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,arg.getNom());
			preparedStatement.setString(2,arg.getPrenom());
			preparedStatement.setString(3,arg.getAdress());
			preparedStatement.setString(4,arg.getEmail());
			preparedStatement.setString(5,arg.getTele());
			preparedStatement.setString(6, arg.getIdUtilisateur());
			preparedStatement.setString(7,arg.getCivilite());
			preparedStatement.setString(8,arg.getLieu_naissance());
			preparedStatement.setString(9, arg.getCode_postale());
			preparedStatement.setString(10, arg.getVille());
			preparedStatement.setString(11, arg.getPays());
			preparedStatement.setString(12, arg.getNationalite());
			preparedStatement.setString(13, arg.getEtat_compte());
			preparedStatement.setLong(14,arg.getRole().getIdRole());//role
			preparedStatement.setString(15, arg.getNaissance());
			preparedStatement.setString(16, arg.getCarte_identifiant());
			preparedStatement.setBytes(17, arg.getImage());
			status = preparedStatement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Utilisateur edit(Utilisateur arg) {
		
		String sql = "UPDATE utilisateur SET nom=?,prenom=?,adress=?,telephone=?, email=?, idRole =? where CodeUtilisateur=?";
		Connection con = Abst.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1,arg.getNom());
			ps.setString(2,arg.getPrenom());
			ps.setString(3,arg.getAdress());
			ps.setString(4,arg.getTele());
			ps.setString(5,arg.getEmail());
			ps.setLong(6, arg.getRole().getIdRole());
			ps.setString(7, arg.getIdUtilisateur());
			ps.executeUpdate();
			arg.setAdress(arg.getAdress());
			arg.setNom(arg.getNom());
			arg.setPrenom(arg.getPrenom());
			arg.setTele(arg.getTele());
			arg.setEmail(arg.getEmail());
			arg.setIdUtilisateur(arg.getIdUtilisateur());
			arg.setRole(arg.getRole());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arg;
	}

	@Override
	public int delete(Utilisateur arg) {
		int status = 0;
		try {
			String sql = "DELETE FROM utilisateur where CodeUtilisateur=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setString(1,arg.getIdUtilisateur());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public List<Utilisateur> getAll() {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		try {
			String sql = "select * from utilisateur";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setIdUtilisateur(rs.getString(1));
				user.setNom(rs.getString(2));
				user.setPrenom(rs.getString(3));
				user.setAdress(rs.getString(4));
				user.setTele(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setRole(H.role.getById(rs.getLong(8)));
				list.add(user);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Utilisateur get(String nom) {
		Utilisateur c = new Utilisateur();
		try {
			String sql = "Select codeUtilisateur,nom,prenom,adress,telephone,email,idReservation,idRole from utilisateur where nom=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nom);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				c.setIdUtilisateur(rs.getString(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setAdress(rs.getString(4));
				c.setTele(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setImage(rs.getBytes("photo"));
				c.setRole(H.role.getById(rs.getLong("idRole")));
			}else {
				return null;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	@Override
	public Utilisateur getById(long id) {
		Utilisateur c = new Utilisateur();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select idUtilisateur,nom,prenom,adress,telephone,email,idReservation,idRole from utilisateur where idUtilisateur=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				c.setIdUtilisateur(rs.getString(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setAdress(rs.getString(4));
				c.setTele(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setRole(H.role.getById(rs.getLong("idRole")));
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				System.out.println("closed from utilisateur ?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}

	@Override
	public Utilisateur getById(String idUser) {
		Utilisateur c = new Utilisateur();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select idUtilisateur,nom,prenom,adress,telephone,email,idRole from utilisateur where idUtilisateur=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idUser);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				c.setIdUtilisateur(rs.getString(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setAdress(rs.getString(4));
				c.setTele(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setRole(H.role.getById(rs.getLong("idRole")));
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				System.out.println("closed from utilisateur ?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}

}
