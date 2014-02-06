package test.news;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.db.DBAccess;
import portal.facebook.FacebookUser;
import portal.news.FacadeNews;
import portal.news.Post;
import portal.users.FacadeUser;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class FacadeNewsTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private final FacadeNews facadeNews = new FacadeNews();
	private final FacadeUser facadeUser = new FacadeUser();
	private final static String token = "CAACEdEose0cBANCOoxMuzxgg1DQfOTegH6CRFMRtQHrcL0y4FAiHOMnJmK9GEu2ZA9hTH9Novxn7ZB1ysNAaJi9yoiArs2cmuHCi4iJO2muwh46WbrWH3673xdXdjbXsnLqaJZC54lA0Mm9mCf6Flk7FrVfMUZBQzwJZCdwxp4FO45PHupNkhsW59ZBCEb9LsZD";
	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	// run this test twice to prove we're not leaking any state across tests
	private void doTest() {
		Post post = new Post();
		post.setUserId("345345");
		post.setTitle("Test title");
		FacebookUser user = facadeUser.getUser(token);
		facadeNews.createPost(user, post);
		DBAccess.save(post);
		Post dbInstance = DBAccess.findObjectById(post.getId(), Post.class);
		assertEquals(post.getId(), dbInstance.getId());
		assertEquals(post.getTitle(), dbInstance.getTitle());
		post.setTitle("Test update");
		DBAccess.update(post);
		dbInstance = DBAccess.findObjectById(post.getId(), Post.class);
		assertEquals(post.getTitle(), dbInstance.getTitle());
		DBAccess.delete(post.getId(), Post.class);
	}

	@Test
	public void testInsert1() {
		Post post = new Post();
		post.setUserId("345345");
		post.setTitle("Test title");
		FacebookUser user = facadeUser.getUser(token);
		facadeNews.createPost(user, post);
		
		assertEquals(facadeNews.getPosts(0, 0).size(), 1);
		
		post = new Post();
		post.setUserId("345345");
		post.setTitle("Test title 2");
		user = facadeUser.getUser(token);
		facadeNews.createPost(user, post);
		
		
		
		assertEquals(facadeNews.getPosts(0, 0).size(), 2);
	}

	@Test
	public void testUpdate() {
		Post post = new Post();
		post.setUserId("345345");
		post.setTitle("Test title");
		FacebookUser user = facadeUser.getUser(token);
		facadeNews.createPost(user, post);
		
		
		assertEquals(facadeNews.getPosts(0, 0).size(), 1);
		
		facadeNews.updatePost(token, post);
		
		
		assertEquals(facadeNews.getPosts(0, 0).size(), 2);
	}
	
	@Test
	public void testInsert2() {
		doTest();
	}

}
