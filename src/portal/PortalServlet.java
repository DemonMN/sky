package portal;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import portal.db.DBAccess;
import portal.facebook.FacebookUser;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
@Api(name = "users")
public class PortalServlet {
	
	@ApiMethod(
		name = "user.get",
		path = "users/{id}"
	)
	public FacebookUser getUser(@Named("id") Long id) {
		FacebookUser user = new FacebookUser();
		PersistenceManagerFactory factory = DBAccess.getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		
		transaction.begin();
		try{
			user = manager.getObjectById(FacebookUser.class , id);
			return user;
		}
		finally{
			DBAccess.close(manager, transaction);
		}
	}
	
	@ApiMethod(
		name = "user.getAll"
	)
	public List<FacebookUser> getUsers() {
		List<FacebookUser> users = new ArrayList<FacebookUser>();
		PersistenceManagerFactory factory = DBAccess.getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		try{
			Extent<FacebookUser> result = manager.getExtent(FacebookUser.class);
			for (FacebookUser user : result) {
				users.add(user);
			}
			return users;
		}
		finally{
			DBAccess.close(manager, transaction);
		}
	}
	
}