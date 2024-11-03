package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    public void switchAllDirectionsNext() {
        //then
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
    }

    @Test
    public void switchAllDirectionsPrevious() {
        //then
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
    }
}