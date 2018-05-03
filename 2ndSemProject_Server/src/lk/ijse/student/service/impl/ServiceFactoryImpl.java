/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.SuperService;
import lk.ijse.student.service.custom.impl.AttendanceServiceImpl;
import lk.ijse.student.service.custom.impl.BatchServiceImpl;
import lk.ijse.student.service.custom.impl.Batch_Teacher_ServiceImpl;
import lk.ijse.student.service.custom.impl.ExamServiceImpl;
import lk.ijse.student.service.custom.impl.LoginServiceImpl;
import lk.ijse.student.service.custom.impl.PaymentServiceImpl;
import lk.ijse.student.service.custom.impl.RegistrationServiceImpl;
import lk.ijse.student.service.custom.impl.ResultServiceImpl;
import lk.ijse.student.service.custom.impl.StudentServiceImpl;
import lk.ijse.student.service.custom.impl.SubjectServiceImpl;
import lk.ijse.student.service.custom.impl.TeacherServiceImpl;

/**
 *
 * @author Kavindu
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
    public static ServiceFactory serviceFactory;

    private ServiceFactoryImpl()throws RemoteException {
    }

    public static ServiceFactory getInstance()throws RemoteException{
        if (serviceFactory==null) {
            serviceFactory=new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getServiceTypes(ServiceTypes types) throws Exception {
        switch(types){
            
            case STUDENT:return new StudentServiceImpl();
            case SUBJECT:return new SubjectServiceImpl();
            case EXAM:return new ExamServiceImpl();
            case BATCH:return new BatchServiceImpl();
            case RESULT:return new ResultServiceImpl();
            case REGISTRATION:return new RegistrationServiceImpl();
            case PAYMENT:return new PaymentServiceImpl();
            case ATTENDANCE:return new AttendanceServiceImpl();
            case TEACHER:return new TeacherServiceImpl();
            case LOGIN:return new LoginServiceImpl();
            case BATCH_TEACHER:return new Batch_Teacher_ServiceImpl();
            
            default:return null;
        }
    }
    
}
