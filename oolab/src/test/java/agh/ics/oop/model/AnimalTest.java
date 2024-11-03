package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    void setUp() {
        animal1 = new Animal(new Vector2d(2, 2));
        animal2 = new Animal(new Vector2d(3, 3));
    }

    @Test
    void testDefaultDirection() {
        assertEquals(MapDirection.NORTH, animal1.getDirection(), "Animal 1's default direction should be NORTH.");
        assertEquals(MapDirection.NORTH, animal2.getDirection(), "Animal 2's default direction should be NORTH.");
    }

    @Test
    void testMoveForward() {
        animal1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition(), "Animal 1 should move forward to (2, 3).");

        animal2.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(3, 4), animal2.getPosition(), "Animal 2 should move forward to (3, 4).");
    }

    @Test
    void testMoveBackward() {
        animal1.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 1), animal1.getPosition(), "Animal 1 should move backward to (2, 1).");

        animal2.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3, 2), animal2.getPosition(), "Animal 2 should move backward to (3, 2).");
    }

    @Test
    void testTurnRight() {
        animal1.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal1.getDirection(), "Animal 1 should face EAST after turning right.");

        animal2.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal2.getDirection(), "Animal 2 should face EAST after turning right.");
    }

    @Test
    void testTurnLeft() {
        animal1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal1.getDirection(), "Animal 1 should face WEST after turning left.");

        animal2.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal2.getDirection(), "Animal 2 should face WEST after turning left.");
    }

    @Test
    void testIsAtPosition() {
        assertTrue(animal1.isAt(new Vector2d(2, 2)), "Animal 1 should be at position (2, 2).");
        assertTrue(animal2.isAt(new Vector2d(3, 3)), "Animal 2 should be at position (3, 3).");

        animal1.move(MoveDirection.FORWARD);
        assertFalse(animal1.isAt(new Vector2d(2, 2)), "Animal 1 should not be at position (2, 2) after moving forward.");
        assertTrue(animal1.isAt(new Vector2d(2, 3)), "Animal 1 should be at position (2, 3) after moving forward.");
    }

    @Test
    void testBoundaryConditions() {
        animal1.move(MoveDirection.FORWARD); // Move to (2, 3)
        animal1.move(MoveDirection.FORWARD); // Move to (2, 4)
        animal1.move(MoveDirection.FORWARD); // Move to (2, 5) - should stay at (2, 4)

        assertEquals(new Vector2d(2, 4), animal1.getPosition(), "Animal 1 should not move beyond the upper boundary.");

        animal1.move(MoveDirection.LEFT); // Turn west
        animal1.move(MoveDirection.BACKWARD); // Move to (3, 4)
        animal1.move(MoveDirection.BACKWARD); // Move to (4, 4)
        animal1.move(MoveDirection.BACKWARD); // Move to (5, 4) - should stay at (4, 4)

        assertEquals(new Vector2d(4, 4), animal1.getPosition(), "Animal 1 should not move beyond the right boundary.");

        animal1.move(MoveDirection.FORWARD); // Move to (3, 4)
        animal1.move(MoveDirection.FORWARD); // Move to (2, 4)
        animal1.move(MoveDirection.FORWARD); // Move to (1, 4)
        animal1.move(MoveDirection.FORWARD); // Move to (0, 4)
        animal1.move(MoveDirection.FORWARD); //  Move to (-1, 4) should stay at (0, 4)

        assertEquals(new Vector2d(0, 4), animal1.getPosition(), "Animal 1 should not move beyond the left boundary.");

        animal1.move(MoveDirection.LEFT); // Turn south
        animal1.move(MoveDirection.FORWARD); // Move to (0, 3)
        animal1.move(MoveDirection.FORWARD); // Move to (0, 2)
        animal1.move(MoveDirection.FORWARD); // Move to (0, 1)
        animal1.move(MoveDirection.FORWARD); // Move to (0, 0)
        animal1.move(MoveDirection.FORWARD); //  Move to (0, -1) should stay at (0, 0)

        assertEquals(new Vector2d(0, 0), animal1.getPosition(), "Animal 1 should not move beyond the lower boundary.");
    }

    @Test
    void testAnimalInitialization() {
        Animal newAnimal = new Animal(); // Should initialize at (2, 2) facing NORTH
        assertEquals(new Vector2d(2, 2), newAnimal.getPosition(), "New animal should start at (2, 2).");
        assertEquals(MapDirection.NORTH, newAnimal.getDirection(), "New animal should face NORTH by default.");
    }
}
