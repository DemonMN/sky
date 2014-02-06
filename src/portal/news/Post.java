package portal.news;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

import portal.facebook.FacebookUser;

import com.google.appengine.api.datastore.Text;
@XmlRootElement(name = "post")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Post implements Serializable{
	private static final long serialVersionUID = -769199512993133256L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE)
	private Long id;
	
	private String title;
	
	private String userId;
	
	private String userName;
	
	private Text summary;

	private Long visited;

	@Persistent
	private Date insertDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String string) {
		this.userId = string;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Text getSummary() {
		return summary;
	}

	public void setSummary(Text summary) {
		this.summary = summary;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Long getVisited() {
		return visited;
	}

	public void setVisited(Long visited) {
		this.visited = visited;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public Set<Key> getCategory() {
//		return category;
//	}
//
//	public void setCategory(Set<Key> category) {
//		this.category = category;
//	}
	
	@Override
	public String toString() {
		return id + " : " + title;
	}
	
}
