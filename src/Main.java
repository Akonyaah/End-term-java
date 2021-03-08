
import ConsoleMenu.MainMenu;

import java.io.IOException;

public class Main {
//in the main class or main activity we just call method from mainmenu to start our application
    //The user sees and works with app by instructions but he won't know how it works.
// it looks like a facade
    public static void main(String[] args) throws IOException {
        MainMenu mainMenu=new MainMenu();
        mainMenu.MainMenu();
    }
}
