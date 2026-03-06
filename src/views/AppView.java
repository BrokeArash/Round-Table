package views;

import models.App;
import models.enums.Menu;

import java.util.Scanner;

public class AppView {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        App.setCurrentMenu(Menu.SignupMenu);

        do {
            App.getCurrentMenu().checkCommand(scanner);

            /* go to login menu */
            if (App.isGotoLogin()) {
                App.setGotoLogin(false);
                App.setCurrentMenu(Menu.LoginMenu);
            }
            /* back to signup menu */
            else if (App.isBack()) {
                App.setBack(false);
                App.setCurrentMenu(Menu.SignupMenu);
            }




        }
        while (!App.isExit());
    }
}
