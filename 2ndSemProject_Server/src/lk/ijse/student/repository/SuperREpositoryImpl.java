/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 * @param <T>
 * @param <ID>
 */
public abstract class SuperREpositoryImpl <T,ID extends Serializable> implements SuperRepository<T, ID>{
    
     private Session session;
     private Class<T> entityClass;
     
     public SuperREpositoryImpl() {
        entityClass = (Class<T>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }
     
     @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(T t) throws Exception {
        return (session.save(t) != null);
    }

    @Override
    public void delete(T t) throws Exception {
        session.delete(t);
    }

    @Override
    public void update(T t) throws Exception {
        session.update(t);
    }

    @Override
    public T findById(ID id) throws Exception {
        return (T) session.get(entityClass,id);
    }

    @Override
    public List<T> findAll() throws Exception {
        return session.createQuery("FROM " + entityClass.getSimpleName()).list();
    }
    

}
