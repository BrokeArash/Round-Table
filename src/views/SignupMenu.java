package views;


import controllers.SignupController;
import models.App;
import models.Result;
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
                    Result result = controller.showCurrentMenu();
                    System.out.println(result);
                    break;
                case Exit:
                    result = controller.exit();
                    System.out.println(result);
                    break;
                case Signup:
                    Matcher matcher = SignupCommands.Signup.getMatcher(input);
                    result = controller.register(matcher);
                    System.out.println(result);
                    break;
                case Login:
                    Matcher loginMatcher = SignupCommands.Login.getMatcher(input);
                    result = controller.login(loginMatcher);
                    System.out.println(result);
                    break;

            }
        }
    }
}
