package edu.gadalov.webservice.command;


public class CommandManager {
	public AbstractCommand getCommand(String command){
		switch(command){
		case "registration":
			return new RegistrationCommand();
		case "login":
			return new LoginCommand();
		case "logout":
			return new LogoutCommand();
		case "profile":
			return new ProfileCommand("profile");
		case "edit-profile":
			return new ProfileCommand("edit");
		case "save-profile":
			return new EditProfileCommand();
		case "change-password":
			return new ChangePasswordCommand();
		case "change-password-page":
			return new ChangePasswordPageCommand();
		case "admin-page":
			return new AdminPageCommand();
		case "addTrack":
			return new AddTrackCommand();
		case "tracks-page":	
			return new TracksPageCommand();
		case "pagination":
			return new PaginationCommand();
		case "buy-track":
			return new BuyTrackPageCommand();
		case "transaction":
			return new BuyTrackCommand();
		default:
				return new DefaultCommand();
		}	
	}
}
