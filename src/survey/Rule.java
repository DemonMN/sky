package survey;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Rule implements IRule {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	public Set<IRule> rules = new HashSet<IRule>();
	
	public Key question;

	public Rule(Key question) {
		this.question = question;
	}

	public void addRule(IRule rule){
		rules.add(rule);
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Set<IRule> getRules() {
		return rules;
	}

	public void setRules(Set<IRule> rules) {
		this.rules = rules;
	}

	public Key getQuestion() {
		return question;
	}

	public void setQuestion(Key question) {
		this.question = question;
	}

	@Override
	public boolean satisfy(Session session) {
		boolean satisfy = true;
		for (IRule rule : rules) {
			satisfy = satisfy && rule.satisfy(session);
		}
		return satisfy;
	}
	
	
}
