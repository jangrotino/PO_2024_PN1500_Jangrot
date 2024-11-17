package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private Vector2d lowerBound = null;
    private Vector2d upperBound = null;
    private final MapVisualizer map;

    public GrassField(int grassFieldNumber) {
        map = new MapVisualizer(this);
        grassPlacing(grassFieldNumber);
    }

    public Vector2d getLowerBound() {
        return lowerBound;
    }

    public Vector2d getUpperBound() {
        return upperBound;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition()) && animals.putIfAbsent(animal.getPosition(), animal) == null) {
            this.bordersChange(animal.getPosition());
            return true;
        }
        return false;
    }

    private void grassPlacing(int grassFieldNumber) {
        Random random = new Random();
        int maxVal = (int) Math.sqrt(grassFieldNumber * 10);
        while(grasses.size() < grassFieldNumber) {
            int x = random.nextInt(maxVal);
            int y = random.nextInt(maxVal);
            Vector2d newVec = new Vector2d(x, y);
            if(!grasses.containsKey(newVec)) {
                grasses.put(newVec, new Grass(newVec));
                bordersChange(newVec);
            }
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d startPosition = animal.getPosition();

        if (animal == objectAt(startPosition)) {
            animal.move(direction, this);

            Vector2d newPosition = animal.getPosition();
            this.bordersChange(newPosition);
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
    public WorldElement objectAt(Vector2d position) {
        if (isOccupied(position)) {
            return animals.get(position);
        }
        return grasses.get(position);
    }

    private void bordersChange(Vector2d position) {
        if(lowerBound == null || upperBound == null) {
            lowerBound = position;
            upperBound = position;
        }
        else if(position.precedes(lowerBound)) {
            lowerBound = position;
        }
        else if(position.follows(upperBound)) {
            upperBound = position;
        }
    }

    public String toString() {
        return map.draw(lowerBound, upperBound);
    }
}
