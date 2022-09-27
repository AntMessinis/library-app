package libraryapp.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.BookDAOImpl;
import libraryapp.dao.IBookDAO;
import libraryapp.dto.BookDTO;
import libraryapp.model.Book;
import libraryapp.service.BookServiceImpl;
import libraryapp.service.IBookService;

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
