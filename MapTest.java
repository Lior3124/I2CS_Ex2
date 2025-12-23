package assignments.Ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;
    @BeforeEach
    public void setuo() {
        _m3_3 = new Map(_map_3_3);
        _m0 = new Map(5,5,0);
        _m1 = new Map(5,5,0);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }

    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0,_m1);
    }

    private final int[][] arr1 = {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
    };
    private final int[][] arr2 = {
            {1,2,3,4},
            {2,3,4,5},
    };
    private final int[][] arr3 = {
            {3,3,3},
            {3,3,3},
            {3,3,3}
    };
    private Map map1 = new Map(arr1);
    private Map map2 = new Map(arr2);
    private Map map3 = new Map(arr3);

    /**
     * Test the map constructor
     * gets width,height and value
     */
    @Test
    void test_map1(){
        _m0 = new Map(5,5,0);
        assertEquals(_m0,map1);
        _m1 = new Map(3,3,3);
        assertEquals(_m1,map3);
    }

    /**
     * Test the map constructor
     * gets size
     */
    @Test
    void test_map2() {
        _m0 = new Map(5);
        assertEquals(_m0,map1);
    }

    /**
     * Test map constructor with cases that should return runtime exception(given arr is ragged,null or empty)
     */
    @Test
    void test_map3() {
        int [][] empty = new int[][]{};
        int [][] ragged = new int[][]{
                {1,2,3,4},
                {2,3,4,}
        };
        Assertions.assertThrows(RuntimeException.class, () -> {_m0 = new Map(null);});
        Assertions.assertThrows(RuntimeException.class, () -> {_m0 = new Map(empty);});
        Assertions.assertThrows(RuntimeException.class, () -> {_m0 = new Map(ragged);});
    }

    /**
     * Tests getMap
     */
    @Test
    void test_getMap() {
        int[][] arr = new int [2][2];
        arr = map1.getMap();
        _m0 = new Map(arr);
        assertEquals(map1,_m0);
    }

    /**
     * Test getWidth
     */
    @Test
    void test_getWidth(){
        assertEquals(5,map1.getWidth());
        assertEquals(3,map3.getWidth());
        assertEquals(4,map2.getWidth());
    }

    /**
     * Test getHeight
     */
    @Test
    void test_getHeight(){
        assertEquals(5,map1.getHeight());
        assertEquals(2,map2.getHeight());
        assertEquals(3,map3.getHeight());
    }

    /**
     * Test getPixel
     * gets x and y values (int x, int y)
     */
    @Test
    void test_getPixel(){
        assertEquals(0,map1.getPixel(0,0));
        assertEquals(5,map2.getPixel(3,1));
        assertEquals(map2.getPixel(1,1),map2.getPixel(2,0));
    }

    /**
     * Test getPixel
     * gets a point(Pixel2D)
     */
    @Test
    void test_getPixel2(){
        Index2D p1 = new Index2D(0,0);
        Index2D p2 = new Index2D(3,1);
        Index2D p3 = new Index2D(2,0);
        Index2D p4 = new Index2D(1,1);

        assertEquals(0,map1.getPixel(p1));
        assertEquals(5,map2.getPixel(p2));
        assertEquals(map2.getPixel(p4),map2.getPixel(p3));
    }

    /**
     * Test set pixel
     * gets 3 integers x value y value and a value to insert(int x, int y, int v)
     */
    @Test
    void test_setPixel(){
        map1.setPixel(1,1,1);
        assertEquals(1,map1.getPixel(1,1));

        map2.setPixel(2,1,0);
        assertEquals(0,map2.getPixel(2,1));
    }

    /**
     * Test set pixel
     * gets a point(Pixel2D) and a value to insert (Pixel2D p, int v)
     */
    @Test
    void test_setPixel2(){
        Index2D p1 = new Index2D(0,0);
        Index2D p2 = new Index2D(3,1);
        Index2D p3 = new Index2D(2,0);

        map1.setPixel(p1,60);
        map1.setPixel(p2,120);
        map3.setPixel(p3,5);

        assertEquals(60,map1.getPixel(0,0));
        assertEquals(120,map1.getPixel(3,1));
        assertEquals(5,map3.getPixel(2,0));
    }

    /**
     * Test isInside
     * return true if point(Pixel2D) is inside the map
     */
    @Test
    void test_isInside(){
        Index2D p1 = new Index2D(10,10);
        Index2D p2 = new Index2D(4,4);
        Index2D p3 = new Index2D(2,0);

        assertFalse(map1.isInside(p1));
        assertFalse(map2.isInside(p2));
        assertTrue(map1.isInside(p2));
        assertTrue(map3.isInside(p3));

        int[][] new_arr ={
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Map2D new_map = new Map(new_arr);

        Index2D new_p1 = new Index2D(-1,0);

        assertFalse(map1.isInside(new_p1));
    }

    /**
     * Test sameDimensions
     * returns true if both maps have the same dimensions otherwise false
     */
    @Test
    void test_sameDimensnions(){
        assertTrue(map3.sameDimensions(_m3_3));
        assertTrue(map1.sameDimensions(map1));
        assertFalse(map1.sameDimensions(map2));
    }

    /**
     * Test addMap2D
     * gets another map and add them both if they have the same dimensions
     * if they don't have the same dimensions the maps remain the same
     */
    @Test
    void test_addMap2D(){
        int [][] arr = {
                {3,4,3},
                {4,3,4},
                {3,4,3}
        };
        Map new_map = new Map(arr);
        map3.addMap2D(_m3_3);
        assertEquals(new_map,map3);
    }


    /**
     * Test mul
     * gets a double scalar that the matrix will be multiplied by
     */
    @Test
    void test_mul(){
        Map new_map = new Map(5);
        map1.mul(5);
        assertEquals(new_map,map1);

        int[][] arr = {
                {2,4,6,8},
                {4,6,8,10}
        };
        Map new_map2 = new Map(arr);
        map2.mul(2);
        assertEquals(new_map2,map2);
    }

    /**
     * Test drawing a circle of a certain color inside a map(one regular test, one where the radius is 1 and one where the radius is zero)
     */
    @Test
    void test_circle(){
        Index2D p1 = new Index2D(2,2);
        int[][] circle1 = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map circle_map1 = new Map(circle1);
        map1.drawCircle(p1,0,1);
        assertEquals(circle_map1,map1);

        //reset map1
        map1 = new Map(5,5,0);

        int[][] circle3 = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map circle_map3 = new Map(circle3);
        map1.drawCircle(p1,1,1);
        assertEquals(circle_map3,map1);

        //reset map1
        map1 = new Map(5,5,0);

        int[][] circle2 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,0,0,0,0}
        };
        Map circle_map2 = new Map(circle2);
        map1.drawCircle(p1,2,1);

        assertEquals(circle_map2,map1);

    }

    /**
     * Test the drawing of a circle inside a map, but the given center is outside the map(throws runtime exception)
     */
    @Test
    void test_circle2(){
        Index2D p1 = new Index2D(2,3);
        Assertions.assertThrows(RuntimeException.class, () -> {map2.drawCircle(p1,2,1);});
    }

    /**
     * Test the drawing of a circle inside a map, when the center is the corner of the map
     */
    @Test
    void test_circle3(){
        Index2D p1 = new Index2D(0,0);
        int[][] circle1 = {
                {1,1,1,0,0},
                {1,1,1,0,0},
                {1,1,1,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map circle_map1 = new Map(circle1);
        map1.drawCircle(p1,3,1);
        assertEquals(circle_map1,map1);
    }


    @Test
    void test_rect1(){
        int[][] rect1 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map rect_map1 = new Map(rect1);
        Index2D p1 = new Index2D(1,1);
        Index2D p2 = new Index2D(3,2);
        map1.drawRect(p1,p2,1);
        assertEquals(rect_map1,map1);

        //reset map1
        map1 = new Map(5,5,0);

        map1.drawRect(p2,p1,1);
        assertEquals(rect_map1,map1);

        //reset map1
        map1 = new Map(5,5,0);

        int[][] rect2 = {
                {1,0,0,0,0},
                {1,0,0,0,0},
                {1,0,0,0,0},
                {1,0,0,0,0},
                {1,0,0,0,0}
        };
        Map rect2_map = new  Map(rect2);
        Index2D p3 = new Index2D(0,0);
        Index2D p4 = new Index2D(0,4);
        map1.drawRect(p3,p4,1);
        assertEquals(rect2_map,map1);

        //reset map1
        map1 = new Map(5,5,0);
        map1.drawRect(p4,p3,1);
        assertEquals(rect2_map,map1);

        //reset map1
        map1 = new Map(5,5,0);

        int[][] rect3 = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {1,1,1,1,1}
        };
        Map rect3_map = new  Map(rect3);
        Index2D p5 = new Index2D(0,4);
        Index2D p6 = new Index2D(4,4);
        map1.drawRect(p5,p6,1);
        assertEquals(rect3_map,map1);

        //reset map1
        map1 = new Map(5,5,0);

        int[][] rect4 = {
                {1,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map rect4_map = new  Map(rect4);
        Index2D p7 = new Index2D(0,0);
        Index2D p8 = new Index2D(0,0);
        map1.drawRect(p7,p8,1);
        assertEquals(rect4_map,map1);

    }

    /**
     * Test the fill function on a not cyclic matrix
     */
    @Test
    void test_fillNotCyclic(){
        int[][] test1 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map test_1 = new Map(test1);
        Index2D p1 = new Index2D(2,2);
        int num1 =test_1.fill(p1,0,false);
        assertEquals(6,num1);
        assertEquals(test_1,map1);


        int[][] test2 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,0,1,0},
                {0,1,0,1,0},
                {0,1,1,1,0},
        };
        Map test_2 = new Map(test2);
        int num2 = test_2.fill(p1,5,false);
        assertEquals(2,num2);

        int[][] result2 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,5,1,0},
                {0,1,5,1,0},
                {0,1,1,1,0},
        };
        Map result_2 = new Map(result2);
        assertEquals(result_2,test_2);

        int[][] test3 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
        };
        Map test_3 = new Map(test3);
        int num3 =  result_2.fill(p1,1,false);
        assertEquals(test_3,result_2);
        assertEquals(2,num3);
    }

    @Test
    void test_fillCyclic(){
        int[][] test1 = {
                {1,0,0,0,0},
                {1,1,1,1,0},
                {0,2,0,0,0},
        };
        int[][] result1 = {
                {1,5,5,5,5},
                {1,1,1,1,5},
                {5,2,5,5,5},
        };
        Map test_1 = new Map(test1);
        Map result_1 = new Map(result1);
        Index2D p1 = new Index2D(2,2);
        int num1 =test_1.fill(p1,5,true);
        assertEquals(9,num1);
        assertEquals(result_1,test_1);

    }

    @Test
    void test_rescale(){

        int[][] test1 = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        };
        int[][] result1 = {
                {1,3},
                {9,11}
        };
        Map test_1 = new Map(test1);
        Map result_1 = new Map(result1);
        test_1.rescale(0.5,0.5);
        assertEquals(result_1,test_1);

        result_1.rescale(2,2);
        int[][] result2 = {
                {1,0,3,0},
                {0,0,0,0},
                {9,0,11,0},
                {0,0,0,0}
        };
        Map result_2 = new Map(result2);
        assertEquals(result_2,result_1);

        int[][] test4 = {
                {1,2},
                {5,6}
        };
        int[][] test3 = {
                {1,0,2,0},
                {0,0,0,0},
                {5,0,6,0},
                {0,0,0,0}
        };
        Map test_3 = new Map(test3);
        Map test_4 = new Map(test4);
        test_3.rescale(0.5,0.5);
        assertEquals(test_3,test_4);
        test_3.rescale(0.5,1);
        int[][] test5 = {
                {1},
                {5}
        };
        Map test_5 = new Map(test5);
        assertEquals(test_5,test_3);
        int[][] test6 = {
                {1,0,2,0},
                {0,0,0,0},
                {5,0,6,0},
                {0,0,0,0}
        };
        Map test_6 = new Map(test6);
        test_6.rescale(0.5,2);
        int[][] test7 = {
                {1,2},
                {0,0},
                {0,0},
                {0,0},
                {5,6},
                {0,0},
                {0,0},
                {0,0},
        };
        Map test_7 = new Map(test7);
        assertEquals(test_7,test_6);

        test_7.rescale(2,0.5);

        int[][] test8 = {
                {1,0,2,0},
                {0,0,0,0},
                {5,0,6,0},
                {0,0,0,0}
        };
        Map test_8 = new Map(test8);
        assertEquals(test_8,test_7);
    }


    @Test
    void test_drawLine(){
        Index2D p1 = new Index2D(0,0);
        Index2D p2 = new Index2D(4,4);
        map1.drawLine(p1,p2,1);
        int[][] test1 = {
                {1,0,0,0,0},
                {0,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,0},
                {0,0,0,0,1}
        };
        Map test_1 = new Map(test1);
        assertEquals(test_1,map1);

        //reset map 1
        map1 = new Map(5,5,0);

        map1.drawLine(p2,p1,1);
        assertEquals(test_1,map1);

        //reset map 1
        map1 = new Map(5,5,0);

        int[][] test2 = {
                {1,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Map test_2 = new Map(test2);
        map1.drawLine(p1,p1,1);
        assertEquals(test_2,map1);

        //reset map 1
        map1 = new Map(5,5,0);

        Index2D p3 = new Index2D(0,1);
        Index2D p4 = new Index2D(2,4);
        int [][] test3 = {
                {0,0,0,0,0},
                {1,0,0,0,0},
                {0,1,0,0,0},
                {0,1,0,0,0},
                {0,0,1,0,0},
        };
        Map test_3 = new Map(test3);
        map1.drawLine(p3,p4,1);
        assertEquals(test_3,map1);

        //reset map 1
        map1 = new Map(5,5,0);

        map1.drawLine(p4,p3,1);
        assertEquals(test_3,map1);
    }
}