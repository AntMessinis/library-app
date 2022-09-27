package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dto.BookDTO;
import libraryapp.model.Book;

public interface IBookService extends IGenericService<BookDTO, Book>{
	public Book getBookByISBN(BookDTO dto) throws SQLException;
	public List<Book> getBooksByAuthor(BookDTO dto) throws SQLException;
	public List<Book> getBooksByKeyword(String keyword) throws SQLException;
	public List<Book> getBooksByCategory(BookDTO dto) throws SQLException;
}
