package portal.api;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;

public class FacebookAPI {
	private static final String APP_ID = "236027393115554";
	private static final String APP_SECRET = "771f70a5e0e0185373393f375b255b31";
	private static final FacebookClient client = new DefaultFacebookClient();
	public static AccessToken getToken(){
		return client.obtainAppAccessToken(APP_ID, APP_SECRET);
	}
	
	public static FacebookClient getClient(String token){
		FacebookClient client = new DefaultFacebookClient(token);
		return client;
	}
}
