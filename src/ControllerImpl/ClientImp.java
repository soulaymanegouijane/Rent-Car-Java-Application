package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import AbstactClasses.Abst;
import Entities.Client;
import Entities.Reservation;
import Interfaces.ClientInter;
import javafx.collections.ObservableList;

public class ClientImp extends Abst implements ClientInter{

	@Override
	public int add(Client arg) {
		
		int status =0;
		String sql = "INSERT INTO client ( nom, prenom, adress, telephone, email, age, civilite, date_naissance,"
				+ "lieu_naissance,n_permis,delevre_le , validitePermis, delevre_a,type_identifiant , num_carte, validitePI,code_postale,ville,pays,nationalite,idClient)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = Abst.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(21, arg.getIdClient());
			preparedStatement.setString(1,arg.getNom());
			preparedStatement.setString(2,arg.getPrenom());
			preparedStatement.setString(3,arg.getAdress());
			preparedStatement.setString(4,arg.getTelephone());
			preparedStatement.setString(5,arg.getEmail());
			preparedStatement.setInt(6,arg.getAge());
			preparedStatement.setString(7,arg.getCivilite());
			preparedStatement.setString(8,arg.getDate_naissance());
			preparedStatement.setString(9,arg.getLieu_naissance());
			preparedStatement.setString(10,arg.getN_permis());
			preparedStatement.setString(11,arg.getDelevre_le());
			preparedStatement.setString(12,arg.getValiditePermis());
			preparedStatement.setString(13,arg.getDelevre_a());
			preparedStatement.setString(14,arg.getCarte_identifiant());
			preparedStatement.setString(15,arg.getNum_carte());
			preparedStatement.setString(16,arg.getValiditePI());
			preparedStatement.setString(17, arg.getCode_postale());
			preparedStatement.setString(19, arg.getPays());
			preparedStatement.setString(18, arg.getVille());
			preparedStatement.setString(20, arg.getNationalite());
			status = preparedStatement.executeUpdate();
			
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
	public Client edit(Client arg) {
		String sql = "UPDATE Client SET nom=?, prenom=?, adress=?, telephone=?, email=?, age=?, civilite=?, date_naissance=?,"
				+ "lieu_naissance=?,n_permis=?,delevre_le=? , validitePermis=?, delevre_a=?,type_identifiant=? , num_carte=?, validitePI=?  where idClient=?";
		Connection con = Abst.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,arg.getNom());
			ps.setString(2,arg.getPrenom());
			ps.setString(3,arg.getAdress());
			ps.setString(4,arg.getTelephone());
			ps.setString(5,arg.getEmail());
			ps.setInt(5, arg.getAge());
			ps.setString(5,arg.getCivilite());
			//ps.setDate(1, arg.getDate_naissance());
			ps.setString(5,arg.getLieu_naissance());
			ps.setString(5,arg.getN_permis());
			//ps.setDate(1, arg.getDelevre_le());
			//ps.setDate(1, arg.getValiditePermis());
			ps.setString(5,arg.getDelevre_a());
			ps.setString(5,arg.getCarte_identifiant());
			ps.setString(5,arg.getNum_carte());
			//ps.setDate(1, arg.getValiditePI());
			ps.setString(6, arg.getIdClient());
			ps.executeUpdate();
			arg.setIdClient(arg.getIdClient());
			arg.setAdress(arg.getAdress());
			arg.setNom(arg.getNom());
			arg.setPrenom(arg.getPrenom());
			arg.setTelephone(arg.getTelephone());
			arg.setEmail(arg.getEmail());
			arg.setIdClient(arg.getIdClient());
			arg.setAge(arg.getAge());
			arg.setCivilite(arg.getCivilite());
			arg.setDate_naissance(arg.getDate_naissance());
			arg.setLieu_naissance(arg.getLieu_naissance());
			arg.setN_permis(arg.getN_permis());
			arg.setDelevre_a(arg.getDelevre_a());
			arg.setCarte_identifiant(arg.getCarte_identifiant());
			arg.setNum_carte(arg.getNum_carte());
			arg.setValiditePermis(arg.getValiditePermis());
			arg.setValiditePI(arg.getValiditePI());
			
			
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
		
		return arg;
	}

