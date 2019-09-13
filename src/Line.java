/**
 * This class represents a Line with a Point2D left point and a Point2D right point. This left and
 * right points (Point2D) are denoted in Cartesian coordinates as (x,y).
 */
public class Line {
  private Point2D leftPoint;
  private Point2D rightPoint;

  /**
   * Constructs a Line object with the given Point2Ds as the left and right end-points.
   *
   * @param leftPoint  the furthest left point of line.
   * @param rightPoint the furthest right point of line.
   * @throws IllegalArgumentException if leftPoint or rightPoint is the same or a null object.
   */
  public Line(Point2D leftPoint, Point2D rightPoint) throws IllegalArgumentException {
    if (leftPoint == null || rightPoint == null) {
      throw new IllegalArgumentException("Point on a line cannot be null.");
    }
    if (leftPoint.equals(rightPoint) || rightPoint.equals(leftPoint)) {
      throw new IllegalArgumentException("A line cannot be the same point.");
    }
    this.leftPoint = leftPoint;
    this.rightPoint = rightPoint;
  }

  /**
   * Protected Method getLeftPoint returns the left-point of this Line.
   *
   * @return (Point2D) to represent the left-point of this Line.
   */
  public Point2D getLeftPoint() {
    return leftPoint;
  }

  /**
   * Protected Method getRightPoint returns the right-point of this Line.
   *
   * @return (Point2D) to represent the right-point of this Line.
   */
  public Point2D getRightPoint() {
    return rightPoint;
  }

  /**
   * Public Method toString returns the properly formatted string representation of this Line.
   *
   * @return (String) representation of this Line's left and right points.
   */
  @Override
  public String toString() {
    return leftPoint.toString() + " and " + rightPoint.toString();
  }

  /**
   * Public Method equals returns whether This Line object is equal to the Object other.
   *
   * @param other (Object) to represent another object.
   * @return (boolean) to represent whether This Line object is the same as Object other.
   */
  @Override
  public boolean equals(Object other) {
    if ((other == null) || (getClass() != other.getClass())) {
      return false;
    }

    Line otherLine = (Line) other;

    return ((this.rightPoint.equals(otherLine.rightPoint)
            && this.leftPoint.equals(otherLine.leftPoint))
            || (this.rightPoint.equals(otherLine.leftPoint)
            && this.leftPoint.equals(otherLine.rightPoint)));
  }
}