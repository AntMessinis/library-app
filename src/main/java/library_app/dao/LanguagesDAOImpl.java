package library_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_app.dao.dbutil.DBUtil;
import library_app.model.Language;

public class LanguagesDAOImpl implements ILanguagesDAO{

	@Override
	public void insert(Language m) throws SQLException {
		String sql = "insert into languages (language_name) value (?) on duplicate key update id=id";
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, m.getLanguageName());
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void update(Language m) throws SQLException {
		String sql = "update languages set language_name=? where languages.id=?";
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, m.getLanguageName());
			ps.setLong(2, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void delete(Language m) throws SQLException {
		String sql = "delete * from languages where languages.id=?";
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setLong(1, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Language getInstanceById(long id) throws SQLException {
		String sql = "select * from languages where id=?";
		Language lang = new Language();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery()){
				
				if (rs.next()) {
					lang.setId(rs.getLong(1));
					lang.setLanguageName(rs.getString(2));
				}
				return lang;
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
	public Language getInstanceByStrField(String fieldName, String value) throws SQLException {
		String sql = "select * from languages where " + fieldName +"=?";
		Language lang = new Language();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setString(1, value);
			
			try (ResultSet rs = ps.executeQuery()){
				
				if (rs.next()) {
					lang.setId(rs.getLong(1));
					lang.setLanguageName(rs.getString(2));
				}
				return lang;
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
	public List<Language> getAll() throws SQLException {
		String sql = "select * from languages";
		List<Language> langs = new ArrayList<>();
		
		try (ResultSet rs = DBUtil.openConnection().prepareStatement(sql).executeQuery()){
			while(rs.next()) {
				Language lang = new Language();
				lang.setId(rs.getLong(1));
				lang.setLanguageName(rs.getString(2));
					
				langs.add(lang);
			}
				
			return langs;
	
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public List<Language> getListByField(String fieldName, String value) throws SQLException {
		String sql = "select * from languages where " + fieldName +"=?";
		List<Language> langs = new ArrayList<>();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, value);
			
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
				Language lang = new Language();
					lang.setId(rs.getLong(1));
					lang.setLanguageName(rs.getString(2));
					
					langs.add(lang);
				}
				
				return langs;
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
