package library_app.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import library_app.dao.BookDAOImpl;
import library_app.dao.IBookDAO;
import library_app.dto.BookDTO;
import library_app.model.Book;
import library_app.service.BookServiceImpl;
import library_app.service.IBookService;

/**
 * Servlet implementation class SearchByTitleController
 */
@WebServlet("/searchByTitle")
public class SearchByTitleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final IBookDAO bookDao = new BookDAOImpl();
	private final IBookService bookService = new BookServiceImpl(bookDao);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		String title = request.getParameter("title").trim();
		
		BookDTO dto = new BookDTO();
		dto.setTitle(title);
		
		String jsonString;
		try {
			Book book = bookService.get(dto);
			if (book != null) {
				ObjectMapper mapper = new ObjectMapper();
				jsonString = mapper.writeValueAsString(book);
				response.getWriter().write(jsonString);
			}else {
				response.getWriter().write("{}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

}
