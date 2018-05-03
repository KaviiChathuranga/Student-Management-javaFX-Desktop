/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service;

import java.rmi.Remote;

/**
 *
 * @author Kavindu
 */
public interface ServiceFactory extends Remote{
    
    public enum ServiceTypes{
        STUDENT,SUBJECT,TEACHER,LOGIN,EXAM,REGISTRATION,PAYMENT,RESULT,BATCH,ATTENDANCE,BATCH_TEACHER
    }
    public SuperService getServiceTypes(ServiceTypes types)throws Exception;
}
