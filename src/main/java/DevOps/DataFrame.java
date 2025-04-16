package DevOps;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataFrame {
    ArrayList<Series<?>> series;



    public <T> DataFrame selectRow(int i){
        if(i < 0 || i >= series.size()){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int j = 0; j < series.size(); j++){
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(series.get(j).getValue(i));
            data.add(temp);
            label.add(series.get(j).getLabel());
        }
        return new DataFrame(label,data);
    }

    public DataFrame selectRow(ArrayList<Integer> i){
        if(i.size() == 0){
            throw new IllegalArgumentException("List vide");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int j = 0; j < i.size(); j++){
            data.add(series.get(j).getValues(i.get(j)));
            label.add(series.get(j).getLabel());
        }
        return new DataFrame(label,data);
    }

    public DataFrame selectRow(int i, int j){
        if(i < 0 || j < 0 || i >= series.size() || j >= series.size()){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(i > j){
            throw new IllegalArgumentException("i doit etre plus petit que j");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int k = i; k <= j; k++){
            data.add(series.get(k).getValues(k));
            label.add(series.get(k).getLabel());
        }
        return new DataFrame(label,data);
    }

    public DataFrame selectCol(String label){
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label_array = new ArrayList<>();
        for(int i = 0; i < series.size(); i++){
            if(series.get(i).labels.equals(label)){
                data.add(series.get(i).getValues(i));
                label.add(series.get(i).getLabel());
            }
        }
        if(data.size() == 0){
            throw new IllegalArgumentException("Colonne non trouvée");
        }
        return new DataFrame(label,data);

    }

    public DataFrame selectCol (ArrayList<String> labels){
        if(labels.size() == 0){
            throw new IllegalArgumentException("List vide");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int i = 0; i < series.size(); i++){
            for(int j = 0; j < labels.size(); j++){
                if(series.get(i).labels.equals(labels.get(j))){
                    data.add(series.get(i).getValues(i));
                    label.add(series.get(i).getLabels());
                }
            }
        }
        if(data.size() == 0){
            throw new IllegalArgumentException("Colonne non trouvée");
        }
        return new DataFrame(label,data);
    }
    
    public void setColRow(ArrayList<Integer> index,ArrayList<String> labels,Object valeur){
        if(index.size() == 0 || labels.size() == 0){
            throw new IllegalArgumentException("List vide");
        }
        for(int i = 0;)
    }

    public void setRow(int index,Object valeur){
        if(index < 0 || index > series.size()){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        for(int i = 0;i<series.size();i++){
            if(valeur.getClass() != series.get(i).getValues(0).getclass()){
                throw new IllegalArgumentException("Erreur du type d'argument");
            }
        }
        for(int i = 0;i<series.size();i++){
            series.get(i).setValue(i,valeur);
        }
    }

    public void setCol(String label,Object valeur){
        boolean exist = false;
        for(int i = 0;i<series.size();i++){
            if(valeur.getClass() != series.get(i).getValues(0).getclass()){
                throw new IllegalArgumentException("Erreur du type d'argument");
            }
            if(series.get(i).getLabel()){
                exist = true;
            }
        }
        if(exist == false){
            throw new IllegalArgumentException("Le label est pas dans le DataFrame");
        }
    }

}
