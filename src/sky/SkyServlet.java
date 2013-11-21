package sky;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

		EntityManagerFactory emf = DBAccess.getFactory();

		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<FacebookUser> result = em.createQuery("SELECT g FROM FacebookUser g").getResultList();
		for (FacebookUser user : result) {
			resp.getWriter().println(user.toString());
		}
		em.getTransaction().commit();
		em.close();
		resp.getWriter().println("Hello, world");
		resp.getWriter().flush();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String json = FacebookMessage.convertMessage(req.getParameter("signed_request"));
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.readValue(json,  new TypeReference<HashMap<String, Object>>(){});
		FacebookUser user = mapper.convertValue(map.get("registration"), FacebookUser.class);
		user.setUserId(map.get("user_id").toString());
		
		EntityManagerFactory emf = DBAccess.getFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();

	}
}
