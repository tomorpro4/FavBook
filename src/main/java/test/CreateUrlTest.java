package test;

import model.Keyword;
import model.UrlCreate;

public class CreateUrlTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		int isbnCon = 0;
		String isbn = "";
		int titleCon = 0;
		String title = "Java";
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
		System.out.println(urlCreate.getStartRecord());
		System.out.println(urlCreate.getMaximumRecords());
		System.out.println(urlStr);
	}

}
