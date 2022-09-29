package libraryapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends AbstractEntity{
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private Address address;
	private String phoneNumber;
	private Date birthdate;
	private boolean isAdmin;
	private List<BorrowDetails> booksCurrentlyBorrowed = new ArrayList<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, String firstname, String lastname, Address address, String phoneNumber, String email, String username, String password,  
			Date birthdate, boolean isAdmin) {
		super(id);
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthdate = birthdate;
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<BorrowDetails> getBooksCurrentlyBorrowed() {
		return booksCurrentlyBorrowed;
	}

	public void setBooksCurrentlyBorrowed(List<BorrowDetails> booksCurrentlyBorrowed) {
		this.booksCurrentlyBorrowed = booksCurrentlyBorrowed;
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		if (!(obj instanceof User)) return false;
		
		User other = (User)obj;
		return id == other.id
				&& username.equals(other.username)
				&& password.equals(other.password)
				&& email.equals(other.email);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((isAdmin) ? 1 : 0);
		result = prime * result + ((booksCurrentlyBorrowed == null) ? 0 : booksCurrentlyBorrowed.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return username;
	}

}
