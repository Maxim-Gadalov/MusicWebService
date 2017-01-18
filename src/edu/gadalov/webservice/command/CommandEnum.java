package edu.gadalov.webservice.command;

public enum CommandEnum {
	REGISTRATION{
		{
			this.command = new RegistrationCommand();
		}	
	},
	LOGIN{
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT{
		{
			this.command = new LogoutCommand();
		}
	},
	PROFILE{
		{
			this.command = new ProfileCommand("profile");
		}
	},
	EDIT_PROFILE{
		{
			this.command = new ProfileCommand("edit");
		}
	},
	SAVE_PROFILE{
		{
			this.command = new EditProfileCommand();
		}
	},
	CHANGE_PASSWORD{
		{
			this.command = new ChangePasswordCommand();
		}
	},
	CHANGE_PASSWORD_PAGE{
		{
			this.command = new ChangePasswordPageCommand();
		}
	},
	ADMIN_PAGE{
		{
			this.command = new AdminPageCommand();
		}
	},
	ADD_TRACK{
		{
			this.command = new AddTrackCommand();
		}
	},
	TRACKS_PAGE{
		{
			this.command = new TracksPageCommand();
		}
	},
	EMPTY_COMMAND{
		{
			this.command = new DefaultCommand();
		}
	};
	public AbstractCommand command;
	public AbstractCommand getCommand() {
		return command;
		}

}
