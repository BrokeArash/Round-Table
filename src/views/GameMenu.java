package views;

import controllers.GameController;
import models.App;
import models.Knight;
import models.Result;
import models.enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameController controller = new GameController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        GameCommands matched = null;
        for (GameCommands command : GameCommands.values()) {
            Matcher matcher = command.getMatcher(input);
            if (matcher != null && matcher.matches()) {
                matched = command;
                break;
            }
        }

        if (matched == null) {
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
                case Attack:
                    Matcher matcher = GameCommands.Attack.getMatcher(input);
                    result = controller.Attack(matcher);
                    System.out.println(result);
                    break;
                case ShowTurn:
                    result = controller.showTurn();
                    System.out.println(result);
                    break;
                case ShowStats:
                    matcher = GameCommands.ShowStats.getMatcher(input);
                    result = controller.showStats(matcher, false);
                    System.out.println(result);
                    break;
                case ShowStatsEnemy:
                    matcher = GameCommands.ShowStatsEnemy.getMatcher(input);
                    result = controller.showStats(matcher, true);
                    System.out.println(result);
                    break;
                case SkillsDetails:
                    result = controller.skillDetails();
                    System.out.println(result);
                    break;
                case ShowAP:
                    result = controller.showAP();
                    System.out.println(result);
                    break;
                case SkipTurn:
                    result = controller.skipTurn();
                    System.out.println(result);
                    break;
                case ShowCharms:
                    result = controller.showCharms();
                    System.out.println(result);
                    break;
                case Skill:
                    matcher = GameCommands.Skill.getMatcher(input);
                    result = controller.skill(matcher);
                    System.out.println(result);
                    break;

            }
        }
    }
}
