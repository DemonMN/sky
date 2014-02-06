package survey;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Answer {
	private Set<Choice> choices;
	
	public Answer(Choice... choices) {
		this.choices = new HashSet<Choice>(Arrays.asList(choices));
	}

	public Set<Choice> getChoices() {
		return choices;
	}

	public void setChoices(Set<Choice> choices) {
		this.choices = choices;
	}
	@Override
	public String toString() {
		return choices.toString();
	}
}
