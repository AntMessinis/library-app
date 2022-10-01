package libraryapp.dto;

public class SubcategoryDTO extends AbstractDTO{
	private String categoryName;
	private String subcategoryName;
	
	
	
	public SubcategoryDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    public SubcategoryDTO(String subcategoryName) {
        super();
        this.subcategoryName = subcategoryName;
    }
    public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubcategoryName() {
		return subcategoryName;
	}
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	
	
}
