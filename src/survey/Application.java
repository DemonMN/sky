package survey;


public class Application {

	public static void main(String[] args) throws Exception {
		Survey survey = new Survey("Test");
		survey.addQuestion(
				new Question("What is your age?")
					.addChoice(new Choice("18-21"))
					.addChoice(new Choice("21-55"))
					.addChoice(new Choice("55 >")));
		
		
		survey.addQuestion(
				new Question("What is your gender?")
					.addChoice(new Choice("male"))
					.addChoice(new Choice("female"))
					.addChoice(new Choice("non prefered")));
		
		
		Session session = new Session(survey);
		
		session.start();
		
	}

}
