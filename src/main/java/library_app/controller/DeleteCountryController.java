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
 * Servlet implementation class DeleteCountryController
 */
@WebServlet("/DeleteCountryController")
public class DeleteCountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ICountryDAO dao = new CountryDAOImpl();
    private final ICountryService service = new CountryServiceImpl(dao);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCountryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		long id = Long.parseLong(request.getParameter("id"));
		CountryDTO dto = new CountryDTO();
		
		dto.setId(id);
		
		try {
			service.delete(dto);
			request.setAttribute(getServletName(), dto);
			
		} catch (SQLException e) {
			request.setAttribute("deleteError", true);
			e.printStackTrace();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
