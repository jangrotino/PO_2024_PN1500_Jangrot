package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<T> animals;
    private final List<MoveDirection> moveList;
    private final WorldMap<T, P> map;

    public Simulation(List<T> animals, List<MoveDirection> moveList, WorldMap<T, P> map) {
        this.map = map;
        this.animals = animals;
        this.moveList = moveList;

        for (T animal : animals) {
            map.place(animal);
        }
    }

    public List<T> getAnimals() {
        return animals;
    }

    public void run() {
        int animalCount = animals.size();
        System.out.println(map);
        for (int i = 0; i < moveList.size(); i++) {
            int currentAnimalIndex = i % animalCount;
            T currentAnimal = animals.get(currentAnimalIndex);
            map.move(currentAnimal, moveList.get(i));
            System.out.println(map);
        }
    }
}
