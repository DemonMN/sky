package util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class Message {
	private Map<String, Object> data = new HashMap<String, Object>();

	public Message(){
		
	}
	public Message addField(String name, Object data){
		this.data.put(name, data);
		return this;
	}
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
