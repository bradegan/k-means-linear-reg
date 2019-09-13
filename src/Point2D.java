/**
 * This class represents a 2D point. This point is denoted in Cartesian coordinates as (x,y).
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Constructs a Point2D object with the given x and y coordinates.
   *
   * @param x the x-coordinate of this point.
   * @param y the y-coordinate of this point.
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Protected Method getX returns the x-coordinate of this point.
   *
   * @return (double) to represent the x-coordinate of this point.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Protected Method getY returns the y-coordinate of this point.
   *
   * @return (double) to represent the y-coordinate of this point.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Public Method toString returns the properly formatted string representation of this Point2D.
   *
   * @return (String) representation of this Point2D's x and y coordinates.
   */
  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  /**
   * Public Method equals returns whether This Point2D object is equal to the Object other.
   *
   * @param other (Object) to represent another object.
   * @return (boolean) to represent whether This Point2D object is the same as Object other.
   */
  @Override
  public boolean equals(Object other) {
    if ((other == null) || (getClass() != other.getClass())) {
      return false;
    }

    Point2D otherPoint = (Point2D) other;
    return (this.getX() == otherPoint.getX() && this.getY() == otherPoint.getY());
  }
}