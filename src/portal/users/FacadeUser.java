package portal.users;

import java.util.List;

import portal.api.FacebookAPI;
import portal.db.DBAccess;
import portal.facebook.FacebookUser;
import util.MemcacheAPI;

import com.restfb.types.User;

import consistency.LoggerProxy;

public class FacadeUser {
	public FacebookUser getUser(String token){
		User user = MemcacheAPI.get(token, User.class);
		if (!MemcacheAPI.contains(token)){
			user = FacebookAPI.getClient(token).fetchObject("me", User.class);
		}
		FacebookUser appUser = getUserById(user.getId());
		if (appUser == null){
			appUser = new FacebookUser(user);
			appUser = DBAccess.save(appUser);
			LoggerProxy.newInstance(appUser);
		}
		MemcacheAPI.put(appUser.getId(), appUser);
		return appUser;
	}
	
	public FacebookUser getUserById(String id){
		FacebookUser appUser = MemcacheAPI.get(id, FacebookUser.class);
		if (appUser == null){
			List<FacebookUser> users = DBAccess.findFilter(FacebookUser.class, "key desc", " id == '" + id + "'");
			if (users != null && users.size() >  0){
				appUser = users.get(0);
				MemcacheAPI.put(appUser.getId(), appUser);
				
			}
		}
		return appUser;
	}
	
	public FacebookUser getUserByKey(Long key){
		FacebookUser appUser = MemcacheAPI.get(key, FacebookUser.class);
		if (appUser == null){
			appUser = DBAccess.findObjectById(key, FacebookUser.class);
			MemcacheAPI.put(key, appUser);
		}
		return appUser;
	}
	
	public void updateUser(FacebookUser user){
		MemcacheAPI.put(user.getId(), user);
	}
}
