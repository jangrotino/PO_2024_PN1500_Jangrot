package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.Map;
import java.util.HashMap;

public class RectangularMap implements WorldMap {
    private final  Map<Vector2d, Animal> animals = new HashMap<>();
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final MapVisualizer map;

    public RectangularMap(int width, int height) {
        upperRight = new Vector2d(width, height);
        map = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        return canMoveTo(animal.getPosition()) && animals.putIfAbsent(animal.getPosition(), animal) == null;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        Vector2d startPosition = animal.getPosition();

        if (animal == objectAt(startPosition)) {
            animal.move(direction, this);

            Vector2d newPosition = animal.getPosition();
            if (!startPosition.equals(newPosition)) {
                animals.remove(startPosition);
                animals.put(newPosition, animal);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return map.draw(lowerLeft, upperRight);
    }
}
