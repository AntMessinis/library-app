package libraryapp.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import libraryapp.model.BorrowDetails;

public interface IBorrowDetailsDAO extends IGenericDAO<BorrowDetails>{
    List<BorrowDetails> getListByField(String fieldName, long value) throws SQLException;
    List<BorrowDetails> getListByField(String fieldName, Date value) throws SQLException;
    List<BorrowDetails> getListByField(String fieldName, boolean value) throws SQLException;
} 
