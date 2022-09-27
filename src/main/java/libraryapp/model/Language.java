package libraryapp.model;

public class Language extends AbstractEntity{
	private String languageName;

	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Language(long id, String languageName) {
		super();
		this.languageName = languageName;
	}

	public Language(String languageName) {
		super();
		this.languageName = languageName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Language)) return false;
		
		Language other = (Language)obj;
		
		return languageName.equals(other.languageName);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		
		return result;
	}
	
	@Override
	public String toString() {
		return languageName;
	}
}
