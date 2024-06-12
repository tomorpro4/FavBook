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
		this.title = title;
		this.isbn = isbn;
		this.creator = creator;
		this.publisher = publisher;
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

	public int getIsbnCon() {
		return isbnCon;
	}
	public void setIsbnCon(int isbnCon) {
		this.isbnCon = isbnCon;
	}
	public int getTitleCon() {
		return titleCon;
	}
	public void setTitleCon(int titleCon) {
		this.titleCon = titleCon;
	}
	public int getCreatorCon() {
		return creatorCon;
	}
	public void setCreatorCon(int creatorCon) {
		this.creatorCon = creatorCon;
	}
	public int getPublisherCon() {
		return publisherCon;
	}
	public void setPublisherCon(int publisherCon) {
		this.publisherCon = publisherCon;
	}
	public int getCategoryCon() {
		return categoryCon;
	}
	public void setCategoryCon(int categoryCon) {
		this.categoryCon = categoryCon;
	}
	public int getRecordCategoryCon() {
		return recordCategoryCon;
	}
	public void setRecordCategoryCon(int recordCategoryCon) {
		this.recordCategoryCon = recordCategoryCon;
	}
	public int getRecordSubCategoryCon() {
		return recordSubCategoryCon;
	}
	public void setRecordSubCategoryCon(int recordSubCategoryCon) {
		this.recordSubCategoryCon = recordSubCategoryCon;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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
