package ru.croc.task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class SalesDAO {
    private final String JDBC_URL = "jdbc:h2:~/test";
    private final String JDBC_CLASSNAME = "org.h2.Driver";
    private final String username = "sa";
    private final String password = "sa";

    /**
     * Creates a SALES table with 4 columns
     * (id IDENTITY NOT NULL PRIMARY KEY,
    *   order_id INTEGER NOT NULL,
    *   login VARCHAR2(255) NOT NULL,
    *   product_id VARCHAR2(255) NOT NULL REFERENCES PRODUCTS)
     */
    void createTable() throws ClassNotFoundException, SQLException {
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
                Statement statement = connection.createStatement()) {
            Class.forName(JDBC_CLASSNAME);

            String sql = """
                     CREATE TABLE IF NOT EXISTS SALES
                     (id IDENTITY NOT NULL PRIMARY KEY,
                      order_id INTEGER NOT NULL,
                      login VARCHAR2(255) NOT NULL,
                      product_id VARCHAR2(255) NOT NULL REFERENCES PRODUCTS
                      )
                  """;
            statement.executeUpdate(sql);


        }
    }

    /**
     * imports data from a csv file
     * @param path to a csv file
     */
    void importSalesFromCSV(String path) throws IOException, SQLException {
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
     * For study purposes
     **/
    void dropTable(){
        try(Connection connection = DriverManager.getConnection(JDBC_URL,username,password);
            Statement statement = connection.createStatement()) {
        String sql = "DROP TABLE IF EXISTS SALES";
        statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
