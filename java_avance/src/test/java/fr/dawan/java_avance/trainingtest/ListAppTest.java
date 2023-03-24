package fr.dawan.java_avance.trainingtest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListAppTest {
	ListApp app;

    @BeforeEach
    void setUp() throws Exception {
            app = new ListApp();
    }

    @Test
    void testBuildListIntIntInt() {
            List<Integer> result = app.buildList(2, 4, 6);
            assertTrue(2 == result.get(0));
            assertTrue(4 == result.get(1));

            result = app.buildList(0, -1, 6);
            assertTrue(0 == result.get(0));
            assertTrue(-1 == result.get(1));
    }

    @Test
    void testBuildListIntArray() {
            List<Integer> result = app.buildList(2, 4, 6, 7, 9);
            assertTrue(2 == result.get(0));
            assertTrue(4 == result.get(1));
            assertTrue(9 == result.get(4));

    }

    @Test
    void testEqualityVal1Null() {
            List<Integer> list1 = null;
            List<Integer> list2 = app.buildList(2, 400, 6);
            assertFalse(app.equality(list1, list2));
    }
    
    @Test
    void testEquality() {
            List<Integer> list1 = app.buildList(2, 400, 6);
            List<Integer> list2 = app.buildList(2, 400, 6);
            List<Integer> list3 = app.buildList(0, -1, 6);
            assertTrue(app.equality(list1, list2));
            assertFalse(app.equality(list1, list3));
    }

    @Test
    void testFirst() {
            List<Integer> list1 = app.buildList(2, 4, 6);
            assertTrue(2 == app.first(list1));
    }

    @Test
    void testLast() {
            List<Integer> list1 = app.buildList(2, 4, 6, 10);
            assertTrue(10 == app.last(list1));
    }

    @Test
    void testMedium() {
            List<Integer> list1 = app.buildList(2, 4, 6);
            List<Integer> list2 = app.buildList(2, 4, 6, 10);

            assertTrue(4 == app.medium(list1));
            assertTrue(4 == app.medium(list2));
    }


}
