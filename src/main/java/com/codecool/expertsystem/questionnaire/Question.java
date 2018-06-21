package com.codecool.expertsystem.questionnaire;

/**
 * Class representing question.
 */
public class Question {

    private final String ID;
    private final String QUESTION;
    private final Answer ANSWER;

    public Question(String id, String question, Answer answer) {

        this.ID = id;
        this.ANSWER = answer;
        this.QUESTION = question;

    }


    public String getId() {

        return this.ID;

    }

    public String getQuestion() {

        return this.QUESTION;

    }

    public boolean getEvaluatedAnswer(String input) {

        return ANSWER.evaluateAnswerByInput(input);

    }
}
