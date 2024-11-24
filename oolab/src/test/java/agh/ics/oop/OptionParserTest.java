package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static agh.ics.oop.OptionParser.parser;
import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void testValidArguments() {
        // Valid argument cases
        String[] args1 = {"l", "r", "b", "f"}; // mix of valid directions
        String[] args2 = {"l", "l"};           // all left
        String[] args3 = {"r", "r"};           // all right
        String[] args4 = {"f", "f"};           // all forward
        String[] args5 = {"b", "b"};           // all backward

        List<MoveDirection> expected1 = List.of(MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD);
        List<MoveDirection> expected2 = List.of(MoveDirection.LEFT, MoveDirection.LEFT);
        List<MoveDirection> expected3 = List.of(MoveDirection.RIGHT, MoveDirection.RIGHT);
        List<MoveDirection> expected4 = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD);
        List<MoveDirection> expected5 = List.of(MoveDirection.BACKWARD, MoveDirection.BACKWARD);

        // Assertions
        assertIterableEquals(expected1, parser(args1), "Valid mixed directions should be parsed correctly.");
        assertIterableEquals(expected2, parser(args2), "Valid left directions should be parsed correctly.");
        assertIterableEquals(expected3, parser(args3), "Valid right directions should be parsed correctly.");
        assertIterableEquals(expected4, parser(args4), "Valid forward directions should be parsed correctly.");
        assertIterableEquals(expected5, parser(args5), "Valid backward directions should be parsed correctly.");
    }

    @Test
    void testInvalidArguments() {
        // Invalid argument cases
        String[] args1 = {",", " ", "a", "c", "igor"}; // all invalid arguments
        String[] args2 = {"x"};                       // single invalid argument
        String[] args3 = {"l", "x", "f"};             // mixed valid and invalid arguments

        // Assertions for exceptions
        assertThrows(IllegalArgumentException.class, () -> parser(args1),
                "Invalid arguments should throw IllegalArgumentException.");
        assertThrows(IllegalArgumentException.class, () -> parser(args2),
                "Single invalid argument should throw IllegalArgumentException.");
        assertThrows(IllegalArgumentException.class, () -> parser(args3),
                "Mixed valid and invalid arguments should throw IllegalArgumentException.");
    }
}
