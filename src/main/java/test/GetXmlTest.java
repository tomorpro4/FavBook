package test;

import model.BookList;
import model.GetXmlLogic;
import model.Keyword;
import model.UrlCreate;
import model.XMLmodel;
import model.XmlToBookLogic;

public class GetXmlTest {

	public GetXmlTest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		int isbnCon = 3;
		String isbn = "9784800712271";
		int titleCon = 0;
		String title = "Java入門";
		int creatorCon = 0;
		String creator = "";
		int publisherCon = 0;
		String publisher = "";
		int categoryCon = 0;
		String category = "";
		int recordCategoryCon = 0;
		String recordCategory = "";
		int recordSubCategoryCon = 0;
		String recordSubCategory = "";

		Keyword keyword = new Keyword(titleCon, title, isbnCon, isbn, creatorCon, creator, publisherCon, publisher,
				categoryCon, category);
		UrlCreate urlCreate = new UrlCreate();
		
		String urlStr = urlCreate.CreateSearchUrl(keyword);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+urlCreate.getStartRecord());
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+urlCreate.getMaximumRecords());
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+urlStr);
		

//		urlStr = "https://ndlsearch.ndl.go.jp/api/sru?operation%3DsearchRetrieve%26version%3D1.2%26recordPacking%3Dxml%26recordSchema%3Ddcndl%26inprocess%3Dfalse%26onlyBib%3Dfalse%26startRecord%3D1%26maximumRecords%3D20%26query%3Dtitle%3D%22Java%22";
		GetXmlLogic getXmlLogic = new GetXmlLogic();
		String xmlStr = getXmlLogic.GetXmlByUrl(urlStr);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"GetXmlTest");
//			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+xmlStr);
		XMLmodel xmLmodel = new XMLmodel(xmlStr);
		XmlToBookLogic xmlToBookLogic = new XmlToBookLogic();
		BookList bookList = xmlToBookLogic.XmlToBookList(xmLmodel);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookList.get(0).getBookId());
	}

}
