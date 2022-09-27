package libraryapp.model;

public abstract class Category extends AbstractEntity{
	protected String category;

	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(long id, String category) {
		super(id);
		this.category = category;
	}
	
	public Category(String category) {
		super();
		this.category = category;
	}

	public String getCategoryName() {
		return category;
	}

	public void setCategoryName(String category) {
		this.category = category;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if(!(obj instanceof Category)) return false;
		
		Category other = (Category)obj;
		return category.equals(other.category);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return category;
	}
	
	
}
