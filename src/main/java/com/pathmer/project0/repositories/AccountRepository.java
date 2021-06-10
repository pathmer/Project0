package com.pathmer.project0.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pathmer.project0.beans.Account;
import com.pathmer.project0.logging.AppLogger;
import com.pathmer.project0.utils.JDBCConnection;

public class AccountRepository implements RootRepository<Account> {
	
	private Connection conn = JDBCConnection.getConnection();


	@Override
	public List<Account> getAll() {
		AppLogger.logger.info("Account table view all request.");
		List<Account> accounts = new ArrayList<Account>();
		
		String sql = "select * from \"Project_0\".accounts;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccnumber(rs.getInt("accnumber"));
				a.setAtype(rs.getString("atype"));
				a.setBalance(rs.getDouble("balance"));
				a.setAstatus(rs.getString("astatus"));
				a.setUsers(rs.getInt("users"));
				
				accounts.add(a);
			}
			return accounts;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getById(Integer id) {
		AppLogger.logger.info("Account table view by ID request.");
		String sql = "select * from \"Project_0\".accounts where id = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccnumber(rs.getInt("accnumber"));
				a.setAtype(rs.getString("atype"));
				a.setBalance(rs.getDouble("balance"));
				a.setAstatus(rs.getString("astatus"));
				a.setUsers(rs.getInt("users"));
				return a;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account add(Account a) {
		AppLogger.logger.info("Account table add record request.");
		String sql = "call Insert_Account('" + a.getAtype() + "', '" + a.getUsers() + "');";
		
		try {
			Statement ps = conn.createStatement();
			ps.execute(sql);	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account update(Account a) {
		AppLogger.logger.info("Account table update record request.");
		String sql = "update \"Project_0\".accounts set id = ?, accnumber = ? , atype = ?, balance = ?, astatus = ?, users = ? where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.setInt(2, a.getAccnumber());
			ps.setString(3, a.getAtype());
			ps.setDouble(4, a.getBalance());
			ps.setString(5, a.getAstatus());
			ps.setInt(6, a.getUsers());
			ps.setInt(7, a.getId());
			
			boolean success = (ps.execute());
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				
				if (rs.next()) {
					a.setId(rs.getInt("id"));
					a.setAccnumber(rs.getInt("accnumber"));
					a.setAtype(rs.getString("atype"));
					a.setBalance(rs.getDouble("balance"));
					a.setAstatus(rs.getString("astatus"));
					a.setUsers(rs.getInt("users"));
					return a;
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public boolean delete(Integer id) {
		AppLogger.logger.info("Account table delete record request.");
		String sql = "delete from \"Project_0\".accounts where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Account> getByAstatus(String astatus) {
		AppLogger.logger.info("Account table view by status request.");
		List<Account> accounts = new ArrayList<Account>();
		
		String sql = "select * from \"Project_0\".accounts where astatus = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, astatus);
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccnumber(rs.getInt("accnumber"));
				a.setAtype(rs.getString("atype"));
				a.setBalance(rs.getDouble("balance"));
				a.setAstatus(rs.getString("astatus"));
				a.setUsers(rs.getInt("users"));
				
				accounts.add(a);
			}
			return accounts;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Account> getByUser(Integer userid) {
		AppLogger.logger.info("Account table view by user request.");
		List<Account> accounts = new ArrayList<Account>();
		
		String sql = "select * from \"Project_0\".accounts where users = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccnumber(rs.getInt("accnumber"));
				a.setAtype(rs.getString("atype"));
				a.setBalance(rs.getDouble("balance"));
				a.setAstatus(rs.getString("astatus"));
				a.setUsers(rs.getInt("users"));
				
				accounts.add(a);
			}
			return accounts;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account getByAccNumber(Integer accnum) {
		AppLogger.logger.info("Account table view by account number request.");
		String sql = "select * from \"Project_0\".accounts where accnumber = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accnum);
			
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccnumber(rs.getInt("accnumber"));
				a.setAtype(rs.getString("atype"));
				a.setBalance(rs.getDouble("balance"));
				a.setAstatus(rs.getString("astatus"));
				a.setUsers(rs.getInt("users"));
				return a;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
