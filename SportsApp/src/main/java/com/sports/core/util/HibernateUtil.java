package com.sports.core.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sports.core.domain.FamilyDetails;
import com.sports.core.domain.Requests;
import com.sports.core.domain.Team;
import com.sports.core.domain.TeamMapping;



@Repository
public class HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	public <T> Serializable create(final T entity) {
	return sessionFactory.getCurrentSession().save(entity); 
	
	}

	public <T> T update(final T entity) {
	sessionFactory.getCurrentSession().update(entity); 
	return entity;
	}

	public <T> void delete(final T entity) {
	sessionFactory.getCurrentSession().delete(entity);
	}
	
	
	public <T> void delete(Serializable id, Class<T> entityClass) {
	T entity = fetchById(id, entityClass);
	delete(entity);
	}
	public <T> int delete(String query1,int id) {
		org.hibernate.Query query =  sessionFactory.getCurrentSession().createQuery(query1);
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
		}
	
	public <T> int delete1(String query1,T entity) {
		org.hibernate.Query query =  sessionFactory.getCurrentSession().createQuery(query1);
		query.setEntity("entity", entity);
		int result = query.executeUpdate();
		return result;
		}
	public <T> int update(String query1,int id) {
		org.hibernate.Query query =  sessionFactory.getCurrentSession().createQuery(query1);
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
		}
	public <T> T exist(String query1,String userName) {
		org.hibernate.Query query =  sessionFactory.getCurrentSession().createQuery(query1);
		query.setParameter("userName", userName);
		
		 @SuppressWarnings("unchecked")
		T result =  (T) query.uniqueResult();
		return result;
		}
	@SuppressWarnings("unchecked") 
	public <T> List<T> fetchAll(Class<T> entityClass) { 
	return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list(); 
	}
	@SuppressWarnings("unchecked") 
	public <T> String columnNames(Class<T> entityClass) { 
		ClassMetadata classMetadata = sessionFactory.getClassMetadata(entityClass); 
		  classMetadata.getIdentifierPropertyName(); 
		  //classMetadata.getPropertyNames(); 
		  //System.out.println(classMetadata.getIdentifierPropertyName());
		  String id = classMetadata.getIdentifierPropertyName();
		  return id;
	}
	
	 

	@SuppressWarnings("rawtypes")
	public <T> List fetchAll(String query) { 
	return sessionFactory.getCurrentSession().createSQLQuery(query).list(); 
	}
	@SuppressWarnings("rawtypes")
	public <T> List fetchAll1(String query1,String userName,String userPassword) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setString("userName",userName);
		querylist.setParameter("userPassword",userPassword);
		List list = querylist.list();
	 return list; 
	}
	/*@SuppressWarnings("rawtypes")
	public <T> List fetchAddress(String query1,String pincode,String tablename) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setString("pincode",pincode);
		//querylist.setParameter("pincode",pincode);
		querylist.setString("tablename",tablename);
		List list = querylist.list();
	 return list; 
	}*/
	@SuppressWarnings("rawtypes")
	public <T> List fetchAddress(String query1,String frompincode,String topincode,String tablename) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setString("frompincode",frompincode);
		//querylist.setParameter("pincode",pincode);
		querylist.setString("topincode",topincode);
		querylist.setString("tablename",tablename);
		List list = querylist.list();
	 return list; 
	}
	
	@SuppressWarnings({ "unchecked" })
	public <T> T fetchAddress1(String query1,int id,String tablename) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setParameter("id", id);
		//querylist.setParameter("pincode",pincode);
		querylist.setString("tablename",tablename);
		T result = (T) querylist.uniqueResult();
	 return  result; 
	}
	@SuppressWarnings("rawtypes")
	public <T> List fetchAll2(String query1,T entity) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setEntity("entity",entity);
		
		List list = querylist.list();
	 return list; 
	}
	@SuppressWarnings("rawtypes")
	public <T> List fetchAll5(String query1,T entity,Date date) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setEntity("entity",entity);
		querylist.setParameter("dateToLookBackAfter", date);
		List list = querylist.list();
	 return list; 
	}
	
	@SuppressWarnings("rawtypes")
	public <T> List fetchAll4(String query1,int id) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setParameter("id", id);
		
		
		List list = querylist.list();
	 return list; 
	}
	
	public <T> Requests fetchAll3(String query1,T entity,T entity1) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setEntity("entity",entity);
		querylist.setEntity("entity1",entity1);
		@SuppressWarnings("unchecked")
		Requests result = (Requests) querylist.uniqueResult();
	 return result; 
	}
	public <T,S> S fetchAll7(String query1,T entity,T entity1) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setEntity("entity",entity);
		querylist.setEntity("entity1",entity1);
		@SuppressWarnings("unchecked")
		S result = (S) querylist.uniqueResult();
	 return result; 
	}
	public <T> FamilyDetails fetchfamily(String query1,T entity) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setEntity("entity",entity);
		
		@SuppressWarnings("unchecked")
		FamilyDetails result = (FamilyDetails) querylist.uniqueResult();
	 return result; 
	}
	public <T, S> S fetchteam(String query1,T entity) { 
		org.hibernate.Query querylist =   sessionFactory.getCurrentSession().createQuery(query1);
		querylist.setEntity("entity",entity);
		
		@SuppressWarnings("unchecked")
		S result = (S) querylist.uniqueResult();
	 return result; 
	}
	@SuppressWarnings("unchecked")
	public <T> T fetchById(Serializable id, Class<T> entityClass) {
	return (T)sessionFactory.getCurrentSession().get(entityClass, id);
	}
	//Long lastId = ((BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
}
