package library_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_app.dao.dbutil.DBUtil;
import library_app.model.Author;
import library_app.model.Country;

public class AuthorDAOImpl implements IAuthorDAO{

	@Override
	public void insert(Author m) throws SQLException {
		String sql = "insert into authors (author_firstname, author_lastname, origin_country) values (?, ?, (select id from countries where country_name=?)) on duplicate key update id=id";
		try	(Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, m.getFirstname());
			ps.setString(2, m.getLastname());
			ps.setString(3, m.getCountryOfOrigin().getName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(Author m) throws SQLException {
		String sql = "update authors set author_firstname=?, author_lastname=?, country=? where id=?";
		try	(Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, m.getFirstname());
			ps.setString(2, m.getLastname());
			ps.setLong(3,m.getCountryOfOrigin().getId());
			ps.setLong(4, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void delete(Author m) throws SQLException {
		String sql = "delete from authors where id=?";
		try	(Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setLong(1, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Author getInstanceByName(String lastname) throws SQLException {
		String sql = "select * from authors inner join countries on country=countries.id where author_lastname=?";
		Author author = new Author();
		try	(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, lastname);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					
					author.setId(rs.getLong(1));
					author.setFirstname(rs.getString(2));
					author.setLastname(rs.getString(3));
					
					author.setCountryOfOrigin(new Country(rs.getLong(4),rs.getString(5)));
				}
				return author;
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
	public List<Author> getAll() throws SQLException {
		
		String sql = "select * from authors inner join countries on coyntry=countries.id";
		List<Author> authors = new ArrayList<>();
		
		try	(Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			try (ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					authors.add(new Author(
							rs.getLong(1),
							rs.getString(2),
							rs.getString(3),
							new Country(rs.getLong(5), rs.getString(6))
					));
				}
				return authors;
				
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
