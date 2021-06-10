package com.pathmer.project0.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pathmer.project0.beans.Transaction;
import com.pathmer.project0.logging.AppLogger;
import com.pathmer.project0.utils.JDBCConnection;

public class TransactionRepository implements RootRepository<Transaction> {
	private Connection conn = JDBCConnection.getConnection();
	

	@Override
	public List<Transaction> getAll() {
		AppLogger.logger.info("Transactions table view all request.");
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		String sql = "select * from \"Project_0\".transactions;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setTtype(rs.getString("ttype"));
				t.setAmount(rs.getDouble("amount"));
				t.setFromacc(rs.getInt("fromacc"));
				t.setToacc(rs.getInt("toacc"));
				t.setUserssn(rs.getInt("userssn"));
				t.setTstatus(rs.getString("tstatus"));
				t.setTransferid(rs.getInt("transferid"));
				t.setAccounts(rs.getInt("accounts"));
				
				transactions.add(t);
			}
			return transactions;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Transaction getById(Integer id) {
		AppLogger.logger.info("Transactions table view by ID request.");
		String sql = "select * from \"Project_0\".transactions where id = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setTtype(rs.getString("ttype"));
				t.setAmount(rs.getDouble("amount"));
				t.setFromacc(rs.getInt("fromacc"));
				t.setToacc(rs.getInt("toacc"));
				t.setUserssn(rs.getInt("userssn"));
				t.setTstatus(rs.getString("tstatus"));
				t.setTransferid(rs.getInt("transferid"));
				t.setAccounts(rs.getInt("accounts"));
				return t;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Transaction add(Transaction t) {
		AppLogger.logger.info("Transactions table add record request.");
		String sql = "insert into \"Project_0\".transactions values (default, ?, ?, ?, ?, ?, ?, default, ?) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTtype());
			ps.setDouble(2, t.getAmount());
			ps.setInt(3, t.getFromacc());
			ps.setInt(4, t.getToacc());
			ps.setInt(5, t.getUserssn());
			ps.setString(6, t.getTstatus());
			ps.setInt(7, t.getAccounts());


			
			boolean success = ps.execute();
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				
				if (rs.next()) {
					t.setId(rs.getInt("id"));
					t.setTtype(rs.getString("ttype"));
					t.setAmount(rs.getDouble("amount"));
					t.setFromacc(rs.getInt("fromacc"));
					t.setToacc(rs.getInt("toacc"));
					t.setUserssn(rs.getInt("userssn"));
					t.setTstatus(rs.getString("tstatus"));
					t.setTransferid(rs.getInt("transferid"));
					t.setAccounts(rs.getInt("accounts"));
					
					return t;
				}
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Transaction update(Transaction t) {
		AppLogger.logger.info("Transactions table update record request.");
		String sql = "update \"Project_0\".transactions set id = ?, ttype = ? , amount = ?, fromacc = ?,"
				+ " toacc = ?, userssn = ?, tstatus = ?, transferid = ?, accounts = ? where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.setString(2, t.getTtype());
			ps.setDouble(3, t.getAmount());
			ps.setInt(4, t.getFromacc());
			ps.setInt(5, t.getToacc());
			ps.setInt(6, t.getUserssn());
			ps.setString(7, t.getTstatus());
			ps.setInt(8, t.getTransferid());
			ps.setInt(9, t.getAccounts());
			ps.setInt(10, t.getId());
			
			boolean success = (ps.execute());
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				
				if (rs.next()) {
					t.setId(rs.getInt("id"));
					t.setTtype(rs.getString("ttype"));
					t.setAmount(rs.getDouble("amount"));
					t.setFromacc(rs.getInt("fromacc"));
					t.setToacc(rs.getInt("toacc"));
					t.setUserssn(rs.getInt("userssn"));
					t.setTstatus(rs.getString("tstatus"));
					t.setTransferid(rs.getInt("transferid"));
					t.setAccounts(rs.getInt("accounts"));
					return t;
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public boolean delete(Integer id) {
		AppLogger.logger.info("Transactions table delete record request.");
		String sql = "delete from \"Project_0\".transactions where id = ? returning *;";
		
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

	public List<Transaction> getByTtype(String ttype, Integer accounts) {
		AppLogger.logger.info("Transactions table view by type request.");
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		String sql = "select * from \"Project_0\".transactions where ttype = ? and accounts = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ttype);
			ps.setInt(2, accounts);
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setTtype(rs.getString("ttype"));
				t.setAmount(rs.getDouble("amount"));
				t.setFromacc(rs.getInt("fromacc"));
				t.setToacc(rs.getInt("toacc"));
				t.setUserssn(rs.getInt("userssn"));
				t.setTstatus(rs.getString("tstatus"));
				t.setTransferid(rs.getInt("transferid"));
				t.setAccounts(rs.getInt("accounts"));
				
				transactions.add(t);
			}
			return transactions;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Transaction> getByAccount(Integer accountid) {
		AppLogger.logger.info("Transactions table view by account request.");
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		String sql = "select * from \"Project_0\".transactions where accounts = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountid);
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setTtype(rs.getString("ttype"));
				t.setAmount(rs.getDouble("amount"));
				t.setFromacc(rs.getInt("fromacc"));
				t.setToacc(rs.getInt("toacc"));
				t.setUserssn(rs.getInt("userssn"));
				t.setTstatus(rs.getString("tstatus"));
				t.setTransferid(rs.getInt("transferid"));
				t.setAccounts(rs.getInt("accounts"));
				
				transactions.add(t);
			}
			return transactions;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Transaction> getByTransferId(Integer transferid) {
		AppLogger.logger.info("Transactions table view by transfer ID request.");
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		String sql = "select * from \"Project_0\".transactions where transferid = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, transferid);
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setTtype(rs.getString("ttype"));
				t.setAmount(rs.getDouble("amount"));
				t.setFromacc(rs.getInt("fromacc"));
				t.setToacc(rs.getInt("toacc"));
				t.setUserssn(rs.getInt("userssn"));
				t.setTstatus(rs.getString("tstatus"));
				t.setTransferid(rs.getInt("transferid"));
				t.setAccounts(rs.getInt("accounts"));
				
				transactions.add(t);
			}
			return transactions;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
