package com.isa.aem.currency.calculator;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import javax.validation.Validation;

public class ValidationResultAssertion extends AbstractAssert<ValidationResultAssertion, ValidationResult> {

    public ValidationResultAssertion(ValidationResult actual, Class<?> selfType) {
        super(actual, selfType);
    }

    static ValidationResultAssertion assertThat(ValidationResult actual) {
        return new ValidationResultAssertion(actual, ValidationResultAssertion.class);
    }

    ValidationResultAssertion hasNoErrors() {
        isNotNull();
        if (actual.hasErrors()) {
            failWithMessage("Expected to have errors");
        }
        return this;
    }

    ValidationResultAssertion hasErrors() {
        isNotNull();
        if (!actual.hasErrors()) {
            failWithMessage("Expected to have errors");
        }
        return this;
    }

    ValidationResultAssertion hasErrorMessage(String... errorMessage) {
        isNotNull();
        Assertions.assertThat(actual.getAllErrors().contains(errorMessage));
        return this;
    }
}
