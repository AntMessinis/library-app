package libraryapp.dao;

import java.sql.SQLException;
import java.util.List;

import libraryapp.model.AbstractEntity;

public interface IGenericDAO<M extends AbstractEntity> {
	void insert(M m) throws SQLException;
	void update(M m) throws SQLException;
	void delete(M m) throws SQLException;
	M getInstanceById(long id) throws SQLException;
	M getInstanceByStrField(String fieldName,String value) throws SQLException;
	List<M> getAll() throws SQLException;
	List<M> getListByField(String fieldName, String value) throws SQLException;
	
}
