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
import libraryapp.dto.BorrowDetailsDTO;
import libraryapp.model.BorrowDetails;
import libraryapp.model.User;
import libraryapp.service.BorrowDetailsServiceImpl;
import libraryapp.service.IBorrowDetailsService;

/**
 * Servlet implementation class BorrowedListController
 */
@WebServlet("/books-borrowed")
public class BorrowedListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IBorrowDetailsDAO borrowDAO = new BorrowDetailsDAOImpl();
    private final IBorrowDetailsService borrowService = new BorrowDetailsServiceImpl(borrowDAO); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		BorrowDetailsDTO borrowDTO = new BorrowDetailsDTO();
        borrowDTO.setMemberId(user.getId());
        
        try {
            List<BorrowDetails> borrowList = borrowService.getListForMember(borrowDTO);
            if(borrowList.size() > 0) {
                request.setAttribute("listFound", true);
                request.setAttribute("borrowList", borrowList);
            } else {
                request.setAttribute("listFound", false);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        request.setAttribute("user", user);
        request.getRequestDispatcher("jsps/borrowedlist.jsp").forward(request, response);
	}

	
}
