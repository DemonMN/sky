package survey;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.appengine.api.datastore.Key;

@JsonRootName("survey")
@PersistenceCapable
public class Survey {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	
	@Persistent(defaultFetchGroup = "true", dependentValue = "")
	private List<Question> questions = new ArrayList<Question>();
	
	private String name;
	
	private Question last;
	private Question start;
	
	
	public Survey(String name) {
		this.name = name;
	}
	public void addQuestion(Question question){
		if (last != null){
			Rule rule = new Rule(question.getKey());
			last.addRule(rule);
		} else {
			start = question;
		}
		questions.add(question);
		last = question;
	}
	
	public void updateQuestion(Question question){
		Question old = null; 
		for (Question temp : questions) 
			if (temp.getKey().equals(question.getKey())){
				old = temp;
			}
		if (old != null){
			questions.remove(old);
			question.setKey(null);
			addQuestion(question);
		}
	}
	
	public Question getQuestion(long id){
		for (Question question : questions) 
			if (question.getKey().getId() == id){
				return question;
			}
		return null;
	}
	
	public Question getStart() {
		return start;
	}
	public void setStart(Question start) {
		this.start = start;
	}
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Question getLast() {
		return last;
	}
	public void setLast(Question last) {
		this.last = last;
	}
}
