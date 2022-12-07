package ru.croc.task18;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Task18 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        ProductsDAO productsDAO = new ProductsDAO();
        SalesDAO salesDAO = new SalesDAO();

        String hardcodedPath = "src/ru/croc/task18/resources/orders.csv";

        createAndFillDatabase(hardcodedPath);
        // find a T1 product
        System.out.println("Find a T1 product");
        System.out.println(productsDAO.findProduct("Т1"));
        System.out.println();

        // add a new product (new)
        System.out.println("Add a new product (new)");
        try {
            System.out.println(productsDAO.createProduct(new Product("Т10", "Станция", 6000)));
        } catch (ProductsDAO.ProductAlreadyExists e){
            System.out.println("Product already exists");
        }
        System.out.println();

        // add a new product (existed)
        System.out.println("Add a new product (existed)");
        try {
            System.out.println(productsDAO.createProduct(new Product("Т10", "Станция", 6000)));
        } catch (ProductsDAO.ProductAlreadyExists e){
            System.out.println("Product already exists");
        }
        System.out.println();

        // create order
        System.out.println("Create an order");
        List<Product> products = new ArrayList<>();
        products.add(new Product("Т12", "Фитнес-браслет", 3000));
        products.add(new Product("Т11", "Лампа", 4000));
        products.add(new Product("Т4", "Блок питания", 200));   //exists
        List<Sale> sales =  salesDAO.createOrder("xen", products);
        for(Sale sale : sales)
            System.out.println(sale);
        System.out.println();

        // delete product
        System.out.println("Delete T11");
        System.out.println(productsDAO.findProduct("Т11"));
        System.out.println("Deleting T11...");
        productsDAO.deleteProduct("Т11");
        System.out.println(productsDAO.findProduct("Т11"));
        System.out.println();

        // update product
        System.out.println("Update T3");
        System.out.println(productsDAO.findProduct("Т3"));
        System.out.println("Updating T3...");
        System.out.println(productsDAO.updateProduct(new Product("Т3", "Ноутбук", 100000)));
    }

    public static void createAndFillDatabase(String pathToCSV) throws SQLException, IOException, ClassNotFoundException {
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
