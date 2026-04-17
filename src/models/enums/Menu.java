package models.enums;

import views.*;

import java.util.Scanner;

public enum Menu {
    SignupMenu("signup menu", new SignupMenu()),
    MainMenu("main menu", new MainMenu()),
    GameMenu("game menu", new GameMenu()),
    ExitMenu("exit menu", new ExitMenu())
    ;

    private final AppMenu menu;
    private final String name;

    Menu(String name, AppMenu menu) {
        this.name = name;
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }

    @Override
    public String toString() {
        return name;
    }
}
