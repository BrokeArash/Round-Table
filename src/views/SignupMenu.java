package views;


import controllers.SignupController;
import models.App;
import models.Result;
import models.enums.Menu;
import models.enums.SignupCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu implements AppMenu {
    private final SignupController controller =  new SignupController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        SignupCommands matched = null;
        for (SignupCommands command : SignupCommands.values()) {
            Matcher matcher = command.getMatcher(input);
            if (matcher != null && matcher.matches()) {
                matched = command;
                break;
            }
        }

        if (matched == null ||
            matched == SignupCommands.CheckUsername ||
            matched == SignupCommands.CheckPass ||
            matched == SignupCommands.CheckEmail) {
            System.out.println("Invalid command!");
        } else {
            switch(matched) {
                case ShowCurrentMenu:
                    System.out.println(App.getCurrentMenu());
                    break;

                case Exit:
                    App.setExit(true);
                    break;

                case Register:
                    Matcher matcher = SignupCommands.Register.getMatcher(input);
                    Result result = controller.register(matcher);
                    System.out.println(result);
                    break;
                case Login:
                    result = controller.gotoLogin();
                    System.out.println(result);
                    break;

            }
        }
    }
}
