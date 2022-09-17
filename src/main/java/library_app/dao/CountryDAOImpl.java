package library_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_app.dao.dbutil.DBUtil;
import library_app.model.Country;

public class CountryDAOImpl implements ICountryDAO{

	@Override
	public void insert(Country m) throws SQLException {
		String sql = "insert into countries (country_name) value (?)";
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, m.getName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(Country m) throws SQLException {
		String sql = "update countries set country_name=? where countries.id=?";
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, m.getName());
			ps.setLong(2, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}		
	}

	@Override
	public void delete(Country m) throws SQLException {
		String sql = "delete from countries where countries.id=?";
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setLong(1, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Country getInstanceByName(String name) throws SQLException {
		String sql = "select * from countries where country_name=?";
		Country country = new Country();
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					country.setId(rs.getLong(1));
					country.setName(rs.getString(2));
				}
				
				return country;
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Country> getAll() throws SQLException {
		String sql = "select * from countries";
		List<Country> countries = new ArrayList<>();
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			try (ResultSet rs = ps.executeQuery()){
				
				while (rs.next()) {
					countries.add(new Country(rs.getLong(1),	rs.getString(2)));
				}
				
				return countries;
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	

}
