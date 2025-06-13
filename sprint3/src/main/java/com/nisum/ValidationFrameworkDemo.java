package com.nisum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Record to represent validation error
record ValidationError(String field, String message) {}

// Custom exception that holds all validation errors
class ValidationException extends Exception {
    private final List<ValidationError> errors;

    public ValidationException(List<ValidationError> errors) {
        super("Validation failed with " + errors.size() + " error(s)");
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ValidationException:\n");
        for (ValidationError error : errors) {
            sb.append("- ").append(error.field()).append(": ").append(error.message()).append("\n");
        }
        return sb.toString();
    }
}

// Reusable validator
class Validator<T> {
    private final List<ValidationRule<T>> rules = new ArrayList<>();

    public Validator<T> addRule(Predicate<T> condition, String field, String message) {
        rules.add(new ValidationRule<>(condition, field, message));
        return this;
    }

    public void validate(T target) throws ValidationException {
        List<ValidationError> errors = new ArrayList<>();
        for (ValidationRule<T> rule : rules) {
            if (!rule.condition.test(target)) {
                errors.add(new ValidationError(rule.field, rule.message));
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    // Inner class to represent a rule
    private static class ValidationRule<T> {
        Predicate<T> condition;
        String field;
        String message;

        ValidationRule(Predicate<T> condition, String field, String message) {
            this.condition = condition;
            this.field = field;
            this.message = message;
        }
    }
}

// Sample object to validate
record User(String name, int age, String email) {}

public class ValidationFrameworkDemo {

    public static void main(String[] args) {
        User user = new User("", -5, "invalid-email");

        Validator<User> userValidator = new Validator<User>()
                .addRule(u -> u.name() != null && !u.name().isBlank(), "name", "Name must not be blank")
                .addRule(u -> u.age() >= 0 && u.age() <= 120, "age", "Age must be between 0 and 120")
                .addRule(u -> u.email() != null && u.email().contains("@"), "email", "Email must be valid");

        try {
            userValidator.validate(user);
            System.out.println("User is valid ");
        } catch (ValidationException ex) {
            System.out.println(ex);
        }
    }
}

