package account;

import java.util.List;

public class Account {
	private Long number;
	private List<Record> records;
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public void addEntry(Record entry) {
		records.add(entry);
	}
	
	public void removeEntry(Record entry) {
		records.remove(entry);
	}
}
