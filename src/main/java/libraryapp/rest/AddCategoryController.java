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

import libraryapp.dao.ISubcategoryDAO;
import libraryapp.dao.SubcategoryDAOImpl;
import libraryapp.dto.SubcategoryDTO;
import libraryapp.service.ISubcategoryService;
import libraryapp.service.SubcategoryServiceImpl;

/**
 * Servlet implementation class AddCategoryController
 */
@WebServlet("/add-category")
public class AddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ISubcategoryDAO categoryDao = new SubcategoryDAOImpl();
    private final ISubcategoryService categoryService = new SubcategoryServiceImpl(categoryDao);
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jsonRequest = new StringBuffer();
		String jsonLine;
		
		try(BufferedReader reader = request.getReader()){
		    while((jsonLine = reader.readLine())  != null) {
		        jsonRequest.append(jsonLine);
		    }
		}catch(Exception e) {
		    e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		
		try {
		    SubcategoryDTO dto = mapper.readValue(jsonRequest.toString(), SubcategoryDTO.class);
		    categoryService.add(dto);
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
