package com.isa.aem.model;

public enum ActionType {
    CALCUALTOR("calculator"),
    GLOBAL("global"),
    LOCAL("local");

    private String label;

    private ActionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
