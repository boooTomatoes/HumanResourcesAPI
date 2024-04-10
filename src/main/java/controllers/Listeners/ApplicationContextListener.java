package controllers.Listeners;

import java.sql.Driver;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import persistence.util.CustomEntityManagerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
@Slf4j
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Unregister JDBC driver
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("Error deregistering driver", e);
            }
        }
        CustomEntityManagerFactory.INSTANCE.close();

        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
}
