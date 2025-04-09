package DevOps;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testDataFrameCreation() {
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

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Colonne1");
        labels.add("Colonne2");

        DataFrame df = new DataFrame(labels,data);
        
        assertEquals(df.colonnes.size(), 2, "erreur nombre de colones");
    }

    @Test
    public void testNameColonneCreation() {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        Colonne<Integer> col = new Colonne<>("Test", values);

        assertEquals(col.labels, "Test", "erreur label");
    }

    @Test
    public void testValColonneCreation(){
        ArrayList<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        Colonne<Integer> col = new Colonne<>("Test", values);

        assertEquals(col.values, values, "erreur valeurs");
    }

    @Test
    public void testColonneVide(){
        ArrayList<Integer> values = new ArrayList<>();
        Colonne<Integer> col = new Colonne<>("Test", values);

        assertEquals(col.values.size(), 0, "erreur colonne vide");
    }

    @Test
    public void testValeurDataFrameVide() {
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        DataFrame df = new DataFrame(labels, data);
        
        assertEquals(df.colonnes.size(), 0, "erreur nombre de colones");
    }
    
    

}
