package portal.news;

import java.util.HashMap;
import java.util.Map;

import portal.model.Article;
import util.Message;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@Api(name = "news")
public class NewsHandler {

	private static Map<Integer, Article> articles = new HashMap<Integer, Article>();
	
	@ApiMethod(name = "create")
	public Message createArticle(Article article) {
		articles.put(article.getId(), article);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		return new Message().addField("message", "success");
	}
	
	@ApiMethod(name = "all")
	public Map<Integer, Article> getAll() {
		return articles;
	}
}
