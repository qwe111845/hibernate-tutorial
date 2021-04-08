package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 2;

			session.beginTransaction();
			
			System.out.println("Getting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("deletting student ...");
            session.delete(myStudent);	
            
			session.getTransaction().commit();
			System.out.println("Done!");
			

			session = factory.getCurrentSession();
            session.beginTransaction();
			
			System.out.println("deletting student who have first name saw and last name ream ...");
            session.createQuery("delete from Student s where s.firstName = 'saw' and s.lastName = 'ream' ")
                   .executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} 
		finally {
			factory.close();
		}
	}

}
