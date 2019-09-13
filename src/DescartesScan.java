import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class DescartesScan that reads data file and saves it to a Data object so the data can be
 * calculated and the results of k-means clustering or a linear regression can be plotted. The file
 * name is a file path to a .txt file. Each line of the file should have two values seperated by a
 * space to represent an x coordinate and a y coordinate of a point on a cartesian plane. Each line
 * is stored as a Point2D and added to a Data object.
 */
public class DescartesScan {
  private List<String> listOfPoints;
  private Data data;
  private Path fileName;

  /**
   * Constructs a DescartesScan Object with fields for a Data object, a filename/path, and list of
   * strings from the file.
   *
   * @param data     to represent the Data object the file will be read to.
   * @param fileName to represent the filename/path of the .txt file to be scanned.
   */
  public DescartesScan(Data data, String fileName) {
    this.data = data;
    this.fileName = getPath(fileName);
    this.listOfPoints = new ArrayList<>();
  }

  /**
   * Private Method getPath converts the filename/path (relative path) to an absolute path for
   * processing. Returns path obj from given filepath string.
   *
   * @param relativePath to represent the filename/path of the .txt file to be scanned.
   * @return (Path) path ro represent the absolute path of the .txt file to be scanned.
   */
  private Path getPath(String relativePath) {
    Path path = Paths.get(relativePath);
    return path;
  }

  /**
   * Private Method openFile opens the path object, scans space-delimited item from the file and
   * stores it in a list of strings to be returned.
   *
   * @param path to represent the filename/path of the .txt file to be scanned.
   * @return (List < String >) listOfPoints to represent the scanned data.
   * @throws IOException if the file does not exist or other IO issue.
   */
  private List<String> openFile(Path path) throws IOException {
    try {
      Scanner scanner = new Scanner(new File(String.valueOf(path)));
      while (scanner.hasNext()) {
        listOfPoints.add(scanner.next());
      }
      scanner.close();
      return listOfPoints;

    } catch (IOException e) {
      throw new IOException("File read error.");
    }
  }

  /**
   * Protected Method loadPoints adds each string in the listOfPoints as a List2D data point to a
   * Data object. Because each point requires an x and a y, all even indexes in the listOfPoints are
   * x values, and all odd indexes are y values. If the size of the list is not even, or it is
   * empty, or less than 2, an error is thrown.
   *
   * @throws IOException If the data from the file is bad (size of the list is not even, or it is
   *                     empty, or less than 2).
   */
  protected void loadPoints() throws IOException {
    List<String> tempList = openFile(this.fileName);

    if (tempList.size() % 2 != 0 || tempList.size() < 2 || tempList.isEmpty()) {
      throw new IOException("File read error.");
    }

    int i = 0;
    while (i < tempList.size()) {
      double tempX = Double.parseDouble(tempList.get(i));
      i++;
      double tempY = Double.parseDouble(tempList.get(i));
      i++;
      this.data.addPoint(new Point2D(tempX, tempY));
    }
  }
}