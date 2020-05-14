package org.firstapp.controllers.CRUD;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOTest {

    private final ClientDAO clientDAO = new ClientDAO();

    @Test
    public void testConnection() throws SQLException {
        org.firstapp.controllers.JDBC.Connection connectionClass = new org.firstapp.controllers.JDBC.Connection("hotel");
        Connection connection = connectionClass.connection();

        // Connection method return null if catch exception
        assertNotNull(connection);

        connection.close();
    }

    @Test
    public void testClientHotel() {
        ClientHotel client = new ClientHotel();
        client.setName("Name");
        client.setFirstName("FirstName");
        client.setCity("City");
        client.setAddress("Address");
        client.setId(23);

        assertEquals("Name", client.getName());
        assertEquals("FirstName", client.getFirstName());
        assertEquals("City", client.getCity());
        assertEquals("Address", client.getAddress());
        assertEquals(23, client.getId());
    }

    @Test
    public void testList() throws SQLException, IOException {
        // List
        List<ClientHotel> list = clientDAO.list();

        String filterWord = "^[a-zA-Zéèêëàáâîïç\\-]+$";
        String filterString = "^[a-zA-Z0-9éèêëàáâîïç\\-\\s]+$";
        String filterInt = "^[0-9]+$";

        // Check content list
        for (ClientHotel client : list) {
            assertNotNull(client.getName());
            assertNotNull(client.getFirstName());
            assertNotNull(client.getAddress());
            assertNotNull(client.getCity());

            assertTrue(client.getName().matches(filterWord));
            assertTrue(client.getFirstName().matches(filterWord));
            assertTrue(client.getCity().matches(filterString));
            assertTrue(String.valueOf(client.getId()).matches(filterInt));
        }
    }

    @Test
    public void testInsertAndFind() throws SQLException {

        ClientHotel client = new ClientHotel();
        client.setName("Name");
        client.setFirstName("FirstName");
        client.setCity("City");
        client.setAddress("Address");

        // Insertion
        clientDAO.insert(client);

        List<ClientHotel> clientList = clientDAO.list();

        // Recover last index
        int lastIndex = clientList.get(clientList.size() - 1).getId();


        ClientHotel clientHotel = clientDAO.find(lastIndex);

        assertEquals("Name", clientHotel.getName());
        assertEquals("FirstName", clientHotel.getFirstName());
        assertEquals("City", clientHotel.getCity());
        assertEquals("Address", clientHotel.getAddress());
    }

}