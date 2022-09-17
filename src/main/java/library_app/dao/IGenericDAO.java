package library_app.dao;

import java.sql.SQLException;
import java.util.List;

import library_app.model.AbstractEntity;

public interface IGenericDAO<M extends AbstractEntity> {
	void insert(M m) throws SQLException;
	void update(M m) throws SQLException;
	void delete(M m) throws SQLException;
	M getInstanceByName(String name) throws SQLException;
	List<M> getAll() throws SQLException;

}
