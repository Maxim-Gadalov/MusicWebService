package edu.gadalov.webservice.entity;

import edu.gadalov.webservice.util.Unusable;

/**
 * Entity class for interaction with database information and business logic.
 * Audio track object.
 * @author Maxim Gadalov
 *
 */

public class AudioTrack implements Unusable{
	private int id;
	private User user;
	private String singer;
	private String trackName;
	private String album;
	private String filePath;
	private float cost;
	private String genre;
	private boolean visibility;
	/** Create object with following parameters
	 * @param id - id of object similar PRIMARY KEY (int)
	 * @param user - user(administrator) who create this audio track @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 * @param singer - the name of performer this audio track (String)
	 * @param trackName - track name (String)
	 * @param album - album (String)
	 * @param filePath - a reference to the relative path to the audio resource(String)
	 * @param cost - cost of the audio track (float)
	 * @param genre - genre of the audio track (String)
	 * @param visibility - boolean variable , removal mark. true for valid audio tracks and false otherwise
	 */
	public AudioTrack(int id, User user, String singer, String trackName, String album, String filePath,
			float cost, String genre, boolean visibility) {
		this.id = id;
		this.user = user;
		this.singer = singer;
		this.trackName = trackName;
		this.album = album;
		this.filePath = filePath;
		this.cost = cost;
		this.genre = genre;
		this.visibility = visibility;
	}
	/**
	 * Default constructor which create empty object.
	 */
	public AudioTrack(){
		
	}
	/**
	 * @return the integer Id of object
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id set integer id to current object
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return administrator who create object @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user set administrator to current object @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the name of performer 
	 */
	public String getSinger() {
		return singer;
	}
	/**
	 * @param singer set performer to current object
	 */
	public void setSinger(String singer) {
		this.singer = singer;
	}
	/**
	 * @return audio track name
	 */
	public String getTrackName() {
		return trackName;
	}
	/**
	 * @param trackName set audio track name to current object
	 */
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	/**
	 * @return the name of track album
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * @param album set name of album to current object
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	/**
	 * @return the relative file path
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath set file path to current object
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return cost value
	 */
	public float getCost() {
		return cost;
	}
	/**
	 * @param cost set cost to current object
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}
	/**
	 * @param genre set genre to current object
	 */
	public void setGenre(String genre){
		this.genre = genre;
	}
	/**
	 * @return name of genre
	 */
	public String getGenre(){
		return genre;
	}
	/**
	 * @param visibility set removal mark
	 */
	public void setVisibility(boolean visibility){
		this.visibility = visibility;
	}
	/**
	 * @return true if object is valid and false otherwise
	 */
	public boolean getVisibility(){
		return visibility;
	}
	@Override
	public boolean isUntapped() {
		return ((this.id == 0) & (this.user == null) & (this.cost == 0.0) & (this.album == null) &
				(this.filePath == null) & (this.genre == null) & (this.singer == null) &
				(this.trackName == null) & (this.visibility == false));
	}

}
