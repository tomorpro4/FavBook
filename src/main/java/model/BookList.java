package model;

import java.util.ArrayList;
import java.util.Collection;

public class BookList extends ArrayList<FavBook> {
	private int numberOfRecord;
	private int nextRecord;

	public int getNumberOfRecord() {
		return numberOfRecord;
	}

	public void setNumberOfRecord(int numberOfRecord) {
		this.numberOfRecord = numberOfRecord;
	}

	public int getNextRecord() {
		return nextRecord;
	}

	public void setNextRecord(int nextRecord) {
		this.nextRecord = nextRecord;
	}

	public BookList() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public BookList(int initialCapacity) {
		super(initialCapacity);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public BookList(Collection<? extends FavBook> c) {
		super(c);
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
