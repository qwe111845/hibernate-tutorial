package com.lin.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Getting students object...");
			
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
            displayStudents(theStudents);
			
            theStudents = session.createQuery("from Student s where s.lastName = 'Wall'").list();
            
			System.out.println("\nGetting students object...");
            
            displayStudents(theStudents);

			System.out.println("\nGetting students object who have email daffy@gmail.com");

            theStudents = session.createQuery("from Student s where s.lastName = 'Wall' and s.email = 'daffy@gmail.com'").list();

            displayStudents(theStudents);

			session.getTransaction().commit();
			System.out.println("Done!");

			
		} 
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
