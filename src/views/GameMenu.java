package views;

import controllers.GameController;
import models.App;
import models.Player;
import models.Result;
import models.enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameController controller = new GameController();
    @Override
    public void check(Scanner scanner) {
        Player winner;
        if ((winner = App.getGame().checkEnd()) != null) {
            System.out.println(controller.gameOutro(winner));
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
        } else if ((matcher = GameCommands.ShowSkillsDetails.isMatch(input)) != null) {
            result = controller.showSkills();
            System.out.println(result);
        }  else if ((matcher = GameCommands.ShowCharms.isMatch(input)) != null) {
            result = controller.showCharms();
            System.out.println(result);
        } else if ((matcher = GameCommands.ShowAP.isMatch(input)) != null) {
            result = controller.showAP();
            System.out.println(result);
        } else if ((matcher =GameCommands.ShowStats.isMatch(input)) != null) {
            String name = matcher.group("knight");
            result = controller.showStats(name, false);
            System.out.println(result);
        } else if ((matcher = GameCommands.ShowStatsEnemy.isMatch(input)) != null) {
            String name = matcher.group("knight");
            result = controller.showStats(name, true);
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
