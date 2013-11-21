package portal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import portal.db.DBAccess;
import portal.facebook.FacebookUser;
import portal.model.User;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
@Api(name = "users")
public class PortalServlet {
	
	@ApiMethod(name = "create")
	public User createUser(User user) {
		
		return user;
	}

	@ApiMethod(
		name = "user.get",
		path = "users/{id}"
	)
	public FacebookUser getUser(@Named("id") Long id) {
		FacebookUser user = new FacebookUser();
		EntityManagerFactory emf = DBAccess.getFactory();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		user = em.find(FacebookUser.class, id);
		return user;
	}
	
	@ApiMethod(
		name = "user.getAll"
	)
	public List<FacebookUser> getUsers() {
		EntityManagerFactory emf = DBAccess.getFactory();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		try{
			return em.createQuery("SELECT g FROM FacebookUser g").getResultList();
		}
		finally{
			em.close();
		}
	}
	
}