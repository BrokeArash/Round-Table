package models.enums;

public enum KnightClass {
    Commander("commander"),
    Warrior("warrior"),
    Mage("mage"),
    Healer("healer"),
    ;

    private final String name;

    KnightClass(String name) {
        this.name = name;
    }

    public String getKnightName() {
        return name;
    }
}
