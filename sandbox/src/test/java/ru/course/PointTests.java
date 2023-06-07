package ru.course;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance()
    {
        Point p1 = new Point(2, 7);
        Point p2 = new Point( -2, 7);
        Assert.assertEquals(p1.distance(p2), 4.0);
    }

    @Test
    public void testDistance2()
    {
        Point p1 = new Point(1, 8);
        Point p2 = new Point( 6, 8);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }
}
