package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import agh.ics.oop.model.util.*;

class GrassFieldTest {
    private GrassField grassField;

    @BeforeEach
    void setUp() {
        grassField = new GrassField(10); // Initializes with 10 grass fields
    }

    @Test
    void testPlaceAnimal() {
        Animal animal = new Animal(new Vector2d(2, 2));

        assertDoesNotThrow(() -> grassField.place(animal), "Animal placement should not throw an exception.");
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)), "Animal should be present at its position.");
    }

    @Test
    void testPlaceAnimalOnOccupiedPosition() {
        Animal animal1 = new Animal(new Vector2d(3, 3));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        assertDoesNotThrow(() -> grassField.place(animal1));
        IncorrectPositionException exception = assertThrows(
                IncorrectPositionException.class,
                () -> grassField.place(animal2),
                "Placing an animal on an occupied position should throw IncorrectPositionException."
        );
        assertEquals("pozycja (3,3) jest nieprawidłowa", exception.getMessage());
        assertEquals(animal1, grassField.objectAt(new Vector2d(3, 3)), "First animal should remain at the position.");
    }

    @Test
    void testMoveAnimal() {
        Animal animal = new Animal(new Vector2d(1, 1));
        assertDoesNotThrow(() -> grassField.place(animal));

        grassField.move(animal, MoveDirection.RIGHT); // Change orientation to West
        grassField.move(animal, MoveDirection.FORWARD);   // Move to (2, 1)

        assertEquals(new Vector2d(2, 1), animal.getPosition(), "Animal should be at the correct new position.");
        assertTrue(grassField.objectAt(new Vector2d(1, 1)) == null || grassField.objectAt(new Vector2d(1, 1)) instanceof Grass,
                "Old position should either be empty or have an object of class Grass.");

        assertEquals(animal, grassField.objectAt(new Vector2d(2, 1)), "Animal should occupy the new position.");
    }

    @Test
    void testCannotMoveToOccupiedPosition() {
        Animal animal1 = new Animal(new Vector2d(4, 4));
        Animal animal2 = new Animal(new Vector2d(5, 5));

        assertDoesNotThrow(() -> grassField.place(animal1));
        assertDoesNotThrow(() -> grassField.place(animal2));

        grassField.move(animal2, MoveDirection.LEFT); // Attempt to move to (4, 5)
        assertEquals(new Vector2d(5, 5), animal2.getPosition(), "Animal should not move to an occupied position.");
    }

    @Test
    void testGrassPlacing() {
        int grassFieldNumber = 10;
        GrassField testField = new GrassField(grassFieldNumber);

        int grassCount = 0;
        for (int x = 0; x < Math.sqrt(grassFieldNumber * 10); x++) {
            for (int y = 0; y < Math.sqrt(grassFieldNumber * 10); y++) {
                if (testField.objectAt(new Vector2d(x, y)) instanceof Grass) {
                    grassCount++;
                }
            }
        }

        assertEquals(grassFieldNumber, grassCount, "The number of grass objects placed should match the specified number.");
    }

    @Test
    void testBordersChange() {
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(10, 10));
        GrassField testField = new GrassField(0);

        assertDoesNotThrow(() -> testField.place(animal1));
        assertDoesNotThrow(() -> testField.place(animal2));

        assertEquals(new Vector2d(1, 1), testField.getLowerBound(), "Lower bound should adjust to the lowest coordinate.");
        assertEquals(new Vector2d(10, 10), testField.getUpperBound(), "Upper bound should adjust to the highest coordinate.");
    }

    @Test
    void testIsOccupied() {
        Animal animal = new Animal(new Vector2d(6, 6));
        assertDoesNotThrow(() -> grassField.place(animal));

        assertTrue(grassField.isOccupied(new Vector2d(6, 6)), "Position should be occupied by the animal.");
        assertFalse(grassField.isOccupied(new Vector2d(7, 7)), "Position should not be occupied.");
    }

    @Test
    void testObjectAt() {
        Animal animal = new Animal(new Vector2d(8, 8));
        assertDoesNotThrow(() -> grassField.place(animal));

        assertEquals(animal, grassField.objectAt(new Vector2d(8, 8)), "Object at position should be the placed animal.");
    }

    @Test
    void testAnimalInteractionWithGrass() {
        GrassField testField = new GrassField(1); // Single grass field
        Animal animal = new Animal(new Vector2d(0, 0));
        assertDoesNotThrow(() -> testField.place(animal));

        boolean hasGrass = testField.objectAt(new Vector2d(0, 0)) instanceof Grass;
        assertFalse(hasGrass, "Grass at position should be replaced by the animal.");
    }

    @Test
    void testBoundsUpdateAfterAnimalPlacement() {
        assertDoesNotThrow(() -> grassField.place(new Animal(new Vector2d(10, 10))));
        assertTrue(grassField.getUpperBound().equals(new Vector2d(10, 10)), "Upper bound should expand after animal placement.");

        assertDoesNotThrow(() -> grassField.place(new Animal(new Vector2d(-5, -5))));
        assertTrue(grassField.getLowerBound().equals(new Vector2d(-5, -5)), "Lower bound should expand after animal placement.");
    }
}
