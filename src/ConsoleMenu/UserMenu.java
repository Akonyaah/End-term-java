package ConsoleMenu;

import Database.BookDB;
import Database.BookingDB;
import Database.UserDB;
import Models.Book;
import Models.User;

import java.io.IOException;
import java.util.Scanner;
//it looks like a user facade
public class UserMenu implements Menu {
    private String username = "";
    private String password = "";
    UserDB userDB = new UserDB();
    BookDB bookDB = new BookDB();
    BookingDB bookingDB = new BookingDB();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void showMenu() {
        //showMenu() for User menu list
        System.out.println("1. Book list");
        System.out.println("2.Borrow book");
        System.out.println("3.My books");
        System.out.println("4. Return book");
        System.out.println("5. User settings");
        System.out.println("0. Go to user menu");
    }

    public void UserSetting() throws IOException {
        System.out.print("To change your data please enter 'CHANGE' or to cancel 'CANCEL':");
        String input=scanner.next();
        if(input.toLowerCase().equals("change")){
            System.out.print("Your ID:");int id=scanner.nextInt();
            System.out.print("New Name:");String name=scanner.next();
            System.out.print("New Surname:");String surname=scanner.next();
            System.out.print("New Username:");String username=scanner.next();
            System.out.print("New Password:");String password=scanner.next();
            userDB.updateUser(id, name, surname, username, password);
            UserLogin();
        }
        else if(input.toLowerCase().equals("cancel")){
            LoggedUserMenu();
        }
        else{
            System.out.println("Please enter appropriate command!");
            UserSetting();
        }
    }

    public void UserLogin() throws IOException {
        System.out.println("----Login form----");
        System.out.print("Username:");
        username = scanner.next();
        System.out.print("Password:");
        password = scanner.next();
        User user = userDB.Login(username, password);
        if (user.getName() != null) {
            System.out.println("Hello," + user.getName()+" "+user.getSurname()+"!");
            System.out.println("What do you want to do?");
            LoggedUserMenu();
        } else {
            System.out.println("Incorrect Login or password!");
            UserLogin();
        }
    }

    public void UserStart() throws IOException {
        System.out.println("1.Login");
        System.out.println("2.Register");
        System.out.println("3.Go to main");
        Scanner scan = new Scanner(System.in);
        Integer ch = scan.nextInt();
        if (ch == 1) {
            UserLogin();
        } else if (ch == 2) {
            userDB.getAllUsers();
            System.out.println("Registration form!");
            System.out.print("Name:");
            String name = scan.next();
            System.out.print("Surname:");
            String surname = scan.next();
            System.out.print("Username:");
            String user_name = scan.next();
            System.out.print("Password:");
            String pass = scan.next();
            userDB.addUser(name, surname, user_name, pass);
            UserStart();
        }
        else if(ch==3){
            MainMenu mainMenu=new MainMenu();
            mainMenu.MainMenu();
        }
        else {
            System.out.println("Please enter appropriate number!");
            UserStart();
        }
    }

    public void LoggedUserMenu() throws IOException {
        User user = userDB.Login(username, password);
        showMenu();
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("----List of Library books----");
            bookDB.getAllBooks();
            LoggedUserMenu();
        } else if (choice == 2) {
            System.out.println("----Borrow a book----");
            bookDB.getAllBooks();
            System.out.println("Which book do you want borrow?");
            System.out.print("ISBN:");
            Integer isbn = scanner.nextInt();
            Book book = bookDB.getBookById(isbn);
            //When user borrow a book, we get count of this book bu id
            bookingDB.borrowBook(book.getIsbn(), book.getName(), user.getName(), user.getId());
            //and reduce the count of book
            bookDB.updateCount(book.getIsbn(), book.getCount() - 1);
            LoggedUserMenu();
        } else if (choice == 3) {
            System.out.println("----See your books----");
            bookingDB.userBooks(user.getId());
            LoggedUserMenu();
        } else if (choice == 4) {
            System.out.println("----Book returning----");
            bookingDB.userBooks(user.getId());
            System.out.println("Which book do you want to return?");
            System.out.print("Order ID:");Integer order=scanner.nextInt();
            System.out.print("ISBN:");
            Integer isbn = scanner.nextInt();
            Book book = bookDB.getBookById(isbn);
            bookingDB.returnBook(order);
            //on the contrary when we return borrowed book we increase the count
            bookDB.updateCount(book.getIsbn(), book.getCount() + 1);
            LoggedUserMenu();
        }
        else if(choice==5){
            System.out.println("----User Settings----");
            System.out.println("----User INFO----");
            System.out.println("UserId:"+user.getId());
            System.out.println("User:"+user.getName()+ " "+user.getSurname());
            System.out.println("Username:"+user.getUsername());
            System.out.println("Password:"+user.getPassword());
            UserSetting();

        }
        else if (choice == 0) {
            UserStart();
        }
        else {
            System.out.println("Please enter appropriate number!");
            LoggedUserMenu();
        }

    }
}
