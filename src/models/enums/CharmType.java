package models.enums;

public enum CharmType {
    Attack("attack"),
    Magic("magic"),
    Defense("defense"),
    Speed("speed"),
    Clean("clean"),
    ;

    private final String name;

    CharmType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
