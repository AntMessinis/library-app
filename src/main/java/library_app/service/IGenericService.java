package library_app.service;

import java.sql.SQLException;
import java.util.List;

import library_app.dto.AbstractDTO;
import library_app.model.AbstractEntity;

public interface IGenericService<D extends AbstractDTO ,M extends AbstractEntity> {
	public void add(D d) throws SQLException;
	public void update(D d) throws SQLException;
	public M get(D d) throws SQLException;
	public void delete(D d) throws SQLException;
	public List<M> getList() throws SQLException;
}
