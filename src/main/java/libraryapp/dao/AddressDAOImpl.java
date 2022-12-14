package libraryapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Address;
import libraryapp.model.Country;

public class AddressDAOImpl implements IAddressDAO{

	@Override
	public void insert(Address m) throws SQLException {
		String sql = "insert into addresses (address_name, postal_code, city, country) values (?,?,?, (select id from countries where country_name=?)) on duplicate key update id=id";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			
			ps.setString(1, m.getAddressName());
			ps.setString(2, m.getPostalCode());
			ps.setString(3, m.getCity());
			ps.setString(4, m.getCountry().getName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(Address m) throws SQLException {
		String sql = "update addresses set address_name=?, postal_code=?, city=?, country=? where address.id=?";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setString(1, m.getAddressName());
			ps.setString(2, m.getPostalCode());
			ps.setString(3, m.getCity());
			ps.setLong(4, m.getCountry().getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void delete(Address m) throws SQLException {
		String sql = "delete from addresses where address.id=?";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setLong(1, m.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Address getInstanceById(long id) throws SQLException {
		String sql = "select * from addresses inner join countries on country=countries.id where id=?";
		
		Address address = new Address();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					address.setId(rs.getLong(1));
					address.setAddressName(rs.getString(2));
					address.setPostalCode(rs.getString(3));
					address.setCity(rs.getString(4));

					address.setCountry(new Country(rs.getLong(6), rs.getString(7)));
				}
				return address;
				
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
	public Address getInstanceByStrField(String fieldName, String name) throws SQLException {
		String sql = "select * from addresses inner join countries on country=countries.id where " + fieldName + "=?";
		
		Address address = new Address();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setString(1, name);
			
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					address.setId(rs.getLong(1));
					address.setAddressName(rs.getString(2));
					address.setPostalCode(rs.getString(3));
					address.setCity(rs.getString(4));

					address.setCountry(new Country(rs.getLong(6), rs.getString(7)));
				}
				return address;
				
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
	public List<Address> getAll() throws SQLException {
		String sql = "select * from addresses inner join countries on country=countries.id";
		
		List<Address> addresses = new ArrayList<>();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					Address address = new Address(
							rs.getLong(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							new Country(rs.getLong(6), rs.getString(7))
					);

					addresses.add(address);
				}
				
				return addresses;
			}catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Address> getListByField(String fieldName, String value) throws SQLException {
		String sql = "select * from addresses inner join countries on " + fieldName +"=?";
		
		List<Address> addresses = new ArrayList<>();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)) {
			ps.setString(1, value);
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					Address address = new Address(
							rs.getLong(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							new Country(rs.getLong(6), rs.getString(7))
					);

					addresses.add(address);
				}
				
				return addresses;
			}catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	
}
