package edu.gadalov.webservice.entity;

public class AudioTrack {
	private int id;
	private User user;
	private String singer;
	private String trackName;
	private String album;
	private String filePath;
	private float cost;
	private String genre;
	public AudioTrack(int id, User user, String singer, String trackName, String album, String filePath,
			float cost, String genre) {
		this.id = id;
		this.user = user;
		this.singer = singer;
		this.trackName = trackName;
		this.album = album;
		this.filePath = filePath;
		this.cost = cost;
		this.genre = genre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
	public String getGenre(){
		return genre;
	}

}
