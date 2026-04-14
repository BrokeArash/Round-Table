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
                    System.out.println(App.getCurrentMenu());
                    break;
                case Exit:
                    App.setExit(true);
                    break;
                case KnightsDetails:
                    Result result = controller.SeeCharacters();
                    System.out.println(result);
                    break;
                case LogOut:
                    result = controller.Logout();
                    System.out.println(result);
                    break;
                case Play:
                    Matcher matcher = MainCommands.Play.getMatcher(input);
                    result = controller.Play(matcher);
                    System.out.println(result);
                    if(result.isTrue()){
                        App.setGame(new Game(App.getMainPlayer(), App.getOtherPlayer()));
                        for(int i = 0; i < 4; i++) {
                            boolean validName = false;
                            while (!validName) {
                                if (i < 2) {
                                    System.out.println("choosing knight for " + App.getGame().getPlayer1().getName() + ":");
                                } else
                                    System.out.println("choosing knight for " + App.getGame().getPlayer2().getName() + ":");
                                String name = scanner.nextLine();
                                Knights chosen = App.getGame().findKnightByName(name);
                                if(chosen == null) {
                                    System.out.println("Invalid knight name!");
                                    continue;
                                }

                                if (i == 1) {
                                    if (App.getGame().getKnights1().get(0).getKnight().equals(chosen)) {
                                        System.out.println("You already chosen this knight!");
                                        continue;
                                    }
                                }

                                if (i == 3) {
                                    if (App.getGame().getKnights2().get(0).getKnight().equals(chosen)) {
                                        System.out.println("You already chosen this knight!");
                                        continue;
                                    }
                                }

                                if (i < 2) {
                                    Knight newKnight = new Knight(chosen, App.getGame().getPlayer1());
                                    App.getGame().getKnights1().add(newKnight);
                                    App.getGame().getQueue().add(newKnight);
                                } else {
                                    Knight newKnight = new Knight(chosen, App.getGame().getPlayer2());
                                    App.getGame().getKnights2().add(newKnight);
                                    App.getGame().getQueue().add(newKnight);
                                }
                                validName = true;
                            }
                        }
                        App.setGotoGame(true);
                    }

            }
        }
    }
}
