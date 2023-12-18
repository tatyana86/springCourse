package org.hibernate_many_to_many;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate_many_to_many.model.Actor;
import org.hibernate_many_to_many.model.Movie;

public class App 
{
    public static void main( String[] args )
    {
    	Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey", 81);
//            Actor actor2 = new Actor("Samuel", 72);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);
            
            Movie movie = session.get(Movie.class, 1);
            System.out.println(movie.getActors());
            
            session.getTransaction().commit();
 
        }    
    }
}







