package com.syntask.sale.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;

public abstract class BaseDao<PK extends Serializable, T> implements Serializable {

	protected final Class<T> persistentClass;
	protected static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Class<?> obtainedClass = getClass();
		Type genericSuperclass = null;
		for (;;) {
			genericSuperclass = obtainedClass.getGenericSuperclass();
			if (genericSuperclass instanceof ParameterizedType) {
				break;
			}
			obtainedClass = obtainedClass.getSuperclass();
		}
		ParameterizedType genericSuperclass_ = (ParameterizedType) genericSuperclass;
		persistentClass = ((Class<T>) ((Class<T>) genericSuperclass_.getActualTypeArguments()[1]));
		
	}
	
	@In
	protected EntityManager entityManager;

	public T findById(PK id) {
		return entityManager.find(persistentClass, id);
	}
	

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public List<T> getEntities(int pageSize, int pageIndex) {

		return entityManager.createQuery("Select o from " + persistentClass.getName() + " o", persistentClass)
				.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).getResultList();

	}

	public void update(T entity) throws Exception{
		entityManager.refresh(entity);
	}

}
