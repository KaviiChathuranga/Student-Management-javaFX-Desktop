/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.resouce;

import java.io.File;
import lk.ijse.student.entity.Attendance;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.entity.Batch_Subject;
import lk.ijse.student.entity.Exam;
import lk.ijse.student.entity.Login;
import lk.ijse.student.entity.Payment;
import lk.ijse.student.entity.Registration;
import lk.ijse.student.entity.Result;
import lk.ijse.student.entity.Student;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Kavindu
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            
            registry = new StandardServiceRegistryBuilder().loadProperties(new File("settings/hibernate.properties")).build();
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Login.class)
                    .addAnnotatedClass(Batch.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(Exam.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(Result.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Registration.class)
                    .addAnnotatedClass(Attendance.class)
                    .addAnnotatedClass(Batch_Subject.class)                    
                    .buildMetadata().buildSessionFactory();
            
        } catch (Throwable ex) {
          
            System.err.println(" * SessionFactory creation failed ! * " + ex);
            
            StandardServiceRegistryBuilder.destroy(registry);
            
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
