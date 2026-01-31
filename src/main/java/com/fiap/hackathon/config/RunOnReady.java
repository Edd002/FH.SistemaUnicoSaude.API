package com.fiap.hackathon.config;

import com.fiap.hackathon.domain.city.CityServiceGateway;
import com.fiap.hackathon.domain.city.entity.City;
import com.fiap.hackathon.domain.loadtable.LoadTableServiceGateway;
import com.fiap.hackathon.domain.question.QuestionServiceGateway;
import com.fiap.hackathon.domain.question.entity.Question;
import com.fiap.hackathon.domain.questionnaire.QuestionnaireServiceGateway;
import com.fiap.hackathon.domain.questionnaire.entity.Questionnaire;
import com.fiap.hackathon.domain.questionnairequestion.QuestionnaireQuestionServiceGateway;
import com.fiap.hackathon.domain.questionnairequestion.entity.QuestionnaireQuestion;
import com.fiap.hackathon.domain.state.StateServiceGateway;
import com.fiap.hackathon.domain.state.entity.State;
import com.fiap.hackathon.domain.user.UserServiceGateway;
import com.fiap.hackathon.domain.user.entity.User;
import com.fiap.hackathon.global.util.JsonUtil;
import com.google.gson.reflect.TypeToken;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@Log
@Configuration
public class RunOnReady {

    @Value("${crypto.key}")
    private String cryptoKey;

    private static final String PATH_RESOURCE_STATE = "/runready/state.json";
    private static final String PATH_RESOURCE_CITY = "/runready/city.json";
    private static final String PATH_RESOURCE_USER = "/runready/user.json";
    private static final String PATH_RESOURCE_QUESTION = "/runready/question.json";
    private static final String PATH_RESOURCE_QUESTIONNAIRE = "/runready/questionnaire.json";
    private static final String PATH_RESOURCE_QUESTIONNAIRE_QUESTION = "/runready/questionnaire_question.json";

    private final LoadTableServiceGateway loadTableServiceGateway;
    private final StateServiceGateway stateServiceGateway;
    private final CityServiceGateway cityServiceGateway;
    private final UserServiceGateway userServiceGateway;
    private final QuestionServiceGateway questionServiceGateway;
    private final QuestionnaireServiceGateway questionnaireServiceGateway;
    private final QuestionnaireQuestionServiceGateway questionnaireQuestionServiceGateway;

    @Autowired
    public RunOnReady(LoadTableServiceGateway loadTableServiceGateway, StateServiceGateway stateServiceGateway, CityServiceGateway cityServiceGateway, UserServiceGateway userServiceGateway, QuestionServiceGateway questionServiceGateway, QuestionnaireServiceGateway questionnaireServiceGateway, QuestionnaireQuestionServiceGateway questionnaireQuestionServiceGateway) {
        this.loadTableServiceGateway = loadTableServiceGateway;
        this.stateServiceGateway = stateServiceGateway;
        this.cityServiceGateway = cityServiceGateway;
        this.userServiceGateway = userServiceGateway;
        this.questionServiceGateway = questionServiceGateway;
        this.questionnaireServiceGateway = questionnaireServiceGateway;
        this.questionnaireQuestionServiceGateway = questionnaireQuestionServiceGateway;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        List<State> stateList = JsonUtil.objectListFromJson("state", PATH_RESOURCE_STATE, new TypeToken<ArrayList<State>>() {
        }.getType());
        List<City> cityList = JsonUtil.objectListFromJson("city", PATH_RESOURCE_CITY, new TypeToken<ArrayList<City>>() {
        }.getType());
        List<User> userList = JsonUtil.objectListFromJson("user", PATH_RESOURCE_USER, new TypeToken<ArrayList<User>>() {
        }.getType());
        List<Question> questionList = JsonUtil.objectListFromJson("question", PATH_RESOURCE_QUESTION, new TypeToken<ArrayList<Question>>() {
        }.getType());
        List<Questionnaire> questionnaireList = JsonUtil.objectListFromJson("questionnaire", PATH_RESOURCE_QUESTIONNAIRE, new TypeToken<ArrayList<Questionnaire>>() {
        }.getType());
        List<QuestionnaireQuestion> questionnaireQuestionList = JsonUtil.objectListFromJson("questionnaireQuestion", PATH_RESOURCE_QUESTIONNAIRE_QUESTION, new TypeToken<ArrayList<QuestionnaireQuestion>>() {
        }.getType());

        if ((loadTableServiceGateway.isEntityLoadEnabled(State.class.getSimpleName()))) {
            stateList.forEach(this::createState);
            loadTableServiceGateway.create(State.class.getSimpleName());
        }
        if ((loadTableServiceGateway.isEntityLoadEnabled(City.class.getSimpleName()))) {
            cityList.forEach(this::createCity);
            loadTableServiceGateway.create(City.class.getSimpleName());
        }
        if ((loadTableServiceGateway.isEntityLoadEnabled(User.class.getSimpleName()))) {
            userList.forEach(this::createUser);
            loadTableServiceGateway.create(User.class.getSimpleName());
        }
        if ((loadTableServiceGateway.isEntityLoadEnabled(Question.class.getSimpleName()))) {
            questionList.forEach(this::createQuestion);
            loadTableServiceGateway.create(Question.class.getSimpleName());
        }
        if ((loadTableServiceGateway.isEntityLoadEnabled(Questionnaire.class.getSimpleName()))) {
            questionnaireList.forEach(this::createQuestionnaire);
            loadTableServiceGateway.create(Questionnaire.class.getSimpleName());
        }
        if ((loadTableServiceGateway.isEntityLoadEnabled(QuestionnaireQuestion.class.getSimpleName()))) {
            questionnaireQuestionList.forEach(this::createQuestionnaireQuestion);
            loadTableServiceGateway.create(QuestionnaireQuestion.class.getSimpleName());
        }
    }

    private void createState(State state) {
        try {
            stateServiceGateway.save(state);
        } catch (Exception exception) {
            log.severe(String.format("O estado de nome %s não pode ser cadastrado. Erro: %s", state.getName(), exception.getMessage()));
        }
    }

    private void createCity(City city) {
        try {
            cityServiceGateway.save(city);
        } catch (Exception exception) {
            log.severe(String.format("A cidade de nome %s não pode ser cadastrada. Erro: %s", city.getName(), exception.getMessage()));
        }
    }

    private void createUser(User user) {
        try {
            user.setEncryptedPassword(cryptoKey, user.getPassword());
            userServiceGateway.save(user);
        } catch (Exception exception) {
            log.severe(String.format("O usuário de nome %s não pode ser cadastrado. Erro: %s", user.getName(), exception.getMessage()));
        }
    }

    private void createQuestion(Question question) {
        try {
            questionServiceGateway.save(question);
        } catch (Exception exception) {
            log.severe(String.format("A questão de título %s não pode ser cadastrada. Erro: %s", question.getTitle(), exception.getMessage()));
        }
    }

    private void createQuestionnaire(Questionnaire questionnaire) {
        try {
            questionnaireServiceGateway.save(questionnaire);
        } catch (Exception exception) {
            log.severe(String.format("O questionário de nome %s não pode ser cadastrado. Erro: %s", questionnaire.getName(), exception.getMessage()));
        }
    }

    private void createQuestionnaireQuestion(QuestionnaireQuestion questionnaireQuestion) {
        try {
            questionnaireQuestionServiceGateway.save(questionnaireQuestion);
        } catch (Exception exception) {
            log.severe(String.format("A questão do questionário não pode ser cadastrada. Erro: %s", exception.getMessage()));
        }
    }
}