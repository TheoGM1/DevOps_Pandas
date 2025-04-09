package DevOps;

import java.util.ArrayList;

public class DataFrame {
    ArrayList<Colonne<?>> colonnes;

    public DataFrame(ArrayList<String> label, ArrayList<ArrayList<?>> entree) {
        ArrayList<Colonne<?>> colonnes = new ArrayList<>();
        int i = 0;
        for (ArrayList<?> col : entree) {
            Colonne<?> colonne = new Colonne<>(label.get(i), col);
            colonnes.add(colonne);
            i++;
        }
        this.colonnes = colonnes;
    }

}
