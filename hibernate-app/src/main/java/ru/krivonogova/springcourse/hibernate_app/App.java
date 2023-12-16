package ru.krivonogova.springcourse.hibernate_app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.krivonogova.springcourse.hibernate_app.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();


            Person person = session.get(Person.class, 2);
            person.setName("Anna");

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}




















