package edu.gadalov.webservice.entity;

public enum EDMGenres {
	TRANCE("Trance"),HOUSE("House"),TECHNO("Techno"),DUBSTEP("Dubstep"),DRAM_AND_BASS("Drum & Bass");
	private String genreValue;
	private EDMGenres(String genreValue){
		this.genreValue = genreValue;
	}

}