	@Override
	public int delete(Client arg) {
		Connection con = Abst.getConnection();
		int status = 0;
		try {
			String sql = "DELETE FROM client where idClient=?";
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setString(1,arg.getIdClient());
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
	public List<Client> getAll() {
		Connection con = Abst.getConnection();
		List<Client> list = new ArrayList<Client>();
		try {
			String sql = "select * from Client";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getString("idClient"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdress(rs.getString("adress"));
				client.setTelephone(rs.getString("telephone"));
				client.setEmail(rs.getString("email"));
				client.setCarte_identifiant(rs.getString("type_identifiant"));
				//client.setValiditePI(rs.getDate("validitePI"));
				client.setNum_carte(rs.getString("num_carte"));
				client.setDelevre_a(rs.getString("delevre_a"));
				//client.setValiditePermis(rs.getDate("validitePermis"));
				//client.setDelevre_le(rs.getDate("delevre_le"));
				client.setN_permis(rs.getString("n_permis"));
				client.setLieu_naissance(rs.getString("lieu_naissance"));
				//client.setDate_naissance(rs.getDate("date_naissance"));
				client.setCivilite(rs.getString("civilite"));
				client.setAge(rs.getInt("age"));
				list.add(client);
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
	public Client get(String nom) {
		Connection con = Abst.getConnection();
		Client client = new Client();
		try {
			String sql = "Select * from Client where nom=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nom);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				client.setIdClient(rs.getString("idClient"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdress(rs.getString("adress"));
				client.setTelephone(rs.getString("telephone"));
				client.setEmail(rs.getString("email"));
				client.setCarte_identifiant(rs.getString("type_identifiant"));
				//client.setValiditePI(rs.getDate("validitePI"));
				client.setNum_carte(rs.getString("num_carte"));
				client.setDelevre_a(rs.getString("delevre_a"));
				//client.setValiditePermis(rs.getDate("validitePermis"));
				//client.setDelevre_le(rs.getDate("delevre_le"));
				client.setN_permis(rs.getString("n_permis"));
				client.setLieu_naissance(rs.getString("lieu_naissance"));
				//client.setDate_naissance(rs.getDate("date_naissance"));
				client.setCivilite(rs.getString("civilite"));
				client.setAge(rs.getInt("age"));
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
		
		return client;
	}
	
	/** Logique ? ou non */
	@Override
	public List<Reservation> trouverTousLesReservation(Client client) {
		Connection con = Abst.getConnection();
		List<Reservation> list = new ArrayList<Reservation>();
		try {
			String sql = "Select * from Reservation where idClient = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, client.getIdClient());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setIdReservation(rs.getLong(1));
				res.setDatReservation(rs.getString(2));
				ClientImp cli = new ClientImp();
				StatusImpl sti = new StatusImpl();
				TypeReservationImpl tri = new TypeReservationImpl();
				res.setClient(cli.getById(rs.getLong(3)));
				res.setStatus(sti.getById(rs.getLong(5)));
				res.setTypeRes(tri.getById(rs.getLong(4)));
				list.add(res);
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
	public Client getById(long id) {
		Connection con = Abst.getConnection();
		Client client = new Client();
		try {
			String sql = "select * from Client where idClient = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				client.setIdClient(rs.getString("idClient"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdress(rs.getString("adress"));
				client.setTelephone(rs.getString("telephone"));
				client.setEmail(rs.getString("email"));
				client.setCarte_identifiant(rs.getString("type_identifiant"));
				//client.setValiditePI( rs.getDate("validitePI"));
				client.setNum_carte(rs.getString("num_carte"));
				client.setDelevre_a(rs.getString("delevre_a"));
				//client.setValiditePermis( rs.getDate("validitePermis"));
				//client.setDelevre_le( rs.getDate("delivrer"));
				client.setN_permis(rs.getString("n_permis"));
				client.setLieu_naissance(rs.getString("lieu_naissance"));
				//client.setDate_naissance( rs.getDate("date_naissance"));
				client.setCivilite(rs.getString("civilite"));
				client.setAge(rs.getInt("age"));
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
		return client;
	}

	public Client byPrenom(String prenom) {
		Connection con = Abst.getConnection();
		Client client = new Client();
		try {
			String sql = "Select * from Client where prenom=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, prenom);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				client.setIdClient(rs.getString("idClient"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdress(rs.getString("adress"));
				client.setTelephone(rs.getString("telephone"));
				client.setEmail(rs.getString("email"));
				client.setDate_naissance(rs.getString("date_naissance"));
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
		
		return client;
	}
	
	public Client byNom(String nom) {
		Connection con = Abst.getConnection();
		Client client = new Client();
		try {
			String sql = "Select * from Client where nom=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nom);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				client.setIdClient(rs.getString("idClient"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdress(rs.getString("adress"));
				client.setTelephone(rs.getString("telephone"));
				client.setEmail(rs.getString("email"));
				client.setDate_naissance(rs.getString("date_naissance"));
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
		
		return client;
	}
	
	public Client ByCin(String cin) {
		Connection con = Abst.getConnection();
		Client client = new Client();
		try {
			String sql = "Select * from Client where cin=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cin);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				client.setIdClient(rs.getString("idClient"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdress(rs.getString("adress"));
				client.setTelephone(rs.getString("telephone"));
				client.setEmail(rs.getString("email"));
				client.setDate_naissance(rs.getString("date_naissance"));
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
		
		return client;
	}
	
	public ObservableList<Client> retourne(){
		
		return null;
	}

	@Override
	public Client getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}