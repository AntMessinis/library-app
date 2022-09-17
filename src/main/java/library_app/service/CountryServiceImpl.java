package library_app.service;

import java.sql.SQLException;
import java.util.List;

import library_app.dao.ICountryDAO;
import library_app.dto.CountryDTO;
import library_app.model.Country;

public class CountryServiceImpl implements ICountryService{
	
	private final ICountryDAO dao;
	
	public CountryServiceImpl(ICountryDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(CountryDTO d) throws SQLException {
		Country newCountry = extractFields(d);
		try {
			dao.insert(newCountry);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(CountryDTO d) throws SQLException {
		Country updated = extractFields(d);
		try {
			dao.update(updated);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Country get(CountryDTO d) throws SQLException {
		try {
			Country getCountry = dao.getInstanceByName(d.getCountryName());
			return getCountry;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	
	}

	@Override
	public void delete(CountryDTO d) throws SQLException {
		Country toDelete = extractFields(d);
		try {
			dao.delete(toDelete);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public List<Country> getList() throws SQLException {
		List<Country> countries;
		try {
			countries = dao.getAll();
			return countries;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	private Country extractFields(CountryDTO dto) {
		Country newCountry = new Country();
		newCountry.setId(dto.getId());
		newCountry.setName(dto.getCountryName());
		return newCountry;
	}
	
}
