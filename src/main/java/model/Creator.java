package model;

import java.io.Serializable;

public class Creator implements Serializable {
	private int creatorId;
	private String creatorName;
	public Creator() {}
	public Creator(int creatorId) {
		this.creatorId = creatorId;
	}
	public Creator(String creatorName) {
		this.creatorName = creatorName;
	}
	public Creator(int creatorId, String creatorName) {
		this.creatorId = creatorId;
		this.creatorName = creatorName;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
}
