package portal.facebook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.utils.SystemProperty;


public class FacebookMessage {
	private String algorithm;
	private Long expires;
	private Long issued_at;
	private String oauth_token;
	private String user_id;
	
	public static String convertMessage(String data){
		String payload = data.split("[.]", 2)[1];
		payload = payload.replace("-", "+").replace("_", "/").trim();
		return new String(Base64.decodeBase64(payload));
	}
	


	public String getAlgorithm() {
		return algorithm;
	}


	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}


	public Long getExpires() {
		return expires;
	}


	public void setExpires(Long expires) {
		this.expires = expires;
	}


	public Long getIssued_at() {
		return issued_at;
	}


	public void setIssued_at(Long issued_at) {
		this.issued_at = issued_at;
	}


	public String getOauth_token() {
		return oauth_token;
	}


	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "FacebookMessage [algorithm=" + algorithm + ", expires="
				+ expires + ", issued_at=" + issued_at + ", oauth_token="
				+ oauth_token + ", user_id=" + user_id + "]";
	}

	

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
		String json = "{\"algorithm\":\"HMAC-SHA256\",\"expires\":1385060400,\"issued_at\":1385055104,\"oauth_token\":\"CAADWqmjshaIBAP4DYgRgNzFoBqk8P85xBCl96S4lD2uWMaRV0trLntYX1kOq91K8OHdUlFFQq5K0kOBRXiimrNWh8xFZB6XgtmHfBOzLwGAyVnoEUH7yibsaUoU6QLZAzRvQDxyTCJ1vjy88upWlkij7CkNLoqZB3GraDmDx3vROPflRHZCZAsMZCoRskY8VIZD\",\"registration\":{\"name\":\"Shagai Nyamdorj\",\"email\":\"shagai_1111\\u0040yahoo.co.nz\",\"location\":{\"name\":\"Johnston, Iowa\",\"id\":109414119077127},\"gender\":\"male\",\"birthday\":\"11\\/29\\/1988\",\"phone\":\"99835555\"},\"registration_metadata\":{\"fields\":\"[{\\\"name\\\":\\\"name\\\"},{\\\"name\\\":\\\"email\\\"},{\\\"name\\\":\\\"location\\\"},{\\\"name\\\":\\\"gender\\\"},{\\\"name\\\":\\\"birthday\\\"},{\\\"name\\\":\\\"phone\\\",\\\"description\\\":\\\"Phone Number\\\",\\\"type\\\":\\\"text\\\"}]\"},\"user\":{\"country\":\"us\",\"locale\":\"en_US\"},\"user_id\":\"592469092\"}";
		HashMap<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		map = mapper.readValue(json,  new TypeReference<HashMap<String, Object>>(){});
		
		System.out.println(map);
		FacebookUser user = mapper.convertValue(map.get("registration"), FacebookUser.class);
		user.setUserId(map.get("user_id").toString());
		System.out.println(user);
	}
}
