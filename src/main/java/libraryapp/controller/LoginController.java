package libraryapp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import libraryapp.dao.IUserDAO;
import libraryapp.dao.UserDAOImpl;
import libraryapp.dto.UserDTO;
import libraryapp.model.User;
import libraryapp.service.IUserService;
import libraryapp.service.UserServiceImpl;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IUserDAO DAO = new UserDAOImpl();
    private final IUserService SERVICE = new UserServiceImpl(DAO);
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		// Get parameters for log in validation
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Create DTO
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		
		try {
			//  Search for user that matches username and pass
			User user = SERVICE.userLogInValidation(dto);
			
			// if  user is found 
			if (user != null) {
				
				// Get the old session and invalidate
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) oldSession.invalidate();
				
				//Create a new session
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("user", user);
				newSession.setAttribute("userFound", true);
				// Set session to expire in 10 minutes
				newSession.setMaxInactiveInterval(10*60);
				
				Cookie ck = new Cookie("cfuser", newSession.getId());
				
				response.addCookie(ck);
				
				//request.setAttribute("userFound", true);
				//request.getRequestDispatcher("index.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} else {
				String message = "Invalid username or password";
				request.setAttribute("errorMessage", message);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}catch (SQLException e){
			e.printStackTrace();
			String message = "Something went wrong";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
