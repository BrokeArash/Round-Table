package models.enums;

import views.*;

import java.util.Scanner;

public enum Menu {
    SignupMenu("signup menu", new SignupMenu()),
    LoginMenu("login menu", new LoginMenu()),
    MainMenu("main menu", new MainMenu()),
    GameMenu("game menu", new GameMenu()),
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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
