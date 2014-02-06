package account.command;

import java.math.BigDecimal;
import java.util.Date;

import account.Account;
import account.Record;

public class DepositCommand implements Command {
	private BigDecimal amount;
	private Account account;
	private Record entry;
	public DepositCommand(BigDecimal amount, Account account) {
		this.amount = amount;
		this.account = account;
	}

	public void execute() {
		this.entry = new Record(new Date(), amount, "deposit");
		account.addEntry(entry);
	}

	public void unexecute() {
		entry.setDescription("undo deposit");
		account.removeEntry(entry);
	}

}