package model;

public class FavBook extends Book {
	private int favBookId;
	private Status status;
	private String memo;

	public FavBook() {
		// TODO 自動生成されたコンストラクター・スタブ
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
}
