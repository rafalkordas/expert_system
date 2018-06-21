package com.codecool.expertsystem;

import com.codecool.expertsystem.iterators.QuestionIterator;
import com.codecool.expertsystem.parsers.FactParser;
import com.codecool.expertsystem.parsers.RuleParser;
import com.codecool.expertsystem.questionnaire.Answer;
import com.codecool.expertsystem.questionnaire.Fact;
import com.codecool.expertsystem.questionnaire.Question;
import com.codecool.expertsystem.repositories.FactRepository;
import com.codecool.expertsystem.repositories.RuleRepository;
import com.codecool.expertsystem.error.CarNotInXmlException;
import com.codecool.expertsystem.view.Display;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Class representing Expert System Provider.
 */
public class ESProvider {

    private final FactParser FACT_PARSER;
    private final RuleParser RULE_PARSER;
    private final RuleRepository RULE_REPOSITORY;
    private final FactRepository FACT_REPOSITORY;

    private Map<String, Boolean> answersMap = new HashMap<>();

    public ESProvider(FactParser factParser, RuleParser ruleParser) {

        this.RULE_PARSER = ruleParser;
        this.FACT_PARSER = factParser;
        this.RULE_REPOSITORY = RULE_PARSER.getRuleRepository();
        this.FACT_REPOSITORY = FACT_PARSER.getFactRepository();

    }

    public void resetAnswers() {

        answersMap = new HashMap<>();

    }

    public void collectAnswers() {

        Iterator<Question> questionIterator = RULE_REPOSITORY.getIterator();

        while (questionIterator.hasNext()) {

            Question nextQuestion = questionIterator.next();
            String input = Display.getStringInput(nextQuestion.getQuestion());
            boolean evaluatedAnswer = nextQuestion.getEvaluatedAnswer(input);
            answersMap.put(nextQuestion.getId(), evaluatedAnswer);

        }

    }

    public String evaluate() throws CarNotInXmlException {

        String cars = "";
        Iterator<Fact> factIterator = FACT_REPOSITORY.getIterator();

        while (factIterator.hasNext()) {

            int thisCar = 0;
            Fact factTemp = factIterator.next();

            for (Map.Entry<String, Boolean> entry : answersMap.entrySet()) {
                String id = entry.getKey();
                boolean value = entry.getValue();

                if (value == factTemp.getValueById(id)) {
                    thisCar++;
                }
            }

            if (thisCar == answersMap.size()) {
                cars += factTemp.getDescription() + "\n";
            }
        }

        if (cars.equals(""))
            throw new CarNotInXmlException("Not found car in our system.");
        return cars;

    }
}
