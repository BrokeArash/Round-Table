package models;

public class Player {
    private final String name;
    private final String password;

    private int point;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.point = 0;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point) {
        this.point += point;
    }
}
