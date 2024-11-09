package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {

    static List<MoveDirection> parser(String[] args) {
        List<MoveDirection> moveDirections = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "l":
                    moveDirections.add(MoveDirection.LEFT);
                    break;
                case "r":
                    moveDirections.add(MoveDirection.RIGHT);
                    break;
                case "b":
                    moveDirections.add(MoveDirection.BACKWARD);
                    break;
                case "f":
                    moveDirections.add(MoveDirection.FORWARD);
                    break;
                default:
                    break;
            }
        }

        return moveDirections;
    }
}
