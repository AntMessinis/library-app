package libraryapp.dto;

public class AuthorDTO extends AbstractDTO{
	private String firstname;
	private String lastname;
	private CountryDTO countryOfOrigin;
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
	public CountryDTO getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(CountryDTO countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
	

}
