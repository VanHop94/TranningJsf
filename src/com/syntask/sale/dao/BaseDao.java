package com.syntask.sale.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.syntask.sale.entity.Employee;

public abstract class BaseDao<PK extends Serializable, T> implements Serializable {

	protected final Class<T> persistentClass;
	protected static final long serialVersionUID = 1L;

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

	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManagerFactory");
	protected EntityManager entityManager;

	public T findById(PK id) {
		List<T> datas =  getEntityManager().createNamedQuery("findByCode", persistentClass).setParameter("code", id).getResultList();
		if(datas.size() > 0)
			return datas.get(0);
		return null;
	}

	protected EntityManager getEntityManager() {

		if (entityManager == null)
			entityManager = factory.createEntityManager();

		if (!entityManager.getTransaction().isActive())
			entityManager.getTransaction().begin();

		return entityManager;
	}

	public void persist(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
	}

	public void delete(T entity) {
		getEntityManager().remove(entity);
		getEntityManager().getTransaction().commit();
	}

	public List<T> getEntities(int pageSize, int pageIndex) {

		return getEntityManager().createQuery("Select o from " + persistentClass.getName() + " o", persistentClass)
				.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).getResultList();

	}

	public void update(T entity) {
		getEntityManager().refresh(entity);
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
	}

}
