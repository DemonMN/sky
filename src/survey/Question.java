package survey;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class Question {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	private String statement;
	@Persistent(defaultFetchGroup = "true")
	private List<Choice> choices = new ArrayList<Choice>();
	@Persistent(defaultFetchGroup = "true")
	private List<Rule> rules = new ArrayList<Rule>();
	
	public Question(String statement) {
		this.statement = statement;
	}
	
	public Question addChoice(Choice choice) {
		if (choices == null){
			choices =  new ArrayList<Choice>();
		}
		choices.add(choice);
		return this;
	}
	public Question removeChoice(long choice) {
		Choice found = null;
		for (Choice temp : choices) 
			if (temp.getKey().getId() == choice){
				found = temp;
			}
		if (found != null){
			choices.remove(found);
		}
		return this;
	}
	public Question addRule(Rule rule) {
		rules.add(rule);
		return this;
	}
	
	public Choice getChoice(int index){
		return choices.get(index);
	}
	
	public String getStatement() {
		return statement;
	}
	
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("Question [statement=" + statement + "]\n");
		if (choices != null){
			for (Choice choice : choices) {
				buffer.append(choice);
				buffer.append("\n");
			}
		}
		return  buffer.toString();
	}
}
