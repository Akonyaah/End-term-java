package Database;

import Models.Book;


import java.sql.*;

public class BookDB extends Database {
    //CRUD for book table
    public void getAllBooks(){
        //when we call this function it shows only exist books or where count is greater than 0
        String query="select isbn, name, author, count from book where count>0";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            //we use executeQuery() method to get data from data base
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("isbn");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int count = rs.getInt("count");
                System.out.println(id + "." + name + " - " + author + " - " + count);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public  void addBook( String name, String author, int count){
        String query="Insert into book (name, author, count) values(?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, count);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            //we use executeUpdate() just to update database
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The book was successfully added!");
    }
    public void updateBook(int id, int count, String name, String author){
        String query="Update book set count=?, name=? author=? where isbn=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, id);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The book was successfully updated!");
    }
    public void updateCount(int isbn, int count){
        //this function to update count of book when user borrows or returns book
        String query="Update book set count=? where isbn=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, isbn);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteBook(int uid){
        String query="Delete from book where isbn=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, uid);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The Book was successfully deleted!");
    }
    public Book getBookById(int id){
        String QUERY="Select * from book where isbn=?";
        // using try-with-resources to avoid closing resources (boiler plate
        // code)
        Book book=new Book();
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, id);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                book.setIsbn(rs.getInt("isbn"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setCount(rs.getInt("count"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return book;
    }

}
