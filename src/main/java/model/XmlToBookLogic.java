package model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import dao.BookListDAO;
import dao.FavoritelistDAO;
import dao.categorylistDAO;
import dao.creatorlistDAO;
import dao.publisherlistDAO;

public class XmlToBookLogic {

	public XmlToBookLogic() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String GetRecordCategory(Element recordElement) {
		String recordCategory = "";
		NodeList recordCategoryNodeList = recordElement.getElementsByTagName("dcndl:bibRecordCategory");
		if (recordCategoryNodeList.getLength() > 0) {
			recordCategory = recordCategoryNodeList.item(0).getTextContent();
		}
		return recordCategory;
	}

	public String GetRecordSubCategory(Element recordElement) {
		String recordSubCategory = "";
		NodeList recordSubCategoryNodeList = recordElement.getElementsByTagName("dcndl:bibRecordSubCategory");
		if (recordSubCategoryNodeList.getLength() > 0) {
			recordSubCategory = recordSubCategoryNodeList.item(0).getTextContent();
		}
		return recordSubCategory;
	}

	public String GetValue(Element element) {
		String valueStr = "";
		NodeList nodeList = element.getElementsByTagName("rdf:value");
		if (nodeList.getLength() > 0) {
			valueStr = nodeList.item(0).getTextContent();
		}
		return valueStr;
	}

	public String GetTranscription(Element element) {
		String transcriptionStr = "";
		NodeList nodeList = element.getElementsByTagName("dcndl:transcription");
		if (nodeList.getLength() > 0) {
			transcriptionStr = nodeList.item(0).getTextContent();
		}
		return transcriptionStr;
	}

	public String GetName(Element element) {
		String nameStr = "";
		NodeList nodeList = element.getElementsByTagName("foaf:name");
		if (nodeList.getLength() > 0) {
			nameStr = nodeList.item(0).getTextContent();
		}
		return nameStr;
	}

	public String GetBookTitle(Element recordElement) {
		String bookTitle = "";
		String bookTitleTranscription = "";
		NodeList recordBookTitleNodeList = recordElement.getElementsByTagName("dc:title");
		if (recordBookTitleNodeList.getLength() > 0) {
			Element bookTitleElement = (Element) recordBookTitleNodeList.item(0);
			bookTitle = GetValue(bookTitleElement);
			bookTitleTranscription = GetTranscription(bookTitleElement);
		}

		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookTitleTranscription);

