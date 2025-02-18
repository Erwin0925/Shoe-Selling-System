import Utility.GlobalScanner;
import Route.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MainMenu menu = new MainMenu();
        menu.getUserOption();
        GlobalScanner.getInstance().closeScanner();

    }
}