package agh.ics.oop.model;

public class Animal implements WorldElement{
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.direction = MapDirection.NORTH;
    }

    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;
        this.position = position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return direction.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;

            case LEFT:
                this.direction = this.direction.previous();
                break;

            case FORWARD:
                Vector2d unitVec1 = this.direction.toUnitVector();
                if (validator.canMoveTo(position.add(unitVec1))) {
                    position = position.add(unitVec1);
                }
                break;

            case BACKWARD:
                Vector2d unitVec2 = this.direction.toUnitVector();
                if (validator.canMoveTo(position.subtract(unitVec2))) {
                    position = position.subtract(unitVec2);
                }
                break;

            default:
                break;
        }
    }
}
