import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class LineTest to test the Point2D Class.
 */
public class Point2DTest {
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;
  private Point2D point4;
  private Point2D point5;
  private Point2D point6;
  private Point2D point7;
  private Point2D point8;
  private Point2D point9;
  private Point2D point10;
  private Point2D point11;
  private Point2D point12;

  @Before
  public void setup() {
    point1 = new Point2D(5, 6);
    point2 = new Point2D(-1, 1.1);
    point3 = new Point2D(-3.9998, -0.09);
    point4 = new Point2D(-0.9, -1.18);
    point5 = new Point2D(-5, 0);
    point6 = new Point2D(5, 0);
    point7 = new Point2D(-5.5, 0);
    point8 = new Point2D(0, -5);
    point9 = new Point2D(8, 6);
    point10 = new Point2D(-4.4, -12);
    point11 = new Point2D(-10.21, 12);
    point12 = new Point2D(0, 0);
  }

  @Test
  public void constructorPass() {
    assertEquals("(5.0,6.0)", point1.toString());
    assertEquals("(-1.0,1.1)", point2.toString());
    assertEquals("(-3.9998,-0.09)", point3.toString());
    assertEquals("(-0.9,-1.18)", point4.toString());
    assertEquals("(-5.0,0.0)", point5.toString());
    assertEquals("(5.0,0.0)", point6.toString());
    assertEquals("(-5.5,0.0)", point7.toString());
    assertEquals("(0.0,-5.0)", point8.toString());
    assertEquals("(8.0,6.0)", point9.toString());
    assertEquals("(-4.4,-12.0)", point10.toString());
    assertEquals("(-10.21,12.0)", point11.toString());
    assertEquals("(0.0,0.0)", point12.toString());
  }

  @Test
  public void getX() {
    assertEquals(5.0, point1.getX(), 0.001);
    assertEquals(-1.0, point2.getX(), 0.001);
    assertEquals(-3.9998, point3.getX(), 0.001);
    assertEquals(-0.9, point4.getX(), 0.001);
    assertEquals(-5.0, point5.getX(), 0.001);
    assertEquals(5.0, point6.getX(), 0.001);
    assertEquals(-5.5, point7.getX(), 0.001);
    assertEquals(0.0, point8.getX(), 0.001);
    assertEquals(8.0, point9.getX(), 0.001);
    assertEquals(-4.4, point10.getX(), 0.001);
    assertEquals(-10.21, point11.getX(), 0.001);
    assertEquals(0.0, point12.getX(), 0.001);
  }

  @Test
  public void getY() {
    assertEquals(6.0, point1.getY(), 0.001);
    assertEquals(1.1, point2.getY(), 0.001);
    assertEquals(-0.09, point3.getY(), 0.001);
    assertEquals(-1.18, point4.getY(), 0.001);
    assertEquals(0.0, point5.getY(), 0.001);
    assertEquals(0.0, point6.getY(), 0.001);
    assertEquals(0.0, point7.getY(), 0.001);
    assertEquals(-5.0, point8.getY(), 0.001);
    assertEquals(6.0, point9.getY(), 0.001);
    assertEquals(-12.0, point10.getY(), 0.001);
    assertEquals(12.0, point11.getY(), 0.001);
    assertEquals(0.0, point12.getY(), 0.001);
  }

  @Test
  public void testEqualsPass() {
    Point2D pointObject1 = new Point2D(5, 6);
    Point2D pointObject2 = new Point2D(-1, 1.1);
    Point2D pointObject3 = new Point2D(-3.9998, -0.09);
    Point2D pointObject4 = new Point2D(-0.9, -1.18);
    Point2D pointObject5 = new Point2D(-5, 0);
    Point2D pointObject6 = new Point2D(5, 0);
    Point2D pointObject7 = new Point2D(-5.5, 0);
    Point2D pointObject8 = new Point2D(0, -5);
    Point2D pointObject9 = new Point2D(8, 6);
    Point2D pointObject10 = new Point2D(-4.4, -12);
    Point2D pointObject11 = new Point2D(-10.21, 12);
    Point2D pointObject12 = new Point2D(0, 0);

    assertEquals(point1, pointObject1);
    assertEquals(point2, pointObject2);
    assertEquals(point3, pointObject3);
    assertEquals(point4, pointObject4);
    assertEquals(point5, pointObject5);
    assertEquals(point6, pointObject6);
    assertEquals(point7, pointObject7);
    assertEquals(point8, pointObject8);
    assertEquals(point9, pointObject9);
    assertEquals(point10, pointObject10);
    assertEquals(point11, pointObject11);
    assertEquals(point12, pointObject12);
  }

  @Test
  public void testEqualsFail() {
    Point2D pointObject1 = new Point2D(5.8, 6);
    Point2D pointObject2 = new Point2D(-1, 1.12);
    Point2D pointObject3 = new Point2D(3.9998, -0.09);
    Point2D pointObject4 = new Point2D(-0.9, 1.18);
    Point2D pointObject5 = new Point2D(-5, -120);
    Point2D pointObject6 = new Point2D(50, 0);
    Point2D pointObject7 = new Point2D(-5.5, 0.1);
    Point2D pointObject8 = new Point2D(0.999999, -5);
    Point2D pointObject9 = new Point2D(8.000001, 6);
    Point2D pointObject10 = new Point2D(-4.4, -12.9);
    Point2D pointObject11 = new Point2D(-10.20, 12);
    Point2D pointObject12 = new Point2D(0.1, 0);

    assertNotEquals(point1, pointObject1);
    assertNotEquals(point2, pointObject2);
    assertNotEquals(point3, pointObject3);
    assertNotEquals(point4, pointObject4);
    assertNotEquals(point5, pointObject5);
    assertNotEquals(point6, pointObject6);
    assertNotEquals(point7, pointObject7);
    assertNotEquals(point8, pointObject8);
    assertNotEquals(point9, pointObject9);
    assertNotEquals(point10, pointObject10);
    assertNotEquals(point11, pointObject11);
    assertNotEquals(point12, pointObject12);
  }
}