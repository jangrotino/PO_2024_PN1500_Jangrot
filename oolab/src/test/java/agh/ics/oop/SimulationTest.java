package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    private Animal animal1;
    private Animal animal2;
    private final RectangularMap map = new RectangularMap(4, 4);

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
        animal1.move(MoveDirection.FORWARD, map);
        assertEquals(new Vector2d(2, 3), animal1.getPosition(), "Animal 1 should move forward to (2, 3).");

        animal2.move(MoveDirection.FORWARD, map);
        assertEquals(new Vector2d(3, 4), animal2.getPosition(), "Animal 2 should move forward to (3, 4).");
    }

    @Test
    void testMoveBackward() {
        animal1.move(MoveDirection.BACKWARD, map);
        assertEquals(new Vector2d(2, 1), animal1.getPosition(), "Animal 1 should move backward to (2, 1).");

        animal2.move(MoveDirection.BACKWARD, map);
        assertEquals(new Vector2d(3, 2), animal2.getPosition(), "Animal 2 should move backward to (3, 2).");
    }

    @Test
    void testTurnRight() {
        animal1.move(MoveDirection.RIGHT, map);
        assertEquals(MapDirection.EAST, animal1.getDirection(), "Animal 1 should face EAST after turning right.");

        animal2.move(MoveDirection.RIGHT, map);
        animal2.move(MoveDirection.RIGHT, map);
        assertEquals(MapDirection.SOUTH, animal2.getDirection(), "Animal 2 should face EAST after turning right.");
    }

    @Test
    void testTurnLeft() {
        animal1.move(MoveDirection.LEFT, map);
        assertEquals(MapDirection.WEST, animal1.getDirection(), "Animal 1 should face WEST after turning left.");

        animal2.move(MoveDirection.LEFT, map);
        animal2.move(MoveDirection.LEFT, map);
        assertEquals(MapDirection.SOUTH, animal2.getDirection(), "Animal 2 should face WEST after turning left.");
    }

    @Test
    void testBoundaryConditions() {
        animal1.move(MoveDirection.FORWARD, map); // Move to (2, 3)
        animal1.move(MoveDirection.FORWARD, map); // Move to (2, 4)
        animal1.move(MoveDirection.FORWARD, map); // Move to (2, 5) - should stay at (2, 4)

        assertEquals(new Vector2d(2, 4), animal1.getPosition(), "Animal 1 should not move beyond the upper boundary.");

        animal1.move(MoveDirection.LEFT, map); // Turn west
        animal1.move(MoveDirection.BACKWARD, map); // Move to (3, 4)
        animal1.move(MoveDirection.BACKWARD, map); // Move to (4, 4)
        animal1.move(MoveDirection.BACKWARD, map); // Move to (5, 4) - should stay at (4, 4)

        assertEquals(new Vector2d(4, 4), animal1.getPosition(), "Animal 1 should not move beyond the right boundary.");

        animal1.move(MoveDirection.FORWARD, map); // Move to (3, 4)
        animal1.move(MoveDirection.FORWARD, map); // Move to (2, 4)
        animal1.move(MoveDirection.FORWARD, map); // Move to (1, 4)
        animal1.move(MoveDirection.FORWARD, map); // Move to (0, 4)
        animal1.move(MoveDirection.FORWARD, map); //  Move to (-1, 4) should stay at (0, 4)

        assertEquals(new Vector2d(0, 4), animal1.getPosition(), "Animal 1 should not move beyond the left boundary.");

        animal1.move(MoveDirection.LEFT, map); // Turn south
        animal1.move(MoveDirection.FORWARD, map); // Move to (0, 3)
        animal1.move(MoveDirection.FORWARD, map); // Move to (0, 2)
        animal1.move(MoveDirection.FORWARD, map); // Move to (0, 1)
        animal1.move(MoveDirection.FORWARD, map); // Move to (0, 0)
        animal1.move(MoveDirection.FORWARD, map); //  Move to (0, -1) should stay at (0, 0)

        assertEquals(new Vector2d(0, 0), animal1.getPosition(), "Animal 1 should not move beyond the lower boundary.");
    }

    @Test
    void testAnimalInitialization() {
        Animal newAnimal = new Animal(); // Should initialize at (2, 2) facing NORTH
        assertEquals(new Vector2d(2, 2), newAnimal.getPosition(), "New animal should start at (2, 2).");
        assertEquals(MapDirection.NORTH, newAnimal.getDirection(), "New animal should face NORTH by default.");
    }

    @Test
    public void testSimulationRunWithInvalidDirections() {
        String [] invalid = {"f", "adfa", "r", "b"};
        List<MoveDirection> directions = OptionParser.parser(invalid);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0, 0));

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 1);

        Animal animal = animals.getFirst();
        assertTrue(animal.getDirection() == MapDirection.EAST);
        assertTrue(animal.getPosition().equals(new Vector2d(0, 1)));
    }

    @Test
    public void testSimulationRunWithValidDirectionsTwoAnimalsNotCrossing() {
        String [] valid = {"f", "b", "r", "l", "f"};
        List<MoveDirection> directions = OptionParser.parser(valid);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0, 0));
        positions.add(new Vector2d(1, 0));

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 2);

        Animal animal1 = animals.getFirst();
        Animal animal2 = animals.getLast();

        assertEquals(animal1.getDirection(), MapDirection.EAST);
        assertEquals(animal1.getPosition(), new Vector2d(1, 1));

        assertEquals(animal2.getDirection(), MapDirection.WEST);
        assertEquals(animal2.getPosition(), new Vector2d(1, 0));
    }

    @Test
    public void testSimulationRunWithValidDirectionsTwoAnimalsCrossing() {
        String [] valid = {"f", "r", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionParser.parser(valid);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(1, 0));
        positions.add(new Vector2d(0, 2));

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 2);

        Animal animal1 = animals.getFirst();
        Animal animal2 = animals.getLast();

        assertEquals(animal1.getDirection(), MapDirection.NORTH);
        assertEquals(animal1.getPosition(), new Vector2d(1, 3));

        assertEquals(animal2.getDirection(), MapDirection.EAST);
        assertEquals(animal2.getPosition(), new Vector2d(1, 2 ));
    }
}