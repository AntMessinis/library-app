package libraryapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;


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
	
    private  final IUserDAO dao  = new UserDAOImpl();
    private  final IUserService countryService = new UserServiceImpl(dao);
   
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
		//DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		//mapper.setDateFormat(df);
		try {
			UserDTO user = mapper.readValue(jsonString.toString(), UserDTO.class);
			countryService.add(user);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
