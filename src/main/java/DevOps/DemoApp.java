package DevOps;

import DevOps.DataFrame;
import DevOps.Series;

import java.util.ArrayList;

public class DemoApp {
    public static void main(String[] args) {
        // Load DataFrame
        DataFrame df = new DataFrame("product.csv");

        System.out.println("First 5 products:");
        System.out.println(df.printFirstLines(5));

        // Filter: Products in stock
        Series<?> inStock = df.getSeries("InStock");
        ArrayList<Integer> inStockIndices = new ArrayList<>();
        for (int i = 0; i < inStock.getValues().size(); i++) {
            if (Boolean.TRUE.equals(inStock.getValue(i))) {
                inStockIndices.add(i);
            }
        }
        DataFrame availableProducts = df.selectRow(inStockIndices);
        System.out.println("\nProducts in Stock:");
        System.out.println(availableProducts.printAll());

        // Select columns: ProductName, Price
        ArrayList<String> selectedColumns = new ArrayList<>();
        selectedColumns.add("ProductName");
        selectedColumns.add("Price");
        DataFrame nameAndPrice = df.selectCol(selectedColumns);
        System.out.println("\nProduct Names and Prices:");
        System.out.println(nameAndPrice.printAll());

        // Change the price of USB-C Cable to 7.99
        Series<?> productNames = df.getSeries("ProductName");
        for (int i = 0; i < productNames.getValues().size(); i++) {
            if (productNames.getValue(i).equals("USB-C Cable")) {
                df.changeValue("Price", i, 7.99);
                break;
            }
        }
        System.out.println("\nUpdated Price for USB-C Cable:");
        System.out.println(df.printAll());

        // Rename "InStock" to "Available"
        df.changeLabel("InStock", "Available");
        System.out.println("\nRenamed 'InStock' to 'Available':");
        System.out.println(df.printAll());
    }
}
