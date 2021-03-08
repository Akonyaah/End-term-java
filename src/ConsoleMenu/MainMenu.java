package ConsoleMenu;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu implements Menu {
    @Override
    public void showMenu() {
        //showMenu() function for MainMenu, it shows main menu list
        System.out.println("----Welcome to Library Management System!----");
        System.out.println("Who are you?");
        System.out.println("1. Librarian");
        System.out.println("2. User");
        System.out.println("0. Exit");
    }

    public void MainMenu() throws IOException {
        //we create an instance of user menu and librarian menu to get access to their methods
        UserMenu userMenu=new UserMenu();
        LibrarianMenu librarianMenu=new LibrarianMenu();
        showMenu();
        Scanner choice = new Scanner(System.in);
        System.out.print("Please type number of command:");int i = choice.nextInt();
        if(i==1){
           librarianMenu.LibrarianStart();
        }
        else if(i==2){
            userMenu.UserStart();
        }
        else if(i==0){
            return;
        }
        else{
            System.out.println("Please enter appropriate number!");
            MainMenu();
        }
    }
}
