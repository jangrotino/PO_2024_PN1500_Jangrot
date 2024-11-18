package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer map;
    protected Vector2d lowerBound = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperBound = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    public AbstractWorldMap() {
        map = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        return canMoveTo(animal.getPosition()) && animals.putIfAbsent(animal.getPosition(), animal) == null;
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

    @Override
    public String toString() {
        return map.draw(lowerBound, upperBound);
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
}
