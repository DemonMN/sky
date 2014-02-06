package portal.facebook;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Contact {

	private String information;

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
