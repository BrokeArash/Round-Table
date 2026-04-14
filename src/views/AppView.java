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

            /* go to signup menu */
            if (App.isGotoSignup()) {
                App.setGotoSignup(false);
                App.setCurrentMenu(Menu.SignupMenu);
            }
            /* is login successful */
            else if (App.isLoginSuccessful()) {
                App.setLoginSuccessful(false);
                App.setCurrentMenu(Menu.MainMenu);
            }

            /* is go to game successful */
            else if (App.isGotoGame()) {
                App.setGotoGame(false);
                App.setCurrentMenu(Menu.GameMenu);
            }


        }
        while (!App.isExit());
    }
}
