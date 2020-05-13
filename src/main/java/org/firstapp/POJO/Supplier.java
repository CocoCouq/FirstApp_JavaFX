package org.firstapp.POJO;

import org.firstapp.controllers.JDBC.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Supplier class
 *
 * @see Connection
 */
public class Supplier {
    // Variables
    private int satisf, numfou;
    private String nomfou, ruefou, posfou, vilfou, confou;

    // Connection package
    private final java.sql.Connection connection;

    /**
     * Default constructor
     */
    public Supplier() {
        this.connection = new Connection("papyrus").connection();
    }

    /**
     * Constructor for new Supplier
     * @param nomfou : Supplier name
     * @param ruefou : Supplier address
     * @param posfou : Supplier zip code
     * @param vilfou : Supplier city
     * @param confou : Contact name
     */
    public Supplier(String nomfou, String ruefou, String posfou, String vilfou, String confou) {
        this.nomfou = nomfou;
        this.ruefou = ruefou;
        this.posfou = posfou;
        this.vilfou = vilfou;
        this.confou = confou;
        this.connection =  new Connection("papyrus").connection();
    }

    /**
     * Function to get list of suppliers
     * @return List of all suppliers
     * @throws SQLException : Sql Exception
     */
    public ResultSet supplierList() throws SQLException {
        return this.connection.createStatement().executeQuery("SELECT * FROM fournis ORDER BY nomfou ASC");
    }

    /**
     * Function to get orders details for one supplier
     * @param supplier : String value of supplier id
     * @return Orders details for one supplier
     * @throws SQLException : Sql Exception
     */
    public ResultSet ordersDetails(String supplier) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM entcom JOIN fournis ON entcom.numfou = fournis.numfou WHERE fournis.nomfou = ?");
        statement.setString(1, supplier);
        return statement.executeQuery();
    }

    /**
     * Function to insert new values on table 'fournis'
     * @return True if supplier add
     */
    public boolean addSupplier() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO fournis(nomfou, ruefou, posfou, vilfou, confou, numfou) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, this.nomfou);
            statement.setString(2, this.ruefou);
            statement.setString(3, this.posfou);
            statement.setString(4, this.vilfou);
            statement.setString(5, this.confou);
            statement.setInt(6, getLastNum()+1);

            statement.execute();
            statement.close();
            this.connection.close();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Select supplier by id
     * @param id : Supplier primary key
     * @return Details for one supplier
     * @throws SQLException : Sql Exception
     */
    public ResultSet supplierByNum(String id) throws SQLException {
        return this.connection.createStatement().executeQuery("SELECT * FROM fournis WHERE numfou = " + id);
    }

    /**
     * Function to define last id who insert
     * @return The bigger primary key or 0 if haven't any supplier
     * @throws SQLException : Sql exception
     */
    public int getLastNum() throws SQLException {
        Statement stmt = this.connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT numfou FROM fournis ORDER BY numfou DESC LIMIT 1");
        if (result.next())
            return result.getInt("numfou");
        else
            return 0;
    }

    // GETTERS & SETTERS
    /**
     * Get Satisfaction
     * @return Satifaction integer value
     */
    public int getSatisf() {
        return satisf;
    }

    /**
     * Set new satisfaction
     * @param satisf : Satisfaction
     */
    public void setSatisf(int satisf) {
        this.satisf = satisf;
    }

    /**
     * Get supplier id
     * @return Primary key of table
     */
    public int getNumfou() {
        return numfou;
    }

    /**
     * Set new supplier's number
     * @param numfou : Primary key
     */
    public void setNumfou(int numfou) {
        this.numfou = numfou;
    }

    /**
     * Get supplier name
     * @return Supplier Name
     */
    public String getNomfou() {
        return nomfou;
    }

    /**
     * Set new supplier name
     * @param nomfou : Supplier name
     */
    public void setNomfou(String nomfou) {
        this.nomfou = nomfou;
    }

    /**
     * Get supplier address
     * @return Supplier's address
     */
    public String getRuefou() {
        return ruefou;
    }

    /**
     * Set new supplier address
     * @param ruefou : Supplier's address
     */
    public void setRuefou(String ruefou) {
        this.ruefou = ruefou;
    }

    /**
     * Get zip code
     * @return Zip code
     */
    public String getPosfou() {
        return posfou;
    }

    /**
     * Set new zip code
     * @param posfou : Supplier Zip Code
     */
    public void setPosfou(String posfou) {
        this.posfou = posfou;
    }

    /**
     * Get supplier city
     * @return City of supplier
     */
    public String getVilfou() {
        return vilfou;
    }

    /**
     * Set new city
     * @param vilfou : City
     */
    public void setVilfou(String vilfou) {
        this.vilfou = vilfou;
    }

    /**
     * Get supplier contact name
     * @return Name of contact
     */
    public String getConfou() {
        return confou;
    }

    /**
     * Set new contact name
     * @param confou : Contact name
     */
    public void setConfou(String confou) {
        this.confou = confou;
    }
}
