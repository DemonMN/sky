package portal.news;

import java.util.Date;
import java.util.List;

import portal.db.DBAccess;
import portal.facebook.FacebookUser;
import portal.users.FacadeUser;

public class FacadeNews {
	private static FacadeUser facadeUser = new FacadeUser();
	public Post createPost(FacebookUser user, Post post){
		post.setInsertDate(new Date());
		post.setUserId(user.getId());
		post.setUserName(user.getName());
		DBAccess.save(post);
		facadeUser.updateUser(user);
		return post;
	}

	public boolean isAllowedUser(String token, Post post){
		FacebookUser user = facadeUser.getUser(token);
		return isAllowedUser(user, post);
	}
	
	public boolean isAllowedUser(FacebookUser user, Post post){
		return true;
	}
	public Post deletePost(String token, Post post){
		FacebookUser user = facadeUser.getUser(token);
		post = DBAccess.findObjectById(post.getId(), Post.class);
		if (isAllowedUser(user, post)){
			DBAccess.delete(post.getId(), Post.class);
		}
		facadeUser.updateUser(user);
		return post;
	}
	
	public Post updatePost(String token, Post post){
		FacebookUser user = facadeUser.getUser(token);
		System.out.println(post.getId());
		if (post.getId() != null && isAllowedUser(user, post)){
			post.setUserId(user.getId());
			post.setUserName(user.getName());
			DBAccess.update(post);
		}
		if (post.getId() == null){
			createPost(user, post);
		}
		facadeUser.updateUser(user);
		return post;
	}
	
	public List<Post> getPosts(int limit, int offset){
		return DBAccess.findFilter(Post.class, "id desc", "");
	}
	
	public List<Post> getUserPosts(FacebookUser user){
		return DBAccess.findFilter(Post.class, "id desc", "userId =='" + user.getId() + "'");
	}
}
