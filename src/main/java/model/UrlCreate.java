package model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlCreate {

	String urlStr = "https://ndlsearch.ndl.go.jp/api/sru?operation=searchRetrieve&version=1.2&recordPacking=xml&recordSchema=dcndl&inprocess=false&onlyBib=false";
	private String startRecord;
	private String maximumRecords;
	
	
	
	public void setStartRecord(String startRecord) {
		this.startRecord = startRecord;
	}

	public void setMaximumRecords(String maximumRecords) {
		this.maximumRecords = maximumRecords;
	}

	public String getUrlStr() {
		return urlStr;
	}

	public String getStartRecord() {
		return startRecord;
	}

	public String getMaximumRecords() {
		return maximumRecords;
	}

	public UrlCreate() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.startRecord = String.valueOf(1);
		this.maximumRecords = String.valueOf(20);
	}

	public UrlCreate(int startRecord, int maximumRecords) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.startRecord = String.valueOf(startRecord);
		this.maximumRecords = String.valueOf(maximumRecords);
	}
	
	private String addCon(int con) {
		String conStr;
		switch (con) {
		case 0 -> conStr="all";
		case 1 -> conStr=" any ";
		case 2 -> conStr=" ^ ";
		case 3 -> conStr=" exact";
		default -> conStr="=";
		}
		return conStr;
	}
	
	private String AddAnd(int i) {
		String str = "";
		if(i>0) {
			str = " AND ";
		}
		return str;
	}
	
	private String EncUrl(String url) {
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return url;
	}
	
	
	public String CreateSearchUrl(Keyword keyword) {
		String urlStr1 = this.urlStr;
		String startRecord = String.valueOf(keyword.getStartRecord());
		String maximumRecords = String.valueOf(keyword.getMaximumRecords());
		System.out.println(startRecord);
		System.out.println(maximumRecords);
		if(!startRecord.equals("0")) {
			this.startRecord = startRecord;
		}
		if(!maximumRecords.equals("0")) {
			this.maximumRecords = maximumRecords;
		}
		
		urlStr1 += "&startRecord=";
		urlStr1 += this.startRecord;
		urlStr1 += "&maximumRecords=";
		urlStr1 += this.maximumRecords;
		urlStr1 += "&query=";
		
		String urlStr2 = "";
		int i = 0;
		int titleCon = keyword.getIsbnCon();
		String title = keyword.getTitle();
		if(!(title == null || title.equals(""))) {
			urlStr2 += AddAnd(i);
			urlStr2 += "title";
			urlStr2 += addCon(titleCon);
			urlStr2 += "\"";
			urlStr2 += title;
			urlStr2 += "\"";
			i++;
		}
		int creatorCon = keyword.getCreatorCon();
		String creator = keyword.getCreator();
		if(!(creator == null || creator.equals(""))) {
			urlStr2 += AddAnd(i);
			urlStr2 += "creator";
			urlStr2 += addCon(creatorCon);
			urlStr2 += "\"";
			urlStr2 += creator;
			urlStr2 += "\"";
			i++;
		}
		int publisherCon = keyword.getPublisherCon();
		String publisher = keyword.getPublisher();
		if(!(publisher == null || publisher.equals(""))) {
			urlStr2 += AddAnd(i);
			urlStr2 += "publisher";
			urlStr2 += addCon(publisherCon);
			urlStr2 += "\"";
			urlStr2 += publisher;
			urlStr2 += "\"";
			i++;
		}
		
		
		
		
		int isbnCon = keyword.getIsbnCon();
		String isbn = keyword.getIsbn();
		if(!(isbn == null || isbn.equals(""))) {
			urlStr2 += AddAnd(i);
			urlStr2 += "isbn";
			urlStr2 += "=";
			urlStr2 += "\"";
			urlStr2 += isbn;
			urlStr2 += "\"";
			i++;
		}

		int categoryCon;
		String category;
		int recordCategoryCon;
		String recordCategory;
		int recordSubCategoryCon;
		String recordSubCategory;
		
		this.urlStr = urlStr1 + EncUrl(urlStr2);
		return urlStr;
		
		
	}

}
