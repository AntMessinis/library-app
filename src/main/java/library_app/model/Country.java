package library_app.model;

public class Country extends AbstractEntity{
	private String name;
	
	public Country() {
		super();
	}
	
	public Country(long id,String name) {
		super(id);
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		
		result = prime * result + name.hashCode();
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
		return this.name.equals(other.name);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
	
}
