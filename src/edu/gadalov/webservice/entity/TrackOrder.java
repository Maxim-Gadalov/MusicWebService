package edu.gadalov.webservice.entity;

/**Entity class for interaction with database information and business logic.
 * Order of the track object.
 * @author Maxim Gadalov
 *
 */
public class TrackOrder {
	private User user;
	private AudioTrack track;
	/**Create object with following parameters
	 * @param user - user who order track @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 * @param track - audio track @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 */
	public TrackOrder(User user, AudioTrack track){
		this.user = user;
		this.track = track;
	}
	/**
	 * @return user @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user - new user @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return audio track @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 */
	public AudioTrack getTrack() {
		return track;
	}
	/**
	 * @param track - new audio track @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 */
	public void setTrack(AudioTrack track) {
		this.track = track;
	}

}
