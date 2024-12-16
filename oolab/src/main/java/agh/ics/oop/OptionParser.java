package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {

    public static List<MoveDirection> parser(String[] args) throws IllegalArgumentException {
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
                        throw new IllegalArgumentException("Nieznany kierunek ruchu: " + arg);
                }
        }

        return moveDirections;
    }
}
