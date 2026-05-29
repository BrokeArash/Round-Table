package models.enums;

public enum SortType {
    GamesPlayed("games_played"),
    point("point"),
    GamesWon("games_won"),
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
