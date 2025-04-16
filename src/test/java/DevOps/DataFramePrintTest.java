package DevOps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataFramePrintTest {
    DataFrame df;

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

        String expectedOutput = "Name,Age,Salary,IsManager,HireDate\n"+
        "John Smith,30,50000.5,true,15/04/2020\n"+
        "Jane Doe,25,45000.0,false,23/09/2021\n"+
        "Smith, Robert,35,55000.75,true,10/01/2019";

        assertEquals(expectedOutput, df.printAll(), "The printed DataFrame does not match the expected output.");
    }

    @BeforeAll
    public void create(){

        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        data.add(col1);

        ArrayList<String> col2 = new ArrayList<>();
        col2.add("A");
        col2.add("B");
        col2.add("C");
        data.add(col2);

        ArrayList<Double> col3 = new ArrayList<>();
        col3.add(4.8);
        col3.add(15.16);
        col3.add(23.42);
        data.add(col3);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Colonne1");
        labels.add("Colonne2");
        labels.add("Colonne3");

        df = new DataFrame(labels,data);

    }

    @Test
    void testLecturePremiereLigne(){
        
        String expectedOutput = "Colonne1,Colonne2,Colonne3\n" + 
                "1,A,4.8\n" +
                "2,B,15.16";
        
        assertEquals(expectedOutput, df.printFirstLines(2), "erreur affichage premiere ligne");
    }

    @Test
    void testLectureDerniereLigne(){

        String expectedOutput = "Colonne1,Colonne2,Colonne3\n" + 
                "2,B,15.16\n" +
                "3,C,23.42";
        
        assertEquals(expectedOutput, df.printLastLines(2), "erreur affichage derniere ligne");
    }

    @Test
    void testLecturePremiereLigneOne(){
        String expectedOutput = "Colonne1,Colonne2,Colonne3\n1,A,4.8";

        assertEquals(expectedOutput,df.printFirstLines(1), "erreur affichage premiere ligne");
    }

    @Test
    void testLectureDerniereLigneOne(){
        String expectedOutput = "Colonne1,Colonne2,Colonne3\n3,C,23.42";

        assertEquals(expectedOutput, df.printLastLines(1), "erreur affichache derniere ligne");
    }

    @Test
    void testLecturePremiereLigneZero(){
        String expectedOutput = "Colonne1,Colonne2,Colonne3";

        assertEquals(expectedOutput,df.printFirstLines(0), "erreur affichage premiere ligne");
    }

    @Test
    void testLectureDerniereLigneZero(){
        String expectedOutput = "Colonne1,Colonne2,Colonne3";

        assertEquals(expectedOutput, df.printLastLines(0), "erreur affichache derniere ligne");
    }

    @Test 
    void testLecturePremiereSup(){
        String expectedOutput = "Colonne1,Colonne2,Colonne3\n1,A,4.8\n2,B,15.16\n3,C,23.42";

        assertEquals(expectedOutput, df.printLastLines(6), "erreur affichache derniere ligne");
    }

    

}
