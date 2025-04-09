package DevOps;

import java.util.ArrayList;

public class DataFrame {
    ArrayList<Series<?>> colonnes;

    public DataFrame(ArrayList<String> label, ArrayList<ArrayList<?>> entree) {
        ArrayList<Series<?>> colonnes = new ArrayList<>();
        int i = 0;
        for (ArrayList<?> col : entree) {
            Series<?> colonne = new Series<>(label.get(i), col);
            colonnes.add(colonne);
            i++;
        }
        this.colonnes = colonnes;
    }

}
