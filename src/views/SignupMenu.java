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
        Matcher matcher;
        Result result;

        if ((matcher = SignupCommands.ShowCurrentMenu.isMatch(input)) != null) {
            result = controller.showCurrentMenu();
            System.out.println(result);
        } else if ((matcher = SignupCommands.Exit.isMatch(input)) != null) {
            result = controller.exit();
            System.out.println(result);
        } else if ((matcher = SignupCommands.Signup.isMatch(input)) != null) {
            String username = matcher.group("username");
            String password = matcher.group("password");
            result = controller.register(username.trim(), password.trim());
            System.out.println(result);
        } else  if ((matcher = SignupCommands.Login.isMatch(input)) != null) {
            String username = matcher.group("username");
            String password = matcher.group("password");
            result = controller.login(username.trim(), password.trim());
            System.out.println(result);
        } else {
            System.out.println("invalid command");
        }

    }
}
