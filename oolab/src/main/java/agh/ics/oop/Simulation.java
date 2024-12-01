package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moveList;
    private final WorldMap map;

    public Simulation(List<Vector2d> animalPositions, List<MoveDirection> moveList, WorldMap map) {
        this.map = map;
        for (Vector2d position : animalPositions) {
            Animal placeAnimal = new Animal(position);
            try {
                if (map.place(placeAnimal)) {
                    this.animals.add(placeAnimal);
                }
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }
        this.moveList = moveList;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void run() {
        int animalCount = animals.size();
        for (int i = 0; i < moveList.size(); i++) {
            int currentAnimalIndex = i % animalCount;
            Animal currentAnimal = animals.get(currentAnimalIndex);
            map.move(currentAnimal, moveList.get(i));
        }
    }
}
