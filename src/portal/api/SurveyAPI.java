package portal.api;

import java.util.List;

import portal.db.DBAccess;
import survey.Choice;
import survey.Question;
import survey.Survey;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.Key;

@Api(name = "survey")
public class SurveyAPI {
	@ApiMethod(name = "all")
	public List<Survey> getAllSurvey() {
		return DBAccess.findFilter(Survey.class, "key desc", "");
	}
	
	@ApiMethod(name = "create")
	public Survey createSurvey(@Named("name")String name) {
		Survey survey = new Survey(name);
		survey = save(survey);
		return survey;
	}
	
	@ApiMethod(name = "save")
	public Survey save(Survey survey) {
		survey = DBAccess.save(survey);
		return survey;
	}
	
	@ApiMethod(name = "addQuestion")
	public Survey addQuestion(@Named("survey") Long id, Question question) {
		Survey survey = DBAccess.findObjectById(id, Survey.class);
		survey.addQuestion(question);
		survey = save(survey);
		return survey;
	}
	
	@ApiMethod(name = "updateQuestion")
	public Survey updateQuestion(@Named("survey")Long s, @Named("question") Long id, Question question) {
		Survey survey = getSurvey(s);
		if (question != null && question.getKey() != null) {
			Question old = survey.getQuestion(id);
			if (old != null){
				question.setKey(old.getKey());
				DBAccess.update(question);
			}
		}
		survey = getSurvey(s);
		return survey;
	}
	
	@ApiMethod(name = "addChoice")
	public Survey addChoice(@Named("id")Long s, @Named("question") Long id, Choice choice) {
		Survey survey = getSurvey(s);
		Question question = survey.getQuestion(id.longValue());
		if (question != null){
			question.addChoice(choice);
			question =  DBAccess.save(question);
			return getSurvey(s);
		}
		return survey;
	}
	
	@ApiMethod(name = "find")
	public Survey getSurvey(@Named("id")Long id) {
		Survey survey = DBAccess.findObjectById(id, Survey.class);
		return survey;
	}
	
	@ApiMethod(name = "deleteQuestion")
	public Question deleteQuestion(@Named("survey")Long s, @Named("id")Long id) {
		Survey survey = getSurvey(s);
		for (Question question : survey.getQuestions()) 
			if (id.equals(question.getKey().getId())){
				return question;
			}
		return null;
	}
	
	@ApiMethod(name = "deleteChoice")
	public Survey deleteChoice(@Named("id")Long s, @Named("question") Long id, @Named("choice")Long c) {
		Survey survey = getSurvey(s);
		Question question = survey.getQuestion(id.longValue());
		if (question != null){
			question.removeChoice(c);
			question =  DBAccess.save(question);
			return getSurvey(s);
		}
		return null;
	}
}
