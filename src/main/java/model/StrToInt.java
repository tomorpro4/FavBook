package model;

public class StrToInt {

	public StrToInt() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public int StrToIntLog(String str) {
		int i=0;
		if(str != null) {
			i = Integer.parseInt(str);
		}
		return i;
	}

}
