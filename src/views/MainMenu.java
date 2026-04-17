package views;

import controllers.MainController;
import models.App;
import models.Game;
import models.Knight;
import models.Result;
import models.enums.Knights;
import models.enums.MainCommands;

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
                case ShowCurrentMenu:
                    Result result = controller.showCurrentMenu();
                    System.out.println(result);
                    break;
                case Exit:
                    result = controller.exit();
                    System.out.println(result);
                    break;
                case KnightsDetails:
                    result = controller.SeeCharacters();
                    System.out.println(result);
                    break;
                case CustomizeKnight:
                    Matcher matcher = MainCommands.CustomizeKnight.getMatcher(input);
                    result = controller.customizeKnight(matcher);
                    System.out.println(result);
                case LogOut:
                    result = controller.Logout();
                    System.out.println(result);
                    break;
                case Play:
                    matcher = MainCommands.Play.getMatcher(input);
                    result = controller.Play(matcher);
                    System.out.println(result);
                    if(result.isTrue()) {
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
                    break;
            }
        }
    }
}
