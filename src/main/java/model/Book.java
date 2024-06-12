package model;

import java.io.Serializable;

public class Book implements Serializable {
	private String id;
	private String isbn;
	private String title;
	//	private ArrayList<String> creator = new ArrayList<String>();
	private Creator creator;
	private Publisher publisher;
	private Category category;
	private String recordCategory;
	private String recordSubCategory;

	public Book() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Book(String id, String title, String isbn, Creator creator, Publisher publisher, Category category) {
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		//		this.creator.add(creator);
		this.creator = creator;
		this.publisher = publisher;
		this.category = category;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getRecordCategory() {
		return recordCategory;
	}

	public void setRecordCategory(String recordCategory) {
		this.recordCategory = recordCategory;
	}

	public String getRecordSubCategory() {
		return recordSubCategory;
	}

	public void setRecordSubCategory(String recordSubCategory) {
		this.recordSubCategory = recordSubCategory;
	}

}
