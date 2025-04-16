package DevOps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(1);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("A");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);


        DataFrame result = df.selectRow(1);
        
        assertEquals(df.getSeries().get(0).getValues(), expected.getSeries.get(0).getValues(), "erreur ligne DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), expected.getSeries.get(1).getValues(), "erreur ligne DataFrame values different assert 2");
        assertEquals(df.getSeries().get(0).getLabel(), expected.getSeries.get(0).getLabel(), "erreur ligne DataFrame Label different assert 3");
        assertEquals(df.getSeries().get(1).getLabel(), expected.getSeries.get(1).getLabel(), "erreur ligne DataFrame Label different assert 4");
    }


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

        ArrayList<ArrayList<?>> data2 = new ArrayList<>();
        ArrayList<Object> col3 = new ArrayList<>();
        col3.add(1);
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("A");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        labels2.add("Colonne2");
        DataFrame expected = new DataFrame(labels2,data2);


        DataFrame result = df.selectRow(1);
        
        assertEquals(df.getSeries().get(0).getValues(), expected.getSeries.get(0).getValues(), "erreur ligne DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), expected.getSeries.get(1).getValues(), "erreur ligne DataFrame values different assert 2");
        assertEquals(df.getSeries().get(0).getLabel(), expected.getSeries.get(0).getLabel(), "erreur ligne DataFrame Label different assert 3");
        assertEquals(df.getSeries().get(1).getLabel(), expected.getSeries.get(1).getLabel(), "erreur ligne DataFrame Label different assert 4");
    }

    @Test
    public void testSelectRowIndexByList(){
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
        DataFrame result = df.selectRow(indices);
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
        assertEquals(df.getSeries().get(0).getValues(), expected.getSeries.get(0).getValues(), "erreur ligneIndexList DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), expected.getSeries.get(1).getValues(), "erreur ligneIndexList DataFrame values different assert 2");
        assertEquals(df.getSeries().get(0).getLabel(), expected.getSeries.get(0).getLabel(), "erreur ligneIndexList DataFrame Label different assert 3");
        assertEquals(df.getSeries().get(1).getLabel(), expected.getSeries.get(1).getLabel(), "erreur ligneIndexList DataFrame Label different assert 4");
    }

    @Test
    public void testSelectRowIndexByList(){
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
        
        assertEquals(df.getSeries().get(0).getValues(), expected.getSeries.get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), expected.getSeries.get(1).getValues(), "erreur ligneIntervalle DataFrame values different assert 2");
        assertEquals(df.getSeries().get(0).getLabel(), expected.getSeries.get(0).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 3");
        assertEquals(df.getSeries().get(1).getLabel(), expected.getSeries.get(1).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 4");
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
        ArrayList<Object> col4 = new ArrayList<>();
        col4.add("A");
        data2.add(col3);
        data2.add(col4);
        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("Colonne1");
        DataFrame expected = new DataFrame(labels2,data2);

        
        assertEquals(df.getSeries().get(0).getValues(), expected.getSeries.get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), expected.getSeries.get(1).getValues(), "erreur ligneIntervalle DataFrame values different assert 2");
        assertEquals(df.getSeries().get(0).getLabel(), expected.getSeries.get(0).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 3");
        assertEquals(df.getSeries().get(1).getLabel(), expected.getSeries.get(1).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 4");

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

        List<String> labelsList = new ArrayList<>();
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

        assertEquals(df.getSeries().get(0).getValues(), expected.getSeries.get(0).getValues(), "erreur ligneIntervalle DataFrame values different assert 1");
        assertEquals(df.getSeries().get(1).getValues(), expected.getSeries.get(1).getValues(), "erreur ligneIntervalle DataFrame values different assert 2");
        assertEquals(df.getSeries().get(0).getLabel(), expected.getSeries.get(0).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 3");
        assertEquals(df.getSeries().get(1).getLabel(), expected.getSeries.get(1).getLabel(), "erreur ligneIntervalle DataFrame Label different assert 4");

    }
}
