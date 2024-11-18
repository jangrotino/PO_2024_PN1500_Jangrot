package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer map;
    private List<MapChangeListener> mapChangeListeners = new ArrayList<>();

    public AbstractWorldMap() {
        map = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition()) && animals.putIfAbsent(animal.getPosition(), animal) == null) {
            return true;
        }
        else{
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    public void move(Animal animal, MoveDirection direction) {
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

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public List<WorldElement> getElements() {
        return new ArrayList<>(this.animals.values());
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return map.draw(currentBounds.lowerBound(), currentBounds.upperBound());
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
}
