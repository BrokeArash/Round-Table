package views;

import controllers.MainController;
import models.App;
import models.Game;
import models.Result;
import models.enums.MainCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private MainController controller = new MainController();
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
        } else if ((matcher = MainCommands.KnightsDetails.isMatch(input)) != null) {
            result = controller.SeeCharacters();
            System.out.println(result);
        } else if ((matcher = MainCommands.LogOut.isMatch(input)) != null) {
            result = controller.Logout();
            System.out.println(result);
        } else if ((matcher = MainCommands.Play.isMatch(input)) != null) {
            String username = matcher.group("username");
            result = controller.Play(username);
            System.out.println(result);
            if (result.isTrue()) {
                App.setGame(new Game(App.getMainPlayer(), App.getOtherPlayer()));
                for (int i = 0; i < 4; i++) {
                    boolean validName = false;

                    while (!validName) {

                        if (i < 2) {
                            System.out.println("choosing knight for " +
                                    App.getGame().getPlayer1().getName() + ":");
                        } else {
                            System.out.println("choosing knight for " +
                                    App.getGame().getPlayer2().getName() + ":");
                        }

                        String name = scanner.nextLine();

                        result = controller.chooseKnight(App.getGame(), name, i);
                        System.out.println(result);

                        validName = result.isTrue();
                    }
                }
                controller.playOutro();
            }
        } else {
            System.out.println("invalid command!");
        }
    }
}
