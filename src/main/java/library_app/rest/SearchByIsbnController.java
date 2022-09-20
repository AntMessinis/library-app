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


@WebServlet("/searchByIsbn")
public class SearchByIsbnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IBookDAO dao = new BookDAOImpl();
    private final IBookService bookService = new BookServiceImpl(dao);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; text=UTF-8");
		
		String JSONstring;
		String isbn = request.getParameter("isbn");
		BookDTO dto = new BookDTO();
		dto.setIsbn(isbn);
		
		try {
			Book book = bookService.getBookByISBN(dto);
			if(book != null) {
				ObjectMapper mapper = new ObjectMapper();
				JSONstring = mapper.writeValueAsString(book);
				response.getWriter().write(JSONstring);
			}else {
				response.getWriter().write("{}");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

}
