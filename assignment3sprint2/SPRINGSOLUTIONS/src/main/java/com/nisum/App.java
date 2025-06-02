package com.nisum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("----- Constructor Injection -----");
        TextEditor te1 = (TextEditor) context.getBean("textEditorConstructor");
        te1.spellCheck();

        System.out.println("----- Setter Injection -----");
        TextEditor te2 = (TextEditor) context.getBean("textEditorSetter");
        te2.spellCheck();
    }
}
