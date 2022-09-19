package library_app.service;

import java.sql.SQLException;
import java.util.List;

import library_app.dao.IBookDAO;
import library_app.dto.BookDTO;
import library_app.model.Author;
import library_app.model.Book;
import library_app.model.Country;
import library_app.model.Language;
import library_app.model.Subcategory;

public class BookServiceImpl implements IBookService{
	private final IBookDAO dao;
	
	
	
	public BookServiceImpl(IBookDAO dao) {
		this.dao = dao;
	}

	@Override
	public void add(BookDTO d) throws SQLException {
		try{
			dao.insert(extractFields(d));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(BookDTO d) throws SQLException {
		try{
			dao.update(extractFields(d));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Book get(BookDTO d) throws SQLException {
		try{
			return dao.getInstanceByName(d.getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(BookDTO d) throws SQLException {
		try{
			dao.delete(extractFields(d));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public List<Book> getList() throws SQLException {
		try{
			return dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private Book extractFields(BookDTO dto) {
		Language language = new Language(
				//dto.getLanguage().getId(),
				dto.getLanguage().getLanguageName());
		
		Author author = new Author(
				//dto.getAuthor().getId(),
				dto.getAuthor().getFirstname(), 
				dto.getAuthor().getLastname(), 
				new Country(dto.getAuthor().getCountryOfOrigin().getCountryName()));
		
		Subcategory category = new Subcategory(
				//dto.getCategory().getId(),
				dto.getCategory().getSubcategoryName(), 
				dto.getCategory().getCategoryName());
		
		Book book = new Book(
				//dto.getId(),
				dto.getTitle(), 
				dto.getIsbn(), 
				author, 
				language, 
				category, 
				dto.getDescription(),
				dto.getAmountInLibrary(), 
				dto.getCurrentlyBorrowed());
		
		return book;
	}
}
