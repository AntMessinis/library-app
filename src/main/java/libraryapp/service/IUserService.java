package libraryapp.service;

import java.sql.SQLException;
import libraryapp.dto.UserDTO;
import libraryapp.model.User;

public interface IUserService extends IGenericService<UserDTO,User>{
	User getByEmail(UserDTO dto) throws SQLException;
	User getByUsername(UserDTO dto) throws SQLException;
	User userLogInValidation(UserDTO dto) throws SQLException;

}
