package com.isa.aem.currency.calculator;

import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ComplexConsoleCalculatorValidator {

    private static final String INCORRECT_ANSWER = "rate is invalid";
    private static final String NULL_ARGUMENT = "argument cannot be null";

    ValidationResult validationResultOfMostRecentRate(CurrencyRepository repository, String name, Double rate) {
        if (repository == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }

        List<String> validationErrors = new ArrayList<>();

        if(correctNameIncorrectRate(repository, name, rate)) {
            validationErrors.add(INCORRECT_ANSWER);
        }
        if(incorrectNameCorrectRate(repository, name, rate)) {
            validationErrors.add(INCORRECT_ANSWER);
        }
        return new ValidationResult(validationErrors);
    }

    ValidationResult validateDateOf(CurrencyRepository repository,
                                    String name,
                                    LocalDate date,
                                    Double result) {
        if (repository ==null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }

        if (name == null ){
            throw new NoSuchElementException();
        }

        List<String> validationErrors = new ArrayList<>();

        if (correctNameAndDateIncorrectResult(repository, name, date, result)){
            validationErrors.add(INCORRECT_ANSWER);
        }

        if (incorrectNameOrDateAndCorrectResult(repository, name, date, result)) {
            validationErrors.add(INCORRECT_ANSWER);
        }
        return new ValidationResult(validationErrors);
    }

    private Boolean incorrectNameOrDateAndCorrectResult(CurrencyRepository repository,
                                                        String name,
                                                        LocalDate date,
                                                        Double result) {
        return repository.getRateOfGivenDate(name, date) != result;
    }

    private Boolean correctNameAndDateIncorrectResult(CurrencyRepository repository,
                                                      String name,
                                                      LocalDate date,
                                                      Double result) {
        return repository.getRateOfGivenDate(name, date) != result;
    }

    private Boolean correctNameIncorrectRate(CurrencyRepository repository,
                                             String name,
                                             Double result) {
        return repository.getMostRecentExchangedRateForChosenCurrencyName(name) == result;
    }
    private Boolean incorrectNameCorrectRate(CurrencyRepository repository,
                                             String name,
                                             Double result) {
        return repository.getMostRecentExchangedRateForChosenCurrencyName(name) != result;
    }
}
