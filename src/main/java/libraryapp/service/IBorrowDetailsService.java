package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dto.BorrowDetailsDTO;
import libraryapp.model.BorrowDetails;

public interface IBorrowDetailsService extends IGenericService<BorrowDetailsDTO, BorrowDetails>{
    public List<BorrowDetails> getListForMember(BorrowDetailsDTO dto) throws SQLException;
    public List<BorrowDetails> getListCurrentlyBorrowed(BorrowDetailsDTO dto) throws SQLException;
}
