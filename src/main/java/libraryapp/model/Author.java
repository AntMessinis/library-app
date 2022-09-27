package libraryapp.model;

public class Author extends AbstractEntity{
	private String firstname;
	private String lastname;
	private Country countryOfOrigin;
	
	public Author() {
		super();
	}
	
	public Author(long id, String firstname, String lastname, Country countryOfOrigin) {
		super(id);
		this.firstname = firstname;
		this.lastname = lastname;
		this.countryOfOrigin = countryOfOrigin;
	}
	
	public Author(String firstname, String lastname, Country countryOfOrigin) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.countryOfOrigin = countryOfOrigin;
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
	public Country getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((countryOfOrigin == null) ? 0 : countryOfOrigin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())	return false;
		
		Author other = (Author) obj;
		return firstname.equals(other.firstname)
				&& lastname.equals(other.lastname)
				&& countryOfOrigin.equals(other.countryOfOrigin);
	}

	@Override
	public String toString() {
		return  firstname + " " + lastname;
	}
	
	

}
