package portal.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public class DBAccess {
	public static EntityManagerFactory getFactory(){
		Map<String, String> properties = new HashMap<String, String>();
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url"));
		} else {
			properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url.dev"));
		}
		
		return Persistence.createEntityManagerFactory("Demo", properties);
	}
}
