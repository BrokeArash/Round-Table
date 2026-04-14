package views;

import controllers.MainController;
import models.enums.MainCommands;
import models.enums.SignupCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private MainController controller = new MainController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        MainCommands matched = null;
        for (MainCommands command : MainCommands.values()) {
            Matcher matcher = command.getMatcher(input);
            if (matcher != null && matcher.matches()) {
                matched = command;
                break;
            }
        }

        if (matched == null) {
            System.out.println("Invalid command!");
        } else {
            switch (matched) {
        }
    }
}
