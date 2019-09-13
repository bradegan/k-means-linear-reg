import java.util.List;

/**
 * Abstract Class AbstractCalc that operates upon data in the form of a list of (x,y) points to
 * calculate the bar or numerical average of a set of data's x and y coordinates.
 */
public abstract class AbstractCalc {

  /**
   * Private Method getXBar that calculates the bar or numerical average of a set of data's x
   * coordinates. The bar is the sum of all values divided by the number of values.
   *
   * @param list to represent a list of points.
   * @return to represent the calculated x bar.
   */
  protected double getXbar(List<Point2D> list) {
    double xBar = 0;
    for (Point2D i : list) {
      xBar += i.getX();
    }
    return xBar / list.size();
  }

  /**
   * Private Method getYBar that calculates the bar or numerical average of a set of data's y
   * coordinates. The bar is the sum of all values divided by the number of values.
   *
   * @param list to represent a list of points.
   * @return to represent the calculated y bar of the list of points.
   */
  protected double getYbar(List<Point2D> list) {
    double yBar = 0;
    for (Point2D i : list) {
      yBar += i.getY();
    }
    return yBar / list.size();
  }
}