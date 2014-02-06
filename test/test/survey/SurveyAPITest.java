package test.survey;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.api.SurveyAPI;
import survey.Choice;
import survey.Question;
import survey.Survey;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class SurveyAPITest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private SurveyAPI API = new SurveyAPI();
	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
	
	@Test
	public void testInsert1() {
		List<Survey> list = API.getAllSurvey();
		Survey survey = API.createSurvey("Test Survey");
		survey.addQuestion(
				new Question("What is your age?")
					.addChoice(new Choice("18-21"))
					.addChoice(new Choice("21-55"))
					.addChoice(new Choice("55 >")));
		
		
		survey.addQuestion(
				new Question("What is your gender1?")
					.addChoice(new Choice("male"))
					.addChoice(new Choice("female"))
					.addChoice(new Choice("non prefered")));
		survey = API.save(survey);
		System.err.println(survey);
		survey.addQuestion(
				new Question("What is your gender2?")
					.addChoice(new Choice("male"))
					.addChoice(new Choice("female"))
					.addChoice(new Choice("non prefered")));
		survey = API.save(survey);
		System.err.println(survey);
		survey.addQuestion(
				new Question("What is your gender3?")
					.addChoice(new Choice("male"))
					.addChoice(new Choice("female"))
					.addChoice(new Choice("non prefered")));
		survey = API.save(survey);
		survey.getQuestions().get(0).setStatement("updated statement");
		for (Question question : survey.getQuestions()) {
			System.err.println(question.getKey().getId());
			System.err.println(question);
		}
		
	}
	
	@Test
	public void testChoice() {
		List<Survey> list = API.getAllSurvey();
		Survey survey = API.createSurvey("Test Survey");
		survey.addQuestion(
				new Question("What is your age?")
					.addChoice(new Choice("18-21"))
					.addChoice(new Choice("21-55"))
					.addChoice(new Choice("55 >")));
		
		
		survey.addQuestion(
				new Question("What is your gender1?")
					.addChoice(new Choice("male"))
					.addChoice(new Choice("female"))
					.addChoice(new Choice("non prefered")));
		survey = API.save(survey);
		System.err.println(survey);
		Question question = survey.getQuestions().get(0);
		survey = API.addChoice(survey.getKey().getId(), question.getKey().getId(), new Choice("100+"));
		System.out.println(survey.getQuestions().get(0));
		survey = API.addChoice(survey.getKey().getId(), question.getKey().getId(), new Choice("100+"));
		System.out.println(survey.getQuestions().get(0));
		
	}
	
	
	@Test
	public void testDeleteChoice() {
		List<Survey> list = API.getAllSurvey();
		Survey survey = API.createSurvey("Test Survey");
		survey.addQuestion(
				new Question("What is your age?")
					.addChoice(new Choice("18-21"))
					.addChoice(new Choice("21-55"))
					.addChoice(new Choice("55 >")));
		
		
		survey.addQuestion(
				new Question("What is your gender1?")
					.addChoice(new Choice("male"))
					.addChoice(new Choice("female"))
					.addChoice(new Choice("non prefered")));
		survey = API.save(survey);
		System.err.println(survey);
		Question question = survey.getQuestions().get(0);
		survey = API.addChoice(survey.getKey().getId(), question.getKey().getId(), new Choice("100+"));
		System.out.println(survey.getQuestions().get(0));
		question = survey.getQuestions().get(0);
		survey = API.deleteChoice(survey.getKey().getId(), question.getKey().getId(), question.getChoices().get(3).getKey().getId());
		System.out.println(survey.getQuestions().get(0));
		
	}
	

	@Test
	public void testUpdateQuestion() {
		Survey survey = API.createSurvey("Test Survey");
		survey.addQuestion(
				new Question("What is your age?")
					.addChoice(new Choice("18-21"))
					.addChoice(new Choice("21-55"))
					.addChoice(new Choice("55 >")));
		survey = API.save(survey);
		System.err.println(survey);
		Question question = survey.getQuestions().get(0);
		question.setStatement("updated");
		survey = API.updateQuestion(survey.getKey().getId(), question.getKey().getId(), question);
		question = survey.getQuestions().get(0);
		assertEquals("updated", question.getStatement());
		
	}
}
