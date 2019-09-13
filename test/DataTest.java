import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotEquals;

/**
 * Class DataTest to test the Data Class.
 */
public class DataTest {
  private Data obj1;

  @Before
  public void setUp() {
    obj1 = new Data();
  }

  @Test
  public void testAddPointListOfPoints() {
    obj1.addPoint(new Point2D(5.1, 6));
    assertEquals("[(5.1,6.0)]", obj1.getListOfPoints().toString());
    obj1.addPoint(new Point2D(0, 5));
    assertEquals("[(5.1,6.0), (0.0,5.0)]", obj1.getListOfPoints().toString());
    obj1.addPoint(new Point2D(-4, 4.2));
    assertEquals("[(5.1,6.0), (0.0,5.0), (-4.0,4.2)]", obj1.getListOfPoints().toString());
  }

  @Test
  public void testLineSetup() {
    Point2D p1 = new Point2D(0, 5);
    Point2D p2 = new Point2D(5.1, 6);

    Line line = new Line(p1, p2);

    assertEquals(0, line.getLeftPoint().getX(), 0.0001);
    assertEquals(5, line.getLeftPoint().getY(), 0.0001);
    assertEquals(5.1, line.getRightPoint().getX(), 0.0001);
    assertEquals(6, line.getRightPoint().getY(), 0.0001);
  }

  @Test
  public void testFitLine1() {
    Data d1 = new Data();

    d1.addPoint(new Point2D(0, 1));
    d1.addPoint(new Point2D(1, 1));
    d1.addPoint(new Point2D(4, 1));

    Line l1 = d1.fitLine();

    assertEquals(0, l1.getLeftPoint().getX(), 0.0001);
    assertEquals(1, l1.getLeftPoint().getY(), 0.0001);
    assertEquals(4, l1.getRightPoint().getX(), 0.0001);
    assertEquals(1, l1.getRightPoint().getY(), 0.0001);
  }

  @Test
  public void testFitLine2() {
    Data d1 = new Data();

    d1.addPoint(new Point2D(6.5, 26.65));
    d1.addPoint(new Point2D(5.48, 25.03));
    d1.addPoint(new Point2D(6.54, 26.01));
    d1.addPoint(new Point2D(7.18, 27.19));
    d1.addPoint(new Point2D(6.2, 26.3));
    d1.addPoint(new Point2D(7.93, 30.47));

    Line l1 = d1.fitLine();

    assertEquals(5.48, l1.getLeftPoint().getX(), 0.4);
    assertEquals(24.536, l1.getLeftPoint().getY(), 0.4);
    assertEquals(7.93, l1.getRightPoint().getX(), 0.4);
    assertEquals(29.624, l1.getRightPoint().getY(), 0.4);
  }

  @Test
  public void testFitLine3() {
    Data d1 = new Data();

    d1.addPoint(new Point2D(1, 1));
    d1.addPoint(new Point2D(2, 2));
    d1.addPoint(new Point2D(3, 3));
    d1.addPoint(new Point2D(4, 4));
    d1.addPoint(new Point2D(5, 5));

    Line l1 = d1.fitLine();

    assertEquals(1, l1.getLeftPoint().getX(), 0.3);
    assertEquals(1, l1.getLeftPoint().getY(), 0.3);
    assertEquals(5, l1.getRightPoint().getX(), 0.3);
    assertEquals(5, l1.getRightPoint().getY(), 0.3);
  }

  @Test
  public void testReadingandDrawing() {
    Data dataPoints = new Data();

    try {
      DescartesScan obj = new DescartesScan(dataPoints, "data/linedata-1.txt");
      obj.loadPoints();
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    Line line = dataPoints.fitLine();
    assertEquals("(-400.0,-270.09065902014476) and (398.79,370.3824700443886)",
            dataPoints.fitLine().toString());
  }


  @Test
  public void kmeans() {
    Data dataPoints = new Data();
    try {
      DescartesScan obj = new DescartesScan(dataPoints, "data/clusterdata-2.txt");
      obj.loadPoints();
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    dataPoints.kmeans(2);
  }

  @Test
  public void testkMeans1() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1, 0));
    obj.addPoint(new Point2D(0, 1));
    obj.addPoint(new Point2D(-1, 0));
    obj.addPoint(new Point2D(0, -1));

