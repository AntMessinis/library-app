package library_app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library_app.dao.CountryDAOImpl;
import library_app.dao.ICountryDAO;
import library_app.model.Country;
import library_app.service.CountryServiceImpl;
import library_app.service.ICountryService;



/**
 * Servlet implementation class ListCountryController
 */
@WebServlet("/list-countries")
public class ListCountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final ICountryDAO dao = new CountryDAOImpl();
	private final ICountryService service = new CountryServiceImpl(dao);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		List<Country> countries = new ArrayList<>();
		
		try {
			
			countries = service.getList();
			if (countries.size() > 0) {
				request.setAttribute("countryList", true);
				request.setAttribute("countries", countries);
				
				
			} else {
				
				String noListError = "There are no countries in the database.";
				
				request.setAttribute("noCountriesError", true);
				request.setAttribute("noListError", noListError);
				
			}
			
		} catch (SQLException e) {
			String generalError = "Something went wrong please try again.";
			request.setAttribute("listError", true);
			request.setAttribute("generalError", generalError);
			
		}finally {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
