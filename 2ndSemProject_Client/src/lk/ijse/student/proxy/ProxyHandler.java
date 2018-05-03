/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.ServiceFactory.ServiceTypes;
import lk.ijse.student.service.SuperService;
import lk.ijse.student.service.custom.AttendanceService;
import lk.ijse.student.service.custom.BatchService;
import lk.ijse.student.service.custom.Batch_TeacherService;
import lk.ijse.student.service.custom.ExamService;
import lk.ijse.student.service.custom.LoginService;
import lk.ijse.student.service.custom.PaymentService;
import lk.ijse.student.service.custom.RegistrationService;
import lk.ijse.student.service.custom.ResultService;
import lk.ijse.student.service.custom.StudentService;
import lk.ijse.student.service.custom.SubjectService;
import lk.ijse.student.service.custom.TeacherService;

/**
 *
 * @author Kavindu
 */
public class ProxyHandler implements ServiceFactory{
    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    
    private StudentService studentService;
    private BatchService batchService;
    private LoginService loginService;
    private AttendanceService attendanceService;
    private ExamService examService;
    private RegistrationService registrationService;
    private TeacherService teacherService;
    private ResultService resultService;
    private SubjectService subjectService;
    private PaymentService paymentService;
    private Batch_TeacherService batch_TeacherService;

    private ProxyHandler() {
        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/student");
            
            batchService = (BatchService) serviceFactory.getServiceTypes(ServiceTypes.BATCH);
            studentService = (StudentService) serviceFactory.getServiceTypes(ServiceTypes.STUDENT);
            loginService=(LoginService)serviceFactory.getServiceTypes(ServiceTypes.LOGIN);
            attendanceService=(AttendanceService)serviceFactory.getServiceTypes(ServiceTypes.ATTENDANCE);
            examService=(ExamService)serviceFactory.getServiceTypes(ServiceTypes.EXAM);
            registrationService=(RegistrationService)serviceFactory.getServiceTypes(ServiceTypes.REGISTRATION);
            teacherService=(TeacherService)serviceFactory.getServiceTypes(ServiceTypes.TEACHER);
            resultService=(ResultService)serviceFactory.getServiceTypes(ServiceTypes.RESULT);
            subjectService=(SubjectService)serviceFactory.getServiceTypes(ServiceTypes.SUBJECT);
            paymentService=(PaymentService)serviceFactory.getServiceTypes(ServiceTypes.PAYMENT);
            batch_TeacherService=(Batch_TeacherService) serviceFactory.getServiceTypes(ServiceTypes.BATCH_TEACHER);

        } catch (NotBoundException ex) {
            System.out.println("1");
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException | RemoteException ex) {
            System.out.println("2");
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("3");
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ProxyHandler getInstance(){
        if (proxyHandler==null) {
            proxyHandler=new ProxyHandler();
        }
        return proxyHandler;
    }

    @Override
    public SuperService getServiceTypes(ServiceTypes types) throws Exception {
       switch(types){
           case STUDENT:return studentService;
           case BATCH:return batchService;
           case LOGIN:return loginService;
           case REGISTRATION:return registrationService;
           case EXAM:return examService;
           case RESULT:return resultService;
           case TEACHER:return teacherService;
           case ATTENDANCE:return attendanceService;
           case PAYMENT:return paymentService;
           case SUBJECT:return subjectService ;
           case BATCH_TEACHER:return batch_TeacherService;
           default:return null;
       }
    }
    
    
}
