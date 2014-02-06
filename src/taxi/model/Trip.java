package taxi.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import portal.facebook.FacebookUser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Trip {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent(defaultFetchGroup = "true", dependentValue = "")
	private GeoPoint orgin;
	@Persistent(defaultFetchGroup = "true", dependentValue = "")
	private GeoPoint destination;
	@Persistent
	private Key user;
	@Persistent
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date date;
	
	@NotPersistent
	private FacebookUser fbUser;
	
	public Trip(Key user) {
		this.date = new Date();  
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public FacebookUser getFbUser() {
		return fbUser;
	}


	public void setFbUser(FacebookUser fbUser) {
		this.fbUser = fbUser;
	}

	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	
	public Key getUser() {
		return user;
	}
	
	public void setUser(Key user) {
		this.user = user;
	}
	
	public GeoPoint getOrgin() {
		return orgin;
	}
	public void setOrgin(GeoPoint orgin) {
		this.orgin = orgin;
	}
	public GeoPoint getDestination() {
		return destination;
	}
	public void setDestination(GeoPoint destination) {
		this.destination = destination;
	}	
}
