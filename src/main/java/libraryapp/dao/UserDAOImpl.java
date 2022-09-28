package libraryapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Address;
import libraryapp.model.Country;
import libraryapp.model.User;

public class UserDAOImpl implements IUserDAO{

	@Override
	public void insert(User m) throws SQLException {
		String sqlAddress = "insert into addresses (address_name, postal_code, city, country) values (?,?,?, (select id from countries where country_name=?)) on duplicate key update id=id";
		String sqlUser = "insert into users (firstname, lastname, address, phone_number, email, username, password, birthdate, is_admin) "
				+ "values (?,?,(select id from addresses where address_name=?),?,?,?,?,?,?) on duplicate key update id=id";
		try (
				Connection conn = DBUtil.openConnection();
				PreparedStatement psAddress = conn.prepareStatement(sqlAddress);
				PreparedStatement psUser = conn.prepareStatement(sqlUser)
				) {
			// New Address Details
			psAddress.setString(1, m.getAddress().getAddressName());
			psAddress.setString(2, m.getAddress().getPostalCode());
			psAddress.setString(3, m.getAddress().getCity());
			psAddress.setString(4, m.getAddress().getCountry().getName());
			
			psAddress.executeUpdate();
			
			// New User Details
			psUser.setString(1, m.getFirstname());
			psUser.setString(2, m.getLastname());
			psUser.setString(3, m.getAddress().getAddressName());
			psUser.setString(4, m.getPhoneNumber());
			psUser.setString(5, m.getEmail());
			psUser.setString(6, m.getUsername());
			psUser.setString(7,m.getPassword());
			psUser.setDate(8, new Date( m.getBirthdate().getTime())); // Converting Java.Util.Date to Java.SQL.Date and set it to ps
			psUser.setBoolean(9, m.isAdmin());
			
			psUser.executeUpdate();
			
			// Transaction commit
			conn.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(User m) throws SQLException {
		String sqlAddress = "insert into addresses (address_name, postal_code, city, country) values (?,?,?, (select id from countries where country_name=?)) on duplicate key update id=id";

		String sqlUpdateUser = "update user set firstname=?, lastname=?, address=(select id from addresses where address_name=?), "
				+ "phone_number=?, email=?, password=?, birthdate=?, is_admin=? where users.id=?";
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement psAddress = conn.prepareStatement(sqlAddress);
				PreparedStatement psUser = conn.prepareStatement(sqlUpdateUser)
				) {
			// Insert new Address
			psAddress.setString(1, m.getAddress().getAddressName());
			psAddress.setString(2, m.getAddress().getPostalCode());
			psAddress.setString(3, m.getAddress().getCity());
			psAddress.setString(4, m.getAddress().getCountry().getName());
			
			psAddress.executeUpdate();
			
			// Update User
			psUser.setString(1, m.getFirstname());
			psUser.setString(2, m.getLastname());
			psUser.setString(3, m.getAddress().getAddressName());
			psUser.setString(4, m.getPhoneNumber());
			psUser.setString(5, m.getEmail());
			psUser.setString(6, m.getUsername());
			psUser.setString(7,m.getPassword());
			psUser.setDate(8, (Date) m.getBirthdate());
			psUser.setBoolean(9, m.isAdmin());
			psUser.setLong(10, m.getId());
			
			psUser.executeUpdate();
			
			// Commit Transaction
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void delete(User m) throws SQLException {
		
		String sql = "delete * from users where user.id=?";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setLong(1, m.getId());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public User getInstanceById(long id) throws SQLException {
		
		String sql = "select * from users inner join addresses on address=addresses.id inner join countries on country=countries.id where users.id=?";
		User user = new User();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery()){
				
				if(rs.next()) {
					
					user.setId(rs.getLong(1));
					user.setFirstname(rs.getString(2));
					user.setLastname(rs.getString(3));
					user.setAddress(new Address(
							rs.getLong(11),
							rs.getString(12),
							rs.getString(13),
							rs.getString(14),
							new Country(rs.getLong(15), rs.getString(17))
							));
					user.setPhoneNumber(rs.getString(5));
					user.setEmail(rs.getString(6));
					user.setUsername(rs.getString(7));
					user.setPassword(rs.getString(8));
					user.setBirthdate(rs.getDate(9));
					user.setIsAdmin(rs.getBoolean(10));
				}
				
				return user;
				
			}catch (SQLException e) {
				
				e.printStackTrace();
				throw e;
				
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
			throw e;
			
		}
	}

	@Override
	public User getInstanceByStrField(String fieldName, String value) throws SQLException {
		String sql = "select * from users inner join addresses on address=addresses.id inner join countries on country=countries.id where " + fieldName +"=?";
		User user = new User();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setString(1, value);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					user.setId(rs.getLong(1));
					user.setFirstname(rs.getString(2));
					user.setLastname(rs.getString(3));
					user.setAddress(new Address(
							rs.getLong(11),
							rs.getString(12),
							rs.getString(13),
							rs.getString(14),
							new Country(rs.getLong(15), rs.getString(17))
							));
					user.setPhoneNumber(rs.getString(5));
					user.setEmail(rs.getString(6));
					user.setUsername(rs.getString(7));
					user.setPassword(rs.getString(8));
					user.setBirthdate(rs.getDate(9));
					user.setIsAdmin(rs.getBoolean(10));
				}
				
				return user;
				
			}catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<User> getAll() throws SQLException {
		String sql = "select * from users inner join addresses on address=addresses.id inner join countries on country=countries.id";
		List<User> users = new ArrayList<>();
		try (ResultSet rs = DBUtil.openConnection().prepareStatement(sql).executeQuery()) {
			while (rs.next()) {
				User user = new User(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						new Address(
								rs.getLong(11),
								rs.getString(12),
								rs.getString(13),
								rs.getString(14),
								new Country(rs.getLong(15), rs.getString(17))
								),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getDate(9),
						rs.getBoolean(10)
				);
				users.add(user);
			}
			return users;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<User> getListByField(String fieldName, String value) throws SQLException {
		String sql = "select * from users inner join addresses on address=addresses.id inner join countries on country=countries.id "
				+ "where " + fieldName + "=?";
		List<User> users = new ArrayList<>();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setString(1, value);
			
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					User user = new User(
							rs.getLong(1),
							rs.getString(2),
							rs.getString(3),
							new Address(
									rs.getLong(11),
									rs.getString(12),
									rs.getString(13),
									rs.getString(14),
									new Country(rs.getLong(15), rs.getString(17))
									),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getDate(9),
							rs.getBoolean(10)
					);
					users.add(user);
				}
				return users;
			}catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	

}
