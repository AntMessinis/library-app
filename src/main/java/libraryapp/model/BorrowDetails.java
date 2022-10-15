package libraryapp.model;

import java.util.Date;

import libraryapp.dto.BorrowDetailsDTO;

public class BorrowDetails extends AbstractEntity{
    private long memberId;
	private Book book;
	private Date borrowDate;
	private Date returnDate;
	private Date actualReturn;
	private boolean returned;
	
	
	
	public BorrowDetails() {
		super();
	}
	
	public BorrowDetails(BorrowDetailsDTO dto) {
	    this.memberId = dto.getMemberId();
	    this.book = new Book(dto.getBook());
	    this.borrowDate = dto.getBorrowDate();
	    this.returnDate = dto.getReturnDate();
	    this.actualReturn = dto.getActualReturn();
	    this.returned = dto.isReturned();
	}
	
	public BorrowDetails(long id, long memberId,Book book, Date borrowDate, Date returnDate, Date actualReturn) {
		super(id);
		this.memberId = memberId;
		this.book = book;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.actualReturn = actualReturn;
	}



	public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
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

	public long getMemberId() {
        return memberId;
    }


    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }



    @Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null) 	return false;
		if (!(obj instanceof BorrowDetails)) return false;
		
		BorrowDetails other = (BorrowDetails) obj;
		
		return id == other.id
		        && memberId == other.memberId
				&& book.equals(other.book)
				&& borrowDate.equals(other.borrowDate)
				&& returnDate.equals(other.returnDate)
				&& actualReturn.equals(other.actualReturn);
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + (int)(memberId^(memberId >>> 32));
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((borrowDate == null) ? 0 : borrowDate.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((actualReturn == null) ? 0 : actualReturn.hashCode());
		return result;
	}



	@Override
	public String toString() {
		return "BorrowDetails [Member Id =" + memberId +", Book=" + book + ", Borrow Date=" + borrowDate + ", Official Return Date=" + returnDate
				+ ", Date Returned=" + actualReturn + "]";
	}

	
	
	

}
