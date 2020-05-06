package org.firstapp.POJO;

public class ClientTable {
    // Variables
    private  String name, firstName, city;

    /**
     * Default constructor
     */
    public ClientTable() {}

    /**
     * Constructor with params
     * @param name : client name
     * @param firstName : client first name
     * @param city : client city
     */
    public ClientTable(String name, String firstName, String city) {
        this.name = name;
        this.firstName = firstName;
        this.city = city;
    }

    // GETTERS & SETTERS

    /**
     * Get name
     * @return client name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name : name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get first name
     * @return client first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name
     * @param firstName : first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get city
     * @return client city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     * @param city : city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}
