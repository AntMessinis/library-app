package libraryapp.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import libraryapp.dao.IBorrowDetailsDAO;
import libraryapp.dto.BorrowDetailsDTO;
import libraryapp.model.Book;
import libraryapp.model.BorrowDetails;

public class BorrowDetailsServiceImpl implements IBorrowDetailsService{
    private final IBorrowDetailsDAO dao;
        
    public BorrowDetailsServiceImpl(IBorrowDetailsDAO dao) {
        this.dao = dao;
    }

    @Override
    public void add(BorrowDetailsDTO d) throws SQLException {
        try {
            dao.insert(extractFields(d));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(BorrowDetailsDTO d) throws SQLException {
        try {
            dao.update(extractFields(d));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }        
    }

    @Override
    public BorrowDetails get(BorrowDetailsDTO d) throws SQLException {
        try {
            return dao.getInstanceById(d.getId());
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(BorrowDetailsDTO d) throws SQLException {
        try {
            dao.delete(extractFields(d));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getList() throws SQLException {
        try {
            List<BorrowDetails> detailsList = dao.getAll();
            return detailsList;
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getListForMember(BorrowDetailsDTO dto) throws SQLException {
        try {
           return dao.getListByField("user_id", dto.getMemberId());
        }catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getListCurrentlyBorrowed(BorrowDetailsDTO dto) throws SQLException {
        try {
            return dao.getListByField("returned", false);
         }catch(SQLException e) {
             e.printStackTrace();
             throw e;
         }
    }

    
    private BorrowDetails extractFields(BorrowDetailsDTO dto) {
        //BorrowDetails details = new BorrowDetails(dto);
        
        BorrowDetails details = new BorrowDetails();
        Book book = new Book();
        book.setId(dto.getBook().getId());
        
        details.setId(dto.getId());
        details.setMemberId(dto.getMemberId());
        details.setBook(book);
        details.setBorrowDate(dto.getBorrowDate());
        details.setReturnDate(dto.getReturnDate());
        details.setActualReturn(dto.getActualReturn());
        details.setReturned(dto.isReturned());
        return details;
    }
}
