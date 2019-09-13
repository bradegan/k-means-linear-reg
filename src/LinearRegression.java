import java.util.List;

/**
 * Class LinearRegression that operates upon data in the form of (x,y) points to use the linear
 * regression statistical technique to determine a line of best fit. The points may not lie on any
 * single line but can be visually graphed to show a linear pattern. Since we do not know if the
 * data perfectly fits on a line, we try to find a “best-fit” line. This means determining a line
 * that minimizes the distance between the points and the line. A popular technique to do this is
 * least-squares line fitting. Given a line, we compute the distance of each point to this line, and
 * minimize the sum of squares of this distance.
 */
public class LinearRegression extends AbstractCalc {

  /**
   * Private Method getSyy that calculates the total variability of the list's y values from the y
   * bar.
   *
   * @param list to represent a list of points.
   * @param yBar (double) to represent the calculated y bar of the list of points.
   * @return (double) to represent the calculated y variability.
   */
  private double getSyy(List<Point2D> list, double yBar) {
    double sYY = 0;

    for (Point2D i : list) {
      sYY += Math.pow((i.getY() - yBar), 2);
    }
    return sYY;
  }

  /**
   * Private Method getSxx that calculates the total variability of the list's x values from the x
   * bar.
   *
   * @param list to represent a list of points.
   * @param xBar (double) to represent the calculated x bar of the list of points.
   * @return (double) to represent the calculated x variability.
   */
  private double getSxx(List<Point2D> list, double xBar) {
    double sXX = 0;

    for (Point2D i : list) {
      sXX += Math.pow((i.getX() - xBar), 2);
    }
    return sXX;
  }

  /**
   * Private Method getSxy that calculates the total variability of the list's x and y values from
   * the x bar and y bar.
   *
   * @param list to represent a list of points.
   * @param xBar (double) to represent the calculated x bar of the list of points.
   * @param yBar (double) to represent the calculated y bar of the list of points.
   * @return (double) to represent the calculated x and y variability.
   */
  private double getSxy(List<Point2D> list, double xBar, double yBar) {
    double sXY = 0;

    for (Point2D i : list) {
      sXY += ((i.getX() - xBar) * (i.getY() - yBar));
    }
    return sXY;
  }

  /**
   * Private Method getD that calculates the slope of a “best-fit” line for the list of points.
   *
   * @param sYY (double) to represent the calculated y variability.
   * @param sXX (double) to represent the calculated x variability.
   * @param sXY (double) to represent the calculated xy variability.
   * @return (double) to represent the calculated slope.
   */
  private double getD(double sXY, double sXX, double sYY) {
    return (2 * sXY) / (sXX - sYY);
  }

  /**
   * Private Method getTheta that calculates theta of the “best-fit” line for the list of points.
   *
   * @param d (double) to represent represent the calculated slope.
   * @return (double) to represent theta, the calculated slope (in radians).
   */
  private double getTheta(double d) {
    return Math.atan(d);
  }

  /**
   * Private Method getFofT that determines the value of theta that makes f(t) positive in the
   * equation f(t) = (Syy - Sxx) * cos(t) - 2 * Sxy * sin(t).
   *
   * @param sYY (double) to represent the calculated y variability.
   * @param sXX (double) to represent the calculated x variability.
   * @param sXY (double) to represent the calculated xy variability.
   * @param t   (double) to represent theta, the calculated slope (in radians).
   * @return (double) to represent theta, the calculated slope (in radians).
   */
  private double getFofT(double sYY, double sXX, double sXY, double t) {
    if (((sYY - sXX) * Math.cos(t) - (2 * sXY) * Math.sin(t)) < 0) {
      //return t + 180;
      return t + Math.PI;
    }
    return t;
  }

  /**
   * Private Method getA that determines the value of a in the equation a*x + b*y + c = 0. We can
   * represent the equation as c = -a*xBar - b*yBar to get the best-fit line.
   *
   * @param fOfT (double) to represent the calculated slope (in radians).
   * @return (double) to represent value a in the equation a*x + b*y + c = 0.
   */
  private double getA(double fOfT) {
    return Math.cos((fOfT / 2));
  }

  /**
   * Private Method getB that determines the value of b in the equation a*x + b*y + c = 0. We can
   * represent the equation as c = -a*xBar - b*yBar to get the best-fit line.
   *
   * @param fOfT (double) to represent the calculated slope (in radians).
   * @return (double) to represent value b in the equation a*x + b*y + c = 0.
   */
  private double getB(double fOfT) {
    return Math.sin((fOfT / 2));
  }

  /**
   * Private Method getC that determines the value of c in the equation a*x + b*y + c = 0. We can
   * represent the equation as c = -a*xBar - b*yBar to get the best-fit line.
   *
   * @param a    (double) to represent value a in the equation a*x + b*y + c = 0.
   * @param b    (double) to represent value b in the equation a*x + b*y + c = 0.
   * @param xBar (double) to represent the calculated x bar of the list of points.
   * @param yBar (double) to represent the calculated y bar of the list of points.
   * @return (double) to represent the calculated value c.
   */
  private double getC(double a, double b, double xBar, double yBar) {
    return -(a * xBar) - (b * yBar);
  }

  /**
   * Method getLine that determines the best-fit line for a list of data using least-squares line
   * fitting. Given a line, we compute the distance of each point to this line, and minimize the sum
   * of squares of this distance. This is done by solving y = -a*x/b - c/b (a*x + b*y + c = 0) for
   * the y values of the maximum and minimum x values from the list of points. We then return a new
   * Line object between these left and right points.
   *
   * @return (Line) to represent the best-fit line.
   */
  protected Line getLine(List<Point2D> list, double minX, double maxX) {
    double xBar = getXbar(list);
    double yBar = getYbar(list);

    double sYY = getSyy(list, yBar);
    double sXX = getSxx(list, xBar);
    double sXY = getSxy(list, xBar, yBar);

    double d = getD(sXY, sXX, sYY);
    double theta = getTheta(d);
    double t = getFofT(sYY, sXX, sXY, theta);

    double a = getA(t);
    double b = getB(t);
    double c = getC(a, b, xBar, yBar);

    // y = - Ax/B - C/B
    // y = - 1/B * (Ax + C)
    double solveYforMinX = (-1 / b) * (a * minX + c);
    double solveYforMaxX = (-1 / b) * (a * maxX + c);

    Point2D leftPoint = new Point2D(minX, solveYforMinX);
    Point2D rightPoint = new Point2D(maxX, solveYforMaxX);

    return new Line(leftPoint, rightPoint);
  }
}