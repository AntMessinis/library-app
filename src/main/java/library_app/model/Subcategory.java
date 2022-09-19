package library_app.model;

public class Subcategory extends Category{
	private String subcategoryName;

	public Subcategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subcategory(long id, String category) {
		super(id, category);
		// TODO Auto-generated constructor stub
	}

	public Subcategory(long id, String subcategoryName, String category) {
		super(id, category);
		this.subcategoryName = subcategoryName;
	}
	
	public Subcategory(String subcategoryName, String category) {
		super(category);
		this.subcategoryName = subcategoryName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if	(obj == this) return true;
		if (obj == null) return false;
		if (!(obj instanceof Subcategory)) return false;
		
		Subcategory other = (Subcategory)obj;
		
		return (id == other.id) && (category == other.category) && (subcategoryName == other.subcategoryName);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((subcategoryName == null) ? 0 : subcategoryName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return  subcategoryName;
	}
	
	
}
