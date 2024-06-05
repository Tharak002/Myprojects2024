package com.java.agent;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionHelper {
	
	public static SessionFactory getSession() {
		SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
		return sf;

}
	
}
