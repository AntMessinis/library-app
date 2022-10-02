package libraryapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

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
 * Servlet implementation class GetBookController
 */
@WebServlet("/getbook")
public class GetBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IBookDAO bookDAO = new BookDAOImpl();
    private final IBookService bookService = new BookServiceImpl(bookDAO);
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		StringBuffer jsonRequest = new StringBuffer();
		String jsonLine = "";
		
		try(BufferedReader reader = request.getReader()){
		    while((jsonLine = reader.readLine()) != null) {
		        jsonRequest.append(jsonLine);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    Map map = mapper.readValue(jsonRequest.toString(), Map.class);
		    BookDTO dto = new BookDTO();
		    Book book;
		    
		    if (map.containsKey("title")) {
		        dto.setTitle((String)map.get("title"));
		        book = bookService.get(dto);
		    } else {
		        dto.setIsbn((String)map.get("isbn"));
		        book = bookService.getBookByISBN(dto);
		    }
		    if (book != null) {
		        response.getWriter().write(mapper.writeValueAsString(book));
		    }else {
		        response.getWriter().write("{}");
		    }
		    
		    
		}catch (Exception e){
		    e.printStackTrace();
		}
		
	}
}
