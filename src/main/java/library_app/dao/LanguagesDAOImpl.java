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
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
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
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
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
	public Language getInstanceByName(String name) throws SQLException {
		String sql = "select * from languages where language_name=?";
		Language lang = new Language();
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, name);
			
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
		
		try (Connection conn = DBUtil.openConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
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