    obj.kmeans(1);

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    assertEquals(expectedAssignmentList, obj.kmeans(1));
  }

  @Test
  public void testkMeans2() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1.0, 0));
    obj.addPoint(new Point2D(0, 1.0));
    obj.addPoint(new Point2D(-1.0, 0));
    obj.addPoint(new Point2D(0, -1.0));

    obj.kmeans(1);

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    assertEquals(expectedAssignmentList, obj.kmeans(1));
  }

  @Test
  public void testkMeans3() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1.0, 0));
    obj.addPoint(new Point2D(0, 1.0));
    obj.addPoint(new Point2D(-1.0, 0));
    obj.addPoint(new Point2D(0, -1.0));

    obj.kmeans(1);

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    assertEquals(expectedAssignmentList, obj.kmeans(1));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testkMeans4() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1.0, 0));
    obj.addPoint(new Point2D(0, 1.0));
    obj.addPoint(new Point2D(-1.0, 0));
    obj.addPoint(new Point2D(0, -1.0));

    obj.kmeans(5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testkMeans5() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1.0, 0));
    obj.addPoint(new Point2D(0, 1.0));
    obj.addPoint(new Point2D(-1.0, 0));
    obj.addPoint(new Point2D(0, -1.0));

    obj.kmeans(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testkMeans6() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1.0, 0));
    obj.addPoint(new Point2D(0, 1.0));
    obj.addPoint(new Point2D(-1.0, 0));
    obj.addPoint(new Point2D(0, -1.0));

    obj.kmeans(-1);
  }

  @Test
  public void testkMeans7() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1, 1));
    obj.addPoint(new Point2D(1, -1));
    obj.addPoint(new Point2D(-1, 1));
    obj.addPoint(new Point2D(-1, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(2);
    expectedAssignmentList.add(3);

    List<Integer> actualList = obj.kmeans(4);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans8() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1, 1));
    obj.addPoint(new Point2D(1, -1));
    obj.addPoint(new Point2D(-1, 1));
    obj.addPoint(new Point2D(-1, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(2);
    expectedAssignmentList.add(3);

    List<Integer> actualList = obj.kmeans(4);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans9() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1, 1));
    obj.addPoint(new Point2D(1, -1));
    obj.addPoint(new Point2D(-1, 1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(2);

    List<Integer> actualList = obj.kmeans(3);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans10() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1, 1));
    obj.addPoint(new Point2D(1, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(1);

    List<Integer> actualList = obj.kmeans(2);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans11() {
    Data obj = new Data();

    obj.addPoint(new Point2D(10, 1));
    obj.addPoint(new Point2D(10.0, 1));
    obj.addPoint(new Point2D(10.2, 1));
    obj.addPoint(new Point2D(10.00, 1));
    obj.addPoint(new Point2D(10.21, 1));

    obj.addPoint(new Point2D(10, -1));
    obj.addPoint(new Point2D(10.0, -1));
    obj.addPoint(new Point2D(10.2, -1));
    obj.addPoint(new Point2D(10.00, -1));
    obj.addPoint(new Point2D(10.21, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);

    List<Integer> actualList = obj.kmeans(2);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans12() {
    Data obj = new Data();

    obj.addPoint(new Point2D(10, 1));
    obj.addPoint(new Point2D(10.0, 1));
    obj.addPoint(new Point2D(10.2, 1));
    obj.addPoint(new Point2D(10.00, 1));
    obj.addPoint(new Point2D(10.21, 1));

    obj.addPoint(new Point2D(10, -1));
    obj.addPoint(new Point2D(10.0, -1));
    obj.addPoint(new Point2D(10.2, -1));
    obj.addPoint(new Point2D(10.00, -1));
    obj.addPoint(new Point2D(10.21, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);

    List<Integer> actualList = obj.kmeans(2);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans13() {
    Data obj = new Data();

    obj.addPoint(new Point2D(-10, 1));
    obj.addPoint(new Point2D(-10.0, 1));
    obj.addPoint(new Point2D(-10.2, 1));
    obj.addPoint(new Point2D(-10.00, 1));
    obj.addPoint(new Point2D(-10.21, 1));

    obj.addPoint(new Point2D(-10, -1));
    obj.addPoint(new Point2D(-10.0, -1));
    obj.addPoint(new Point2D(-10.2, -1));
    obj.addPoint(new Point2D(-10.00, -1));
    obj.addPoint(new Point2D(-10.21, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);

    List<Integer> actualList = obj.kmeans(2);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans14() {
    Data obj = new Data();

    obj.addPoint(new Point2D(1, 1));
    obj.addPoint(new Point2D(1.2, 1));
    obj.addPoint(new Point2D(1.00, 1));
    obj.addPoint(new Point2D(1.21, 1));
    obj.addPoint(new Point2D(1.0, 1));

    obj.addPoint(new Point2D(1, -1));
    obj.addPoint(new Point2D(1.0, -1));
    obj.addPoint(new Point2D(1.2, -1));
    obj.addPoint(new Point2D(1.00, -1));
    obj.addPoint(new Point2D(1.21, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);

    List<Integer> actualList = obj.kmeans(2);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }

  @Test
  public void testkMeans15() {
    Data obj = new Data();

    obj.addPoint(new Point2D(-1, 1));
    obj.addPoint(new Point2D(-1.0, 1));
    obj.addPoint(new Point2D(-1.2, 1));
    obj.addPoint(new Point2D(-1.00, 1));
    obj.addPoint(new Point2D(-1.21, 1));

    obj.addPoint(new Point2D(-1, -1));
    obj.addPoint(new Point2D(-1.0, -1));
    obj.addPoint(new Point2D(-1.2, -1));
    obj.addPoint(new Point2D(-1.00, -1));
    obj.addPoint(new Point2D(-1.21, -1));

    List<Integer> expectedAssignmentList = new ArrayList<Integer>();

    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);
    expectedAssignmentList.add(0);

    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);
    expectedAssignmentList.add(1);

    List<Integer> actualList = obj.kmeans(2);
    Collections.sort(actualList);

    assertEquals(expectedAssignmentList, actualList);
  }


  @Test
  public void testAddPoint2() {
    Data obj = new Data();

    obj.addPoint(new Point2D(-10, 1));
    obj.addPoint(new Point2D(-10.0, 1));
    obj.addPoint(new Point2D(-10.2, 1));
    obj.addPoint(new Point2D(-10.00, 1));
    obj.addPoint(new Point2D(-10.21, 1));

    List<Point2D> actual = obj.getListOfPoints();

    List<Point2D> expectedAssignmentList = new ArrayList<Point2D>();

    expectedAssignmentList.add(new Point2D(-10, 1));
    expectedAssignmentList.add(new Point2D(-10.0, 1));
    expectedAssignmentList.add(new Point2D(-10.2, 1));
    expectedAssignmentList.add(new Point2D(-10.00, 1));
    expectedAssignmentList.add(new Point2D(-10.21, 1));

    assertEquals(expectedAssignmentList, actual);
  }

  @Test
  public void testLoadPointsCorrectNumPoints() {
    Data obj = new Data();

    try {
      DescartesScan scan = new DescartesScan(obj, "data/linedata-1.txt");
      scan.loadPoints();

    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    assertEquals(396, obj.getListOfPoints().size());
  }

  @Test
  public void testLoadPointsGreaterNumPoints() {
    Data obj = new Data();

    try {
      DescartesScan scan = new DescartesScan(obj, "data/linedata-1.txt");
      scan.loadPoints();

    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    assertNotEquals(399, obj.getListOfPoints().size());
  }

  @Test
  public void testLoadPointsLessThanNumPoints() {
    Data obj = new Data();

    try {
      DescartesScan scan = new DescartesScan(obj, "data/linedata-1.txt");
      scan.loadPoints();

    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    assertNotEquals(300, obj.getListOfPoints().size());
  }

  @Test
  public void testLoadPointsIncorrectNumPointPairs() {
    Data obj = new Data();

    try {
      DescartesScan scan = new DescartesScan(obj, "data/test-data.txt");
      scan.loadPoints();
      fail("An exception should have been thrown");
    } catch (IOException e) {

      assertEquals("File read error.", e.getMessage());
    }
  }

  @Test
  public void testLoadPointsWrongFile1() {
    Data obj = new Data();

    try {
      DescartesScan scan = new DescartesScan(obj, "data/Z-1.txt");
      scan.loadPoints();
      fail("An exception should have been thrown");
    } catch (IOException e) {
      assertEquals("File read error.", e.getMessage());
    }
  }

  @Test
  public void testLoadPointsWrongFile2() {
    Data obj = new Data();

    try {
      DescartesScan scan = new DescartesScan(obj, "data/Z-1.csv");
      scan.loadPoints();
      fail("An exception should have been thrown");
    } catch (IOException e) {

      assertEquals("File read error.", e.getMessage());
    }
  }
}

