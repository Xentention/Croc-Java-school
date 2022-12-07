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
                mergeProduct(new Product(values[2], values[3], Integer.parseInt(values[4])));
            }
        }

    }

    /**
     * Merges in a new row.
     * Adds a row its id is unique, replaces data otherwise
     * @param p - a Product object
     */
    void mergeProduct(Product p) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            //merge bc of a primary key constraint
            String sql = "MERGE INTO PRODUCTS USING (VALUES ('" + p.productId() + "', '"
                    + p.productName() + "', " + p.rublesPrice() + ")) INS_ROW (id, name, rubles_price) " +
                    "ON products.id = ins_row.id " +
                    "WHEN NOT MATCHED THEN INSERT VALUES ('" + p.productId() + "', '" +
                    p.productName() + "', " + p.rublesPrice() + ") " +
                    "WHEN MATCHED THEN UPDATE SET products.name = ins_row.name, " +
                    "products.rubles_price = ins_row.rubles_price;";
            statement.executeUpdate(sql);
        }
    }

    Product createProduct(Product p) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_CLASSNAME);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO PRODUCTS VALUES ('" + p.productId() + "', '"
                            + p.productName() + "', " + p.rublesPrice() + ");";
            statement.executeUpdate(sql);
            return findProduct(p.productId());
        } catch (SQLException e) {
            throw new SQLException("Product already exists");
        }
    }

    Product findProduct(String productCode) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            //merge bc of a primary key constraint
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM PRODUCTS WHERE id = '" + productCode + "';";

            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                return new Product(result.getString("ID"), result.getString("NAME"),
                                        result.getInt("RUBLES_PRICE"));
            else return null;
        }
    }


    Product updateProduct(Product product) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "UPDATE PRODUCTS SET name = " + product.productName() + ", rubles_price = "
                    + product.rublesPrice() + " WHERE id = " + product.productId() + ";";
            statement.executeUpdate(sql);

            sql = "SELECT * FROM PRODUCTS WHERE id = " + product.productId() + ";";
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                return new Product(result.getString("ID"), result.getString("NAME"),
                                        result.getInt("RUBLES_PRICE"));
            else return null;
        }
    }

    void deleteProduct(String productCode) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_CLASSNAME);
        SalesDAO salesDAO = new SalesDAO();
        salesDAO.deleteSale(productCode);
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM PRODUCTS WHERE id = " + productCode + ";";
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
