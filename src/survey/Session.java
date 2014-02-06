package survey;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Session {
	private Map<Question, State> states = new LinkedHashMap<Question, State>();
	private Survey survey;	
	private Question current;
	private BufferedReader reader;
	public Session(Survey survey) {
		this.survey = survey;
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public void addState(State state) {
		states.put(state.getQuestion() , state);
	}
	
	public State getState(Question question){
		return states.get(question);
	}
	
	public void start() {
		current = survey.getStart();
	}
	
}
