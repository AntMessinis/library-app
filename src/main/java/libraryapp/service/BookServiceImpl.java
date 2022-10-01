package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dao.IBookDAO;
import libraryapp.dto.BookDTO;
import libraryapp.model.Author;
import libraryapp.model.Book;
import libraryapp.model.Country;
import libraryapp.model.Language;
import libraryapp.model.Subcategory;

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
			return dao.getInstanceByStrField("title",d.getTitle());
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
	
	@Override
	public Book getBookByISBN(BookDTO dto) throws SQLException {
		try{
			return dao.getInstanceByStrField("books.isbn", dto.getIsbn());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Book> getBooksByAuthor(BookDTO dto) throws SQLException {
		try{
			return dao.getListByField("authors.lastname", dto.getAuthor().getLastname());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Book> getBooksByKeyword(String keyword) throws SQLException {
		try{
			return dao.getListByField("books.title", keyword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Book> getBooksByCategory(BookDTO dto) throws SQLException {
		try{
			return dao.getListByField("subcategories.subcategory_name", dto.getCategory().getSubcategoryName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private Book extractFields(BookDTO dto) {
		Language language = new Language(
				dto.getLanguage().getId(),
				dto.getLanguage().getLanguageName());
		
		Author author = new Author(
				dto.getAuthor().getId(),
				dto.getAuthor().getFirstname(), 
				dto.getAuthor().getLastname(), 
				new Country(dto.getAuthor().getCountryOfOrigin().getCountryName()));
		
		Subcategory category = new Subcategory(
				dto.getCategory().getId(),
				dto.getCategory().getSubcategoryName(), 
				dto.getCategory().getCategoryName());
		
		Book book = new Book(
				dto.getId(),
				dto.getTitle(), 
				dto.getIsbn(), 
				author, 
				language, 
				category, 
				dto.getDescription(),
				dto.getCopiesInLibrary(), 
				dto.getCurrentlyBorrowed());
		
		return book;
	}



}
