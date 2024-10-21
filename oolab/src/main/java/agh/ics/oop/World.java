package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        run(OptionParser.parser(args));
        System.out.println("system zakończył działanie");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
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
