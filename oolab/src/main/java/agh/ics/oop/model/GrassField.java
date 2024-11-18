package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private Boundary boundary = new Boundary(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE), new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE));

    public GrassField(int grassFieldNumber) {
        grassPlacing(grassFieldNumber);
    }

    public Vector2d getLowerBound() {
        return boundary.lowerBound();
    }

    public Vector2d getUpperBound() {
        return boundary.upperBound();
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException{
        if(super.place(animal)) {
            this.bordersChange(animal.getPosition());
            return true;
        }
        else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    private void grassPlacing(int grassFieldNumber) {
        /*
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
         */
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int) Math.sqrt(10 * grassFieldNumber), (int) Math.sqrt(10 * grassFieldNumber), grassFieldNumber);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
            bordersChange(grassPosition);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal, direction);
        this.bordersChange(animal.getPosition());
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        return grasses.get(position);
    }

    private void bordersChange(Vector2d position) {
        boundary = new Boundary(boundary.lowerBound().lowerLeft(position), boundary.upperBound().upperRight(position));
    }

    public Boundary getCurrentBounds() {
        return boundary;
    }

    @Override
    public List<WorldElement> getElements() {
        var elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }
}
