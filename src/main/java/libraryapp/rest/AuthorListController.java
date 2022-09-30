package libraryapp.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.AuthorDAOImpl;
import libraryapp.dao.IAuthorDAO;
import libraryapp.model.Author;
import libraryapp.service.AuthorServiceImpl;
import libraryapp.service.IAuthorService;

/**
 * Servlet implementation class AuthorListController
 */
@WebServlet("/authors")
public class AuthorListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IAuthorDAO authorDAO = new AuthorDAOImpl();
    private final IAuthorService authorService = new AuthorServiceImpl(authorDAO);
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		String jsonString;
		try {
		    List<Author> authors = authorService.getList();
		    
		    if(authors.size() > 0) {
		        ObjectMapper mapper = new ObjectMapper();
		        jsonString = mapper.writeValueAsString(authors);
		        response.getWriter().write(jsonString);
		    } else {
		        response.getWriter().write("{}");
		    }
		}catch (Exception e){
		    e.printStackTrace();
		}
	}

}
