/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.repository;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 * @param <T>
 * @param <ID>
 */
public interface SuperRepository <T,ID>{
    
    public void setSession(Session session);
    
    public boolean save(T t) throws Exception;
    
    public void delete(T t) throws Exception;
    
    public void update(T t) throws Exception;
    
    public T findById(ID id) throws Exception;
    
    public List<T> findAll() throws Exception;
}
