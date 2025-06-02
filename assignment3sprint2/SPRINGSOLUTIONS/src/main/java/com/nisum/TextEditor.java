package com.nisum;

public class TextEditor {
    private SpellChecker spellChecker;

    // Constructor for constructor injection
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
        System.out.println("TextEditor: Constructor Injection successful");
    }

    // No-arg constructor for setter injection
    public TextEditor() {
        System.out.println("TextEditor: No-arg constructor for Setter Injection");
    }

    // Setter for setter injection
    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
        System.out.println("TextEditor: Setter Injection successful");
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }
}
