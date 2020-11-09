package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    public static String mySqlUrl = "jdbc:mysql://192.168.99.100:3306/app";
    public static String login = "app";
    public static String password = "pass";

    public static void cleanTable() throws SQLException {
        val runner = new QueryRunner();
        val creditRequest = "DELETE FROM credit_request_entity";
        val order = "DELETE FROM order_entity";
        val payment = "DELETE FROM payment_entity";

        try (
                val conn = DriverManager.getConnection(mySqlUrl, login, password);
        ) {
            runner.update(conn, creditRequest);
            runner.update(conn, order);
            runner.update(conn, payment);
        }
    }

    private static String getData(String sqlQuery, String column) throws SQLException {
        try (
                val conn = DriverManager.getConnection(mySqlUrl, login, password);
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(sqlQuery)) {
                if (rs.next()) {
                    val data = rs.getString(column);
                    return data;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public static String getLastPaymentStatus() throws SQLException {
        val getStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "status";
        return SQLHelper.getData(getStatus, column);
    }

    public static String getLastPaymentID() throws SQLException {
        val getStatus = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        val column = "payment_id";
        return SQLHelper.getData(getStatus, column);
    }

    public static String getLastTransactionID() throws SQLException {
        val getStatus = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "transaction_id";
        return SQLHelper.getData(getStatus, column);
    }
}
