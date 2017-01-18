package edu.gadalov.webservice.entity;

public class Playlist {
	private int id;
	private String playlistName;
	private User user;
	private AudioTrack track;
	public Playlist(int id, String playlistName, User user, AudioTrack track) {
		super();
		this.id = id;
		this.playlistName = playlistName;
		this.user = user;
		this.track = track;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AudioTrack getTrack() {
		return track;
	}
	public void setTrack(AudioTrack track) {
		this.track = track;
	}

}
