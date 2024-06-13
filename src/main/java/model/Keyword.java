package model;

import java.io.Serializable;

public class Keyword implements Serializable {
	private int isbnCon;
	private String isbn;
	private int titleCon;
	private String title;
	private int creatorCon;
	private String creator;
	private int publisherCon;
	private String publisher;
	private int categoryCon;
	private String category;
	private int recordCategoryCon;
	private String recordCategory;
	private int recordSubCategoryCon;
	private String recordSubCategory;

	public Keyword(String title, String isbn, String creator, String publisher, String category) {
		this.titleCon = 0;
		this.title = title;
		this.isbnCon = 0;
		this.isbn = isbn;
		this.creatorCon = 0;
		this.creator = creator;
		this.publisherCon = 0;
		this.publisher = publisher;
		this.categoryCon = 0;
		this.category = category;
	}
	public Keyword(int titleCon, String title, int isbnCon, String isbn, int creatorCon, String creator, int publisherCon, String publisher, int categoryCon, String category) {
		this.titleCon = titleCon;
		this.title = title;
		this.isbnCon = isbnCon;
		this.isbn = isbn;
		this.creatorCon = creatorCon;
		this.creator = creator;
		this.publisherCon = publisherCon;
		this.publisher = publisher;
		this.categoryCon = categoryCon;
		this.category = category;
	}
	
	private String setCon(int con, String keyword) {
		String newKeyword;
		switch (con) {
		case 0 -> newKeyword = "%" + keyword + "%";
		case 1 -> newKeyword = keyword + "%";
		case 2 -> newKeyword = "%" + keyword;
		case 3 -> newKeyword = keyword;
		default -> newKeyword = "%" + keyword + "%";
		}
		return newKeyword;
	}
	
	public int getIsbnCon() {
		return isbnCon;
	}
	public String getIsbn() {
		return isbn;
	}
	public int getTitleCon() {
		return titleCon;
	}
	public String getTitle() {
		
		return title;
	}
	public int getCreatorCon() {
		return creatorCon;
	}
	public String getCreator() {
		return creator;
	}
	public int getPublisherCon() {
		return publisherCon;
	}
	public String getPublisher() {
		return publisher;
	}
	public int getCategoryCon() {
		return categoryCon;
	}
	public String getCategory() {
		return category;
	}
	public int getRecordCategoryCon() {
		return recordCategoryCon;
	}
	public String getRecordCategory() {
		return recordCategory;
	}
	public int getRecordSubCategoryCon() {
		return recordSubCategoryCon;
	}
	public String getRecordSubCategory() {
		return recordSubCategory;
	}

	
	

	public String getNewIsbn() {
		String newIsbn = setCon(isbnCon, isbn);
		return newIsbn;
	}

	public String getNewTitle() {
		String newTitle = setCon(titleCon,title);
		return newTitle;
	}

	public String getNewCreator() {
		String newCreator = setCon(creatorCon, creator);
		return newCreator;
	}

	public String getNewPublisher() {
		String newPubliser = setCon(publisherCon, publisher);
		return newPubliser;
	}

	public String getNewCategory() {
		String newCategory = setCon(categoryCon, category);
		return newCategory;
	}


	

}
