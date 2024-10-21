package agh.ics.oop.model;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d add(Vector2d other) {
        Vector2d result = new Vector2d(x + other.x, y + other.y);
        return result;
    }

    public Vector2d subtract(Vector2d other) {
        Vector2d result = new Vector2d(x - other.x, y - other.y);
        return result;
    }

    public Vector2d upperRight(Vector2d other) {
        Vector2d result = new Vector2d(x, y + other.y);
        return result;
    }

    public Vector2d lowerLeft(Vector2d other) {
        Vector2d result = new Vector2d(x, y - other.y);
        return result;
    }

    public Vector2d opposite() {
        Vector2d result = new Vector2d(-x, -y);
        return result;
    }

    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(!(other instanceof Vector2d))
            return false;

        Vector2d that = (Vector2d) other;
        return x == that.x && y == that.y;
    }
}
