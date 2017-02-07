package edu.gadalov.webservice.exception;


public class DatabaseRequestException extends RuntimeException{
	private static final long serialVersionUID = -2289564302790970032L;
	public DatabaseRequestException(){
		super();
	}
	public DatabaseRequestException(String message){
		super(message);
	}
	public DatabaseRequestException(Throwable e){
		super(e);
	}

}
