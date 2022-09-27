package libraryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Author;
import libraryapp.model.Country;

public class AuthorDAOImpl implements IAuthorDAO{

	@Override
	public void insert(Author m) throws SQLException {
		String sql = "insert into authors (author_firstname, author_lastname, origin_country) "
				+ "values (?, ?, (select id from countries where country_name=?)) on duplicate key update id=id";
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
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
		try	(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
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
		try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setLong(1, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Author getInstanceById(long id) throws SQLException {
		String sql = "select * from authors inner join countries on country=countries.id where id=?";
		Author author = new Author();
		try	(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setLong(1, id);
			
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
	public Author getInstanceByStrField(String fieldName, String value) throws SQLException {
		String sql = "select * from authors inner join countries on country=countries.id where "+ fieldName +"=?";
		Author author = new Author();
		try	(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, value);
			
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
		
		String sql = "select * from authors inner join countries on country=countries.id";
		List<Author> authors = new ArrayList<>();
		
		try(ResultSet rs = DBUtil.openConnection().prepareStatement(sql).executeQuery()){
			
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
	}

	@Override
	public List<Author> getListByField(String fieldName, String value) throws SQLException {
		String sql = "select * from authors inner join countries on " + fieldName + "=?";
		List<Author> authors = new ArrayList<>();
		
		try	(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, value);
			
			try(ResultSet rs = ps.executeQuery()){
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
			}
		}
	}

	
