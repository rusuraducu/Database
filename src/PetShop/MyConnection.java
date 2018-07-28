package PetShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyConnection {
    private static String URL = "jdbc:mysql://localhost/petshop";
    private static String USER = "root";
    private static String PASSWORD = "";
    private List<Connection> connectionList = null;
    private static int poolSize = 10;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getAvailableConnections() {
        return connectionList.size();
    }


    public MyConnection() {
        this.initialize();
    }


    private void initialize() {
        if (connectionList == null) {
            connectionList = new ArrayList<>(10);
        }
        for (int i = 0; i < poolSize; i++) {
            connectionList.add(createConnection());
        }
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        int numberOfTries = 0;
        do {
            if (connectionList.size() > 0) {
                connection = connectionList.remove(0);
            }
            numberOfTries++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (connection == null && numberOfTries < 5);
        return connection;
    }


    private static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public synchronized void closeConnection(Connection connection) {
        if (connection != null) {
            connectionList.add(connection);
        }
    }
}
