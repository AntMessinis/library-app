package libraryapp.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.ILanguageDAO;
import libraryapp.dao.LanguageDAOImpl;
import libraryapp.model.Language;
import libraryapp.service.ILanguageService;
import libraryapp.service.LanguageServiceImpl;

/**
 * Servlet implementation class LanguageListController
 */
@WebServlet("/languages")
public class LanguageListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ILanguageDAO languageDAO = new LanguageDAOImpl();
    private final ILanguageService languageService = new LanguageServiceImpl(languageDAO);
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setHeader("Content-Type", "application/json; charset=UTF-8");
	    
	    try {
	        List<Language> languages = languageService.getList();
	        if(languages.size() > 0) {
	            ObjectMapper mapper = new ObjectMapper();
	            String jsonString = mapper.writeValueAsString(languages);
	            response.getWriter().write(jsonString);
	        }else {
	            response.getWriter().write("{}");
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }
	}

}
