package com.codecool.expertsystem.questionnaire.value;

import java.util.List;

public class MultipleValue extends Value {

    private List<String> params;
    private boolean selectionType;


    public MultipleValue(List<String> params, boolean selectionType) {
        this.params = params;
        this.selectionType = selectionType;
    }

    @Override
    public List<String> getInputPattern() {
        return params;
    }

    @Override
    public boolean getSelectionType() {
        return selectionType;
    }
}
