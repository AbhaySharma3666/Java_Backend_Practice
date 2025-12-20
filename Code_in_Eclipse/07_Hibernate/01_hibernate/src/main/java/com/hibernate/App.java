package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Student s1 = new Student();
//		s1.setId(102);
//		s1.setName("rohan dev");
//		s1.setStudentClass("12th Grade");

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = meta.getSessionFactoryBuilder().build();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
//		session.save(s1);
		s1 = session.get(Student.class, 102);
		System.out.println(s1);
		tr.commit();
		sf.close();
		session.close();
		System.out.println("Data Inserted Successfully");
	}

}
