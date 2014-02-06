package survey;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RuleAny implements IRule {

	private Question question;
	private List<Choice> choices;
	public RuleAny(Question question, List<Choice> choices) {
		this.question = question;
		this.choices =  choices;
	}
	public RuleAny(Question question, Choice... choices) {
		this(question, Arrays.asList(choices));
	}
	@Override
	public boolean satisfy(Session session) {
		Set<Choice> selected = session.getState(question).getAnswer().getChoices();
		for (Choice choice : choices) 
			if (selected.contains(choice)){
				return true;
			}
		return false;
	}

}
