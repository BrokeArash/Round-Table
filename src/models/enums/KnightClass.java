package models.enums;

import models.KnightStats;

import java.util.List;

public enum KnightClass {
    Commander("Commander"),
    Warrior("Warrior"),
    Mage("Mage"),
    Healer("Healer"),
    ;

    private final String name;

    KnightClass(String name) {
        this.name = name;
    }

    public String getKnightName() {
        return name;
    }
}
