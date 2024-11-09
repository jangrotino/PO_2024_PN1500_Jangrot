package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moveList;

    public Simulation(List<Vector2d> animalPositions, List<MoveDirection> moveList) {
        for (Vector2d position : animalPositions) {
            this.animals.add(new Animal(position));
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
            animals.get(currentAnimalIndex).move(moveList.get(i));
            System.out.println("Zwierzę " + (currentAnimalIndex + 1) + " : " + animals.get(currentAnimalIndex).toString());
        }
    }
}
