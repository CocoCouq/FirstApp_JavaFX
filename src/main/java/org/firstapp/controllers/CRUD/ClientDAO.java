package org.firstapp.controllers.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to add Crud on client table
 * @see HotelController
 * @see ClientHotel
 */
public class ClientDAO {
    private final Connection connection;

    /**
     * Default constructor
     */
    public ClientDAO() {
        org.firstapp.controllers.JDBC.Connection connectionClass = new org.firstapp.controllers.JDBC.Connection("hotel");
        this.connection = connectionClass.connection();
    }

    /**
     * Function to insert one ClientHotel
     * @param client : ClientHotel
     */
    public void insert(ClientHotel client) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO CLIENT(cli_nom, cli_prenom, cli_adresse, cli_ville) VALUES (?,?,?,?)");
            statement.setString(1, client.getName());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getAddress());
            statement.setString(4, client.getCity());

            statement.execute();
            statement.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to update ClientHotel Values
     * @param client : ClientHotel
     */
    public void update(ClientHotel client) {

        try {
            PreparedStatement statement = this.connection.prepareStatement("UPDATE CLIENT SET cli_nom = ?, cli_prenom = ?, cli_adresse = ?, cli_ville = ? WHERE cli_id = ?");

            statement.setString(1, client.getName());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getAddress());
            statement.setString(4, client.getCity());
            statement.setInt(5, client.getId());

            statement.execute();

            statement.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to delete one ClientHotel
     * @param client : ClientHotel
     */
    public void delete(ClientHotel client) {
        try {
            PreparedStatement statementParent = this.connection.prepareStatement("DELETE FROM reservation WHERE res_cli_id = ?");
            statementParent.setInt(1, client.getId());
            statementParent.execute();

            statementParent.close();

            PreparedStatement statement = this.connection.prepareStatement("DELETE FROM CLIENT WHERE cli_id = ?");
            statement.setInt(1, client.getId());
            statement.execute();

            statement.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param id : Client id
     * @return The client find by id
     */
    public ClientHotel find(int id) {
        ClientHotel client = new ClientHotel();

        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM CLIENT WHERE cli_id = ?");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client.setId(resultSet.getInt("cli_id"));
                client.setName(resultSet.getString("cli_nom"));
                client.setFirstName(resultSet.getString("cli_prenom"));
                client.setCity(resultSet.getString("cli_ville"));
                client.setAddress(resultSet.getString("cli_adresse"));
            }

            resultSet.close();
            statement.close();

            return client;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Function to get all clients
     * @return List of client
     */
    public List<ClientHotel> list() {
        List<ClientHotel> result = new ArrayList<>();

        try {
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT");

            while (resultSet.next()) {
                ClientHotel client = new ClientHotel();
                client.setId(resultSet.getInt("cli_id"));
                client.setName(resultSet.getString("cli_nom"));
                client.setFirstName(resultSet.getString("cli_prenom"));
                client.setAddress(resultSet.getString("cli_adresse"));
                client.setCity(resultSet.getString("cli_ville"));

                result.add(client);
            }

            statement.close();
            resultSet.close();

            return result;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
