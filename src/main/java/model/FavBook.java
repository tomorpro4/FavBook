package model;

public class FavBook extends Book {
	private int favBookId;
	private Status status;
	public int getFavBookId() {
		return favBookId;
	}

	public Status getStatus() {
		return status;
	}

	public String getMemo() {
		return memo;
	}

	private String memo;

	public FavBook() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public FavBook(Book book) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(book.getBookId(),book.getBookTitle(),book.getIsbn(),book.getCreator(),book.getPublisher(),book.getCategory());
	}
	

	public FavBook(int bookId, String bookTitle, String isbn, Creator creator, Publisher publisher, Category category) {
		super(bookId, bookTitle, isbn, creator, publisher, category);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public FavBook(int favBookId, Status status, String memo, int bookId, String bookTitle, String isbn, Creator creator, Publisher publisher, Category category) {
		super(bookId, bookTitle, isbn, creator, publisher, category);
		// TODO 自動生成されたコンストラクター・スタブ
		this.favBookId = favBookId;
		this.status = status;
		this.memo = memo;
	}

	public void setFavBookId(int favBookId) {
		this.favBookId = favBookId;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
