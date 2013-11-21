package portal.crawler.util;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "status")
@XmlEnum
public enum Status {
	RUNNING, IDLE, STOPPED;
}
