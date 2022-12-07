package ru.croc.task18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ProductsDAO {
    private final String JDBC_URL = "jdbc:h2:~/test";
    private final String JDBC_CLASSNAME = "org.h2.Driver";
    private final String username = "sa";
    private final String password = "sa";

    static class ProductAlreadyExists extends Exception {  }



    /**
     * Creates a new PRODUCTS table with 3 columns
     * (id VARCHAR2(255) NOT NULL,
     *  name VARCHAR2(255) NOT NULL,
     *  rubles_price INTEGER NOT NULL,
     *  PRIMARY KEY ( ID ))
     */
    void createTable() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = """
                           CREATE TABLE IF NOT EXISTS PRODUCTS
                           (id VARCHAR2(255) NOT NULL,
                           name VARCHAR2(255) NOT NULL,
                           rubles_price INTEGER NOT NULL,
                           PRIMARY KEY ( ID ));
                         """;
            statement.executeUpdate(sql);

        }
    }

    /**
     * imports data from a csv file
     * @param path to a csv file
     */
    void importProductsFromCSV(String path) throws IOException, SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null){
                String[] values = currentLine.split(",");
                //mergeProduct(new Product(values[2], values[3], Integer.parseInt(values[4])));
                try {
                    createProduct(new Product(values[2], values[3], Integer.parseInt(values[4])));
                } catch (ProductAlreadyExists e) {
                    // add only unique products
                }
            }
        }

    }


    Product createProduct(Product p) throws ClassNotFoundException, ProductAlreadyExists, SQLException {
        Class.forName(JDBC_CLASSNAME);
        if(findProduct(p.productId()) != null)
            throw new ProductAlreadyExists();

        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO PRODUCTS VALUES ('" + p.productId() + "', '"
                    + p.productName() + "', " + p.rublesPrice() + ");";
            statement.executeUpdate(sql);
            return findProduct(p.productId());
        }
    }

    Product findProduct(String productCode) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        Product searchProduct = null;
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            //merge bc of a primary key constraint
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM PRODUCTS WHERE id = '" + productCode + "';";

            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                searchProduct = new Product(result.getString("id"), result.getString("name"),
                                                result.getInt("rubles_price"));
        }
        return searchProduct;
    }


    Product updateProduct(Product p) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);

        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "UPDATE PRODUCTS SET name = '" + p.productName() + "', rubles_price = "
                    + p.rublesPrice() + " WHERE id = '" + p.productId() + "';";
            statement.executeUpdate(sql);

            sql = "SELECT * FROM PRODUCTS WHERE id = '" + p.productId() + "';";
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                 return new Product(result.getString("id"), result.getString("name"),
                                        result.getInt("rubles_price"));
            else
                return null;
        }
    }


    void deleteProduct(String productCode) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        SalesDAO salesDAO = new SalesDAO();
        salesDAO.deleteSale(productCode);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM PRODUCTS WHERE id = '" + productCode + "';";
            statement.executeUpdate(sql);
        }

    }


    /**
     * For study purposes
     **/
    void dropTable() throws ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS PRODUCTS;";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
