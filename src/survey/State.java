package survey;

public class State {
	private Question question;
	private Answer answer;
	
	
	public State(Question question, Answer answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Answer getAnswer() {
		return answer;
	}
	
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return question.getStatement() + " " + getAnswer();
	}
}
