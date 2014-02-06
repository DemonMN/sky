package portal.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
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
	
	
	public static <T> T save(T object){
		PersistenceManagerFactory factory = getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		transaction.begin();
		try{
			return manager.makePersistent(object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBAccess.close(manager, transaction);
		}
		return object;
	}
	
	public static <T> T update(T object){
		PersistenceManagerFactory factory = getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		transaction.begin();
		try{
			return manager.detachCopy(object);
		} finally {
			DBAccess.close(manager, transaction);
		}
	}
	
	public static <T> void delete(Object key, Class<T> type){
		PersistenceManagerFactory factory = getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		transaction.begin();
		try{			
			manager.deletePersistent(manager.getObjectById(type, key));
		} finally {
			DBAccess.close(manager, transaction);
		}
	}
	
	public static  <T> T findObjectById(Object key, Class<T> type){
		PersistenceManagerFactory factory = getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		transaction.begin();
		try{
			return manager.getObjectById(type, key);
		}
		catch (Exception e){
			return null;
		} finally {
			DBAccess.close(manager, transaction);
		}
	}
	
	public static <T> List<T> findFilter(Class<T> type, String order, String filter){
		PersistenceManagerFactory factory = getFactory();
		PersistenceManager manager = factory.getPersistenceManager();
		Transaction transaction = manager.currentTransaction();
		transaction.begin();
		try{
			Query query = manager.newQuery(type);
			query.setFilter(filter);
			query.setOrdering(order);
			return new ArrayList<T>((List<T>)query.execute());
		} finally {
			DBAccess.close(manager, transaction);
		}
	}
}
