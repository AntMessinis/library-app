package libraryapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libraryapp.dao.IUserDAO;
import libraryapp.dao.UserDAOImpl;
import libraryapp.service.IUserService;
import libraryapp.service.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final IUserDAO DAO  = new UserDAOImpl();
    private static final IUserService SERVICE = new UserServiceImpl(DAO);
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jsonString = new StringBuffer();
		String jsonLine = null;
		
		try (BufferedReader br = request.getReader()){
			while((jsonLine = br.readLine()) != null) {
				jsonString.append(jsonLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
