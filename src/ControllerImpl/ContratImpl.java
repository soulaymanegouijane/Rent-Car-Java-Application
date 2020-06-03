package ControllerImpl;

import java.sql.Connection;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Contrat;
import Interfaces.ContratInter;
import Test.H;

public class ContratImpl extends Abst implements ContratInter {


	
	@Override
	public int add(Contrat arg) {
		int status=0;
		Connection con = Abst.getConnection();
		String sql = "INSERT INTO contrat (date_Contrat, date_sortie, idReservation, idVehicule, montant_total, km_retour,"
				+ " km_depart, caution, remise, prix_jour, nbr_jour, heure_sortie, heure_retour, date_retour) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getDateContrat() );
			ps.setString(2, arg.getDate_sortie());
			ps.setLong(3, arg.getReservation().getIdReservation());
			ps.setString(4, arg.getVehicule().getIdVehicule());
			ps.setDouble(5, arg.getMontantTotal());
			ps.setLong(6, arg.getKm_retour());
			ps.setLong(7, arg.getKm_depart());
			ps.setFloat(8, arg.getCaution());
			ps.setFloat(9, arg.getRemise());
			ps.setFloat(10, arg.getPrix_jour());
			ps.setInt(11, arg.getNbr_jour());
			ps.setString(12, arg.getHeure_sortie() );
			ps.setString(13, arg.getHeure_retour() );
			ps.setString(14, arg.getDate_retour() );
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public Contrat edit(Contrat arg) {
		Connection con = Abst.getConnection();
		try {
			String sql = "UPDATE contrat SET date_Contrat =?, date_sortie=?, idReservation=?, idVehicule=?, MontantTotal=?, km_retour=?,"
					+ " km_depart=?, caution=?, remise=?, prix_jours=?, nbr_jours=?, heure_sortie=?, heure_entre=?, date_retour=? where idContrat = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, arg.getDateContrat() );
			ps.setString(2, arg.getDate_sortie());
			ps.setLong(3, arg.getReservation().getIdReservation());
			ps.setString(4, arg.getVehicule().getIdVehicule());
			ps.setDouble(5, arg.getMontantTotal());
			ps.setLong(6, arg.getKm_retour());
			ps.setLong(7, arg.getKm_depart());
			ps.setFloat(8, arg.getCaution());
			ps.setFloat(9, arg.getRemise());
			ps.setFloat(10, arg.getPrix_jour());
			ps.setInt(11, arg.getNbr_jour());
			ps.setString(12, arg.getHeure_sortie() );
			ps.setString(13, arg.getHeure_retour() );
			ps.setString(14, arg.getDate_retour() );
			ps.setLong(15, arg.getIdContrat());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arg;
	}

	@Override
	public int delete(Contrat arg) {
		Connection con = Abst.getConnection();
		int status = 0;
		try {
			String sql = "DELETE FROM contrat where idContrat=?";
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setLong(1,arg.getIdContrat());
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
	public List<Contrat> getAll() {
		Connection con = Abst.getConnection();
		List<Contrat> list = new ArrayList<Contrat>();
		try {
			String sql = "select * from contrat";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Contrat contrat = new Contrat();
				contrat.setDate_retour(rs.getString("date_retour"));
				contrat.setDate_sortie(rs.getString("date_sortie"));
				contrat.setDateContrat(rs.getString("date_Contrat"));
				contrat.setVehicule(H.vehicule.getById(rs.getString("idVehicule")));
				contrat.setReservation(H.reservation.getById(rs.getLong("idReservation")));
				contrat.setMontantTotal(rs.getFloat("MontantTotal"));
				contrat.setCaution(rs.getFloat("caution"));
				contrat.setRemise(rs.getFloat("remise"));
				contrat.setKm_retour(rs.getLong("km_retour"));
				contrat.setKm_depart(rs.getLong("km_depart"));
				contrat.setPrix_jour(rs.getFloat("prix_jours"));
				contrat.setNbr_jour(rs.getInt("nbr_jours"));
				contrat.setHeure_retour(rs.getString("heure_entre"));
				contrat.setHeure_sortie(rs.getString("heure_sortie"));
				contrat.setIdContrat(rs.getLong("idContrat"));
				list.add(contrat);
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
	public Contrat get(String matricule) {
		return null;
	}

	// Logique ?
	public List<Date> ListContratMatricule(String matricule) {
		Connection con = Abst.getConnection();
		List<Date> list = new ArrayList<Date>();
		try {
			String sql = "select date_fin_contrat from contrat where idVehicule=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, matricule );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Date date = rs.getDate("");
				list.add(date);
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
	public Contrat getById(long id){ 
		Connection con = Abst.getConnection();
		Contrat contrat = new Contrat();
		try {
			String sql = "select * from contrat where idContrat=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				contrat.setDate_retour(rs.getString("date_retour"));
				contrat.setDate_sortie(rs.getString("date_sortie"));
				contrat.setDateContrat(rs.getString("date_Contrat"));
				contrat.setVehicule(H.vehicule.getById(rs.getString("idVehicule")));
				contrat.setReservation(H.reservation.getById(rs.getLong("idReservation")));
				contrat.setMontantTotal(rs.getFloat("MontantTotal"));
				contrat.setCaution(rs.getFloat("caution"));
				contrat.setRemise(rs.getFloat("remise"));
				contrat.setKm_retour(rs.getLong("km_retour"));
				contrat.setKm_depart(rs.getLong("km_depart"));
				contrat.setPrix_jour(rs.getFloat("prix_jours"));
				contrat.setNbr_jour(rs.getInt("nbr_jours"));
				contrat.setHeure_retour(rs.getString("heure_entre"));
				contrat.setHeure_sortie(rs.getString("heure_sortie"));
				contrat.setIdContrat(rs.getLong("idContrat"));
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
		return contrat;
	} 

}
