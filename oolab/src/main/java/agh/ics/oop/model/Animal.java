package agh.ics.oop.model;

public class Animal {
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

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Animal [direction=" + direction + ", position=" + position.toString() + "]";
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;

            case LEFT:
                this.direction = this.direction.previous();
                break;

            case FORWARD:
                Vector2d unitVec1 = this.direction.toUnitVector();
                this.position = this.position.add(unitVec1);
                this.position = this.position.lowerLeft(new Vector2d(4, 4));
                this.position = this.position.upperRight(new Vector2d(0, 0));
                break;

            case BACKWARD:
                Vector2d unitVec2 = this.direction.toUnitVector();
                this.position = this.position.subtract(unitVec2);
                this.position = this.position.lowerLeft(new Vector2d(4, 4));
                this.position = this.position.upperRight(new Vector2d(0, 0));
                break;
                
            default:
                break;
        }
    }
}
