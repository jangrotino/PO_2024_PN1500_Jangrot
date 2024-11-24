package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import agh.ics.oop.model.util.*;

public class RectangularMapTest {

    private RectangularMap map;
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    public void setUp() {
        map = new RectangularMap(5, 5); // A 5x5 map with upper right corner at (5, 5)
        animal1 = new Animal(new Vector2d(2, 2));
        animal2 = new Animal(new Vector2d(1, 1));
    }

    @Test
    public void testCanMoveTo() {
        // Should be able to move to an empty position within bounds
        assertTrue(map.canMoveTo(new Vector2d(3, 3)));

        // Placing animal1 at (2, 2), now cannot move there
        assertDoesNotThrow(() -> map.place(animal1));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));

        // Position out of bounds
        assertFalse(map.canMoveTo(new Vector2d(6, 6)));
    }

    @Test
    public void testPlace() {
        // Place animal1 at (2, 2), should succeed
        assertDoesNotThrow(() -> map.place(animal1));

        // Place animal2 at a different position (1, 1), should also succeed
        assertDoesNotThrow(() -> map.place(animal2));

        // Trying to place another animal at (2, 2) should throw IncorrectPositionException
        Animal anotherAnimal = new Animal(new Vector2d(2, 2));
        assertThrows(IncorrectPositionException.class, () -> map.place(anotherAnimal));

        // Trying to place an animal at an out-of-bounds location should throw IncorrectPositionException
        Animal outOfBoundAnimal = new Animal(new Vector2d(4, 6));
        assertThrows(IncorrectPositionException.class, () -> map.place(outOfBoundAnimal));
    }

    @Test
    public void testIsOccupied() {
        // Initially, no position is occupied
        assertFalse(map.isOccupied(new Vector2d(3, 3)));

        // Place animal1 at (2, 2) and check if it's occupied
        assertDoesNotThrow(() -> map.place(animal1));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));

        // Check an unoccupied position
        assertFalse(map.isOccupied(new Vector2d(4, 4)));
    }

    @Test
    public void testObjectAt() {
        // Initially, no object at any position
        assertNull(map.objectAt(new Vector2d(2, 2)));

        // Place animal1 and verify it's found at (2, 2)
        assertDoesNotThrow(() -> map.place(animal1));
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));

        // Verify null at a different position
        assertNull(map.objectAt(new Vector2d(4, 4)));
    }

    @Test
    public void testMove() {
        assertDoesNotThrow(() -> map.place(animal1)); // Place animal1 at (2, 2)

        // Move animal1 north, should move to (2, 3)
        map.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition());

        // Move animal1 west, should move to (1, 3)
        map.move(animal1, MoveDirection.LEFT);
        map.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 3), animal1.getPosition());

        // Attempt to move animal2 to the same position as animal1
        assertDoesNotThrow(() -> map.place(animal2)); // Place animal2 at (1, 1)
        map.move(animal2, MoveDirection.RIGHT);
        map.move(animal2, MoveDirection.FORWARD);
        assertFalse(map.canMoveTo(animal1.getPosition())); // (1, 3) should be occupied by animal1
    }
}

