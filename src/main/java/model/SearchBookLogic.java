package model;

import dao.BookListDAO;
import dao.bookidcategoryidlistDAO;
import dao.bookidcreatoridlistDAO;

public class SearchBookLogic {

	public int SearchBook(Book book) {
		
		Creator creator = book.getCreator();
		CreatorSearchOrCreatLogic creatorSearchOrCreatLogic = new CreatorSearchOrCreatLogic();
		creator = creatorSearchOrCreatLogic.CreatorSearchOrCreat(creator);
		Publisher publisher = book.getPublisher();
		PublisherSearchOrCreatLogic publisherSearchOrCreatLogic = new PublisherSearchOrCreatLogic();
		publisher = publisherSearchOrCreatLogic.PublisherSearchOrCreat(publisher);
		Category category = book.getCategory();
		CategorySearchOrCreatLogic categorySearchOrCreatLogic = new CategorySearchOrCreatLogic();
		category = categorySearchOrCreatLogic.CategorySearchOrCreat(category);
		book.setCreator(creator);
		book.setPublisher(publisher);
		book.setCategory(category);
		
		BookListDAO bookListDAO = new BookListDAO();
		book = bookListDAO.MachBook(book);
		int bookId = book.getBookId();
		int creatorId = book.getCreator().getCreatorId();
		int publisherId = book.getPublisher().getPublisherId();
		int categoryId = book.getCategory().getCategoryId();
		System.out.println("BookId");
		System.out.println(bookId);
		System.out.println("CreatorId");
		System.out.println(creatorId);
		System.out.println("PublisherId");
		System.out.println(publisherId);
		System.out.println("CategoryId");
		System.out.println(book.getCategory().getCategoryId());
		if(bookId == 0) {
			int id = bookListDAO.AddBook(book);
//			book = bookListDAO.MachBook(book);
			bookidcreatoridlistDAO bookidcreatoridlistDAO = new bookidcreatoridlistDAO();
			bookidcreatoridlistDAO.insert(id, creatorId);
			bookidcategoryidlistDAO bookidcategoryidlistDAO = new bookidcategoryidlistDAO();
			bookidcategoryidlistDAO.insert(id, categoryId);
			bookId = id;
		}
		
		return bookId;

	}
}
