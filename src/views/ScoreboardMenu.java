package views;

import controllers.MainController;
import controllers.ScoreboardController;
import models.Result;
import models.enums.MainCommands;
import models.enums.ScoreboardCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ScoreboardMenu implements  AppMenu {
    private ScoreboardController controller = new ScoreboardController();
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
        } else if ((matcher = ScoreboardCommands.ShowScoreboard.isMatch(input)) != null) {
            String sortType = matcher.group("sort");
            result = controller.sort(sortType.trim());
            System.out.println(result);
        } else if ((matcher = ScoreboardCommands.Back.isMatch(input)) != null) {
            result = controller.back();
            System.out.println(result);
        }else {
            System.out.println("invalid command");
        }
    }
}
