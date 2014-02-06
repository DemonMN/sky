package portal.api;

import portal.db.DBAccess;
import portal.facebook.Contact;
import portal.facebook.FacebookUser;
import portal.users.FacadeUser;
import util.MemcacheAPI;
import util.Message;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name = "users")
public class UserAPI {
	private static FacadeUser facade = new FacadeUser();
	@ApiMethod(name = "user.get")
	public FacebookUser getUser(@Named("token") String token) {
		FacebookUser user = facade.getUser(token);
		return user;
	}

	@ApiMethod(name = "user.logout")
	public Message logout(@Named("token") String token) {
		FacebookUser user = facade.getUser(token);
		if (user != null) {
			MemcacheAPI.remove(user.getId());
			MemcacheAPI.remove(token);
		}
		return new Message().addField("message", "success");
	}

	@ApiMethod(name = "user.login")
	public Message login(@Named("token") String token) {
		FacebookUser appUser = facade.getUser(token);
		return new Message().addField("message", "success").addField("me", appUser);
	}
	
	@ApiMethod(name = "user.add.contact")
	public FacebookUser addContact(@Named("token") String token, Contact contact) {
		FacebookUser appUser = facade.getUser(token);
		appUser.addContact(contact);
		appUser = DBAccess.update(appUser);
		return appUser;
	}
}