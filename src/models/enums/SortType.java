package models.enums;

public enum SortType {
    GamesPlayed("games played"),
    TotalDamage("total damage"),
    point("point"),
    GamesWon("games won"),
    ;


    private final String name;

    SortType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
