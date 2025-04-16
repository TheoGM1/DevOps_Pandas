package DevOps;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class DataFrameStatsTest {
    private DataFrame df;

    @BeforeEach
    public void setup() {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Age", "Height", "Name"));
        ArrayList<ArrayList<?>> data = new ArrayList<>();

        data.add(new ArrayList<>(Arrays.asList(25, 30, 20)));                // Age
        data.add(new ArrayList<>(Arrays.asList(170.5, 180.2, 165.0)));       // Height
        data.add(new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"))); // Name

        df = new DataFrame(labels, data);
    }

    @Test
    public void testMeanColumn() {
        assertEquals(25.0, df.meanColumn("Age"),"Wrong average age calculation");
        assertEquals((170.5 + 180.2 + 165.0) / 3.0, df.meanColumn("Height"),"Wrong average height calculation");
    }

    @Test
    public void testMinColumnInt() {
        assertEquals(20.0, df.minColumn("Age"),"Wrong min age calculation with Double");
    }

    @Test
    public void testMinColumnDouble() {
        assertEquals(165.0, df.minColumn("Height"),"Wrong min height calculation with Integer");
    }

    @Test
    public void testMaxColumnInt() {
        assertEquals(30.0, df.maxColumn("Age"),"Wrong max age calculation with Double");
    }

    @Test
    public void testMaxColumnDouble() {
        assertEquals(180.2, df.maxColumn("Height"), "Wrong max age calculation with Integer");
    }

    @Test
    public void testMeanColumnWithNonNumeric() {
       assertThrows(IndexOutOfBoundsException.class, () -> df.meanColumn("Name"));
    }

    @Test
    public void testMinColumnWithNonNumeric() {
        assertThrows(IndexOutOfBoundsException.class, () -> df.minColumn("Name"));
    }

    @Test
    public void testMaxColumnWithNonNumeric() {
        assertThrows(IndexOutOfBoundsException.class, () -> df.maxColumn("Name"));
    }

    @Test
    public void testColumnDoesNotExist() {
        assertThrows(IndexOutOfBoundsException.class, () -> df.meanColumn("None"));

        assertThrows(IndexOutOfBoundsException.class, () -> df.minColumn("None"));
        assertThrows(IndexOutOfBoundsException.class, () -> df.maxColumn("None"));
    }

    @Test
    public void testEmptyDataFrame() {
        DataFrame emptyDf = new DataFrame(new ArrayList<>(), new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class, () -> emptyDf.meanColumn("None"));
    }
}
