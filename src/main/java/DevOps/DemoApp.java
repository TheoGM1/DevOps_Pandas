package DevOps;

public class DemoApp {
    public static void main(String[] args) {
        DataFrame df = new DataFrame("product.csv");

        System.out.println("Number of rows " + df.getRowCount());

        System.out.println("Number of columns " + df.getColumnCount());
    }
}
