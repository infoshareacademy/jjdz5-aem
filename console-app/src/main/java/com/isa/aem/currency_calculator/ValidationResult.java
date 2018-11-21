package com.isa.aem.currency.calculator;

import java.util.Collections;
import java.util.List;

public class ValidationResult {
    private final List<String> errors;

    public ValidationResult(List<String> errors) {
        this.errors = errors;
    }

    boolean hasErrors() {
        return errors.size() > 0;
    }

    List<String> getAllErrors() {
        return Collections.unmodifiableList(errors);
    }
}
