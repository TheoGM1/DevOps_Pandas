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

    public String printLabels(){
        StringBuilder lineToPrint ;
        for (Series s : series) {
            lineToPrint.append(s.getLabel());
        }
        return lineToPrint.toString() ;
    }

    public String printLine(int numLine){
        StringBuilder lineToPrint ;
        for (Series s : series) {
            lineToPrint.append(s.getValue(numLine).toString());
        }
        return lineToPrint.toString() ;
    }

    public String printFirstLines(int nbrLinesToPrint){
        int nbrLines = nbrLinesToPrint ;
        if(nbrLinesToPrint > series.size()){
            nbrLines = series.size();
        }
        StringBuilder linesToPrint ;
        linesToPrint.append(printLine(0));
        for(int i=1 ; i<nbrLines ; i++){
            linesToPrint.append("\n");
            linesToPrint.append(printLine(i));
        }
        return linesToPrint.toString();
    }

    public String printLastLines(int nbrLinesToPrint){
        int nbrLines = nbrLinesToPrint ;
        if(nbrLinesToPrint > series.size()){
            nbrLines = series.size();
        }
        int firstIndice = series.size() - nbrLines ;
        StringBuilder linesToPrint ;
        linesToPrint.append(printLine(firstIndice));
        for(int i=firstIndice+1 ; i<nbrLines ; i++){
            linesToPrint.append("\n");
            linesToPrint.append(printLine(i));
        }
        return linesToPrint.toString();
    }

    public String printAll(){
        StringBuilder linesToPrint ;
        linesToPrint.append(printLabels());
        for(int i=0 ; i<series.size() ; i++){
            linesToPrint.append("\n");
            linesToPrint.append(printLine(i));
        }
        return linesToPrint.toString() ;
    }

}
