package ControllerImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AbstactClasses.Abst;
import Entities.Sanction;
import Interfaces.SanctionInter;

public class SanctionImpl extends Abst implements SanctionInter{

	@Override
	public int add(Sanction arg) {
		int status=0;
		try {
			Connection con = Abst.getConnection();
			String sql = "insert into sanctio (dateSanction,idContrat) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, arg.getDateSanction());
			ps.setLong(2,  arg.getContrat().getIdContrat());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Sanction edit(Sanction arg) {
		try {
			String sql = "UPDATE sanctio SET dateSanction=?,idContrat=? where idSanction=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDate(1, arg.getDateSanction());
			ps.setLong(2, arg.getContrat().getIdContrat());
			ps.setLong(6, arg.getIdSanction());
			ps.executeUpdate();
			arg.setDateSanction(arg.getDateSanction());
			arg.setContrat(arg.getContrat());
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arg;
	}

	@Override
	public int delete(Sanction arg) {
		int status = 0;
		try {
			String sql = "DELETE FROM sanctio where idSanction=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setLong(1,arg.getIdSanction());
			status = ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public List<Sanction> getAll() {
		List<Sanction> list = new ArrayList<Sanction>();
		try {
			String sql = "select * from sanctio";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Sanction sanction = new Sanction();
				sanction.setIdSanction(rs.getLong("idSanction"));
				sanction.setDateSanction(rs.getDate("dateSanction"));
				ContratImpl coni = new ContratImpl();
				sanction.setContrat(coni.getById(rs.getLong("idContrat")));
				list.add(sanction);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Sanction get(String nom) {
		return null;
	}


	@Override
	public Sanction getById(long id) {
		Sanction sanction = new Sanction();
		try {
			String sql ="select * from sanctio where idSanction=?";
			Connection con = Abst.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				sanction.setIdSanction(rs.getLong("idSanction"));
				sanction.setDateSanction(rs.getDate("dateSanction"));
				ContratImpl coni = new ContratImpl();
				sanction.setContrat(coni.getById(rs.getLong("idContrat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sanction;
	}

}
