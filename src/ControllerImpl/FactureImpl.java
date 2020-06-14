package ControllerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Factures;
import Interfaces.FactureInter;
import Test.H;

public class FactureImpl extends Abst implements FactureInter {

	@Override
	public int add(Factures arg) {
		int status = 0;
		Connection con = Abst.getConnection();
		String sql = "insert into facture (date_facture,nbr_jours,nbrJoursRetard,montantSanction,montant_ttc,idContrat) values (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, arg.getDateFacture());
			ps.setInt(2, arg.getNbr_jours());
			ps.setLong(3,arg.getNbrJoursRetard());
			ps.setFloat(4,arg.getMontantSanction());
			ps.setDouble(5, arg.getMontantTTC());
			ps.setLong(6, arg.getContrat().getIdContrat());
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
	public Factures edit(Factures arg) {
		String sql ="update facture set date_facture=?,nbr_jours=?,nbrJoursRetard=?,montantSanction=?,montant_ttc=?,idContrat=? where idFacture=?";
		Connection con = Abst.getConnection();
		Factures facture = new Factures();
		try{
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setString(1, arg.getDateFacture());
			ps.setInt(2, arg.getNbr_jours());
			ps.setLong(3,arg.getNbrJoursRetard());
			ps.setFloat(4,arg.getMontantSanction());
			ps.setDouble(5, arg.getMontantTTC());
			ps.setLong(6, arg.getContrat().getIdContrat());
			ps.setLong(7,arg.getIdFacture());
			ps.executeUpdate();
			facture = H.facture.getById(arg.getIdFacture());
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return facture;
	}

	@Override
	public int delete(Factures arg) {
		int status = 0;
		try {
			String sql = "DELETE FROM facture where idFacture=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setLong(1,arg.getIdFacture());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public List<Factures> getAll() {
		return null;
	}

	@Override
	public Factures get(String nom) {
		return null;
	}
	
	@Override
	public Factures getById(long id) {
		return null;
	}

}
