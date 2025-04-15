package DevOps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class DataFrameCSVTest {

    private File createCsvFile(Path tempDir, String filename, String csvContent) throws IOException {
        File csvFile = tempDir.resolve(filename).toFile();
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write(csvContent);
        }
        return csvFile;
    }

    // Test 1: Header Parsing and Structure
    @Test
    @DisplayName("CSV File: Validate header parsing and structure")
    void testCsvHeaderAndStructure(@TempDir Path tempDir) throws IOException {
        String csvContent = "Name,Age,Salary,IsManager,HireDate\n" +
                "John Smith,30,50000.50,true,15/04/2020\n" +
                "Jane Doe,25,45000,false,23/09/2021\n" +
                "\"Smith, Robert\",35,55000.75,true,10/01/2019\n";
        File csvFile = createCsvFile(tempDir, "test.csv", csvContent);
        DataFrame df = new DataFrame(csvFile.getAbsolutePath());

        // Test number of columns and rows.
        assertEquals(5, df.getColumnCount(), "Error on column count");
        assertEquals(3, df.getRowCount(), "Error on row count");

        // Test header labels.
        ArrayList<String> expectedLabels = new ArrayList<>();
        expectedLabels.add("Name");
        expectedLabels.add("Age");
        expectedLabels.add("Salary");
        expectedLabels.add("IsManager");
        expectedLabels.add("HireDate");
        assertEquals(expectedLabels, df.getLabels(), "Error on header labels");
    }

    // Test 2: Data Type Inference and Specific Data Values
    @Test
    @DisplayName("CSV File: Validate data types and specific values")
    void testDataTypeInference(@TempDir Path tempDir) throws IOException {
        String csvContent = "Name,Age,Salary,IsManager,HireDate\n" +
                "John Smith,30,50000.50,true,15/04/2020\n" +
                "Jane Doe,25,45000,false,23/09/2021\n" +
                "\"Smith, Robert\",35,55000.75,true,10/01/2019\n";
        File csvFile = createCsvFile(tempDir, "test.csv", csvContent);
        DataFrame df = new DataFrame(csvFile.getAbsolutePath());

        Series<?> nameSeries = df.getSeries("Name");
        Series<?> ageSeries = df.getSeries("Age");
        Series<?> salarySeries = df.getSeries("Salary");
        Series<?> managerSeries = df.getSeries("IsManager");
        Series<?> dateSeries = df.getSeries("HireDate");

        // Validate types.
        assertInstanceOf(String.class, nameSeries.getValue(0), "Error on parsed type");
        assertInstanceOf(Integer.class, ageSeries.getValue(0), "Error on parsed type");
        assertInstanceOf(Double.class, salarySeries.getValue(0), "Error on parsed type");
        assertInstanceOf(Boolean.class, managerSeries.getValue(0), "Error on parsed type");
        assertInstanceOf(Date.class, dateSeries.getValue(0), "HError on parsed type");

        // Validate specific values.
        assertEquals("John Smith", nameSeries.getValue(0), "Error on parsed value");
        assertEquals(Integer.valueOf(30), ageSeries.getValue(0), "Error on parsed value");
        assertEquals(Double.valueOf(50000.50), salarySeries.getValue(0), "Error on parsed value");
        assertEquals(Boolean.TRUE, managerSeries.getValue(0), "Error on parsed value");

        // Validate date parsing.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = sdf.format(dateSeries.getValue(0));
        assertEquals("15/04/2020", dateStr, "Error on parsed date value");

        // Validate handling of quoted field with comma.
        assertEquals("Smith, Robert", nameSeries.getValue(2), "Error on parsed value with comma");
    }

    // Test 3: Handling of an Empty CSV File (only headers, no data rows)
    @Test
    @DisplayName("CSV File: Validate handling an empty CSV file")
    void testEmptyCsvFile(@TempDir Path tempDir) throws IOException {
        String csvContent = "Column1,Column2,Column3\n";
        File csvFile = createCsvFile(tempDir, "empty.csv", csvContent);
        DataFrame df = new DataFrame(csvFile.getAbsolutePath());

        // Validate structure for an empty CSV.
        assertEquals(3, df.getColumnCount(), "Error on column count");
        assertEquals(0, df.getRowCount(), "Error on row count");

        ArrayList<String> expectedLabels = new ArrayList<>();
        expectedLabels.add("Column1");
        expectedLabels.add("Column2");
        expectedLabels.add("Column3");
        assertEquals(expectedLabels, df.getLabels(), "Error on header labels");
    }

    // Test 4: Handling Missing Values in CSV Data
    @Test
    @DisplayName("CSV File: Validate handling CSV with missing values")
    void testCsvFileWithMissingValues(@TempDir Path tempDir) throws IOException {
        String csvContent = "ID,Name,Value\n" +
                "1,Item1,100\n" +
                "2,,200\n" +
                "3,Item3,\n";
        File csvFile = createCsvFile(tempDir, "missing.csv", csvContent);
        DataFrame df = new DataFrame(csvFile.getAbsolutePath());

        assertEquals(3, df.getColumnCount(), "Error on column count");
        assertEquals(3, df.getRowCount(), "Error on row count");

        Series<?> nameSeries = df.getSeries("Name");
        Series<?> valueSeries = df.getSeries("Value");

        // Verify that missing fields result in null values.
        assertNull(nameSeries.getValue(1), "Error on value of missing values");
        assertNull(valueSeries.getValue(2), "Error on value of missing values");
    }

    // Test 5: Handling Different Date Formats
    @Test
    @DisplayName("CSV File: Validate date parsing with different date formats")
    void testCsvFileWithDifferentDateFormats(@TempDir Path tempDir) throws IOException {
        String csvContent = "ID,Date\n" +
                "1,15/04/2020\n" +
                "2,2021-05-20\n" +
                "3,06/30/2022\n";
        File csvFile = createCsvFile(tempDir, "dates.csv", csvContent);
        DataFrame df = new DataFrame(csvFile.getAbsolutePath());

        assertEquals(2, df.getColumnCount(), "Unexpected column count for dates CSV");
        assertEquals(3, df.getRowCount(), "Unexpected row count for dates CSV");

        Series<?> dateSeries = df.getSeries("Date");
        assertInstanceOf(Date.class, dateSeries.getValue(0), "date value parsed in wrong type");
        assertInstanceOf(Date.class, dateSeries.getValue(1), "date value parsed in wrong type");
        assertInstanceOf(Date.class, dateSeries.getValue(2), "date value parsed in wrong type");
    }
}
