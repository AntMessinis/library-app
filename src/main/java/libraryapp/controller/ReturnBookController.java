package libraryapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import libraryapp.dao.BorrowDetailsDAOImpl;
import libraryapp.dao.IBorrowDetailsDAO;
import libraryapp.dto.BookDTO;
import libraryapp.dto.BorrowDetailsDTO;
import libraryapp.model.BorrowDetails;
import libraryapp.model.User;
import libraryapp.service.BorrowDetailsServiceImpl;
import libraryapp.service.IBorrowDetailsService;

/**
 * Servlet implementation class ReturnBookController
 */
@WebServlet("/return")
public class ReturnBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IBorrowDetailsDAO borrowDAO = new BorrowDetailsDAOImpl();
    private final IBorrowDetailsService borrowService = new BorrowDetailsServiceImpl(borrowDAO);
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
		String borrowId = request.getParameter("borrowId");
		String bookId = request.getParameter("bookId");
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
		
		try {
		    long id = Long.parseLong(borrowId);
		    long returnedId = Long.parseLong(bookId);
		    
		    BorrowDetailsDTO dto = new BorrowDetailsDTO();
		    BookDTO book = new BookDTO();
		    
		    book.setId(returnedId);
		    
		    dto.setId(id);
		    dto.setBook(book);
		    dto.setMemberId(user.getId());
		    
		    borrowService.update(dto);
		    List<BorrowDetails> borrowed = borrowService.getListForMember(dto);
		    if(borrowed.size() > 0) {
		        request.setAttribute("listFound", true);
		        request.setAttribute("borrowList", borrowed);
		    }
		    
		    request.setAttribute("returnSuccess", true);
            request.getRequestDispatcher("/books-borrowed").forward(request, response);
		} catch(NumberFormatException | SQLException e) {
		    e.printStackTrace();
		    request.setAttribute("returnSuccess", false);
		    request.getRequestDispatcher("/books-borrowed").forward(request, response);
		}
	}
}
