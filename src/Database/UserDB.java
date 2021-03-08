package Database;

import Models.User;

import java.sql.*;

public class UserDB extends Database {
    //The class UserDB contains all function related with users table
    //CRUD for users table
    public void deleteUser(int uid){
        String query="Delete from users where id=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, uid);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The User successfully deleted!");
    }

    public void updateUser(int uid, String name, String surname, String username, String pass){
        String query="Update users set name=?, surname=?,  username =?,  password=? where id=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, pass);
            preparedStatement.setInt(5, uid);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The user was successfully updated!");
    }

    public User Login(String username, String pass) {
        String QUERY="Select id, name, surname, username, password from users where password=? and username=?";
        // using try-with-resources to avoid closing resources (boiler plate
        // code)
        User nuser=new User();
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
              nuser.setId(rs.getInt("id"));
              nuser.setName(rs.getString("name"));
              nuser.setSurname(rs.getString("surname"));
              nuser.setUsername(rs.getString("username"));
              nuser.setPassword(rs.getString("password"));
            }}

        catch (SQLException e) {
            printSQLException(e);
        }
        return nuser;
    }

    public  void addUser(String name, String surname, String username, String pass){
        String query="Insert into users (name, surname, username, password)values(?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, pass);


            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The user was successfully added!");
    }

    public void getAllUsers(){
        String query="select id, name, surname, username from users";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String username = rs.getString("username");
                System.out.println(id + "." + name + " " + surname + "-" + username);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


}
