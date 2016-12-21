package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Natusik on 24.10.2016.
 */
public class PointTest {

  @Test
  public void testDistance1(){

    Point p1 = new Point(5,3);
    Point p2 = new Point(-10,-10);
    Assert.assertEquals(p1.distance(p2),"19,85");

  }

  @Test
  public void testDistance2(){

    Point p3 = new Point(0,3);
    Point p4 = new Point(10,10);
    Assert.assertEquals(p3.distance(p4),"12,20");

  }
}
