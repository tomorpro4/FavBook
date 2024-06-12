package model;

import java.io.Serializable;

public class Publisher implements Serializable {
	private int publisherId;
	private String publisherName;
	
	public Publisher() {}
	public Publisher(int publisherId) {
		this.publisherId = publisherId;
	}
	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}
	public Publisher(int publisherId, String publisherName) {
		this.publisherId = publisherId;
		this.publisherName = publisherName;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
}
