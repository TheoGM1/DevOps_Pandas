package DevOps;

import java.util.ArrayList;

public class Colonne<T> {
    String labels;
    ArrayList<T> values;

    public Colonne(String labels, ArrayList<T> values) {
        this.labels = labels;
        this.values = values;
    }
    
}
