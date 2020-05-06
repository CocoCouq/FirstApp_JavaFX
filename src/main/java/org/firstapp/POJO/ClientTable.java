package org.firstapp.POJO;

public class ClientTable {
    // Variables
    private  String name;
    private  String firstName;
    private  String city;

    // Default constructor
    public ClientTable() {}

    // Constructor with params
    public ClientTable(String name, String firstName, String city) {
        this.name = name;
        this.firstName = firstName;
        this.city = city;
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
