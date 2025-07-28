package com.example.movie;

import com.example.movie.config.AppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Lớp tiện ích để kiểm tra kết nối đến cơ sở dữ liệu.
 */
public class DatabaseConnectionChecker {

    public static void main(String[] args) {
        System.out.println("--- Database Connection Checker ---");
        AnnotationConfigApplicationContext context = null;

        try {
            // Bước 1: Khởi tạo Spring Context.
            // Nếu file .properties bị thiếu hoặc có key bị sai, lỗi sẽ xảy ra ở đây.
            System.out.println("[1/3] Attempting to initialize Spring context and read configuration...");
            context = new AnnotationConfigApplicationContext(AppConfig.class);
            System.out.println("      > Spring context loaded successfully.");

            // Bước 2: Lấy bean DataSource.
            System.out.println("[2/3] Attempting to get DataSource bean from Spring context...");
            DataSource dataSource = context.getBean(DataSource.class);
            System.out.println("      > DataSource bean found.");

            // Bước 3: Thiết lập một kết nối đến database
            System.out.println("[3/3] Attempting to establish a physical connection to the database...");
            try (Connection connection = dataSource.getConnection()) {
                if (connection != null && !connection.isClosed()) {
                    System.out.println("\n=======================================================");
                    System.out.println("  ✅  SUCCESS! Database connection established.");
                    System.out.println("=======================================================");
                    System.out.println("  Database: " + connection.getMetaData().getDatabaseProductName() + " " + connection.getMetaData().getDatabaseProductVersion());
                    System.out.println("  URL: " + connection.getMetaData().getURL());
                    System.out.println("  Username: " + connection.getMetaData().getUserName());
                }
            }

        } catch (BeansException e) {
            System.err.println("\n=======================================================");
            System.err.println("  ❌  FAILURE: Error during Spring Context initialization.");
            System.err.println("=======================================================");
            System.err.println("  Possible Causes:");
            System.err.println("  1. The file 'src/main/resources/database.properties' is missing.");
            System.err.println("  2. A required property (e.g., 'db.driver', 'db.url') is missing from the .properties file.");
            System.err.println("  3. A typo in AppConfig.java or the .properties file.");
            System.err.println("\n  Detailed Error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("\n=======================================================");
            System.err.println("  ❌  FAILURE: Could not establish a physical database connection.");
            System.err.println("=======================================================");
            System.err.println("  Possible Causes:");
            System.err.println("  1. The database server (MySQL) is not running.");
            System.err.println("  2. The database URL, username, or password in 'database.properties' is incorrect.");
            System.err.println("  3. The database name in the URL does not exist on the server.");
            System.err.println("  4. A firewall is blocking the connection to the database port (default: 3306).");
            System.err.println("\n  Detailed SQL State: " + e.getSQLState());
            System.err.println("  Detailed Error Code: " + e.getErrorCode());
            System.err.println("  Detailed Error Message: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n=======================================================");
            System.err.println("  ❌  An unexpected error occurred.");
            System.err.println("=======================================================");
            e.printStackTrace();
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }
}