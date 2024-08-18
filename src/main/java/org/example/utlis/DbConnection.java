package org.example.utlis;


import org.example.entities.CurrentWeather;
import org.example.entities.Forecast;
import org.example.entities.Location;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static SessionFactory FACTORY = null;

    public static SessionFactory getFACTORY(){
        if (FACTORY == null){
            synchronized (SessionFactory.class){
                if(FACTORY == null){
                    try{
                        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                        Properties prop = new Properties();
                        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/weather_app_db");
                        prop.setProperty("hibernate.hbm2ddl.auto", "update");
                        prop.setProperty("hibernate.show_sql", "false");
                        prop.setProperty("hibernate.format_sql", "false");
                        prop.setProperty("hibernate.connection.username", System.getenv("MYSQL_USR"));
                        prop.setProperty("hibernate.connection.password", System.getenv("MYSQL_PSWD"));
                        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                        prop.setProperty("hibernate.current_session_context_class", "thread");

                        // Logging configuration
                        prop.setProperty("hibernate.use_sql_comments", "false");
                        prop.setProperty("hibernate.generate_statistics", "false");
                        prop.setProperty("org.hibernate.SQL.level", "WARN");
                        prop.setProperty("org.hibernate.type.descriptor.sql.BasicBinder.level", "WARN");



                        Configuration config = new Configuration();
                        config.setProperties(prop);
                        config.addPackage("entities");

                        config.addAnnotatedClass(CurrentWeather.class);
                        config.addAnnotatedClass(Location.class);
                        config.addAnnotatedClass(Forecast.class);


                        ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
                                .applySettings(config.getProperties()).build();

                        FACTORY = config.buildSessionFactory(serviceRegistry);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return FACTORY;
    }
}
