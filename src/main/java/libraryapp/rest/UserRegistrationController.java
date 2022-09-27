package libraryapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.IUserDAO;
import libraryapp.dao.UserDAOImpl;
import libraryapp.dto.UserDTO;
import libraryapp.service.IUserService;
import libraryapp.service.UserServiceImpl;

/**
 * Servlet implementation class UserRegistrationController
 */
@WebServlet("/registration")
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final IUserDAO DAO  = new UserDAOImpl();
    private static final IUserService SERVICE = new UserServiceImpl(DAO);
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer jsonString = new StringBuffer();
		
		String jsonLine = null;
		
		try (BufferedReader reader = request.getReader()){
			while((jsonLine = reader.readLine()) != null) {
				jsonString.append(jsonLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		mapper.setDateFormat(df);
		try {
			UserDTO user = mapper.readValue(jsonString.toString(), UserDTO.class);
			SERVICE.add(user);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
