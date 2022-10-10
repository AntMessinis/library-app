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

import libraryapp.dao.AuthorDAOImpl;
import libraryapp.dao.IAuthorDAO;
import libraryapp.dto.AuthorDTO;
import libraryapp.service.AuthorServiceImpl;
import libraryapp.service.IAuthorService;

/**
 * Servlet implementation class AddAuthorController
 */
@WebServlet("/add-author")
public class AddAuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IAuthorDAO authorDAO = new AuthorDAOImpl();
    private final IAuthorService authorService = new AuthorServiceImpl(authorDAO);
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jsonRequest = new StringBuffer();
		String jsonLine;
		try (BufferedReader reader = request.getReader()){
		    while((jsonLine = reader.readLine()) != null) {
		        jsonRequest.append(jsonLine);
		    }
		}catch (Exception e){
		    e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
		    AuthorDTO dto = mapper.readValue(jsonRequest.toString(), AuthorDTO.class);
		    authorService.add(dto);
		    response.getWriter().write("{1: 1}");
		}catch (Exception e) {
		    e.printStackTrace();
		    response.getWriter().write("{}");
		}
	}
}
