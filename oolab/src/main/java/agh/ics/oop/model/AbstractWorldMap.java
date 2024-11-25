package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer map;
    private List<MapChangeListener> mapChangeListeners = new ArrayList<>();
    private static final String animalMoveMessage = "zwierzę ruszyło się na pole: ";
    private static final String animalPlaceMessage = "zwierzę zostało umieszczone na polu: ";
    private final UUID id;

    public AbstractWorldMap() {
        map = new MapVisualizer(this);
        id = UUID.randomUUID();
    }

    public void subscribe(MapChangeListener listener) {
        mapChangeListeners.add(listener);
    }

    public void unsubscribe(MapChangeListener listener) {
        mapChangeListeners.remove(listener);
    }

    private void mapChanged(String message) {
        for (MapChangeListener listener : mapChangeListeners) {
            listener.mapChanged(this, message);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition()) && animals.putIfAbsent(animal.getPosition(), animal) == null) {
            this.mapChanged(animalPlaceMessage + animal.getPosition());
            return true;
        }
        else{
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d startPosition = animal.getPosition();

        if (animal == objectAt(startPosition)) {
            animal.move(direction, this);

            Vector2d newPosition = animal.getPosition();
            if (!startPosition.equals(newPosition)) {
                this.mapChanged(animalMoveMessage + newPosition);
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
    public List<WorldElement> getElements() {
        return new ArrayList<>(this.animals.values());
    }

    @Override
    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return map.draw(currentBounds.lowerBound(), currentBounds.upperBound());
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public UUID getId(){
        return id;
    }
}
