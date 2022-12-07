package ru.croc.task18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
    private final String JDBC_URL = "jdbc:h2:~/test";
    private final String JDBC_CLASSNAME = "org.h2.Driver";
    private final String username = "sa";
    private final String password = "sa";


    SalesDAO() throws ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);

    }

    /**
     * Creates a SALES table with 4 columns
     * (id IDENTITY NOT NULL PRIMARY KEY,
     *   order_id INTEGER NOT NULL,
     *   login VARCHAR2(255) NOT NULL,
     *   product_id VARCHAR2(255) NOT NULL REFERENCES PRODUCTS)
     */
    void createTable() throws SQLException {
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = """
                     CREATE TABLE IF NOT EXISTS SALES
                     (id IDENTITY NOT NULL PRIMARY KEY,
                      order_id INTEGER NOT NULL,
                      login VARCHAR2(255) NOT NULL,
                      product_id VARCHAR2(255) NOT NULL REFERENCES PRODUCTS
                      );
                  """;
            statement.executeUpdate(sql);


        }
    }

    /**
     * imports data from a csv file
     * @param path to a csv file
     */
    void importSalesFromCSV(String path) throws SQLException, IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null){
                String[] values = currentLine.split(",");
                createSale(new Sale(Integer.parseInt(values[0]), values[1], values[2]));
            }
        }

    }

    /**
     * adds a new row
     * @param s - a Sale object
     */
    void createSale(Sale s) throws SQLException {
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO SALES (order_id, login, product_id) VALUES (" + s.orderId() + ", '"
                    + s.userLogin() + "', '" + s.productId() + "');";
            statement.executeUpdate(sql);
        }
    }

    /**
     * Deletes all sales of a specific Product
     * @param productCode product_id
     * @throws SQLException SQL error
     */
    void deleteSalesOfAProduct(String productCode) throws SQLException {
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM SALES WHERE product_id = '" + productCode + "';";
            statement.executeUpdate(sql);
        }
    }

    /**
     * Creates and inserts all Sales in an order into SALES.
     * Creates and inserts Product into Products if not exists
     * @param userLogin login of a customer
     * @param products Products that have been bought
     * @return List of Sales
     * @throws SQLException SQL error
     * @throws ClassNotFoundException ProductsDAO might throw
     */
    List<Sale> createOrder(String userLogin,
                           List<Product> products) throws SQLException, ClassNotFoundException {
        List<Sale> addedSales = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            ProductsDAO productsDAO = new ProductsDAO();
            String sql = "SELECT MAX(order_id) FROM SALES";
            ResultSet resultSet = statement.executeQuery(sql);
            int orderNum = 1;
            if(resultSet.next()){
                orderNum = resultSet.getInt(1) + 1;
            }

            for (Product product : products) {
                try {
                    productsDAO.createProduct(product);
                } catch (ProductsDAO.ProductAlreadyExists e) {
                    // просто идем дальше
                }
                Sale newSale = new Sale(orderNum, userLogin, product.productId());
                addedSales.add(newSale);
                createSale(newSale);
            }

        }
        return addedSales;
    }


    /**
     * Drops the table
     **/
    void dropTable() throws SQLException {
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS SALES;";
            statement.executeUpdate(sql);

        }
    }
}
