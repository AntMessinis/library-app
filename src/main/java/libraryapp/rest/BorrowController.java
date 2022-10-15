package libraryapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import libraryapp.dao.BorrowDetailsDAOImpl;
import libraryapp.dao.IBorrowDetailsDAO;
import libraryapp.dto.BookDTO;
import libraryapp.dto.BorrowDetailsDTO;
import libraryapp.model.User;
import libraryapp.service.BorrowDetailsServiceImpl;
import libraryapp.service.IBorrowDetailsService;


@WebServlet("/borrow")
public class BorrowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IBorrowDetailsDAO dao = new BorrowDetailsDAOImpl();
    private final IBorrowDetailsService service = new BorrowDetailsServiceImpl(dao);
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    StringBuffer jsonRequest = new StringBuffer();
	    String jsonLine;
	    
	    try(BufferedReader reader = request.getReader()){
	        while((jsonLine = reader.readLine()) != null) {
	            jsonRequest.append(jsonLine);
	        }
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    HttpSession session = request.getSession();
	    
	    try {
	        User user = (User) session.getAttribute("user");
	        BookDTO book = mapper.readValue(jsonRequest.toString(), BookDTO.class);
	        
	        if(book.getCopiesInLibrary() > 0) {
	            BorrowDetailsDTO borrow = new BorrowDetailsDTO();
	            
	            borrow.setMemberId(user.getId());
	            borrow.setBook(book);
	            borrow.setBorrowDate(new Date());
	            
	            Calendar calendar = Calendar.getInstance();
	            calendar.add(Calendar.DAY_OF_MONTH, 15);
	            
	            borrow.setReturnDate(calendar.getTime());
	            
	            service.add(borrow);
	        }
	        
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
