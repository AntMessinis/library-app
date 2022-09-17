package library_app.model;

public class Address extends AbstractEntity{
	private String addressName;
	private String postalCode;
	private String city;
	private Country country;
	
	public Address() {
		super();
	}
	public Address(long id, String addressName, String postalCode, String city, Country country) {
		super(id);
		this.addressName = addressName;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Address)) return false;
		Address other = (Address)obj;
		return addressName.equals(other.addressName)
				&& postalCode.equals(other.postalCode)
				&& city.equals(other.city)
				&& country.equals(other.country);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(id ^ (id >>> 32)) ;
		result = prime * result + (addressName == null ? 0 : addressName.hashCode());
		result = prime * result + (postalCode == null ? 0 : postalCode.hashCode());
		result = prime * result + (city == null ? 0 : city.hashCode());
		result = prime * result + (country == null ? 0 : country.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return addressName + ", " + postalCode + ", " + city + ", " + country.toString();
		}

}
