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
                    System.out.println(App.getCurrentMenu());
                    break;
                case Exit:
                    App.setExit(true);
                    break;
                case Attack:
                    Matcher matcher = GameCommands.Attack.getMatcher(input);
                    Result result = controller.Attack(matcher);
                    System.out.println(result);
                    break;
                case ShowTurn:
                    System.out.println(App.getGame().getCurrentKnight().getKnight().getName());
                    break;
                case ShowStats:
                    matcher = GameCommands.ShowStats.getMatcher(input);
                    result = controller.showStats(matcher);
                    System.out.println(result);
                    break;
                case SkillsDetails:
                    result = controller.skillDetails();
                    System.out.println(result);
                case ShowAP:
                    Knight tmp = App.getGame().getCurrentKnight();
                    System.out.println(tmp.getKnight().getName() + "'s AP: " + App.getGame().getCurrentKnight().getAP() + "\n");
                    break;
                case SkipTurn:
                    App.getGame().nextTurn();
                    break;
                case ShowCharms:

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
