package DevOps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public DataFrame(String csvFileName) {
        this.series = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            parseCSV(reader);
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void parseCSV(BufferedReader reader) throws Exception {
        // Parse header row
        String[] headers = parseLine(reader.readLine());

        // Read all lines to infer types and collect data
        ArrayList<String[]> allLines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                allLines.add(parseLine(line));
            }
        }

        if (allLines.isEmpty()) {
            // No data, create empty series
            for (String header : headers) {
                series.add(new Series<>(header, new ArrayList<>()));
            }
            return;
        }

        // Determine types and initialize series
        for (int colIndex = 0; colIndex < headers.length; colIndex++) {
            Class<?> colType = inferColumnType(allLines, colIndex);

            if (colType == Boolean.class) {
                createTypedSeries(headers[colIndex], colType, allLines, colIndex);
            } else if (colType == Integer.class) {
                createTypedSeries(headers[colIndex], colType, allLines, colIndex);
            } else if (colType == Double.class) {
                createTypedSeries(headers[colIndex], colType, allLines, colIndex);
            } else if (colType == Date.class) {
                createTypedSeries(headers[colIndex], colType, allLines, colIndex);
            } else {
                createTypedSeries(headers[colIndex], String.class, allLines, colIndex);
            }
        }
    }

    private <T> void createTypedSeries(String label, Class<T> type, ArrayList<String[]> data, int colIndex) {
        ArrayList<T> values = new ArrayList<>();

        for (String[] row : data) {
            if (colIndex < row.length) {
                String value = row[colIndex].trim();
                values.add(parseValue(value, type));
            } else {
                values.add(null); // Add null for missing values
            }
        }

        Series<T> series = new Series<>(label, values);
        this.series.add(series);
    }

    private <T> T parseValue(String value, Class<T> type) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        if (type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        } else if (type == Integer.class) {
            try {
                return (T) Integer.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (type == Double.class) {
            try {
                return (T) Double.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (type == Date.class) {
            return (T) parseDate(value);
        } else {
            return (T) value;
        }
    }

    private Class<?> inferColumnType(ArrayList<String[]> data, int colIndex) {
        boolean allBoolean = true;
        boolean allInteger = true;
        boolean allDouble = true;
        boolean allDate = true;

        for (String[] row : data) {
            if (colIndex >= row.length || row[colIndex] == null || row[colIndex].trim().isEmpty()) {
                continue; // Skip empty values
            }

            String value = row[colIndex].trim();

            // Check boolean
            if (allBoolean && !value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
                allBoolean = false;
            }

            // Check integer
            if (allInteger) {
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    allInteger = false;
                }
            }

            // Check double
            if (allDouble) {
                try {
                    Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    allDouble = false;
                }
            }

            // Check date
            if (allDate) {
                boolean isDate = false;
                String[] dateFormats = {"dd/MM/yyyy", "yyyy-MM-dd", "MM/dd/yyyy"};
                for (String format : dateFormats) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                        dateFormat.setLenient(false);
                        dateFormat.parse(value);
                        isDate = true;
                        break;
                    } catch (ParseException e) {
                        // Not a date in this format
                    }
                }
                if (!isDate) {
                    allDate = false;
                }
            }
        }

        if (allBoolean) {
            return Boolean.class;
        } else if (allInteger) {
            return Integer.class;
        } else if (allDouble) {
            return Double.class;
        } else if (allDate) {
            return Date.class;
        }

        return String.class;
    }

    private String[] parseLine(String line) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                // Toggle quotes state
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                // End of value
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        // Add the last token
        tokens.add(sb.toString().trim());

        return tokens.toArray(new String[0]);
    }

    private Date parseDate(String value) {
        String[] dateFormats = {"dd/MM/yyyy", "yyyy-MM-dd", "MM/dd/yyyy"};
        for (String format : dateFormats) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                dateFormat.setLenient(false);
                return dateFormat.parse(value);
            } catch (ParseException e) {
                // Try next format
            }
        }
        return null;
    }

    public int getRowCount() {
        if (series.isEmpty()) {
            return 0;
        }
        return series.get(0).getValues().size();
    }

    public int getColumnCount() {
        return series.size();
    }

    public List<String> getLabels() {
        List<String> labels = new ArrayList<>();
        for (Series<?> s : series) {
            labels.add(s.getLabel());
        }
        return labels;
    }

    public Series<?> getSeries(String label) {
        for (Series<?> s : series) {
            if (s.getLabel().equals(label)) {
                return s;
            }
        }
        return null;
    }






    public String printLabelsLine(){
        StringBuilder lineToPrint = new StringBuilder() ;
        int i ;
        for(i=0 ; i<series.size()-1 ; i++){
            lineToPrint.append(series.get(i).getLabel());
            lineToPrint.append(",");
        }
        lineToPrint.append(series.get(i).getLabel());
        return lineToPrint.toString() ;
    }

    public String objectToString(Object valueToPrint){
        if(valueToPrint instanceof Date){
            Date d = (Date) valueToPrint ;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(d);
        } else {
            return valueToPrint.toString();
        }
    }


    //fonction d'affichage



    public String printValuesLine(int numLine){
        StringBuilder lineToPrint = new StringBuilder() ;
        int i ;
        for(i=0 ; i<series.size()-1 ; i++){
            lineToPrint.append(objectToString(series.get(i).getValue(numLine)));
            lineToPrint.append(",");
        }
        lineToPrint.append(objectToString(series.get(i).getValue(numLine)));
        return lineToPrint.toString() ;
    }
    

    public String printFirstLines(int nbrLinesToPrint){

        int nbrLines = nbrLinesToPrint ;

        if(nbrLinesToPrint > series.size()){
            nbrLines = series.size();
        }

        StringBuilder linesToPrint = new StringBuilder() ;
        linesToPrint.append(printLabelsLine());


        for(int i=0 ; i<nbrLines ; i++){
            linesToPrint.append("\n");
            linesToPrint.append(printValuesLine(i));
        }
        return linesToPrint.toString();
    }

    public String printLastLines(int nbrLinesToPrint){

        int nbrLines = nbrLinesToPrint ;
        int firstIndice = series.size() - nbrLines;

        if(nbrLinesToPrint > series.size()){
            firstIndice = 0;
        }

        StringBuilder linesToPrint = new StringBuilder() ;
        linesToPrint.append(printLabelsLine());


        for(int i=firstIndice ; i<series.size() ; i++){
            linesToPrint.append("\n");
            linesToPrint.append(printValuesLine(i));
        }

        return linesToPrint.toString();
    }

    public String printAll(){
        StringBuilder linesToPrint = new StringBuilder() ;
        linesToPrint.append(printLabelsLine());
        for(int i=0 ; i<this.getRowCount() ; i++){
            linesToPrint.append("\n");
            linesToPrint.append(printValuesLine(i));
        }
        return linesToPrint.toString() ;
    }

 

    public <T> DataFrame selectRow(int i){
        if(i < 0 || i >= series.size()){
            throw new IndexOutOfBoundsException("DataFrame Vide ou la valeur de i est invalide");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int j = 0; j < series.size(); j++){
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(series.get(j).getValue(i));
            data.add(temp);
            label.add(series.get(j).getLabel());
        }
        if(data.isEmpty()){
            throw new IllegalArgumentException("Ligne non trouvée");
        }
        return new DataFrame(label,data);
    }

    public DataFrame selectRow(ArrayList<Integer> i){ //Selection de ligne selon une liste d'indice
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int j = 0; j < series.size(); j++){
            ArrayList<Object> temp = new ArrayList<>();
            for(int k=0;k< i.size();k++){
                temp.add(series.get(j).getValue(i.get(k)));
            }
            data.add(temp);
            label.add(series.get(j).getLabel());
        }
        if(data.isEmpty()){
            throw new IllegalArgumentException("Ligne non trouvée");
        }
        return new DataFrame(label,data);
    }

    public DataFrame selectRow(int i, int j){//Selection de ligne selon une intervalle d'indice
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        }
        if(i < 0 || j < 0 || i >= series.size() || j >= series.size()){
            throw new IndexOutOfBoundsException("DataFrame Vide ou la valeur de i est invalide ou la valeur de j");
        }
        if(i > j){
            throw new IllegalArgumentException("i doit etre plus petit que j");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        for(int k = 0; k < series.size(); k++){
            ArrayList<Object> temp = new ArrayList<>();
            for(int size = i;size <= j;size++){
                temp.add(series.get(k).getValue(size));
            }
            data.add(temp);
            label.add(series.get(k).getLabel());
        }
        if(data.isEmpty()){
            throw new IllegalArgumentException("Ligne non trouvée");
        }
        return new DataFrame(label,data);
    }

    public DataFrame selectCol(String label){//Selection de colonne selon un label
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label_array = new ArrayList<>();
        for(int i = 0; i < series.size(); i++){
            if(series.get(i).getLabel().equals(label)){
                data.add(series.get(i).getValues());
                label_array.add(series.get(i).getLabel());
            }
        }
        if(series.isEmpty()){
            throw new IllegalArgumentException("Colonne non trouvée");
        }
        return new DataFrame(label_array,data);

    }

    public DataFrame selectCol (ArrayList<String> labels){//Selection de colonne selon une liste de  label
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        }
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>();
        
        for(int i = 0 ;i < labels.size(); i++){
            for(int j = 0; j <series.size();j++){
                if(series.get(j).label.equals(labels.get(i))){
                    data.add(series.get(j).getValues());
                    label.add(series.get(j).getLabel());
                }
            }
        }
        if(data.isEmpty()){
            throw new IllegalArgumentException("Colonne non trouvée");
        }
        return new DataFrame(label,data);
    }

    public <T> DataFrame SelectColCond(String label,String comp,double x){
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        }
        int d;
        ArrayList<ArrayList<?>> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<T> tmp;
        for(int i = 0;i<series.size();i++){
            if(series.get(i).getLabel().equals(label)){
                if(isComparableNumber(series.get(i).getValue(0))){
                    tmp = new ArrayList<T>();
                    labels.add(series.get(i).getLabel());
                    Series<?> tmpSeries = series.get(i);
                    @SuppressWarnings("unchecked")
                    Series<T> s = (Series <T>) tmpSeries;
                    switch (comp) {
                        case "=":
                            for(int j = 0;j<series.get(i).getValues().size();j++){
                                if(((Number)s.getValue(j)).doubleValue()==x){
                                    tmp.add(s.getValue(j));
                                }
                            }
                            break;
                        case "<":
                            for(int j = 0;j<series.get(i).getValues().size();j++){
                                if(((Number)s.getValue(j)).doubleValue() < x){
                                    tmp.add(s.getValue(j));
                                }
                            }
                            break;
                        case ">":
                            for(int j = 0;j<series.get(i).getValues().size();j++){
                                if(((Number)s.getValue(j)).doubleValue()>x){
                                    tmp.add(s.getValue(j));
                                }
                            }
                            break;

                        case "<=":
                            for(int j = 0;j<series.get(i).getValues().size();j++){
                                if(((Number)s.getValue(j)).doubleValue()<=x){
                                    tmp.add(s.getValue(j));
                                }
                            }
                            break;
                        case ">=":
                            for(int j = 0;j<series.get(i).getValues().size();j++){
                                if(((Number)s.getValue(j)).doubleValue()>=x){
                                    tmp.add(s.getValue(j));
                                }
                            }
                            break;
                        case "!=":
                            for(int j = 0;j<series.get(i).getValues().size();j++){
                                if(((Number)s.getValue(j)).doubleValue()!=x){
                                    tmp.add(s.getValue(j));
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    data.add(tmp);
                }
                else{
                    throw new IllegalArgumentException("Le type comparer n'appartient pas la classe Number");
                }
            }
        }
        if(data.isEmpty()){
            throw new IllegalArgumentException("label non trouvé");
        }
        
        return new DataFrame(labels,data);
    }

    public <T> void changeValue(String label,int index,T value){ //changer une value selon un indice et un label
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame vide");
        }
        for(int i = 0;i<series.size();i++){
            if(label.equals(series.get(i).getLabel())){
                    if(value.getClass().equals(series.get(i).getValue(index).getClass())){
                    Series<?> tmpSeries = series.get(i);
                    @SuppressWarnings("unchecked")
                    Series<T> s = (Series <T>) tmpSeries;
                    s.setValue(index,value);
                }else{
                    throw new ClassCastException("Type de la valeur incompatible avec la colonne");
                }
            }
        }

    }


    public void changeLabel(String labelToChange,String newLabel){ // changer le nom d'un label
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        }
        
        for(int i = 0;i<series.size();i++){
            if(labelToChange.equals(series.get(i).getLabel())){
                series.get(i).setLabel(newLabel);
            }
        }
    }

    private int getLabelIndice(String label){
        if(series.isEmpty()){
            return -1 ;
        }
        int indice = -2 ;
        for(int i=0 ; i<series.size() ; i++){
            if(series.get(i).getLabel().equals(label)){
                indice = i ;
            }
        }
        return indice ;
    }

    public double meanColumn(String labelName){
        int indice = this.getLabelIndice(labelName);
        if(indice == -1){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        } else if(indice == -2){
            throw new IndexOutOfBoundsException("La colonne de label \""+labelName+"\" n'existe pas dans le DataFrame");
        }
        if(series.get(indice).getValue(0) instanceof Number){
            ArrayList<Number> s = (ArrayList<Number>)series.get(indice).getValues();
            double sum = 0.0 ;
            for(int i=0 ; i<s.size() ; i++){
                sum += s.get(i).doubleValue();
            }
            return (sum/s.size()) ;
        } else {
            throw new IndexOutOfBoundsException("La moyenne n'est pas calculable sur la colonne \""+labelName+"\"");
        }
    }

    public double minColumn(String labelName){
        int indice = this.getLabelIndice(labelName);
        if(indice == -1){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        } else if(indice == -2){
            throw new IndexOutOfBoundsException("La colonne de label \""+labelName+"\" n'existe pas dans le DataFrame");
        }
        if(series.get(indice).getValue(0) instanceof Number){
            ArrayList<Number> s = (ArrayList<Number>)series.get(indice).getValues();
            double min = s.get(0).doubleValue() ;
            for(int i=1 ; i<s.size() ; i++){
                if(s.get(i).doubleValue() < min){
                    min = s.get(i).doubleValue();
                }
            }
            return min ;
        } else {
            throw new IndexOutOfBoundsException("Le minimum n'est pas calculable sur la colonne \""+labelName+"\"");
        }
    }

    public double maxColumn(String labelName){
        int indice = this.getLabelIndice(labelName);
        if(indice == -1){
            throw new IndexOutOfBoundsException("DataFrame Vide");
        } else if(indice == -2){
            throw new IndexOutOfBoundsException("La colonne de label \""+labelName+"\" n'existe pas dans le DataFrame");
        }
        if(series.get(indice).getValue(0) instanceof Number){
            ArrayList<Number> s = (ArrayList<Number>)series.get(indice).getValues();
            double max = s.get(0).doubleValue() ;
            for(int i=1 ; i<s.size() ; i++){
                if(s.get(i).doubleValue() > max){
                    max = s.get(i).doubleValue();
                }
            }
            return max ;
        } else {
            throw new IndexOutOfBoundsException("La maximum n'est pas calculable sur la colonne \""+labelName+"\"");
        }
    }
    public <T> void addCol(String label,ArrayList<T> elemsAdd){
        if(elemsAdd.isEmpty()){
            throw new IndexOutOfBoundsException("list d'elements a ajouter Vide");
        }
        if(label.isBlank()){
            throw new IndexOutOfBoundsException("Label Vide");
        }

        Series<T> list = new Series<T>(label, elemsAdd);
        series.add(list);
    }

    public <T> void addRow(Object... element){
        if(series.isEmpty()){
            throw new IndexOutOfBoundsException("list d'elements a ajouter Vide");
        }
        
        int i = 0;
        for(Object elem : element){
            if(isCompare(elem, series.get(i).getValue(0))){
                i++;
            }
        }
        if(i==series.size()){
            i = 0;
            for(Object elem : element){
                if(isCompare(elem, series.get(i).getValue(0))){
                    Series<?> tmpSeries = series.get(i);
                    @SuppressWarnings("unchecked")
                    Series<T> s = (Series <T>) tmpSeries;
                    s.add((T)elem);
                    i++;
                }
            }
        }
        else{
            throw new IllegalArgumentException("Les type ne sont pas les memes");
        }
    }

    private static boolean isComparableNumber(Object obj) {
        return obj instanceof Number && obj instanceof Comparable;
    }

    private static boolean isCompare(Object obj,Object elem){
        return obj.getClass().equals(elem.getClass());
    }


}
