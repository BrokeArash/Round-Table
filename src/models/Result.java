package models;

public class Result {

    boolean isTrue;
    String message;

    public Result(boolean isTrue, String message) {
        this.isTrue = isTrue;
        this.message = message;
    }

    public boolean isTrue() {
        return isTrue;
    }

    @Override
    public String toString() {
        return message;
    }
}
