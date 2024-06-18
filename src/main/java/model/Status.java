package model;

public class Status {
	private int statusId;
	private String status;

	public Status() {
	}

	public Status(int statusId) {
		this.statusId = statusId;
		switch (statusId) {
		case 1 -> this.status = "とりあえず";
		case 2 -> this.status = "ちょっと気になる";
		case 3 -> this.status = "気になる";
		case 4 -> this.status = "超気になる";
		case 5 -> this.status = "読んでる";
		case 6 -> this.status = "読み終わった";
		case 7 -> this.status = "積読";
		default -> {
			this.statusId = 0;
			this.status = "";
		}
		}
	}

	public Status(String status) {
		this.status = status;
	}

	public Status(int statusId, String status) {
		this.statusId = statusId;
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public String getStatus() {
		return status;
	}

}
