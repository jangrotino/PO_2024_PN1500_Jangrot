package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private final Boundary boundary;
    public RectangularMap(int width, int height) {
        Vector2d lowerBound = new Vector2d(0, 0);
        Vector2d upperBound = new Vector2d(width, height);
        boundary = new Boundary(lowerBound, upperBound);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(boundary.lowerBound()) && position.precedes(boundary.upperBound()) && !isOccupied(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        return boundary;
    }
}
