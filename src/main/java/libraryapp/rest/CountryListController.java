package libraryapp.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.CountryDAOImpl;
import libraryapp.dao.ICountryDAO;
import libraryapp.model.Country;
import libraryapp.service.CountryServiceImpl;
import libraryapp.service.ICountryService;

/**
 * Servlet implementation class CountryListController
 */
@WebServlet("/countries")
public class CountryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private final ICountryDAO dao = new CountryDAOImpl();
	private final ICountryService countryService = new CountryServiceImpl(dao);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		String jsonString; 
		try {
			List<Country> countries = countryService.getList();
			if(countries.size() > 0) {
				
				ObjectMapper mapper = new ObjectMapper();
				jsonString = mapper.writeValueAsString(countries);
				response.getWriter().write(jsonString);
				
			} else {
				response.getWriter().write("{}");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
