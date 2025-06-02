package com.nisum;

public class Address {
    private String city;
    private String state;
    private String country;

    public void setCity(String city) {
        System.out.println("Setter Injection successful for city");
        this.city = city;
    }

    public void setState(String state) {
        System.out.println("Setter Injection successful for state");
        this.state = state;
    }

    public void setCountry(String country) {
        System.out.println("Setter Injection successful for country");
        this.country = country;
    }

    public void display() {
        System.out.println(city + ", " + state + ", " + country);
    }
}
