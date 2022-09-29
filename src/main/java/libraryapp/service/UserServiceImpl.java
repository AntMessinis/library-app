package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dao.IUserDAO;
import libraryapp.dto.AddressDTO;
import libraryapp.dto.UserDTO;
import libraryapp.model.Address;
import libraryapp.model.Country;
import libraryapp.model.User;

public class UserServiceImpl implements IUserService{
	private IUserDAO dao;
	
	public UserServiceImpl(IUserDAO dao) {
		this.dao = dao;
	}

	@Override
	public void add(UserDTO dto) throws SQLException {
		try {
			User user = extractFields(dto);
			dao.insert(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(UserDTO dto) throws SQLException {
		try {
			User user = extractFields(dto);
			dao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User get(UserDTO d) throws SQLException {
		try {
			User user = dao.getInstanceById(d.getId());
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public User getByEmail(UserDTO dto) throws SQLException {
		try {
			User user = dao.getInstanceByStrField("email", dto.getEmail());
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User getByUsername(UserDTO dto) throws SQLException {
		try {
			User user = dao.getInstanceByStrField("username", dto.getUsername());
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(UserDTO d) throws SQLException {
		try {
			dao.delete(extractFields(d));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<User> getList() throws SQLException {
		try {
			return dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public User userLogInValidation(UserDTO dto) throws SQLException {
		try {
			// Search in db for a user with this username
			User user = dao.getInstanceByStrField("username", dto.getUsername());
			
			// Return user if password matches or null if it doesn't
			return (user.getPassword().equals(dto.getPassword())) ? user : null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	// Helper Function
	private User extractFields(UserDTO dto) {
		User user = new User(
				dto.getId(),
				dto.getFirstname(),
				dto.getLastname(),
				new Address(
						dto.getAddress().getId(), 
						dto.getAddress().getAddressName(),
						dto.getAddress().getPostalCode(),
						dto.getAddress().getCity(),
						new Country(dto.getAddress().getCountry().getId(), dto.getAddress().getCountry().getCountryName())),
				dto.getPhoneNumber(),
				dto.getEmail(),
				dto.getUsername(),
				dto.getPassword(),
				dto.getBirthdate(),
				dto.isAdmin()
				);
		return user;
	}



	

}
