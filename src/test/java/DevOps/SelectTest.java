package DevOps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class SelectTest {
    

    @Test
    public void testSelectRowIndex(){
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
        DataFrame result = df.selectRow(1);

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(2);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("B");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);


        
        assertEquals(expected.getSeries().get(0).getValues(), result.getSeries().get(0).getValues(), "erreur ligne DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(1).getValues(), result.getSeries().get(1).getValues(), "erreur ligne DataFrame values different assert 2");
        assertEquals(expected.getSeries().get(0).getLabel(), result.getSeries().get(0).getLabel(), "erreur ligne DataFrame Label different assert 3");
        assertEquals(expected.getSeries().get(1).getLabel(), result.getSeries().get(1).getLabel(), "erreur ligne DataFrame Label different assert 4");
    }


    @Test
    public void testSelectRowIndexList(){
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

        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(1);
        indices.add(2);

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(2);
        col3.add(3);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("B");
        col4.add("C");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);


        DataFrame result = df.selectRow(indices);
        
        assertEquals(expected.getSeries().get(0).getValues(), result.getSeries().get(0).getValues(), "erreur ligne DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(1).getValues(), result.getSeries().get(1).getValues(), "erreur ligne DataFrame values different assert 2");
        assertEquals(expected.getSeries().get(0).getLabel(), result.getSeries().get(0).getLabel(), "erreur ligne DataFrame Label different assert 3");
        assertEquals(expected.getSeries().get(1).getLabel(), result.getSeries().get(1).getLabel(), "erreur ligne DataFrame Label different assert 4");
    }


    @Test
    public void testSelectRowIndexByIntervalle(){
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
        DataFrame result = df.selectRow(0,1);
        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(1);
        col3.add(2);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("A");
        col4.add("B");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);
        
        assertEquals(expected.getSeries().get(0).getValues(),result.getSeries().get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(1).getValues(), result.getSeries().get(1).getValues(), "erreur ligneIntervalle DataFrame values different assert 2");
        assertEquals(expected.getSeries().get(0).getLabel(), result.getSeries().get(0).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 3");
        assertEquals(expected.getSeries().get(1).getLabel(), result.getSeries().get(1).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 4");
    }

    @Test
    public void testSelectLabel(){
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
        DataFrame result = df.selectCol("Colonne1");

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(1);
        col3.add(2);
        col3.add(3);
        data2.add(col3);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        DataFrame expected = new DataFrame(labels2,data2);

        
        assertEquals(expected.getSeries().get(0).getValues(), result.getSeries().get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(0).getLabel(), result.getSeries().get(0).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 2");

    }

    @Test
    public void testSelectLabelsList(){
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

        ArrayList<String> labelsList = new ArrayList<>();
        labelsList.add("Colonne1");
        labelsList.add("Colonne2");


        DataFrame df = new DataFrame(labels,data1);
        DataFrame result = df.selectCol(labelsList);

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(1);
        col3.add(2);
        col3.add(3);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("A");
        col4.add("B");
        col4.add("C");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);

        assertEquals(expected.getSeries().get(0).getValues(), result.getSeries().get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(expected.getSeries().get(1).getValues(), result.getSeries().get(1).getValues(), "erreur ligneIntervalle DataFrame values different assert 2");
        assertEquals(expected.getSeries().get(0).getLabel(), result.getSeries().get(0).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 3");
        assertEquals(expected.getSeries().get(1).getLabel(), result.getSeries().get(1).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 4");

    }

    @Test
    public void testSelectComp(){
        ArrayList<ArrayList<?>> data1 = new ArrayList<>();
        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
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
        DataFrame result1 = df.SelectColCond("Colonne1",">",3.0);
        DataFrame result2 = df.SelectColCond("Colonne1","<=",3.0);

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(4);
        col3.add(5);
        col3.add(6);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("A");
        col4.add("B");
        col4.add("C");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);
        ArrayList<ArrayList<?>> data3 = new ArrayList<>();
        ArrayList<Object> col5 = new ArrayList<>();
        col5.add(1);
        col5.add(2);
        col5.add(3);
        ArrayList<Object> col6 = new ArrayList<>();
        col6.add("A");
        col6.add("B");
        col6.add("C");
        data3.add(col5);
        data3.add(col6);
        ArrayList<String> labels3 = new ArrayList<>();
        labels3.add("Colonne1");
        labels3.add("Colonne2");
        DataFrame expected2 = new DataFrame(labels3,data3);

        assertEquals(expected.getSeries().get(0).getValues(), result1.getSeries().get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(expected2.getSeries().get(0).getValues(), result2.getSeries().get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 2");
    }

    @Test
    public void testSelectColReturnsCorrectColumns() {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("ProductID", "Price", "ProductName", "InStock"));
        ArrayList<ArrayList<?>> data = new ArrayList<>();

        data.add(new ArrayList<>(List.of(101)));                 // ProductID
        data.add(new ArrayList<>(List.of(19.99)));               // Price
        data.add(new ArrayList<>(List.of("Wireless Mouse")));    // ProductName
        data.add(new ArrayList<>(List.of(true)));                // InStock

        DataFrame df = new DataFrame(labels, data);

        ArrayList<String> selectedColumns = new ArrayList<>(Arrays.asList("ProductName", "Price"));
        DataFrame nameAndPrice = df.selectCol(selectedColumns);

        List<String> expectedLabels = Arrays.asList("ProductName", "Price");
        assertEquals(expectedLabels, nameAndPrice.getLabels());

        assertEquals(1, nameAndPrice.getRowCount());

        assertEquals("Wireless Mouse", nameAndPrice.getSeries("ProductName").getValue(0));
        assertEquals(19.99, (Double) nameAndPrice.getSeries("Price").getValue(0), 0.001);
    }
}
