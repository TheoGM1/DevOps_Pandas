package DevOps;

import java.io.BufferedReader;
import java.io.FileReader;
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
}