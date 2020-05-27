package it.univpm.GiAle.twitterProj.model;

public class Tweet {
	private String created_at;
	private long id;
	private String text;
	private int retweet_count;
	private int favorite_count;
	
	public Tweet() {
		super();
		this.created_at = "";
		this.id = 0;
		this.text = "";
		this.favorite_count = 0;
		this.retweet_count = 0;
	}
	
	public int getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}
	public int getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Tweet(String created_at, long id, String text, int retweet_count, int favorite_count) {
		super();
		this.created_at = created_at;
		this.id = id;
		this.text = text;
		this.favorite_count = favorite_count;
		this.retweet_count = retweet_count;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}