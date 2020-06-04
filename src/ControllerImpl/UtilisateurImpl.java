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
		
		String sql = "UPDATE utilisateur SET nom = ?,prenom = ?,adress = ?,telephone = ?,email = ?,idUtilisateur = ?,civilite = ?,lieu_naissance ?,ville ?,code_postale =?,pays = ?,nationalite =?,etat_compte =?,idRole =?,naissance =?,type_identifiant =?,photo =?,username =?,pass=? where idUtilisateur=?";
		Connection con = Abst.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
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
			preparedStatement.setLong(14,arg.getRole().getIdRole());
			preparedStatement.setString(15, arg.getNaissance());
			preparedStatement.setString(16, arg.getCarte_identifiant());
			preparedStatement.setBytes(17, arg.getImage());
			preparedStatement.setString(18, arg.getUsername());
			preparedStatement.setString(19, arg.getPass());
			preparedStatement.setString(20, arg.getIdUtilisateur());
			preparedStatement.executeUpdate();
			arg = H.utilisateur.getById(arg.getIdUtilisateur());
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
	public Utilisateur getById(long id) {
		Utilisateur c = new Utilisateur();
		Connection con = Abst.getConnection();
		try {
			String sql = "Select * from utilisateur where idUtilisateur=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
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
