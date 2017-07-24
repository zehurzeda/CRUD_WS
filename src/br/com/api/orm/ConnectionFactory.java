package br.com.api.orm;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lembreteUnit");
	
	private static EntityManager em = factory.createEntityManager();

	public static EntityManager getEntityManager() {
		return em;
	}
}
