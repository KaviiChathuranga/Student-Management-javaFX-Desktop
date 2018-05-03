/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.observer;

import java.rmi.Remote;

/**
 *
 * @author Kavindu
 */
public interface SubjectOb extends Remote{
    public void registerObserver(Observer observer) throws Exception;
    
    public void unregisterObserver(Observer observer) throws Exception;
    
    public void notifyAllObservers() throws Exception;
}
