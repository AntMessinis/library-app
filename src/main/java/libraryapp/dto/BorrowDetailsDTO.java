package libraryapp.dto;

import java.util.Date;

public class BorrowDetailsDTO extends AbstractDTO{
    private long memberId;
    private BookDTO book;
    private Date borrowDate;
    private Date returnDate;
    private Date actualReturn;
    private boolean returned;
    
    
    
    
    

    public BorrowDetailsDTO(long memberId, BookDTO book, Date borrowDate, Date returnDate, Date actualReturn, boolean returned) {
        super();
        this.memberId = memberId;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.actualReturn = actualReturn;
        this.returned = returned;
    }
    public BorrowDetailsDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    public long getMemberId() {
        return memberId;
    }
    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
    public BookDTO getBook() {
        return book;
    }
    public void setBook(BookDTO book) {
        this.book = book;
    }
    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public Date getActualReturn() {
        return actualReturn;
    }
    public void setActualReturn(Date actualReturn) {
        this.actualReturn = actualReturn;
    }
    
    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