		return bookTitle;
	}

	public String GetBookCreator(Element recordElement) {
		String bookCreator = "";
		String bookCreatorTranscription = "";
//		NodeList recordBookCreatorNodeList = recordElement.getElementsByTagName("dcterms:creator");
		NodeList recordBookCreatorNodeList = recordElement.getElementsByTagName("dc:creator");
		if (recordBookCreatorNodeList.getLength() > 0) {
			Element bookCreatorElement = (Element) recordBookCreatorNodeList.item(0);
//			bookCreator = GetName(bookCreatorElement);
			bookCreator = bookCreatorElement.getTextContent();
			bookCreatorTranscription = GetTranscription(bookCreatorElement);
		}

		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookCreatorTranscription);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookCreator);

		return bookCreator;
	}

	public String GetPublisher(Element recordElement) {
		String bookPublisher = "";
		NodeList recordPublisherNodeList = recordElement.getElementsByTagName("dcterms:publisher");
		if (recordPublisherNodeList.getLength() > 0) {
			Element bookPublisherElement = (Element) recordPublisherNodeList.item(0);
			bookPublisher = GetName(bookPublisherElement);
		}
		return bookPublisher;
	}

	public String GetBookCategory(Element recordElement) {
		String bookCategory = "";
		ArrayList<String> bookCategoryList = new ArrayList<String>();
		NodeList recordCategoryNodeList = recordElement.getElementsByTagName("dcterms:subject");
		if (recordCategoryNodeList.getLength() > 0) {
			Element bookCategoryElement = (Element) recordCategoryNodeList.item(0);
			bookCategory = GetValue(bookCategoryElement);
			for (int i = 0; i < recordCategoryNodeList.getLength(); i++) {
				bookCategoryList.add(GetValue((Element) recordCategoryNodeList.item(i)));
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookCategoryList.get(i));
			}
		}
		return bookCategory;
	}

	public String GetBookIsbn(Element recordElement) {
		String bookIsbn = "";
		NodeList recordIsbnNodeList = recordElement.getElementsByTagName("dcterms:identifier");
		if (recordIsbnNodeList.getLength() > 0) {

			for (int i = 0; i < recordIsbnNodeList.getLength(); i++) {
				Element bookIsbnElement = (Element) recordIsbnNodeList.item(i);
				String attributes = bookIsbnElement.getAttributes().item(0).getTextContent();
				//				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+attributes);
				if (attributes.equals("http://ndl.go.jp/dcndl/terms/ISBN")) {
					bookIsbn = bookIsbnElement.getTextContent();
					bookIsbn = bookIsbn.replace("-", "");
				}
			}

		}
		return bookIsbn;
	}

	public Book RecordNodeToBook(Element recordElement) {

		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"RecordNodeToBook");

		String recordCategory = GetRecordCategory(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+recordCategory);

		String recordSubCategory = GetRecordSubCategory(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+recordSubCategory);

		String bookTitle = GetBookTitle(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookTitle);

		String bookPublisher = GetPublisher(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookPublisher);

		String bookCreator = GetBookCreator(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookCreator);

		String bookCategory = GetBookCategory(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookCategory);

		String bookIsbn = GetBookIsbn(recordElement);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"ISBN:");
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookIsbn);

		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"******************");

		Creator creator = new Creator(bookCreator);
		creatorlistDAO creatorlistDAO = new creatorlistDAO();
		creator = creatorlistDAO.searchCreatorByName(creator);
		//		if(creator.getCreatorId()==0) {
		//			creator = creatorlistDAO.insertCreator(creator);
		//		}

		Publisher publisher = new Publisher(bookPublisher);
		publisherlistDAO publisherlistDAO = new publisherlistDAO();
		publisher = publisherlistDAO.searchPublisherByName(publisher);
		//		if(publisher.getPublisherId()==0) {
		//			publisher = publisherlistDAO.insertPublisher(publisher);
		//		}

		Category category = new Category(bookCategory);
		categorylistDAO categorylistDAO = new categorylistDAO();
		category = categorylistDAO.searchCategoryByName(category);
		//		if(category.getCategoryId()==0) {
		//			category = categorylistDAO.insertCategory(category);
		//		}

		Book book = new Book(bookTitle, bookIsbn, creator, publisher, category);
		BookListDAO bookListDAO = new BookListDAO();

		Keyword keyword = new Keyword(3, bookTitle, 3, bookIsbn, 3, creator.getCreatorName(), 3,
				publisher.getPublisherName(), 3, category.getCategoryName());
		ArrayList<Book> bookList = bookListDAO.searchBook(keyword);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookList.size());
		if (bookList.size() > 0) {
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"ISBNで検索した結果bookidは");
			book = bookList.get(0);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+book.getBookId());
		}

		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"===================");
		return book;

	}

	public BookList XmlToBookList(User user, XMLmodel xmLmodel) {
		String xmlStr = xmLmodel.getXmlStr();
		BookList bookList = new BookList();
		try {
//			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+xmlStr);
			Document document = toDocument(xmlStr);
			Element xmlElement = document.getDocumentElement();

			NodeList errorNodeList = xmlElement.getElementsByTagName("details");
			NodeList messageNodeList = xmlElement.getElementsByTagName("message");

			if (errorNodeList.getLength() > 0) {
				System.out.print("エラー：");
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+errorNodeList.item(0).getTextContent());
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+messageNodeList.item(0).getTextContent());
			} else {

				//version取得
				Node vesionNode = xmlElement.getElementsByTagName("version").item(0);
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+vesionNode.getNodeName());
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+vesionNode.getTextContent());

				//numberOfRecords取得
				Node numberOfRecordsNode = xmlElement.getElementsByTagName("numberOfRecords").item(0);
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+numberOfRecordsNode.getNodeName());
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+numberOfRecordsNode.getTextContent());

				int numberOfRecord = Integer.parseInt(numberOfRecordsNode.getTextContent());
				bookList.setNumberOfRecord(numberOfRecord);

				//nextRecordPosition取得
				NodeList nextRecordPositionNodeList = xmlElement.getElementsByTagName("nextRecordPosition");
				int nextRecordPosition = 0;
				if (nextRecordPositionNodeList.getLength() > 0) {
					Node nextRecordPositionNode = nextRecordPositionNodeList.item(0);
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+nextRecordPositionNode.getNodeName());
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+nextRecordPositionNode.getTextContent());
					nextRecordPosition = Integer.parseInt(nextRecordPositionNode.getTextContent());

				}
				bookList.setNextRecord(nextRecordPosition);

				//record取得
				NodeList recordNodeList = xmlElement.getElementsByTagName("record");
				for (int i = 0; i < recordNodeList.getLength(); i++) {
					Element recordeNode = (Element) recordNodeList.item(i);
					//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					Book book = RecordNodeToBook(recordeNode);
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					FavBook book2 = new FavBook(book);
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"bookid");
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+book.getBookId());
					if (book.getBookId() != 0) {
						FavBook favBook = favoritelistDAO.listFavBook(user, book);
						System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"favbook" + favBook);
						if (favBook != null) {
							book2 = favBook;
						}
					}

					bookList.add(book2);
				}
			}

		} catch (ParserConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return bookList;

	}

	public Document toDocument(String _xml) throws ParserConfigurationException, SAXException, IOException {
		try (StringReader reader = new StringReader(_xml)) {
			InputSource source = new InputSource(reader);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(source);
		}
	}
}
