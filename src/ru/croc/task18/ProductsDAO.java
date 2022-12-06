package ru.croc.task18;

import ru.croc.task17.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ProductsDAO {
    private final String JDBC_URL = "jdbc:h2:~/test";
    private final String JDBC_CLASSNAME = "org.h2.Driver";
    private final String username = "sa";
    private final String password = "sa";

    static class ProductAlreadyExistExc extends Exception {     }

    /**
     * Creates a new PRODUCTS table with 3 columns
     * (id VARCHAR2(255) NOT NULL,
     *  name VARCHAR2(255) NOT NULL,
     *  rubles_price INTEGER NOT NULL,
     *  PRIMARY KEY ( ID ))
     */
    public void createTable(){
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
            Class.forName(JDBC_CLASSNAME);

            //��� ����������� � ������� �����
            String sql = "DROP TABLE IF EXISTS PRODUCTS;";
            statement.executeUpdate(sql);

            sql = """
                       CREATE TABLE IF NOT EXISTS PRODUCTS
                       (id VARCHAR2(255) NOT NULL,
                       name VARCHAR2(255) NOT NULL,
                       rubles_price INTEGER NOT NULL,
                       PRIMARY KEY ( ID ))
                   """;
            statement.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * imports data from a csv file
     * @param path to a csv file
     */
    public void importProductsFromCSV(String path){
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null){
                String[] values = currentLine.split(",");
                mergeProduct(new ru.croc.task17.Product(values[2], values[3], Integer.parseInt(values[4])));
            }
        }
        catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Merges in a new row.
     * Adds a row its id is unique, replaces data otherwise
     * @param p - a Product object
     */
    public void mergeProduct(Product p) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product findProduct(String productCode){
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            //merge bc of a primary key constraint
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM PRODUCTS WHERE id = " + productCode;
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                return new Product(result.getString(1), result.getString(2),
                        Integer.parseInt(result.getString(3)));
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Product updateProduct(Product product){
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "UPDATE PRODUCTS SET name = " + product.productName() + ", rubles_price = "
                    + product.rublesPrice() + " WHERE id = " + product.productId();
            statement.executeUpdate(sql);

            sql = "SELECT * FROM PRODUCTS WHERE id = " + product.productId();
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                return new Product(result.getString(1), result.getString(2),
                        Integer.parseInt(result.getString(3)));
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*void deleteProduct(String productCode){
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {

            String sql = "UPDATE PRODUCTS SET name = " + product.productName() + ", rubles_price = "
                    + product.rublesPrice() + " WHERE id = " + product.productId();
            statement.executeUpdate(sql);

            sql = "SELECT * FROM PRODUCTS WHERE id = " + product.productId();
            ResultSet result = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

     */



}
