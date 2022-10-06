package libraryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Author;
import libraryapp.model.Book;
import libraryapp.model.Country;
import libraryapp.model.Language;
import libraryapp.model.Subcategory;

public class BookDAOImpl implements IBookDAO{
	private static final String RETRIEVE_BOOK_SQL = "select * from books  inner join authors on author=authors.id"
			+ "	inner join countries on authors.country=countries.id"
			+ "	inner join languages on books.language=languages.id"
			+ "   inner join subcategories on books.category=subcategories.id"
			+ "   inner join categories on subcategories.category=categories.id";

	@Override
	public void insert(Book m) throws SQLException {
		String sql = "insert into books (title, isbn, author, language, category, description, in_library,currently_borrowed) values "
				+ "(?, ?, (select id from authors where firstname=? and lastname=?), "
				+ "(select id from languages where language_name=?),"
				+ "(select id from subcategories where subcategory_name=?), ? ,?, ?) on duplicate key update id=id";
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, m.getTitle());
			ps.setString(2, m.getIsbn());
			ps.setString(3, m.getAuthor().getFirstname());
			ps.setString(4, m.getAuthor().getLastname());
			ps.setString(5, m.getLanguage().getLanguageName());
			ps.setString(6, m.getCategory().getSubcategoryName());
			ps.setString(7, m.getDescription());
			ps.setInt(8, m.getCopiesInLibrary());
			ps.setInt(9, m.getCopiesCurrentlyBorrowed());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Book m) throws SQLException {
		String sql = "update books set title=?, isbn=?, "
				+ "author=(select id from authors where firstname=? and lastname=?),"
				+ "language=(select id from languages where language_name=?),"
				+ "category=(select id from subcategories where subcategory_name=?),"
				+ "description=?,"
				+ "in_library=?, currently_borrowed=? where id=?";
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, m.getTitle());
			ps.setString(2, m.getIsbn());
			ps.setString(3, m.getAuthor().getFirstname());
			ps.setString(4, m.getAuthor().getLastname());
			ps.setString(5, m.getLanguage().getLanguageName());
			ps.setString(6, m.getCategory().getSubcategoryName());
			ps.setString(7, m.getDescription());
			ps.setInt(8, m.getCopiesInLibrary());
			ps.setInt(9, m.getCopiesCurrentlyBorrowed());
			
			ps.setLong(10, m.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void delete(Book m) throws SQLException {
		String sql = "delete from books where id=?";
		
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
	public Book getInstanceById(long id) throws SQLException {
		String sql = RETRIEVE_BOOK_SQL 	+ "    where id=?";
		Book book = new Book();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					book.setId(rs.getLong(1));
					book.setTitle(rs.getString(2));
					book.setIsbn(rs.getString(3));
					
					book.setCopiesInLibrary(rs.getInt(8));
					book.setCurrentlyBorrowed(9);
					
					book.setAuthor(new Author(
							rs.getLong(10),
							rs.getString(11),
							rs.getString(12),
							new Country(rs.getLong(13), rs.getString(15))	
					));
					
					book.setLanguage(new Language(
							rs.getLong(16),
							rs.getString(17)
					));
					
					book.setCategory(new Subcategory(
							rs.getLong(18),
							rs.getString(19),
							rs.getString(22))
					);
					book.setDescription(rs.getString(7));
				}
				
				return book;
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
	public Book getInstanceByStrField(String fieldName, String value) throws SQLException {
		String sql = RETRIEVE_BOOK_SQL 	+ " where " + fieldName +" like ?";
		Book book = new Book();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setString(1, "%"+value+"%");
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					book.setId(rs.getLong(1));
					book.setTitle(rs.getString(2));
					book.setIsbn(rs.getString(3));
					
					book.setCopiesInLibrary(rs.getInt(8));
					book.setCurrentlyBorrowed(9);
					
					book.setAuthor(new Author(
							rs.getLong(10),
							rs.getString(11),
							rs.getString(12),
							new Country(rs.getLong(13), rs.getString(15))	
					));
					
					book.setLanguage(new Language(
							rs.getLong(16),
							rs.getString(17)
					));
					
					book.setCategory(new Subcategory(
							rs.getLong(18),
							rs.getString(19),
							rs.getString(22))
					);
					book.setDescription(rs.getString(7));
				}
				
				return book;
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
	public List<Book> getAll() throws SQLException {
		String sql = RETRIEVE_BOOK_SQL ;
		List<Book> books = new ArrayList<>();
		try (ResultSet rs = DBUtil.openConnection().prepareStatement(sql).executeQuery()){
			while (rs.next()) {
				Book book = new Book(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						new Author(rs.getLong(10), rs.getString(11), rs.getString(12), new Country(rs.getLong(13), rs.getString(15))),
						new Language(rs.getLong(16), rs.getString(17)),
						new Subcategory(	rs.getLong(18), 	rs.getString(19), rs.getString(22)),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9)
				);
				
				books.add(book);
			}
			return books;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Book> getListByField(String fieldName, String value) throws SQLException {
		String sql = RETRIEVE_BOOK_SQL 	+ " where " + fieldName +"=?";
		List<Book> books = new ArrayList<>();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setString(1, value);
			
			try(ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Book bookToList = new Book(
							rs.getLong(1),
							rs.getString(2),
							rs.getString(3),
							new Author(rs.getLong(10), rs.getString(11), rs.getString(12), new Country(rs.getLong(13), rs.getString(15))),
							new Language(rs.getLong(16), rs.getString(17)),
							new Subcategory(	rs.getLong(18), 	rs.getString(19), rs.getString(22)),
							rs.getString(7),
							rs.getInt(8),
							rs.getInt(9)
					);
					
					books.add(bookToList);
				}
				return books;
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
