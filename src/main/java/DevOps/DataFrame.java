package DevOps;

import java.util.ArrayList;

public class DataFrame {
    ArrayList<Series<?>> series;

    public DataFrame(ArrayList<String> label, ArrayList<ArrayList<?>> entree) {
        this.series = new ArrayList<>();
        int i = 0;
        for (ArrayList<?> col : entree) {
            Series<?> serie = new Series<>(label.get(i), col);
            series.add(serie);
            i++;
        }
    }

    public ArrayList<Series<?>> getSeries() {
        return series;
    }

}
