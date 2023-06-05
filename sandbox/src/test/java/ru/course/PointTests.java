package ru.course;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance()
    {
        Point p1 = new Point(2,7, -2, 7);
        Assert.assertEquals(p1.distance(), 4.0);
    }

    @Test
    public void testDistance2()
    {
        Point p2 = new Point(1,8, 6, 8);
        Assert.assertEquals(p2.distance(), 5.0);
    }
}
