package model;

import java.io.Serializable;

public class Session implements Serializable {
	private int id;
	private String title;
	private String session_date;
	private String location;
	private String review;

	public Session() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSessionDate() {
		return session_date;
	}

	public void setSessionDate(String session_date) {
		this.session_date = session_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
