package portal.facebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;
import com.restfb.types.User;

@XmlRootElement(name = "user")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class FacebookUser implements Serializable {
	private static final long serialVersionUID = -2269400161068704490L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	private String id;
	
	private String name;
	
	private User user;

	@Persistent
	private List<Contact> contacts;
	
	private UserType type;
	
	
	public FacebookUser(User user) {
		this.user = user;
		if (user != null) {
			id = user.getId();
		}
		if (user != null) {
			name = user.getName();
		}
	}

	public void addContact(Contact contact){
		if (contacts == null){
			contacts = new ArrayList<Contact>();
		}
		contacts.add(contact);
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}


	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("FacebookUser [name=");
		buffer.append(name);
		buffer.append(", type=");
		buffer.append(type);
		buffer.append("]");		
		return buffer.toString();
	}
}
