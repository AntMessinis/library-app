package libraryapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Subcategory;

public class SubcategoryDAOImpl implements ISubcategoryDAO{

	@Override
	public void insert(Subcategory m) throws SQLException {
		String sql = "insert into Subcategories (subcategory_name, category) values (?, (select id from categories where category_name=?)";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, m.getSubcategoryName());
			ps.setString(2, m.getCategoryName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Subcategory m) throws SQLException {
		String sql = "update subcategories set subcategory_name where id=?";
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setLong(1, m.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void delete(Subcategory m) throws SQLException {
		String sql = "delete from subcategories where id=?";
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			
			ps.setLong(1, m.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Subcategory getInstanceByStrField(String fieldName, String value) throws SQLException {
		String sql = "select * from subcategories inner join categories where category=categories.id where ?=?";
		Subcategory subCat = new Subcategory();
		
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, fieldName);
			ps.setString(2, value);
			try(ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					subCat.setId(rs.getLong(1));
					subCat.setSubcategoryName(rs.getString(2));
					subCat.setCategoryName(rs.getString(5));
				}
				
				return subCat;
				
			}catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Subcategory> getAll() throws SQLException {
		String sql = "select * from subcategories inner join categories on category=categories.id";
		List<Subcategory> subcategories = new ArrayList<>();
		
		try (ResultSet rs = DBUtil.openConnection().prepareStatement(sql).executeQuery()){
		
			while (rs.next()) {
				Subcategory subCat = new Subcategory();
				subCat.setId(rs.getLong(1));
				subCat.setSubcategoryName(rs.getString(2));
				subCat.setCategoryName(rs.getString(5));
					
				subcategories.add(subCat);
			}
				
			return subcategories;
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Subcategory> getListByField(String fieldName, String value) throws SQLException {
		String sql = "select * from subcategories inner join categories on category=categories.id where ?=?";
		List<Subcategory> subcategories = new ArrayList<>();
		try (PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
			ps.setString(1, fieldName);
			ps.setString(2, value);
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					Subcategory subCat = new Subcategory();
					subCat.setId(rs.getLong(1));
					subCat.setSubcategoryName(rs.getString(2));
					subCat.setCategoryName(rs.getString(5));
						
					subcategories.add(subCat);
				}
					
				return subcategories;
					
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	@Override
	public Subcategory getInstanceById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
