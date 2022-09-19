package library_app.dto;

public class BookDTO extends AbstractDTO{
	private String title;
	private String isbn;
	private AuthorDTO author;
	private LanguageDTO language;
	private SubcategoryDTO category;
	private String description;
	
	private int amountInLibrary;
	private int currentlyBorrowed;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public AuthorDTO getAuthor() {
		return author;
	}
	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	public LanguageDTO getLanguage() {
		return language;
	}
	public void setLanguage(LanguageDTO language) {
		this.language = language;
	}
	public SubcategoryDTO getCategory() {
		return category;
	}
	public void setCategory(SubcategoryDTO category) {
		this.category = category;
	}
	public int getAmountInLibrary() {
		return amountInLibrary;
	}
	public void setAmountInLibrary(int amountInLibrary) {
		this.amountInLibrary = amountInLibrary;
	}
	public int getCurrentlyBorrowed() {
		return currentlyBorrowed;
	}
	public void setCurrentlyBorrowed(int currentlyBorrowed) {
		this.currentlyBorrowed = currentlyBorrowed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
