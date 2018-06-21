package com.codecool.expertsystem.questionnaire;

import com.codecool.expertsystem.questionnaire.value.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer {

    private List<Value> valuesList = new ArrayList<>();

    public boolean evaluateAnswerByInput(String input) {

        List<String> values = new ArrayList<>();
        values = Arrays.asList(input.split(","));

        for (Value value : valuesList) {

            for (String param : values) {

                if (value.getInputPattern().contains(param)) {
                    return value.getSelectionType();
                }
            }
        }
        return false;

    }

    public void addValue(Value value) {

        valuesList.add(value);

    }
}
