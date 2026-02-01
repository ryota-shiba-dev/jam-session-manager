package model;

import java.io.Serializable;

public class Song implements Serializable {
	private int id;
	private int session_id;
	private String song_name;
	private String youtube_url;
	private String song_review;

	public Song() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSessionId() {
		return session_id;
	}

	public void setSessionId(int session_id) {
		this.session_id = session_id;
	}

	public String getSongName() {
		return song_name;
	}

	public void setSongName(String song_name) {
		this.song_name = song_name;
	}

	public String getYoutubeUrl() {
		return youtube_url;
	}

	public void setYoutubeUrl(String youtube_url) {
		this.youtube_url = youtube_url;
	}

	public String getSongReview() {
		return song_review;
	}

	public void setSongReview(String song_review) {
		this.song_review = song_review;
	}

}
