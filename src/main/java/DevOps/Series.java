package DevOps;

import java.util.ArrayList;

public class Series<T> {
    String labels;
    ArrayList<T> values;

    public Series(String labels, ArrayList<T> values) {
        this.labels = labels;
        this.values = values;
    }
}
