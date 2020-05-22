package ControllerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Factures;
import Interfaces.FactureInter;

public class FactureImpl extends Abst implements FactureInter {

	@Override
	public int add(Factures arg) {
		return 0;
	}

	@Override
	public Factures edit(Factures arg) {
		return null;
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
