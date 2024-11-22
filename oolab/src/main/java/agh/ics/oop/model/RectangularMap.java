package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        lowerBound = new Vector2d(0, 0);
        upperBound = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerBound) && position.precedes(upperBound) && !super.isOccupied(position);
    }
}
