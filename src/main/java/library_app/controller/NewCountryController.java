package library_app.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library_app.dao.CountryDAOImpl;
import library_app.dao.ICountryDAO;
import library_app.dto.CountryDTO;
import library_app.service.CountryServiceImpl;
import library_app.service.ICountryService;

/**
 * Servlet implementation class NewCountryController
 */
@WebServlet("/new-country")
public class NewCountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final ICountryDAO dao = new CountryDAOImpl();
    private final ICountryService service = new CountryServiceImpl(dao);
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		// get the form's input
		String countryName = request.getParameter("countryName");
		
		if (countryName.equals("")) request.getRequestDispatcher("/jsps/country.jsp").forward(request, response);
		
		// Create DTO
		CountryDTO dto = new CountryDTO();
		dto.setCountryName(countryName);
		
		// call service layer
		try {
			service.add(dto);
			
			// Construct success message
			String infoSuccess = "New country " + countryName + " added to the database successfully!";
			request.setAttribute("insertSuccess", true);
			request.setAttribute("infoSuccess", infoSuccess);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			String infoError = "Something went wrong.";
			request.setAttribute("insertError", true);
			request.setAttribute("infoError", infoError);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
    }

}
