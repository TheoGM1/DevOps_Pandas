package DevOps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.*;
import org.junit.jupiter.api.Test;

public class ChangeTest {
    

    @Test
    public void testChangeValuebase(){
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
        df.changeValue("Colonne1",0,3);

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Integer> col3 = new ArrayList<>();
        col3.add(3);
        col3.add(2);
        col3.add(3);
        data2.add(col3);

        ArrayList<String> col4 = new ArrayList<>();
        col4.add("A");
        col4.add("B");
        col4.add("C");
        data2.add(col4);

        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");

        DataFrame result = new DataFrame(labels2,data2);

        assertEquals(df.getSeries().get(0).getValues(), result.getSeries().get(0).getValues(), "erreur ChangeValue DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), result.getSeries().get(1).getValues(), "erreur ChangeValue DataFrame values different assert 2");
    }

    @Test
    public void testChangeValueErreur(){
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
        assertThrows(ClassCastException.class, () -> {
            df.changeValue("Colonne1",0,"A");
        });
    }

    @Test
    public void testChangeLabel(){
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
        df.changeLabel("Colonne1","Colonne3");

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Integer> col3 = new ArrayList<>();
        col3.add(3);
        col3.add(2);
        col3.add(3);
        data2.add(col3);

        ArrayList<String> col4 = new ArrayList<>();
        col4.add("A");
        col4.add("B");
        col4.add("C");
        data2.add(col4);

        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne3");
        labels2.add("Colonne2");

        DataFrame result = new DataFrame(labels2,data2);

        assertEquals(df.getSeries().get(0).getLabel(), result.getSeries().get(0).getLabel(), "erreur ChangeLabel DataFrame values different assert 1");
    }
}
