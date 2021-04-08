package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;

			session.beginTransaction();
			
			System.out.println("Getting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updatting student ...");
			myStudent.setFirstName("Joe");
			
			session.getTransaction().commit();
			System.out.println("Done!");
			

			session = factory.getCurrentSession();
            session.beginTransaction();
			
			System.out.println("Updatting student's email who have first name Paul ...");
            session.createQuery("update Student s set email = 'update@gmail.com' where s.firstName = 'Paul' ")
                   .executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} 
		finally {
			factory.close();
		}
	}

}
