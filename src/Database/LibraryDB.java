package Database;

import Models.Library;


import java.sql.*;
//LibraryDB contains all functions related with table library
public class LibraryDB extends Database {
    public Library Login(String username, String pass) {
        String QUERY="Select id, name, surname, username from library where password=? and username=?";
        // using try-with-resources to avoid closing resources (boiler plate
        // code)
        Library library=new Library();
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, username);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                //it will works as session when librarian logins it saves his data to object Library
                library.setId(rs.getInt("id"));
                library.setName(rs.getString("name"));
                library.setSurname(rs.getString("surname"));
                library.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return library;
    }
}
