package libraryapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Country;

public class CountryDAOImpl implements ICountryDAO{

	@Override
	public void insert(Country m) throws SQLException {
		String sql = "insert into countries (country_name) value (?)";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
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
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
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
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setLong(1, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Override
	public Country getInstanceById(long id) throws SQLException {
		String sql = "select * from countries where id=?";
		Country country = new Country();
		try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					country.setId(rs.getLong(1));
					country.setName(rs.getString(2));
					return country;
				} else {
				    return null;
				}
				
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
	public Country getInstanceByStrField(String fieldName, String value) throws SQLException {
		String sql = "select * from countries where " + fieldName +"=?";
		Country country = new Country();
		try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, value);
			try (ResultSet rs = ps.executeQuery()) {
			    if (rs.next()) {
                    country.setId(rs.getLong(1));
                    country.setName(rs.getString(2));
                    return country;
                } else {
                    return null;
                }
				
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
		
		try (ResultSet rs = DBUtil.openConnection().prepareStatement(sql).executeQuery()){

			while (rs.next()) {
				countries.add(new Country(rs.getLong(1),	rs.getString(2)));
			}
			return countries;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Country> getListByField(String fieldName, String value) throws SQLException {
		String sql = "select * from countries where " + fieldName +"=?";
		List<Country> countries = new ArrayList<>();
		try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, value);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					countries.add(new Country(rs.getLong(1),	rs.getString(2)));
				}
				return countries;

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	
	
	

}
