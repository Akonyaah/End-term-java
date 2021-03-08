package ConsoleMenu;

import Database.BookDB;
import Database.BookingDB;
import Database.LibraryDB;
import Database.UserDB;
import Models.Library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LibrarianMenu implements Menu {
    //It likes a Librarian Facade
    @Override
    //It is showMenu() function for Librarian. It shows librarian menu list
    public void showMenu() {
        System.out.println("1. User list");
        System.out.println("2. Book list");
        System.out.println("3. Add user");
        System.out.println("4. Delete user");
        System.out.println("5. Update user");
        System.out.println("6. Add book");
        System.out.println("7. Update book");
        System.out.println("8. Delete book");
        System.out.println("9. Show borrowed books");
        System.out.println("0. Go to main");
    }


    public void LibrarianMenu() throws IOException {
        showMenu();
        Scanner choice = new Scanner(System.in);
        UserDB userDB = new UserDB();
        BookDB bookDB = new BookDB();
        //We ask user to enter number to know what he wants to do
        System.out.print("Please type the command num:");int ch = choice.nextInt();
        //If user have chose 1 the function shows all users
        if (ch == 1) {
            System.out.println("----List of Library users----");
            userDB.getAllUsers();
            //each time, we call the LibrarianMenu () method to not exit the application
            LibrarianMenu();
            //if user selects 2nd command it shows list of all books
        }
        else if (ch == 2) {
            System.out.println("----List of library books----");
            bookDB.getAllBooks();
            LibrarianMenu();
        }
        //if user types 3, will appear prompt to user registration
        else if (ch == 3) {
            System.out.println("----Registration of a new user----");
            System.out.print("Name:");
            String name = choice.next();
            System.out.print("Surname:");
            String surname = choice.next();
            System.out.print("Username:");
            String user_name = choice.next();
            System.out.print("Password:");
            String pass = choice.next();
            //it sends these data to query
            userDB.addUser(name, surname, user_name, pass);
            LibrarianMenu();
        } else if (ch == 4) {
            //if choice is 4 first it shows all users and asks which user do you want to delete
            userDB.getAllUsers();
            System.out.print("Which user do you want to delete?");
            Integer user_id = choice.nextInt();
            userDB.deleteUser(user_id);
            LibrarianMenu();
        } else if (ch == 5) {
            //this case for updating user
            userDB.getAllUsers();
            System.out.println("----Update User----");
            System.out.print("Id:");
            Integer id = choice.nextInt();
            System.out.print("Name:");
            String name = choice.next();
            System.out.print("Surname:");
            String surname = choice.next();
            System.out.print("Username:");
            String user_name = choice.next();
            System.out.print("Password:");
            String pass = choice.next();
            userDB.updateUser(id, name, surname, user_name, pass);
            LibrarianMenu();
        } else if (ch == 6) {
            //for adding a new book
            System.out.println("----Add a new book----");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Name:");
            String name = br.readLine();
            System.out.print("Author:");
            String author = br.readLine();
            System.out.print("Pieces:");
            int count = choice.nextInt();
            bookDB.addBook(name, author, count);
            LibrarianMenu();
        } else if (ch == 7) {
            //for updating book
            System.out.println("----Update Book!----");
            bookDB.getAllBooks();
            System.out.print("ISBN:");
            Integer id = choice.nextInt();
            System.out.print("Name:");String name=choice.next();
            System.out.print("Author:");String author=choice.next();
            System.out.print("Pieces:");
            Integer count = choice.nextInt();
            bookDB.updateBook(id, count,name, author);
            LibrarianMenu();
        } else if (ch == 8) {
            //to delete book
            System.out.println("----Delete book----");
            bookDB.getAllBooks();
            System.out.print("ISBN:");
            Integer id = choice.nextInt();
            bookDB.deleteBook(id);
            LibrarianMenu();
        }
        else if(ch==9){
            //to show all borrowed books
            System.out.println("----Borrowed books----");
            BookingDB bookingDB=new BookingDB();
            bookingDB.getBorrowedBooks();
            LibrarianMenu();
        }
        else if (ch == 0) {
            //if user types 0 it calls of main menu which located in MainMenu class, in order don't exit from console
            MainMenu mainMenu = new MainMenu();
            mainMenu.MainMenu();
        } else {
            //if user types other nums it returns again LibrarianMenu()
            System.out.println("Please enter the appropriate number!");
            LibrarianMenu();
        }
    }

    public void LibrarianStart() throws IOException {
        //here we make prompt for librarian to login the system
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your username:");
        String username = scan.next();
        System.out.print("Please enter your password:");
        String password = scan.next();
        LibraryDB libraryDB = new LibraryDB();
        Library library = libraryDB.Login(username, password);
        //if he login is not success it returns the message about incorrectness of data
        if (library.getName()==null) {
            System.out.println("Incorrect username or password!");
            LibrarianStart();
        }
        else{
            //if it is success it calls the method LibrarianMenu()
            System.out.println("Hello, "+library.getName()+" "+library.getSurname()+"!");
            LibrarianMenu();
        }
    }
}
