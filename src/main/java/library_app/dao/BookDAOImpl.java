package library_app.dao;

import java.sql.SQLException;
import java.util.List;

import library_app.model.Book;

public class BookDAOImpl implements IGenericDAO<Book>{

	@Override
	public void insert(Book m) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Book m) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book m) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getInstanceByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() throws SQLException {
		String sql = "select * from books inner join";
		return null;
	}
	
}
