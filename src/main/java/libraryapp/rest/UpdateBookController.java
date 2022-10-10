package libraryapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.BookDAOImpl;
import libraryapp.dao.IBookDAO;
import libraryapp.dto.BookDTO;
import libraryapp.service.BookServiceImpl;
import libraryapp.service.IBookService;

/**
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/update-book")
public class UpdateBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final IBookDAO bookDAO = new BookDAOImpl();
	    private final IBookService bookService = new BookServiceImpl(bookDAO);
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        StringBuffer requestJson = new StringBuffer();
	        String jsonLine;
	        
	        try(BufferedReader reader = request.getReader()){
	            while((jsonLine = reader.readLine()) != null) {
	                requestJson.append(jsonLine);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	        try {
	            BookDTO dto = mapper.readValue(requestJson.toString(), BookDTO.class);
	            bookService.update(dto);
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        
	    }

}
