package DevOps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AddTest {


    @Test
    public void testAddCol() {
        ArrayList<ArrayList<?>> data1 = new ArrayList<>();
        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        data1.add(col1);

        ArrayList<String> col2 = new ArrayList<>();
        col2.add("A");
        col2.add("B");
        col2.add("C");
        data1.add(col2);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Colonne1");
        labels.add("Colonne2");

        ArrayList<Double> col3 = new ArrayList<>();
        col3.add(2.0);
        col3.add(4.5);
        col3.add(2.9);

        DataFrame df = new DataFrame(labels,data1);
        df.addCol("Colonne3",col3);

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Integer> col4 = new ArrayList<>();
        col4.add(1);
        col4.add(2);
        col4.add(3);
        data2.add(col4);

        ArrayList<String> col5 = new ArrayList<>();
        col5.add("A");
        col5.add("B");
        col5.add("C");
        data2.add(col5);

        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        labels2.add("Colonne3");

        ArrayList<Double> col6 = new ArrayList<>();
        col6.add(2.0);
        col6.add(4.5);
        col6.add(2.9);
        data2.add(col6);

        DataFrame expected = new DataFrame(labels2,data2);

        assertEquals(expected.getSeries().get(0).getValues(), df.getSeries().get(0).getValues(), "erreur addCol DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(1).getValues(), df.getSeries().get(1).getValues(), "erreur addCol DataFrame values different assert 2");
        assertEquals(expected.getSeries().get(2).getValues(), df.getSeries().get(2).getValues(), "erreur addCol DataFrame values different assert 3");
        assertEquals(expected.getSeries().get(0).getLabel(), df.getSeries().get(0).getLabel(), "erreur addCol DataFrame Label different assert 4");
        assertEquals(expected.getSeries().get(1).getLabel(), df.getSeries().get(1).getLabel(), "erreur addCol DataFrame Label different assert 5");
        assertEquals(expected.getSeries().get(2).getLabel(), df.getSeries().get(2).getLabel(), "erreur AddCol DataFrame Label different assert 6");

    }

    @Test
    public void testAddRow() {
        ArrayList<ArrayList<?>> data1 = new ArrayList<>();
        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        data1.add(col1);

        ArrayList<String> col2 = new ArrayList<>();
        col2.add("A");
        col2.add("B");
        col2.add("C");
        data1.add(col2);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Colonne1");
        labels.add("Colonne2");

        DataFrame df = new DataFrame(labels,data1);
        df.addRow(10,"L");

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Integer> col4 = new ArrayList<>();
        col4.add(1);
        col4.add(2);
        col4.add(3);
        col4.add(10);
        data2.add(col4);

        ArrayList<String> col5 = new ArrayList<>();
        col5.add("A");
        col5.add("B");
        col5.add("C");
        col5.add("L");
        data2.add(col5);

        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");

        DataFrame expected = new DataFrame(labels2,data2);

        assertEquals(expected.getSeries().get(0).getValues(), df.getSeries().get(0).getValues(), "erreur addRow DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(1).getValues(), df.getSeries().get(1).getValues(), "erreur addRow DataFrame values different assert 2");
        assertThrows(IllegalArgumentException.class, () -> {
            df.addRow("TEST","L");;});
    }





}
