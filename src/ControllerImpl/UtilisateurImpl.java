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
		String sql = "insert into utilisateur (nom,prenom,adress,telephone,email,idUtilisateur,civilite,lieu_naissance,ville,code_postale,pays,nationalite,etat_compte,idRole,naissance,type_identifiant,photo,username,pass) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try {
			conn = Abst.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,arg.getNom());
			preparedStatement.setString(2,arg.getPrenom());
			preparedStatement.setString(3,arg.getAdress());
			preparedStatement.setString(4,arg.getTele());
			preparedStatement.setString(5,arg.getEmail());
			preparedStatement.setString(6, arg.getIdUtilisateur());
			preparedStatement.setString(7,arg.getCivilite());
			preparedStatement.setString(8,arg.getLieu_naissance());
			preparedStatement.setString(9, arg.getVille());
			preparedStatement.setString(10, arg.getCode_postale());
			preparedStatement.setString(11, arg.getPays());
			preparedStatement.setString(12, arg.getNationalite());
			preparedStatement.setString(13, arg.getEtat_compte());
			preparedStatement.setLong(14,arg.getRole().getIdRole());
			preparedStatement.setString(15, arg.getNaissance());
			preparedStatement.setString(16, arg.getCarte_identifiant());
			preparedStatement.setBytes(17, arg.getImage());
			preparedStatement.setString(18, arg.getUsername());
			preparedStatement.setString(19, arg.getPass());
			status = preparedStatement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Utilisateur edit(Utilisateur arg) {
		Connection con = Abst.getConnection();
		Utilisateur user = null;
		try {
//			String sq = "UPDATE utilisateur set nom = ?,prenom = ?,adress = ?,email = ?,telephone = ?,civilite = ?,"
//					+ "lieu_naissance ?,code_postale ?,ville =?,pays = ?,nationalite =?,etat_compte =?,idRole =?,naissance =?,"
//					+ "type_identifiant =?,username =?,pass=? where idUtilisateur=?";
			String sql = "update utilisateur set nom=?,prenom=?,adress=?,telephone=?,email=?,civilite=?,"
					+ "lieu_naissance=?,ville=?,code_postale=?,pays=?,nationalite=?,"
					+ "etat_compte=?,idRole=?,naissance=?,type_identifiant=?,username=?,pass=?,photo=? where idUtilisateur=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,arg.getNom());
			preparedStatement.setString(2,arg.getPrenom());
			preparedStatement.setString(3,arg.getAdress());
			preparedStatement.setString(4,arg.getTele());
			preparedStatement.setString(5,arg.getEmail());
			preparedStatement.setString(6,arg.getCivilite());
			preparedStatement.setString(7,arg.getLieu_naissance());
			preparedStatement.setString(8, arg.getVille());
			preparedStatement.setString(9, arg.getCode_postale());
			preparedStatement.setString(10, arg.getPays());
			preparedStatement.setString(11, arg.getNationalite());
			preparedStatement.setString(12, arg.getEtat_compte());
			preparedStatement.setLong(13,arg.getRole().getIdRole());
			preparedStatement.setString(14, arg.getNaissance());
			preparedStatement.setString(15, arg.getCarte_identifiant());
			preparedStatement.setString(16, arg.getUsername());
			preparedStatement.setString(17, arg.getPass());
			preparedStatement.setBytes(18, arg.getImage());
			preparedStatement.setString(19, arg.getIdUtilisateur());
			preparedStatement.executeUpdate();
			user = getById(arg.getIdUtilisateur());
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
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
				Utilisateur c = new Utilisateur();
				c.setIdUtilisateur(rs.getString("idUtilisateur"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdress(rs.getString("adress"));
				c.setTele(rs.getString("telephone"));
				c.setEmail(rs.getString("email"));
				c.setRole(H.role.getById(rs.getLong("idRole")));
				list.add(c);
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
			String sql = "Select * from utilisateur where nom=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nom);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				c.setIdUtilisateur(rs.getString("idUtilisateur"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdress(rs.getString("adress"));
				c.setTele(rs.getString("telephone"));
				c.setEmail(rs.getString("email"));
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
	public Utilisateur getById(String idUser) {
		Utilisateur c = new Utilisateur();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select * from utilisateur where idUtilisateur=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idUser);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				c.setIdUtilisateur(rs.getString("idUtilisateur"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAdress(rs.getString("adress"));
				c.setTele(rs.getString("telephone"));
				c.setEmail(rs.getString("email"));
				c.setVille(rs.getString("ville"));
				c.setCode_postale(rs.getString("code_postale"));
				c.setPays(rs.getString("pays"));
				c.setCarte_identifiant(rs.getString("type_identifiant"));
				c.setCivilite(rs.getString("civilite"));
				c.setLieu_naissance(rs.getString("lieu_naissance"));
				c.setRole(H.role.getById(rs.getLong("idRole")));
				c.setNationalite(rs.getString("nationalite"));
				c.setNaissance(rs.getString("naissance"));
				c.setUsername(rs.getString("username"));
				c.setPass(rs.getString("pass"));
				c.setImage(rs.getBytes("photo"));
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

	public Utilisateur getByUsername(String username) {
		Utilisateur loggedInUser = new Utilisateur();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select * from utilisateur where username=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet loggedUtilisateur =  ps.executeQuery();
			
			if(loggedUtilisateur.next()) {
				 loggedInUser.setIdUtilisateur(loggedUtilisateur.getString("idUtilisateur"));
                 loggedInUser.setNom(loggedUtilisateur.getString("nom"));
                 loggedInUser.setPrenom(loggedUtilisateur.getString("prenom"));
                 loggedInUser.setAdress(loggedUtilisateur.getString("adress"));
                 loggedInUser.setTele(loggedUtilisateur.getString("telephone"));
                 loggedInUser.setEmail(loggedUtilisateur.getString("email"));
                 loggedInUser.setNaissance(loggedUtilisateur.getString("naissance"));
                 loggedInUser.setEtat_compte(loggedUtilisateur.getString("etat_compte"));
                 loggedInUser.setCivilite(loggedUtilisateur.getString("civilite"));
                 loggedInUser.setLieu_naissance(loggedUtilisateur.getString("lieu_naissance"));
                 loggedInUser.setVille(loggedUtilisateur.getString("ville"));
                 loggedInUser.setPays(loggedUtilisateur.getString("pays"));
                 loggedInUser.setNationalite(loggedUtilisateur.getString("nationalite"));
                 loggedInUser.setCarte_identifiant(loggedUtilisateur.getString("type_identifiant"));

                 loggedInUser.setUsername(loggedUtilisateur.getString("username"));
                 loggedInUser.setPass(loggedUtilisateur.getString("pass"));
                 loggedInUser.setImage(loggedUtilisateur.getBytes("photo"));
                 loggedInUser.setRole(H.role.getById(loggedUtilisateur.getLong("idRole")));
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
		
		return loggedInUser;
		
	}
}
