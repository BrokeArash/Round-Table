package models;

import models.enums.Knights;

public class Knight {
    private Knights knight;
    private Player owner;

    public Knight(Knights knight, Player owner) {
        this.knight = knight;
        this.owner = owner;
    }

    public Knights getKnight() {
        return knight;
    }

    public void setKnight(Knights knight) {
        this.knight = knight;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
