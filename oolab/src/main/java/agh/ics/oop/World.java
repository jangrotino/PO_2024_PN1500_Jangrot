package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.Simulation;
import agh.ics.oop.OptionParser;

import java.util.ArrayList;
import java.util.List;
import agh.ics.oop.World;
import agh.ics.oop.model.TextMap;

public class World {
    public static void main(String[] args) {
        /*
        System.out.println("system wystartował");
        run(OptionParser.parser(args));
        System.out.println("system zakończył działanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection dir = MapDirection.NORTH;
        System.out.println(dir.toString());
        System.out.println(dir.next());
        System.out.println(dir.previous());
        System.out.println(dir.toUnitVector());

        Animal animal = new Animal();
        System.out.println(animal);
        Animal animal2 = new Animal(new Vector2d(5, 6));
        System.out.println(animal2)

        List<MoveDirection> directions = OptionParser.parser(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
         */
        // run Simulation
        System.out.println("system wystartował");

        List<MoveDirection> directions = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2, 2), new Vector2d(3, 4)));
        List<Animal> animals = new ArrayList<>();

        for (var position : positions) {
            animals.add(new Animal(position));
        }

        WorldMap<Animal, Vector2d> worldMap = new RectangularMap(4, 4);
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, directions, worldMap);
        simulation.run();

        // TextMap Simulation
        List<String> strings = new ArrayList<>(List.of("Ala", "ma", "sowoniedźwiedzia"));
        WorldMap<String, Integer> textMap = new TextMap();
        Simulation<String, Integer> textMapSimulation = new Simulation<>(strings, directions, textMap);
        textMapSimulation.run();

        // stop
        System.out.println("system zakończył działanie");
    }

    static void run(MoveDirection[] args){
        System.out.println("Start");
        for(MoveDirection argument : args){
            switch(argument){
                case LEFT:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
            }
        }
    }
}
