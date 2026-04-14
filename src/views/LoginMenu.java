package views;

import controllers.LoginController;
import models.App;
import models.Result;
import models.enums.LoginCommands;
import models.enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    final LoginController controller = new LoginController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        LoginCommands matched = null;
        for (LoginCommands command : LoginCommands.values()) {
            Matcher matcher = command.getMatcher(input);
            if (matcher != null && matcher.matches()) {
               matched = command;
               break;
            }
        }
        if (matched == null) {
            System.out.println("Invalid command!");
        } else  {
            switch(matched) {
                case ShowCurrentMenu:
                    System.out.println(App.getCurrentMenu());
                    break;
                case Exit:
                    App.setExit(true);
                    break;
                case Login:
                    Matcher loginMatcher = LoginCommands.Login.getMatcher(input);
                    Result result = controller.login(loginMatcher);
                    System.out.println(result);
                    break;


            }
        }

    }
}
