package util;


import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.loadProperties("hibernate.properties");
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistryBuilder.build());
        metadataSources.
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Room.class).
                addAnnotatedClass(Reservation.class).
                addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        sessionFactory =  metadata.getSessionFactoryBuilder().build();
    }

    public static FactoryConfiguration getFactoryConfiguration() {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }


    public Session getSession() {
        return sessionFactory.openSession();
    }
}
