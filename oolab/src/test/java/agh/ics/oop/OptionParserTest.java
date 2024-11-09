package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static agh.ics.oop.OptionParser.parser;
import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {
    @Test
    void OptionParserArray() {
        //given
        String[] args1 = {", ", " ", "l", "r", "b", "f", "a", "c ", "igor"}; //mix
        String[] args2 = {"x"}; // wrong list
        String[] args3 = {"l", "l"}; // all valid left
        String[] args4 = {"r", "r"}; // all valid right
        String[] args5 = {"f", "f"}; // all valid forward
        String[] args6 = {"b", "b"}; // all valid backward
        //OptionParser parser = new OptionParser();
        List<MoveDirection> expected1 = List.of(MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD);
        List<MoveDirection> expected2 = List.of();
        List<MoveDirection> expected3 = List.of(MoveDirection.LEFT, MoveDirection.LEFT);
        List<MoveDirection> expected4 = List.of(MoveDirection.RIGHT, MoveDirection.RIGHT);
        List<MoveDirection> expected5 = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD);
        List<MoveDirection> expected6 = List.of(MoveDirection.BACKWARD, MoveDirection.BACKWARD);

        //then
        assertIterableEquals(expected1, parser(args1));
        assertIterableEquals(expected2, parser(args2));
        assertIterableEquals(expected3, parser(args3));
        assertIterableEquals(expected4, parser(args4));
        assertIterableEquals(expected5, parser(args5));
        assertIterableEquals(expected6, parser(args6));
    }
}