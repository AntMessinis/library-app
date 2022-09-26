package library_app.service;

import java.sql.SQLException;

import library_app.dto.UserDTO;
import library_app.model.User;

public interface IUserService extends IGenericService<UserDTO,User>{
	User getByEmail(UserDTO dto) throws SQLException;
	User getByUsername(UserDTO dto) throws SQLException;
	

}
