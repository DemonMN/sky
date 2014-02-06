package portal.api;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import portal.db.DBAccess;
import portal.news.FacadeNews;
import portal.news.Post;
import portal.news.Visit;
import util.MemcacheAPI;
import util.Message;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@Api(name = "news")
public class NewsAPI {
//	private static final ChannelService channelService = ChannelServiceFactory.getChannelService();
	private static String channelKey;
	private static FacadeNews facadeNews = new FacadeNews();
	static {
//		channelKey = channelService.createChannel("news");
	}

	@ApiMethod(name = "news.update", httpMethod = "POST")
	public Message createArticle(Post post, @Named("token") String token) {
		facadeNews.updatePost(token, post);
		return new Message().addField("message", "success").addField("post", post);
	}

	@ApiMethod(name = "news.delete", httpMethod = "POST")
	public Message deleteNews(Post post, @Named("token") String token) {
		facadeNews.deletePost(token, post);
		return new Message().addField("message", "success").addField("post",post);
	}

	@ApiMethod(name = "all")
	public List<Post> getPosts() {
		return facadeNews.getPosts(0, 0);
	}

	@ApiMethod(name = "channel")
	public Message getChannelKey() {
		return new Message().addField("token", channelKey);
	}

	@ApiMethod(name = "news.visit")
	public Message visit(@Named("session") String session, @Named("post") Long post) {
		Set<Long> set = MemcacheAPI.get(session, Set.class);
		if (set == null) {
			set = new HashSet<Long>();
		}
		if (!set.contains(post)) {
			PersistenceManagerFactory factory = DBAccess.getFactory();
			PersistenceManager manager = factory.getPersistenceManager();
			Transaction transaction = manager.currentTransaction();
			transaction.begin();
			try {
				Post object = manager.getObjectById(Post.class, post);
				if (object != null) {
					transaction.commit();
					transaction.begin();
					manager.makePersistent(new Visit(new Date(), post.toString()));
					transaction.commit();
					transaction.begin();
					Query query = manager.newQuery(Visit.class);
					query.setFilter("post == " + post.longValue());
					List<Visit> result = (List<Visit>) query.execute();
					transaction.commit();
					transaction.begin();
					Long count = result != null ? result.size() : 0L;
					object.setVisited(count + 1);
					manager.makePersistent(object);
				}
			} finally {
				DBAccess.close(manager, transaction);
			}
		}
		set.add(post);
		MemcacheAPI.put(session, set);
		return new Message().addField("message", "success");
	}
}
