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

public class DataFramePrintTest {

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

        String expectedOutput = "<Name,Age,Salary,IsManager,HireDate\n"+
        "John Smith,30,50000.5,true,15/04/2020\n"+
        "Jane Doe,25,45000.0,false,23/09/2021\n"+
        "Smith, Robert,35,55000.75,true,10/01/2019>";

        assertEquals(expectedOutput, df.printAll(), "Error on dataframe display");
    }

}
