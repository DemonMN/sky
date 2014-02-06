package account;

import java.math.BigDecimal;
import java.util.Date;

public class Record {
	private BigDecimal amount;
	private Date date;
	private String description;
	
	public Record(Date date, BigDecimal amount, String description) {
		// TODO Auto-generated constructor stub
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
