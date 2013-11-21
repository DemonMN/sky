package portal.crawler.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "command")
@XmlType(name = "command")
@XmlEnum
public enum Command {
	CREATE(0, "create"),
	START(1, "execute"),
	PAUSE(2, "pause"),
	STOP(3, "stop");
	@XmlElement(name = "id")
	private int id;
	@XmlElement(name = "description")
	private String description;
	private Command(int id, String description){
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
