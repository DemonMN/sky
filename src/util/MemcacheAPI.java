package util;

import java.util.Collections;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

public class MemcacheAPI {
	private static CacheFactory cacheFactory;
	private static Cache cache;

	static {
		try {
			cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}
	
	public static void put(Object key, Object value){
		System.out.println(key);
		System.out.println(value);
		cache.put(key, value);
	}
	
	public static void remove(Object key){
		cache.remove(key);
	}
	
	public static boolean contains(Object key){
		return cache.containsKey(key);
	}
	
	public static <T> T get(Object key, Class<T> nodeType){
		return (T) cache.get(key);
	}
}
