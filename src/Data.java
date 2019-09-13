import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class Data that stores and operates upon data in the form of (x,y) points to use the linear
 * regression statistical technique to determine a line of best fit. The points may not lie on any
 * single line but can be visually graphed to show a linear pattern. Since we do not know if the
 * data perfectly fits on a line, we try to find a “best-fit” line. This means determining a line
 * that minimizes the distance between the points and the line. A popular technique to do this is
 * least-squares line fitting. Given a line, we compute the distance of each point to this line, and
 * minimize the sum of squares of this distance.
 */
public class Data {
  private List<Point2D> listOfPoints;
  private List<Point2D> listOfCentroids;
  private double maxX;
  private double minX;

  /**
   * Constructs a DataMethod object with a listOfPoints to represent the data set, a listOfCentroids
   * to represent the centroids, a minX to represent the minimum x value in the data set for plot
   * purposes, a minX to represent the maximum x value in the data set for plot purposes.
   */
  public Data() {
    this.listOfPoints = new ArrayList<>();
    this.listOfCentroids = new ArrayList<>();
    this.maxX = Double.NEGATIVE_INFINITY;
    this.minX = Double.POSITIVE_INFINITY;
  }

  /**
   * Public Method main that calculates either a linear regression or k-means clustering on a data
   * set from a .txt file, plots the results, and saves an image of the graph as a .png. Main takes
   * arguments in specific order to determine the proper calculations. To do a linear regression or
   * k-means, args[0] must be "L" or "l", or "K" or "k" respectfully. The input file is represented
   * by args[1] and output file by args[2]. If doing a k-means clustering, args[3] represents the k
   * value. For linear regression, args[3] is not required. Example of a proper argument input:
   *
   * "K", "data/clusterdata-2.txt", "output/clusterdataU-2.png", "2" "l", "data/linedata-3.txt",
   * "output/linedataLK-3.png", "3"
   *
   * @param args to represent the terminal commands for this main function.
   * @throws IOException              if bad input or file issue.
   * @throws IllegalArgumentException if incorrect arguments for calculation provided.
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    if (args == null || args[0] == null || args[1] == null || args[2] == null) {
      throw new IllegalArgumentException("Bad input.");
    }

    if (!args[0].equals("k") && !args[0].equals("K") && !args[0].equals("l") &&
            !args[0].equals("L")) {
      throw new IllegalArgumentException("Bad input.");
    }

    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(1200);
    plotter.setHeight(1200);
    plotter.setDimensions(-600, 600, -600, 600);

    Data data = new Data();
    DescartesScan fileScan = new DescartesScan(data, args[1]);
    fileScan.loadPoints();

    if (args[0].equals("l") | args[0].equals("L")) {
      for (Point2D point : data.getListOfPoints()) {
        plotter.addPoint((int) (point.getX()), (int) (point.getY()));
      }
      Line line = data.fitLine();

      int lineLeftX = (int) (line.getLeftPoint().getX());
      int lineLeftY = (int) (line.getLeftPoint().getY());
      int lineRightX = (int) (line.getRightPoint().getX());
      int lineRightY = (int) (line.getRightPoint().getY());

      plotter.addLine(lineLeftX, lineLeftY, lineRightX, lineRightY);
    }

    if (args[0].equals("k") | args[0].equals("K")) {
      try {
        List<Integer> centroidAssignment = data.kmeans(Integer.parseInt(args[3]));
        List<Point2D> centroidList = data.getCentroids();

        List<Color> c = Arrays.asList(Color.blue, Color.magenta, Color.cyan, Color.orange,
                Color.pink, Color.red, Color.green, Color.yellow);
        Collections.shuffle(c);

        for (int i = 0; i < Integer.parseInt(args[3]); i++) {
          Color randomColor = c.get(i);
          plotter.addCircle((int) (centroidList.get(i).getX()), (int) (centroidList.get(i).getY()),
                  15, Color.black);

          for (int j = 0; j < centroidAssignment.size(); j++) {
            if (i == centroidAssignment.get(j)) {
              plotter.addPoint((int) (data.getListOfPoints().get(j).getX()),
                      (int) (data.getListOfPoints().get(j).getY()), randomColor);
            }
          }
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Bad input for K.");
      }
    }
    try {
      plotter.write(args[2]);
    } catch (IOException e) {
    }
  }

  /**
   * Protected getCentroids that gets and returns This centroidList.
   *
   * @return to represent This centroidList.
   */
  private List<Point2D> getCentroids() {
    return this.listOfCentroids;
  }

  /**
   * Method addPoint that adds a point (x,y) in the form of a Point2D object.
   *
   * @param point to represent a data point on a graph.
   */
  public void addPoint(Point2D point) {
    if (point.getX() > maxX) {
      this.maxX = point.getX();
    }
    if (point.getX() < minX) {
      this.minX = point.getX();
    }
    this.listOfPoints.add(point);
  }

  /**
   * Method getListOfPoints that gets the data entered thus far and returns this List object.
   *
   * @return to represent this List object.
   */
  public List<Point2D> getListOfPoints() {
    return this.listOfPoints;
  }

  /**
   * Method fitLine that determines the best-fit line for a listOfPoints of data using least-squares
   * line fitting. Given a line, we compute the distance of each point to this line, and minimize
   * the sum of squares of this distance. This is done by solving y = -a*x/b - c/b (a*x + b*y + c =
   * 0) for the y values of the maximum and minimum x values from the listOfPoints of points. We
   * then return a new Line object between these left and right points.
   *
   * @return to represent the best-fit line.
   */
  public Line fitLine() {
    LinearRegression lRegression = new LinearRegression();
    return lRegression.getLine(listOfPoints, this.minX, this.maxX);
  }

  /**
   * Protected Method getKM performs k-means clustering on the data and returns a list of integers.
   * The ith element of this list is the cluster number assigned to the ith data point. This list
   * should align with the list of data points.
   *
   * @param k to represent k number of data points to be randomly chosen from this list.
   * @return ransacBestAssignment to represent the best assignment of all k-means iterations.
   * @throws IllegalArgumentException if k is negative.
   */
  public List<Integer> kmeans(final int k) throws IllegalArgumentException {
    KMeans kMeans = new KMeans();
    List<Integer> ransacBestAssignment = kMeans.getKM(k, this.listOfPoints);
    this.listOfCentroids = kMeans.getCentroids();
    return ransacBestAssignment;
  }
}