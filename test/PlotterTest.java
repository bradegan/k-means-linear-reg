import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class PlotterTest to test the method main() from the Data Call with arguments for type of data
 * calculation, input file name, output file name, and k value.
 */
public class PlotterTest {

  @Test
  public void testKm() {
    for (int i = 15; i < 30; i++) {
      String out = "output/clusterdataTest_" + i + "_4.png";
      try {
        Data.main(new String[]{"K", "data/clusterdata-4.txt", out, "4"});
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testLinearRegressionLowerCaseArg() {
    try {
      Data.main(new String[]{"l", "data/linedata-1.txt", "output/linedataL-1.png"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"l", "data/linedata-2.txt", "output/linedataL-2.png"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"l", "data/linedata-3.txt", "output/linedataL-3.png"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test
  public void testLinearRegressionUpperCaseArg() {
    try {
      Data.main(new String[]{"L", "data/linedata-1.txt", "output/linedataU-1.png"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"L", "data/linedata-2.txt", "output/linedataU-2.png"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"L", "data/linedata-3.txt", "output/linedataU-3.png"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test
  public void testLinearRegressionWithKArg() {
    try {
      Data.main(new String[]{"l", "data/linedata-1.txt", "output/linedataLK-1.png", "1"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"l", "data/linedata-2.txt", "output/linedataLK-2.png", "2"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"l", "data/linedata-3.txt", "output/linedataLK-3.png", "3"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"L", "data/linedata-1.txt", "output/linedataUK-1.png", "1"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"L", "data/linedata-2.txt", "output/linedataUK-2.png", "2"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"L", "data/linedata-3.txt", "output/linedataUK-3.png", "3"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test
  public void testKMeansLowerCaseArg() {
    try {
      Data.main(new String[]{"k", "data/clusterdata-2.txt", "output/clusterdataL-2.png", "2"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"k", "data/clusterdata-3.txt", "output/clusterdataL-3.png", "3"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"k", "data/clusterdata-4.txt", "output/clusterdataL-4.png", "4"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"k", "data/clusterdata-6.txt", "output/clusterdataL-6.png", "6"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test
  public void testKMeansUpperCaseArg() {
    try {
      Data.main(new String[]{"K", "data/clusterdata-2.txt", "output/clusterdataU-2.png", "2"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"K", "data/clusterdata-3.txt", "output/clusterdataU-3.png", "3"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"K", "data/clusterdata-4.txt", "output/clusterdataU-4.png", "4"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
    try {
      Data.main(new String[]{"K", "data/clusterdata-6.txt", "output/clusterdataU-6.png", "6"});
    } catch (IOException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test
  public void testKMeansFailNoKInput() {
    try {
      Data.main(new String[]{"K", "data/clusterdata-2.txt", "output/clusterdataU-2.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input for K.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"k", "data/clusterdata-2.txt", "output/clusterdataU-2.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input for K.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testNullArgs() {
    try {
      Data.main(new String[]{null, "data/clusterdata-2.txt", "output/clusterdataU-2.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"k", null, "output/clusterdataU-2.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"k", "data/clusterdata-2.txt", null, "2"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"k", "data/clusterdata-2.txt", null});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{null, "linedata-1.txt", "output/line-data1.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"l", null, "output/line-data1.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"l", "linedata-1.txt", null});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"L", "linedata-1.txt", "output/line-data1.png"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
    }
  }

  @Test
  public void testBadArgs() {
    try {
      Data.main(new String[]{"!", "data/linedata-1.txt", "output/linedataLK-1.png", "1"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"adsfad", "data/linedata-1.txt", "output/linedataLK-1.png", "1"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Data.main(new String[]{"g", "data/clusterdata-2.txt", "output/clusterdataU-2.png", "2"});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input.", e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}