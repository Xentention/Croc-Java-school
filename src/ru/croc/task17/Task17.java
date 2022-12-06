package ru.croc.task17;

public class Task17 {
    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Program expects exactly 1 argument");
        }



        createAndFillDatabase(args[0]);
    }

    public static void createAndFillDatabase(String pathToCSV){
        ProductsDAO productsDAO = new ProductsDAO();
        SalesDAO salesDAO = new SalesDAO();
        salesDAO.dropTable();
        productsDAO.dropTable();

        productsDAO.createTable();
        productsDAO.importProductsFromCSV(pathToCSV);
        salesDAO.createTable();
        salesDAO.importSalesFromCSV(pathToCSV);

    }
}
