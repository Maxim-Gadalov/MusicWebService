package edu.gadalov.webservice.command;


/**Manager of command class.
 * @author Maxim Gadalov
 *
 */
public class CommandManager {
	/**Return command
	 * @param command - String command
	 * @return AbstractCommand
	 */
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
		case "add-comment":
			return new AddCommentCommand();
		case "edit-comment":
			return new EditCommentCommand();
		case "remove-track":
			return new DeleteTrackCommand();
		case "ban-user":
			return new BanUserCommand();
		case "unban-user":
			return new UnbanUserCommand();
		case "assign-discount":
			return new AssignDiscountCommand();
		case "edit-track":
			return new EditTrackPageCommand();
		case "save-track":
			return new EditTrackCommand();
		case "locale":
			return new LanguageCommand();
		default:
				return new DefaultCommand();
		}	
	}
}
