package com.pathmer.project0.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pathmer.project0.beans.User;
import com.pathmer.project0.logging.AppLogger;
import com.pathmer.project0.utils.JDBCConnection;

public class UserRepository implements RootRepository<User> {
	
	private Connection conn = JDBCConnection.getConnection();

	
	@Override
	public List<User> getAll() {
		AppLogger.logger.info("User table view all request.");
		List<User> users = new ArrayList<User>();
		
		String sql = "select * from \"Project_0\".users;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUtype(rs.getString("utype"));
				u.setUsernames(rs.getString("usernames"));
				u.setPasswords(rs.getString("passwords"));
				u.setSsn(rs.getInt("ssn"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUstatus(rs.getString("ustatus"));
				
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getById(Integer id) {
		AppLogger.logger.info("User table view by ID request.");
		String sql = "select * from \"Project_0\".users where id = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUtype(rs.getString("utype"));
				u.setUsernames(rs.getString("usernames"));
				u.setPasswords(rs.getString("passwords"));
				u.setSsn(rs.getInt("ssn"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUstatus(rs.getString("ustatus"));
				return u;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User add(User u) {
		AppLogger.logger.info("User table add record request.");
		String sql = "insert into \"Project_0\".users values (default, 'customer', ?, ?, ?, ?, ?, 'pending') returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsernames());
			ps.setString(2, u.getPasswords());
			ps.setInt(3, u.getSsn());
			ps.setString(4, u.getFirstname());
			ps.setString(5, u.getLastname());

			
			boolean success = ps.execute();
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				
				if (rs.next()) {
					u.setId(rs.getInt("id"));
					u.setUtype(rs.getString("utype"));
					u.setUsernames(rs.getString("usernames"));
					u.setPasswords(rs.getString("passwords"));
					u.setSsn(rs.getInt("ssn"));
					u.setFirstname(rs.getString("firstname"));
					u.setLastname(rs.getString("lastname"));
					u.setUstatus(rs.getString("ustatus"));
					
					return u;
				}
			}
			
		}
		catch (SQLException e) {
//			e.printStackTrace();
		}

		
		
		return null;
	}

	@Override
	public User update(User u) {
		AppLogger.logger.info("User table update record request.");
		String sql = "update \"Project_0\".users set id = ?, utype = ? , usernames = ?, passwords = ?,"
				+ " ssn = ?, firstname = ?, lastname = ?, ustatus = ? where id = ? returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getUtype());
			ps.setString(3, u.getUsernames());
			ps.setString(4, u.getPasswords());
			ps.setInt(5, u.getSsn());
			ps.setString(6, u.getFirstname());
			ps.setString(7, u.getLastname());
			ps.setString(8, u.getUstatus());
			ps.setInt(9, u.getId());
			
			boolean success = (ps.execute());
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				
				if (rs.next()) {
					u.setId(rs.getInt("id"));
					u.setUtype(rs.getString("utype"));
					u.setUsernames(rs.getString("usernames"));
					u.setPasswords(rs.getString("passwords"));
					u.setSsn(rs.getInt("ssn"));
					u.setFirstname(rs.getString("firstname"));
					u.setLastname(rs.getString("lastname"));
					u.setUstatus(rs.getString("ustatus"));
					return u;
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean delete(Integer id) {
		AppLogger.logger.info("User table delete record request.");
		String sql = "delete from \"Project_0\".users where id = ? returning *;";
		
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

	public List<User> getByUstatus(String ustatus) {
		AppLogger.logger.info("User table view by status request.");
		List<User> users = new ArrayList<User>();
		
		String sql = "select * from \"Project_0\".users where ustatus = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ustatus);
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUtype(rs.getString("utype"));
				u.setUsernames(rs.getString("usernames"));
				u.setPasswords(rs.getString("passwords"));
				u.setSsn(rs.getInt("ssn"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUstatus(rs.getString("ustatus"));
				
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getByUsername(String username) {
		AppLogger.logger.info("User table view by username request.");
		String sql = "select * from \"Project_0\".users where usernames = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUtype( rs.getString("utype"));
				u.setUsernames(rs.getString("usernames"));
				u.setPasswords(rs.getString("passwords"));
				u.setSsn(rs.getInt("ssn"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUstatus(rs.getString("ustatus"));
				return u;

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
