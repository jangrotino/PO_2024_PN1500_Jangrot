package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {
    @Test
    void OptionParserArrayWithSomeBadVal() {
        //given
        String[] args1 = {", ", " ", "l", "r", "b", "f", "a", "c ", "igor"};
        String[] args2 = {"x"};
        OptionParser parser = new OptionParser();
        MoveDirection[] expected1 = {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD};
        MoveDirection[] expected2 = {};

        //then
        assertArrayEquals(expected1, parser.parser(args1));
        assertArrayEquals(expected2, parser.parser(args2));
    }
}