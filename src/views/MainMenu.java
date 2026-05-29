package views;

import controllers.MainController;
import models.App;
import models.Result;
import models.enums.MainCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private final MainController controller = new MainController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        Result result;

        if ((matcher = MainCommands.ShowCurrentMenu.isMatch(input)) != null) {
            result = controller.showCurrentMenu();
            System.out.println(result);
        } else if ((matcher = MainCommands.Exit.isMatch(input)) != null) {
            result = controller.exit();
            System.out.println(result);
        } else if ((matcher = MainCommands.ShowKnightsDetails.isMatch(input)) != null) {
            result = controller.showKnightsDetails();
            System.out.println(result);
        } else if ((matcher = MainCommands.ScoreBoard.isMatch(input)) != null) {
            result = controller.gotoScoreboard();
            System.out.println(result);
        } else if ((matcher = MainCommands.LogOut.isMatch(input)) != null) {
            result = controller.Logout();
            System.out.println(result);
        } else if ((matcher = MainCommands.Play.isMatch(input)) != null) {
            handlePlay(matcher, scanner);
        } else {
            System.out.println("invalid command");
        }
    }

    private void handlePlay(Matcher matcher, Scanner scanner) {
        String username = matcher.group("username");

        Result result = controller.Play(username.trim());
        System.out.println(result);

        if (result.isTrue()) {
            for (int i = 0; i < 4; i++) {
                boolean validName = false;

                while (!validName) {

                    result = controller.printKnightSelection(i, username);
                    System.out.println(result);

                    String name = scanner.nextLine();

                    result = controller.chooseKnight(App.getGame(), name.trim(), i);
                    System.out.println(result);
                    validName = result.isTrue();
                }
            }
            System.out.println(controller.playOutro());
        }
    }
}
