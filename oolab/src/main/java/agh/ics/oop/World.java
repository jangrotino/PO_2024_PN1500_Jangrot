package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;

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
        System.out.println("system wystartowal");

        List<MoveDirection> directions = OptionParser.parser(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField map = new GrassField(10);
        // WorldMap map = new RectangularMap(5, 5);
        map.subscribe(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        System.out.println("system zakonczyl dzialanie");
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
