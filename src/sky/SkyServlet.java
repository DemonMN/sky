package sky;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import portal.db.DBAccess;
import portal.news.Visit;

@SuppressWarnings("serial")
public class SkyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		
		if (req.getParameter("post") != null){
			String post = req.getParameter("post");
			DBAccess.save(new Visit(new Date(), post));
//			new NewsAPI().visit(req.getSession().getId(), post);
//			PersistenceManagerFactory factory = DBAccess.getFactory();
//			PersistenceManager manager = factory.getPersistenceManager();
//			Transaction transaction = manager.currentTransaction();
//			try {
//				Query query = manager.newQuery(Visit.class);
//				query.setFilter("post == " + post.longValue());
//				List<Visit> result = (List<Visit>) query.execute();
//				resp.getWriter().write(result.size() + "");
//			} finally {
//				DBAccess.close(manager, transaction);
//			}
		}
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	}
}
