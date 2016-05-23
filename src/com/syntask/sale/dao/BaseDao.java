package com.syntask.sale.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public abstract class BaseDao<PK extends Serializable, T> implements Serializable {

	private final Class<T> persistentClass;
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Class obtainedClass = getClass();
		Type genericSuperclass = null;
		for (;;) {
			genericSuperclass = obtainedClass.getGenericSuperclass();
			if (genericSuperclass instanceof ParameterizedType) {
				break;
			}
			obtainedClass = obtainedClass.getSuperclass();
		}
		ParameterizedType genericSuperclass_ = (ParameterizedType) genericSuperclass;
		persistentClass = ((Class) ((Class) genericSuperclass_.getActualTypeArguments()[1]));
	}

	// private EntityManagerFactory factory =
	// Persistence.createEntityManagerFactory("entityManagerFactory");
	 @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		/*
		 * if(entityManager == null) entityManager =
		 * factory.createEntityManager();
		 * 
		 * if(!entityManager.getTransaction().isActive())
		 * entityManager.getTransaction().begin();
		 */

		return entityManager;
	}

	public T findById(PK id) {
		getEntityManager().find(persistentClass, id);
		return (T) getEntityManager().find(persistentClass, id);
	}

	public void persist(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		// zgetEntityManager().getTransaction().commit();
	}

	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	public List<T> getEntities(int pageSize, int pageIndex) {

		return getEntityManager().createQuery("Select o from " + persistentClass.getName() + " o", persistentClass)
				.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).getResultList();

	}

	/*
	 * public PK insert(T entity) { return (PK) getEntityManager().(entity); }
	 */

	public void update(T entity) {
		getEntityManager().refresh(entity);
		getEntityManager().flush();
	}

}
