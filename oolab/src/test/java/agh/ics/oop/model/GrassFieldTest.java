package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    private GrassField grassField;

    @BeforeEach
    void setUp() {
        grassField = new GrassField(10); // Initializes with 10 grass fields
    }

    @Test
    void testPlaceAnimal() {
        Animal animal = new Animal(new Vector2d(2, 2));
        boolean placed = grassField.place(animal);

        assertTrue(placed, "Animal should be placed successfully.");
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)), "Animal should be present at its position.");
    }

    @Test
    void testPlaceAnimalOnOccupiedPosition() {
        Animal animal1 = new Animal(new Vector2d(3, 3));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        grassField.place(animal1);
        boolean placed = grassField.place(animal2);

        assertFalse(placed, "Animal should not be placed on an occupied position.");
        assertEquals(animal1, grassField.objectAt(new Vector2d(3, 3)), "First animal should remain at the position.");
    }

    @Test
    void testMoveAnimal() {
        Animal animal = new Animal(new Vector2d(1, 1));
        grassField.place(animal);

        grassField.move(animal, MoveDirection.RIGHT); // change orientation to West
        grassField.move(animal, MoveDirection.FORWARD);   // Move to (2, 1)

        assertEquals(new Vector2d(2, 1), animal.getPosition(), "Animal should be at the correct new position.");
        assertNull(grassField.objectAt(new Vector2d(1, 1)), "Old position should be empty.");
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 1)), "Animal should occupy the new position.");
    }

    @Test
    void testCannotMoveToOccupiedPosition() {
        Animal animal1 = new Animal(new Vector2d(4, 4));
        Animal animal2 = new Animal(new Vector2d(5, 5));

        grassField.place(animal1);
        grassField.place(animal2);

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
        testField.place(animal1);
        testField.place(animal2);

        assertEquals(new Vector2d(1, 1), testField.getLowerBound(), "Lower bound should adjust to the lowest coordinate.");
        assertEquals(new Vector2d(10, 10), testField.getUpperBound(), "Upper bound should adjust to the highest coordinate.");
    }

    @Test
    void testIsOccupied() {
        Animal animal = new Animal(new Vector2d(6, 6));
        grassField.place(animal);

        assertTrue(grassField.isOccupied(new Vector2d(6, 6)), "Position should be occupied by the animal.");
        assertFalse(grassField.isOccupied(new Vector2d(7, 7)), "Position should not be occupied.");
    }

    @Test
    void testObjectAt() {
        Animal animal = new Animal(new Vector2d(8, 8));
        grassField.place(animal);


        assertEquals(animal, grassField.objectAt(new Vector2d(8, 8)), "Object at position should be the placed animal.");
    }


    @Test
    void testMultipleGrassPlacements() {
        int grassFieldNumber = 20;
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
    void testAnimalInteractionWithGrass() {
        GrassField testField = new GrassField(1); // Single grass field
        Animal animal = new Animal(new Vector2d(0, 0));
        testField.place(animal);

        boolean hasGrass = testField.objectAt(new Vector2d(0, 0)) instanceof Grass;
        assertFalse(hasGrass, "Grass at position should be replaced by the animal.");
    }

    @Test
    void testGetLowerAndUpperBoundsWithoutAnimals() {
        GrassField emptyField = new GrassField(10);

        assertNotNull(emptyField.getLowerBound(), "Lower bound should be initialized by grass placement.");
        assertNotNull(emptyField.getUpperBound(), "Upper bound should be initialized by grass placement.");
        assertTrue(emptyField.getLowerBound().precedes(emptyField.getUpperBound()), "Lower bound should precede upper bound.");
    }

    @Test
    void testBoundsUpdateAfterAnimalPlacement() {
        grassField.place(new Animal(new Vector2d(10, 10)));
        assertTrue(grassField.getUpperBound().equals(new Vector2d(10, 10)), "Upper bound should expand after animal placement.");

        grassField.place(new Animal(new Vector2d(-5, -5)));
        assertTrue(grassField.getLowerBound().equals(new Vector2d(-5, -5)), "Lower bound should expand after animal placement.");
    }

    @Test
    void testBoundsDoNotShrinkAfterAnimalRemoval() {
        Animal animal = new Animal(new Vector2d(10, 10));
        grassField.place(animal);
        Vector2d originalUpperBound = grassField.getUpperBound();

        grassField.move(animal, MoveDirection.LEFT); // Move animal to a different position
        assertEquals(originalUpperBound, grassField.getUpperBound(), "Bounds should not shrink after animal moves.");
    }
}
