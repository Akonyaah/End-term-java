package Database;

import java.sql.*;
//database is singleton we work with only one database and all functions related with him gets instance from here
public class Database {
    protected  String url = "jdbc:postgresql://localhost:5432/library?serverTimezone=UTC";
    protected  String user = "postgres";
    protected  String password ="imanbekova17";
    //this function handle exception which occurs during database transactions and queries
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
