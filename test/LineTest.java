import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Class LineTest to test the Line Class.
 */
public class LineTest {
  private Line line1;
  private Line line2;
  private Line line3;
  private Line line4;
  private Line line5;
  private Line line6;
  private Line line7;

  @Before
  public void setUp() {
    line1 = new Line(new Point2D(-5, 0), new Point2D(5, 0));
    line2 = new Line(new Point2D(5, 0), new Point2D(-5, 0));
    line3 = new Line(new Point2D(-5.5, 0), new Point2D(5.6, 0));
    line4 = new Line(new Point2D(0, -5), new Point2D(0, 5));
    line5 = new Line(new Point2D(8, 6), new Point2D(8, -6));
    line6 = new Line(new Point2D(-4.4, -12), new Point2D(100.0, 2.6));
    line7 = new Line(new Point2D(-10.21, 12), new Point2D(100.0, -2.6));
  }

  @Test
  public void constructorPass() {
    assertEquals("(-5.0,0.0) and (5.0,0.0)", line1.toString());
    assertEquals("(5.0,0.0) and (-5.0,0.0)", line2.toString());
    assertEquals("(-5.5,0.0) and (5.6,0.0)", line3.toString());
    assertEquals("(0.0,-5.0) and (0.0,5.0)", line4.toString());
    assertEquals("(8.0,6.0) and (8.0,-6.0)", line5.toString());
    assertEquals("(-4.4,-12.0) and (100.0,2.6)", line6.toString());
    assertEquals("(-10.21,12.0) and (100.0,-2.6)", line7.toString());
  }

  @Test
  public void constructorFail() {
    try {
      Line fail1 = new Line(new Point2D(0, 0), new Point2D(0, 0));
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("A line cannot be the same point.", e.getMessage());
    }

    try {
      Line fail1 = new Line(new Point2D(12.0, -1), new Point2D(12.0, -1.0));
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("A line cannot be the same point.", e.getMessage());
    }

    try {
      Line fail2 = new Line(null, new Point2D(0, 0));
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Point on a line cannot be null.", e.getMessage());
    }

    try {
      Line fail3 = new Line(new Point2D(0, 0), null);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Point on a line cannot be null.", e.getMessage());
    }

    try {
      Line fail4 = new Line(null, null);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Point on a line cannot be null.", e.getMessage());
    }
  }

  @Test
  public void testGetLeftPoint() {
    assertEquals("(-5.0,0.0)", line1.getLeftPoint().toString());
    assertEquals("(5.0,0.0)", line2.getLeftPoint().toString());
    assertEquals("(-5.5,0.0)", line3.getLeftPoint().toString());
    assertEquals("(0.0,-5.0)", line4.getLeftPoint().toString());
    assertEquals("(8.0,6.0)", line5.getLeftPoint().toString());
    assertEquals("(-4.4,-12.0)", line6.getLeftPoint().toString());
    assertEquals("(-10.21,12.0)", line7.getLeftPoint().toString());
  }

  @Test
  public void testGetRightPoint() {
    assertEquals("(5.0,0.0)", line1.getRightPoint().toString());
    assertEquals("(-5.0,0.0)", line2.getRightPoint().toString());
    assertEquals("(5.6,0.0)", line3.getRightPoint().toString());
    assertEquals("(0.0,5.0)", line4.getRightPoint().toString());
    assertEquals("(8.0,-6.0)", line5.getRightPoint().toString());
    assertEquals("(100.0,2.6)", line6.getRightPoint().toString());
    assertEquals("(100.0,-2.6)", line7.getRightPoint().toString());
  }

  @Test
  public void testEqualsPass() {
    Line lineObject1 = new Line(new Point2D(-5, 0), new Point2D(5, 0));
    Line lineObject2 = new Line(new Point2D(5, 0), new Point2D(-5, 0));
    Line lineObject3 = new Line(new Point2D(-5.5, 0), new Point2D(5.6, 0));
    Line lineObject4 = new Line(new Point2D(0, -5), new Point2D(0, 5));
    Line lineObject5 = new Line(new Point2D(8, 6), new Point2D(8, -6));
    Line lineObject6 = new Line(new Point2D(-4.4, -12), new Point2D(100.0, 2.6));
    Line lineObject7 = new Line(new Point2D(-10.21, 12), new Point2D(100.0, -2.6));

    assertEquals(line1, lineObject1);
    assertEquals(line2, lineObject2);
    assertEquals(line3, lineObject3);
    assertEquals(line4, lineObject4);
    assertEquals(line5, lineObject5);
    assertEquals(line6, lineObject6);
    assertEquals(line7, lineObject7);
  }

  @Test
  public void testEqualsFail() {
    Line lineObject1 = new Line(new Point2D(-6, 0), new Point2D(5, 0));
    Line lineObject2 = new Line(new Point2D(9, 0), new Point2D(-5, 0));
    Line lineObject3 = new Line(new Point2D(-5.5, 0), new Point2D(5.56, 0));
    Line lineObject4 = new Line(new Point2D(0, -5), new Point2D(0.1, 5));
    Line lineObject5 = new Line(new Point2D(8, 6.66), new Point2D(8, -6));
    Line lineObject6 = new Line(new Point2D(-4.3, -12), new Point2D(100.0, 2.6));
    Line lineObject7 = new Line(new Point2D(-10.21, 12), new Point2D(100.1, -2.6));

    assertNotEquals(line1, lineObject1);
    assertNotEquals(line2, lineObject2);
    assertNotEquals(line3, lineObject3);
    assertNotEquals(line4, lineObject4);
    assertNotEquals(line5, lineObject5);
    assertNotEquals(line6, lineObject6);
    assertNotEquals(line7, lineObject7);
  }
}