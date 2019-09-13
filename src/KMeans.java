import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class KMeans that operates upon data in the form of (x,y) points to use the k-means statistical
 * technique to determine if the data can be divided into naturally arising categories. Intuitively,
 * if we plot the points in a graph we can visualize that the points form clusters. The k-means
 * clustering algorithm is a simple but popular technique to group data into k categories, given a
 * specific value of k. These groupings can be visually graphed to show a getKM pattern. Since we
 * want to see if the groupings can arise naturally, we randomly choose k points from the data to be
 * the centroids. Then, each data point is assigned to a centroid grouping by computing the distance
 * between that point and the center of the getKM, and choosing the getKM with the minimum
 * distance.
 */

public class KMeans extends AbstractCalc {
  private List<Point2D> centroidList;
  private List<Integer> assignmentList;

  public KMeans() {
    this.assignmentList = assignmentList;
    this.centroidList = centroidList;
  }

  /**
   * Private Method euclDist computes the distance between two points as the Euclidean distance.
   *
   * @param point1 (Point2D) to represent a point.
   * @param point2 (Point2D) to represent a point.
   * @return (double) to represent the Euclidean distance between the two points.
   */
  private double euclDist(Point2D point1, Point2D point2) {
    return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2)
            + Math.pow(point1.getY() - point2.getY(), 2));
  }

  /**
   * Private Method createCentroids randomly chooses a set number k of points from a data set. The
   * points are stored in This listOfCentroids. This list of points of size k represents the
   * centroids.
   *
   * @param numK (int) to represent k number of data points to be randomly chosen from this list.
   * @return listOfCentroids to represent a list of size k number of centroids.
   */
  private List<Point2D> createCentroids(int numK, List<Point2D> dataList) {
    List<Point2D> listOfCentroids = new ArrayList<>();

    while (listOfCentroids.size() < numK) {
      Random random = new Random();
      Point2D centroid = dataList.get(random.nextInt(dataList.size()));

      if (!listOfCentroids.contains(centroid)) {
        listOfCentroids.add(centroid);
      }
    }
    return listOfCentroids;
  }

  /**
   * Private Method makeAssignment categorizes each data point from a dataList to a centroid in the
   * centroidList by computing the distance between that point and every centroid in the
   * centroidList. The index (centroid number) of the corresponding centroid with the minimum
   * distance to the point is then assigned to the corresponding index (data point number) in a new
   * listOfAssignments.
   *
   * @param dataList     to represent to represent a list of points.
   * @param centroidList to represent a list of size k number of centroids.
   * @return listOfAssignments to represent the centroid number assigned to the corresponding data
   * point.
   */
  private List<Integer> makeAssignment(List<Point2D> dataList, List<Point2D> centroidList) {
    List<Integer> listOfAssignments = new ArrayList<>();

    for (Point2D dataPoint : dataList) {
      List<Double> distanceList = new ArrayList<>();

      for (Point2D centroid : centroidList) {
        distanceList.add(euclDist(dataPoint, centroid));
      }
      int indexMin = distanceList.indexOf(Collections.min(distanceList));
      listOfAssignments.add(indexMin);
    }
    return listOfAssignments;
  }

  /**
   * Private Method calcAverageCentroids re-calculates the centers of data clusters given the list
   * of data points' assigned centroid numbers. This is done by iterating for the size k number of
   * centroids through the centroids list of assigned centroid numbers corresponding to the
   * dataList. For each data point assignment in the assignmentList, if the centroid number matches
   * the value of the data point's assigned centroid in the assignmentList, the corresponding point
   * in the dataList is added to the clusterAssignment. The clusterAssignments' x and y values are
   * averaged and added to a list of average centroids.
   *
   * @param numK           (int) to represent k number of data points to be randomly chosen from
   *                       this list.
   * @param dataList       to represent to represent a list of points.
   * @param assignmentList to represent the centroid number assigned to the corresponding data
   *                       point.
   * @return listOfAvgCentroids to represent the new calculated centroid clusters.
   */
  private List<Point2D> calcAverageCentroids(int numK, List<Point2D> dataList,
                                             List<Integer> assignmentList) {
    List<Point2D> listOfAvgCentroids = new ArrayList<>();

    for (int centroidNum = 0; centroidNum < numK; centroidNum++) {
      List<Point2D> clusterAssignment = new ArrayList<>();

      for (int dataPointAssignment = 0; dataPointAssignment < assignmentList.size();
           dataPointAssignment++) {
        if (centroidNum == assignmentList.get(dataPointAssignment)) {
          clusterAssignment.add(dataList.get(dataPointAssignment));
        }
      }
      listOfAvgCentroids.add(new Point2D(getXbar(clusterAssignment), getYbar(clusterAssignment)));
    }
    return listOfAvgCentroids;
  }

  /**
   * Private Method calcNewError computes the new error by calculating the total distance and
   * dividing by the number of data points. This is done by looping through the averageCentroids and
   * then through the assignmentList. For each data point assignment in the assignmentList, if the
   * centroid number matches the value of the data point's assigned centroid in the assignmentList,
   * the distance is calculated and added to the total distance counter. We are calculating the
   * distance from each data point to each centroid, adding it to the counter and dividing by number
   * of data points to return the average total distance or error.
   *
   * @param dataList         (List<Point2D>) to represent to represent a list of points.
   * @param assignmentList   (List<Point2D>) to represent the centroid number assigned to the
   *                         corresponding data point.
   * @param averageCentroids (List<Point2D>) to represent the new calculated centroid clusters.
   * @return (double) to represent the average total distance or error.
   */
  private double calcNewError(List<Point2D> dataList, List<Integer> assignmentList,
                              List<Point2D> averageCentroids) {

    List<Integer> numberOfPointPerCentroid =
            new ArrayList<>(Collections.nCopies(averageCentroids.size(), 0));
    List<Double> distanceList =
            new ArrayList<>(Collections.nCopies(averageCentroids.size(), 0.0));

    double totalDistance = 0;

    for (int centroidNum = 0; centroidNum < averageCentroids.size(); centroidNum++) {

      for (int dataPointAssignment = 0; dataPointAssignment < assignmentList.size();
           dataPointAssignment++) {

        if (centroidNum == assignmentList.get(dataPointAssignment)) {

          int count = numberOfPointPerCentroid.get(centroidNum);
          count += 1;
          numberOfPointPerCentroid.set(centroidNum, count);

          double currentDistance = distanceList.get(centroidNum);
          currentDistance += euclDist(averageCentroids.get(centroidNum),
                  dataList.get(dataPointAssignment));
          distanceList.set(centroidNum, currentDistance);

        }
      }
    }
    for (int i = 0; i < distanceList.size(); i++) {
      double newDist = distanceList.get(i) / numberOfPointPerCentroid.get(i);
      totalDistance += newDist;

    }
    return totalDistance;
  }

  /**
   * Protected getCentroids that gets and returns This centroidList.
   *
   * @return (List < Point2D >) to represent This centroidList.
   */
  protected List<Point2D> getCentroids() {
    return this.centroidList;
  }

  /**
   * Protected Method getKM performs k-means clustering on the data and returns a list of integers.
   * The ith element of this list is the cluster number assigned to the ith data point. This list
   * should align with the list of data points.
   *
   * @param dataList to represent to represent a list of points.
   * @param numK     (int) to represent k number of data points to be randomly chosen from this
   *                 list.
   * @return ransacBestAssignment to represent the best assignment of all k-means iterations.
   * @throws IllegalArgumentException if k is negative.
   */
  protected List<Integer> getKM(int numK, List<Point2D> dataList) throws IllegalArgumentException {
    if (numK < 1 || numK > dataList.size()) {
      throw new IllegalArgumentException("Use a positive k.");
    }

    List<Integer> ransacBestAssignmentList = new ArrayList<>();
    int ransacItr = 0;
    double ransacError = Double.POSITIVE_INFINITY;

    while (ransacItr < 10) {
      this.centroidList = createCentroids(numK, dataList);
      this.assignmentList = makeAssignment(dataList, this.centroidList);

      int kMeansItr = 0;
      double error = Double.POSITIVE_INFINITY;
      while (kMeansItr < 100) {
        List<Point2D> tempCentroids = calcAverageCentroids(numK, dataList, this.assignmentList);
        this.centroidList = tempCentroids;
        List<Integer> tempAssignList = makeAssignment(dataList, tempCentroids);
        double newError = calcNewError(dataList, tempAssignList, tempCentroids);

        if (((Math.abs(newError - error)) / error < 0.001)) {
          break;
        }
        if (newError <= error) {
          error = newError;
          this.assignmentList = tempAssignList;
        }
        kMeansItr++;
        error = newError;
      }
      if (error < ransacError) {
        ransacBestAssignmentList = this.assignmentList;
        ransacError = error;
      }
      ransacItr++;
    }
    return ransacBestAssignmentList;
  }
}