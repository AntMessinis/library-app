package library_app.model;

public class Book extends AbstractEntity{
	private String title;
	private String isbn;
	private Author author;
	private Language language;
	private Subcategory category;
	
	private int amountInLibrary;
	private int currentlyBorrowed;
	
	
	public Book() {
		super();
	}
	
	
	public Book(long id, String title, String isbn, Author author, Language language, Subcategory category, int amountInLibrary,
			int currentlyBorrowed) {
		super(id);
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.language = language;
		this.category = category;
		this.amountInLibrary = amountInLibrary;
		this.currentlyBorrowed = currentlyBorrowed;
	}


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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Subcategory getCategory() {
		return category;
	}
	public void setCategory(Subcategory category) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		if (!(obj instanceof Book)) return false;
		
		Book other = (Book) obj;
		
		return (id == other.id)
				&& (title.equals(other.title))
				&& (isbn.equals(other.isbn))
				&& (author.equals(other.author))
				&& (language.equals(other.language))
				&& (category.equals(other.category))
				&& (amountInLibrary == other.amountInLibrary)
				&& (currentlyBorrowed == other.currentlyBorrowed);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(id^(id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + amountInLibrary;
		result = prime * result + currentlyBorrowed;
		return result;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
