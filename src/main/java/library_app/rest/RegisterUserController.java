package library_app.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import library_app.dao.IUserDAO;
import library_app.dao.UserDAOImpl;
import library_app.dto.UserDTO;
import library_app.model.User;
import library_app.service.IUserService;
import library_app.service.UserServiceImpl;


@WebServlet("/register")
public class RegisterUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IUserDAO dao = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(dao);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.getRequestDispatcher("jsps/registration.jsp").forward(request, response);
	}

	
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
		try {
			UserDTO user = mapper.readValue(jsonString.toString(), UserDTO.class);
			userService.add(user);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
