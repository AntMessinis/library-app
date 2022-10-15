package libraryapp.model;

import libraryapp.dto.CountryDTO;

public class Country extends AbstractEntity{
	private String countryName;
	
	public Country() {
		super();
	}
	
	public Country(CountryDTO country) {
	    this.countryName = country.getCountryName();
	}
	
	public Country(long id,String name) {
		super(id);
		this.countryName = name;
	}
	
	public Country(String name) {
		super();
		this.countryName = name;
	}
	
	public String getName() {
		return countryName;
	}

	public void setName(String name) {
		this.countryName = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		
		result = prime * result + countryName.hashCode();
		result = prime * result + (int)(id^(id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return this.countryName.equals(other.countryName);
	}

	@Override
	public String toString() {
		return this.countryName;
	}
	
	
	
}
