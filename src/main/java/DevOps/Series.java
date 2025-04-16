package DevOps;

import java.util.ArrayList;

public class Series<T> {
    String label;
    ArrayList<T> values;

    public Series(String label, ArrayList<T> values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<T> getValues() {
        return values;
    }

    public T getValue(int index) {
        if (index >= 0 && index < values.size()) {
            return values.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public void setValue(int index, T value) {
        if (index >= 0 && index < values.size()) {
            values.set(index, value);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public void setValues(ArrayList<T> values) {
        this.values = values;
    }

    public void setLabel(String newLabel){
        this.label = newLabel;
    }

    public void add(T value) {
        values.add(value);
    }
}
