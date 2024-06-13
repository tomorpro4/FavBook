package model;

import java.io.Serializable;

public class Book implements Serializable {
	private int bookId;
	private String isbn;
	private String bookTitle;
	//	private ArrayList<String> creator = new ArrayList<String>();
	private Creator creator;
	private Publisher publisher;
	private Category category;
	private String recordCategory;
	private String recordSubCategory;

	public Book() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Book(int bookId, String bookTitle, String isbn, Creator creator, Publisher publisher, Category category) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		//		this.creator.add(creator);
		this.creator = creator;
		this.publisher = publisher;
		this.category = category;

	}

	public int getBookId() {
		return bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public Creator getCreator() {
		return creator;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public Category getCategory() {
		return category;
	}

	public String getRecordCategory() {
		return recordCategory;
	}

	public String getRecordSubCategory() {
		return recordSubCategory;
	}

}
