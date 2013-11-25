package portal.facebook;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement(name = "user")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class FacebookUser {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE)
	private Long id;
	
	private String userId;
	
	private String name;
	
	private String email;
	
	private String gender;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date birthday;
	
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "FacebookUser [id=" + id + ", user_id=" + userId + ", name="
				+ name + ", email=" + email + ", gender=" + gender
				+ ", birthday=" + birthday + ", phone=" + phone + "]";
	}

}
