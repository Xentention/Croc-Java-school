package ru.croc.task18;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//C:\Users\Xenia\Desktop\Java croc school\Homework\src\ru\croc\task17\resources\orders.csv
public class Task18 {
    static ProductsDAO productsDAO = new ProductsDAO();
    static SalesDAO salesDAO = new SalesDAO();
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String hardcodedPath = "src/ru/croc/task18/resources/orders.csv";

        createAndFillDatabase(hardcodedPath);
        System.out.println(productsDAO.findProduct("T1"));
        try {
            System.out.println(productsDAO.createProduct(new Product("Т10", "Станция", 6000)));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        List<Product> products = new ArrayList<>();
        products.add(new Product("Т12", "Фитнес-браслет", 3000));
        products.add(new Product("Т11", "Лампа", 4000));
        products.add(new Product("Т4", "Блок питания", 200));
        List<Sale> sales =  salesDAO.createOrder("xen", products);
        for(Sale sale : sales)
            System.out.println(sale);

    }

    public static void createAndFillDatabase(String pathToCSV) throws SQLException, IOException, ClassNotFoundException {
        productsDAO.createTable();
        productsDAO.importProductsFromCSV(pathToCSV);
        salesDAO.createTable();
        salesDAO.importSalesFromCSV(pathToCSV);

    }
}
