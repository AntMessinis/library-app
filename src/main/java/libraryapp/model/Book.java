package libraryapp.model;

import libraryapp.dto.BookDTO;

public class Book extends AbstractEntity{
	private String title;
	private String isbn;
	private Author author;
	private Language language;
	private Subcategory category;
	private String description;
	private int copiesInLibrary;
	private int currentlyBorrowed;
	
	
	public Book() {
		super();
	}
	
	public Book(BookDTO book) {
	    this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getTitle();
        this.author = new Author(book.getAuthor());
        this.language = new Language(book.getLanguage());
        this.category = new Subcategory(book.getCategory());
        this.description = book.getDescription();
        this.copiesInLibrary = book.getCopiesInLibrary();
        this.currentlyBorrowed = book.getCurrentlyBorrowed();
	}
	
	
	public Book(long id, String title, String isbn, Author author, Language language, Subcategory category, String description, int copiesInLibrary,
			int currentlyBorrowed) {
		super(id);
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.language = language;
		this.category = category;
		this.description = description;
		this.copiesInLibrary = copiesInLibrary;
		this.currentlyBorrowed = currentlyBorrowed;
	}

	public Book( String title, String isbn, Author author, Language language, Subcategory category, String description, int copiesInLibrary,
			int currentlyBorrowed) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.language = language;
		this.category = category;
		this.description = description;
		this.copiesInLibrary = copiesInLibrary;
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
	public int getCopiesInLibrary() {
		return copiesInLibrary;
	}
	public void setCopiesInLibrary(int amountInLibrary) {
		this.copiesInLibrary = amountInLibrary;
	}
	public int getCopiesCurrentlyBorrowed() {
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
				&& (description.equals(other.description))
				&& (copiesInLibrary == other.copiesInLibrary)
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + copiesInLibrary;
		result = prime * result + currentlyBorrowed;
		return result;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
