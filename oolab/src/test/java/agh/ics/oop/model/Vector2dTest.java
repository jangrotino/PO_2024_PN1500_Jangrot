package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class Vector2dTest {
    @Test
    void equalsForAllPossibleStates(){
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,2);
        int[] v3 = {1,2};
        Vector2d v4 = new Vector2d(3,3);

        //then
        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1));
        assertTrue(v1.equals(v1));
        assertFalse(v2.equals(v3));
        assertFalse(v1.equals(v4));
    }

    @Test
    void toStringGiveSameCoordinates() {
        //given
        Vector2d v1 = new Vector2d(1,2);

        //then
        assertEquals("(1,2)", v1.toString());
    }

    @Test
    void precedesIsBigger() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(3,2);
        Vector2d v3 = new Vector2d(1,2);
        Vector2d v4 = new Vector2d(-1,4);

        //then
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v3));
        assertFalse(v1.precedes(v4));
        assertFalse(v2.precedes(v1));
    }

    @Test
    void followsIsSmaller() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(3,2);
        Vector2d v3 = new Vector2d(1,2);
        Vector2d v4 = new Vector2d(-1,4);

        //then
        assertFalse(v1.follows(v2));
        assertTrue(v1.follows(v3));
        assertFalse(v1.follows(v4));
        assertTrue(v2.follows(v1));
    }

    @Test
    void upperRightNewPoint() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,1);
        Vector2d v3 = new Vector2d(2,2);

        //then
        assertEquals(v3, v1.upperRight(v2));
    }

    @Test
    void lowerLeftNewPoint() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,1);
        Vector2d v3 = new Vector2d(1,1);

        //then
        assertEquals(v3, v1.lowerLeft(v2));
    }

    @Test
    void addNewPoint() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,1);

        //then
        assertEquals(new Vector2d(3, 3), v1.add(v2));
    }

    @Test
    void subtractNewPoint() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,1);

        //then
        assertEquals(new Vector2d(-1, 1), v1.subtract(v2));
    }

    @Test
    void oppositeNewPoint() {
        //given
        Vector2d v1 = new Vector2d(1,1);

        //then
        assertEquals(new Vector2d(-1, -1), v1.opposite());
    }
}