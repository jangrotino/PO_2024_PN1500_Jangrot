package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionParser {

    static MoveDirection[] parser(String[] args) {
        MoveDirection[] moveDirection;
        moveDirection = new MoveDirection[args.length];
        int validValCnt = 0;
        for (String arg : args) {
            switch (arg) {
                case "l":
                    moveDirection[validValCnt] = MoveDirection.LEFT;
                    validValCnt++;
                    break;
                case "r":
                    moveDirection[validValCnt] = MoveDirection.RIGHT;
                    validValCnt++;
                    break;
                case "b":
                    moveDirection[validValCnt] = MoveDirection.BACKWARD;
                    validValCnt++;
                    break;
                case "f":
                    moveDirection[validValCnt] = MoveDirection.FORWARD;
                    validValCnt++;
                    break;
            }
        }

        return Arrays.copyOfRange(moveDirection, 0, validValCnt);
    }
}
