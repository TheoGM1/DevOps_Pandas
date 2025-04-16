package DevOps;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataFrameTest {

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
        
        assertEquals(df.getSeries().size(), 2, "erreur nombre de colones");
    }

    @Test
    public void testNameSeriesCreation() {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        Series<Integer> col = new Series<>("Test", values);

        assertEquals(col.getLabel(), "Test", "erreur label");
    }

    @Test
    public void testValSeriesCreation(){
        ArrayList<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        Series<Integer> col = new Series<>("Test", values);

        assertEquals(col.getValues(), values, "erreur valeurs");
    }

    @Test
    public void testSeriesVide(){
        ArrayList<Integer> values = new ArrayList<>();
        Series<Integer> col = new Series<>("Test", values);

        assertEquals(col.getValues().size(), 0, "erreur colonne vide");
    }

    @Test
    public void testValeurDataFrameVide() {
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        DataFrame df = new DataFrame(labels, data);
        
        assertEquals(df.getSeries().size(), 0, "erreur nombre de colones");
    }
    
}
