package ru.krivonogova.springcourse.hibernate_app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.krivonogova.springcourse.hibernate_app.model.Passport;
import ru.krivonogova.springcourse.hibernate_app.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println(person.getPassport().getPassportNumber());
            
            Passport passport = session.get(Passport.class, 1);
            System.out.println(passport.getPerson().getName());
            
            person.getPassport().setPassportNumber(456789);
            
            System.out.println(person.getPassport().getPassportNumber());
            
            session.remove(person);

            session.getTransaction().commit();
 
        } finally {
            sessionFactory.close();
        }
    }
}




















