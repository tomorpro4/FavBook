package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetXmlLogic {

	public String GetXmlByUrl(String urlStr) {

		String xmlStr = null;
		
		

		try {
//			String titleUrl = "title=%22" + URLEncoder.encode(title, "UTF-8") + "%22";
			URL url = new URL(urlStr);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			System.out.println(con.getResponseCode());
			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); // ③WebAPIからの返却データを取得 

				String str;
				StringBuffer sb = new StringBuffer();
				while ((str = in.readLine()) != null) {
					sb.append(str);
				}
				in.close();
				xmlStr = sb.toString();
//				System.out.println(xmlStr); // ④JSON形式で結果を取得 
				System.out.println(url);
			}
			con.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return xmlStr;
	}
		
		
		
		
		
		
		
		
		
//		try {
//			URL url = new URL(urlStr);
//			System.out.println("URL");
//			System.out.println(urlStr);
//			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//			con.setRequestMethod("GET");
//			con.connect();
//			System.out.println("CONNECTION!");
//			System.out.println(con.getResponseCode());
//			if (con.getResponseCode() == 200) {
//				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); // ③WebAPIからの返却データを取得 
//
//				String str;
//				StringBuffer sb = new StringBuffer();
//				while ((str = in.readLine()) != null) {
//					sb.append(str);
//				}
//				in.close();
//				xmlStr = sb.toString();
//				System.out.println(xmlStr); // ④JSON形式で結果を取得 
//				System.out.println(url);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("error");
//			System.out.println(e);
//		}
//
//		return xmlStr;
//
//	}
}
