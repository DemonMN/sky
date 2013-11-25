package sky;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portal.db.DBAccess;
import portal.facebook.FacebookMessage;
import portal.facebook.FacebookUser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class SkyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");

		PersistenceManagerFactory factory = DBAccess.getFactory();

		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		manager.currentTransaction();
		try{
			Extent<FacebookUser> result = manager.getExtent(FacebookUser.class);
			for (FacebookUser user : result) {
				resp.getWriter().println(user.toString());
			}
		}
		finally{
			DBAccess.close(manager, transaction);
		}
		resp.getWriter().println("Hello, world");
		resp.getWriter().flush();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String json = FacebookMessage.convertMessage(req.getParameter("signed_request"));
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.readValue(json,  new TypeReference<HashMap<String, Object>>(){});
		FacebookUser user = mapper.convertValue(map.get("registration"), FacebookUser.class);
		user.setUserId(map.get("user_id").toString());
		
		PersistenceManagerFactory factory = DBAccess.getFactory();

		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		
		transaction.begin();
		manager.makePersistent(user);
		transaction.commit();
		manager.close();

	}
}
