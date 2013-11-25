package portal.db;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.google.appengine.api.utils.SystemProperty;

public class DBAccess {
	public static PersistenceManagerFactory getFactory(){
		Map<String, String> properties = new HashMap<String, String>();
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url"));
		} else {
			properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url.dev"));
		}
		return JDOHelper.getPersistenceManagerFactory(properties, "transactions-optional");
	}
	
	public static void close(PersistenceManager manager, Transaction transaction){
		if (transaction != null && transaction.isActive()){
			transaction.commit();
		}
		if (manager != null && !manager.isClosed()){
			manager.close();
		}
	}
}
