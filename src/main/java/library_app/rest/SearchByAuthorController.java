package library_app.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import library_app.dao.BookDAOImpl;
import library_app.dao.IBookDAO;
import library_app.dto.AuthorDTO;
import library_app.dto.BookDTO;
import library_app.model.Book;
import library_app.service.BookServiceImpl;
import library_app.service.IBookService;

/**
 * Servlet implementation class SearchByAuthorController
 */
@WebServlet("/searchByAuthor")
public class SearchByAuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final IBookDAO dao = new BookDAOImpl();
	private final IBookService bookService = new BookServiceImpl(dao);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; text=UTF-8");
		
		String author = request.getParameter("author");
		AuthorDTO authDTO= new AuthorDTO();
		authDTO.setLastname(author);
		BookDTO dto = new BookDTO();
		dto.setAuthor(authDTO);
		
		String JSONstring;
		
		try {
			List<Book> booksAuthored = bookService.getBooksByAuthor(dto);
			if	(booksAuthored.size() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				JSONstring = mapper.writeValueAsString(booksAuthored);
				response.getWriter().write(JSONstring);
			} else {
				response.getWriter().write("{}");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
