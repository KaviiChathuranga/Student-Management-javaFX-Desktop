/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.repository;

import lk.ijse.student.repository.custom.Batch_Teacher_Repository;
import lk.ijse.student.repository.custom.impl.AttendanceRepositoryImpl;
import lk.ijse.student.repository.custom.impl.BatchRepositoryImpl;
import lk.ijse.student.repository.custom.impl.Batch_Teacher_RepositoryImpl;
import lk.ijse.student.repository.custom.impl.ExamRepositoryImpl;
import lk.ijse.student.repository.custom.impl.LoginRepositoryImpl;
import lk.ijse.student.repository.custom.impl.PaymentRepositoryImpl;
import lk.ijse.student.repository.custom.impl.RegistrationRepositoryImpl;
import lk.ijse.student.repository.custom.impl.ResultRepositoryImpl;
import lk.ijse.student.repository.custom.impl.StudentRepositoryImpl;
import lk.ijse.student.repository.custom.impl.SubjectRepositoryImpl;
import lk.ijse.student.repository.custom.impl.TeacherRepositoryImpl;

/**
 *
 * @author Kavindu
 */
public class RepositoryFactory {
    
    public enum RepositoryTypes{
        STUDENT,SUBJECT,TEACHER,LOGIN,EXAM,REGISTRATION,PAYMENT,RESULT,BATCH,ATTENDANCE,BATCH_TEACHER
    }
    private static RepositoryFactory repositoryFactory;
    
    private RepositoryFactory(){
        
    }
    public static RepositoryFactory getInstance(){
        if (repositoryFactory==null) {
            repositoryFactory=new RepositoryFactory();
        }
        return repositoryFactory;
    }
    
     public SuperRepository getRepository(RepositoryTypes type){
        switch (type){
            case STUDENT: return new StudentRepositoryImpl();
            case TEACHER: return new TeacherRepositoryImpl();
            case SUBJECT: return new SubjectRepositoryImpl();
            case BATCH: return new BatchRepositoryImpl();
            case LOGIN: return new LoginRepositoryImpl();
            case EXAM: return new ExamRepositoryImpl();
            case RESULT: return new ResultRepositoryImpl();
            case PAYMENT: return new PaymentRepositoryImpl();
            case REGISTRATION: return new RegistrationRepositoryImpl();
            case ATTENDANCE: return new AttendanceRepositoryImpl();
            case BATCH_TEACHER:return new Batch_Teacher_RepositoryImpl();
            
            default: 
                return null;
        }
    }
}
