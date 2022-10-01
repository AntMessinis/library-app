package libraryapp.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.ISubcategoryDAO;
import libraryapp.dao.SubcategoryDAOImpl;
import libraryapp.model.Subcategory;
import libraryapp.service.ISubcategoryService;
import libraryapp.service.SubcategoryServiceImpl;

/**
 * Servlet implementation class CategoryListController
 */
@WebServlet("/categories")
public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ISubcategoryDAO categoryDAO = new SubcategoryDAOImpl();
	private final ISubcategoryService categoryService = new SubcategoryServiceImpl(categoryDAO);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		String jsonString;
		try {
		    List<Subcategory> bookCategories = categoryService.getList();
		    if (bookCategories.size() > 0) {
		        ObjectMapper mapper = new ObjectMapper();
		        jsonString = mapper.writeValueAsString(bookCategories);
		        response.getWriter().write(jsonString);
		    } else {
		        response.getWriter().write("{}");
		    }
		}catch (SQLException e) {
		    e.printStackTrace();
		}
	}

}
