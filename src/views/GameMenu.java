package views;

import controllers.GameController;
import models.App;
import models.Result;
import models.enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameController controller = new GameController();
    @Override
    public void check(Scanner scanner) {
        if (App.getGame().checkEnd()) {
            System.out.println(controller.gameOutro());
            return;
        }

        String input = scanner.nextLine();
        Matcher matcher;
        Result result;

        if ((matcher = GameCommands.ShowCurrentMenu.isMatch(input)) != null) {
            result = controller.showCurrentMenu();
            System.out.println(result);
        } else if ((matcher = GameCommands.Exit.isMatch(input)) != null) {
            result = controller.exit();
            System.out.println(result);
        }  else if ((matcher = GameCommands.ShowTurn.isMatch(input)) != null) {
            result = controller.showTurn();
            System.out.println(result);
        }   else if ((matcher = GameCommands.SkipTurn.isMatch(input)) != null) {
            result = controller.skipTurn();
            System.out.println(result);
        } else if ((matcher = GameCommands.ShowDetails.isMatch(input)) != null) {
            result = controller.showDetails();
            System.out.println(result);
        } else if ((matcher =GameCommands.ShowStats.isMatch(input)) != null) {
            String name = matcher.group("knight");
            String username = matcher.group("username");
            result = controller.showStats(name, username);
            System.out.println(result);
        } else if ((matcher = GameCommands.Attack.isMatch(input)) != null) {
            String name = matcher.group("knight");
            result = controller.Attack(name);
            System.out.println(result);
        } else if ((matcher = GameCommands.Skill.isMatch(input)) != null) {
            String skill = matcher.group("skill");
            String knight = matcher.group("knight");
            result = controller.skill(skill, knight);
            System.out.println(result);
        } else {
            System.out.println("invalid command");
        }
    }
}
