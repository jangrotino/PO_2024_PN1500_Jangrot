package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        run(OptionParser.parser(args));
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
