package Database;

import java.sql.*;
import java.text.SimpleDateFormat;

public class BookingDB extends Database{
    //CRUD for booking table
    public void returnBook(int uid){
        String query="Delete from booking where id=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, uid);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The Book was successfully returned!");
    }

    public  void borrowBook(int book_id, String book_name, String user_name, int user_id){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String query="Insert into booking (book_id, book_name, user_name, user_id, date)values(?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, book_id);
            preparedStatement.setString(2, book_name);
            preparedStatement.setString(3, user_name);
            preparedStatement.setInt(4, user_id);
            preparedStatement.setString(5, formatter.format(date));
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("The book was successfully borrowed!");
    }

    public void userBooks(int uid){
        String query="select id, date, book_name, book_id from booking where user_id=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, uid);
            // Step 3: Execute the query or update query
             ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("book_name");
                int book_id=rs.getInt("book_id");
                String date = rs.getString("date");
                System.out.println("Order ID:"+id + "==="+"ISBN:"+book_id+"===" + name + "===" + date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void getBorrowedBooks(){
        String query="select book_id, date, book_name, user_name from booking";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("book_name");
                String username=rs.getString("user_name");
                String date = rs.getString("date");
                System.out.println("ISBN:"+id + "===" + name + "==="+username+"===" + date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
