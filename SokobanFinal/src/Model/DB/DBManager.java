package Model.DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBManager implements DBM{

	private static DBManager instance = new DBManager();
	private SessionFactory factory;

	private DBManager() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);//turn off the notes..
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	public static DBManager getInstance() {
		return instance;
	}

	@Override
	public void saveOrUpdate(DB data) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(data);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	//@Override
	public <T> void deleteData(Class<T> c, Serializable sKey) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Object obj = session.get(c, sKey);
			session.delete(obj);
			tx.commit();
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		}		
		finally {
			if (session != null)
				session.close();			
		}
	}

	public void close() {
		factory.close();
	}
	
	@Override
	public <T> ObservableList<T> getListByQuery(String q) {
		Session session = null;
		List list = null;
		try {
			session = factory.openSession();
			Query<T> query = session.createQuery(q);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return FXCollections.observableList(list);
	}
	
	@Override
	public <T> ObservableList<T> getListByQuery(String q, int maxResults) {
		Session session = null;
		List list = null;
		try {
			session = factory.openSession();
			Query<T> query = session.createQuery(q);
			query.setMaxResults(maxResults);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return FXCollections.observableList(list);
	}
}