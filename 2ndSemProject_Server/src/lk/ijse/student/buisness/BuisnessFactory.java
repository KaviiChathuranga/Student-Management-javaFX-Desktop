/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness;

import lk.ijse.student.buisness.custom.impl.AttendanceBOImpl;
import lk.ijse.student.buisness.custom.impl.BatchBOImpl;
import lk.ijse.student.buisness.custom.impl.Batch_SubjectBOImpl;
import lk.ijse.student.buisness.custom.impl.ExamBOImpl;
import lk.ijse.student.buisness.custom.impl.LoginBOImpl;
import lk.ijse.student.buisness.custom.impl.PaymentBOImpl;
import lk.ijse.student.buisness.custom.impl.RegistrationBOImpl;
import lk.ijse.student.buisness.custom.impl.ResultBOImpl;
import lk.ijse.student.buisness.custom.impl.StudentBOImpl;
import lk.ijse.student.buisness.custom.impl.SubjectBOImpl;
import lk.ijse.student.buisness.custom.impl.TeacherBOImpl;

/**
 *
 * @author Kavindu
 */
public class BuisnessFactory {
    private static BuisnessFactory buisnessFactory;
    public enum BOTypes{
        STUDENT,SUBJECT,TEACHER,LOGIN,EXAM,REGISTRATION,PAYMENT,RESULT,BATCH,ATTENDANCE,BATCH_TEACHER
    }
    
    
    private BuisnessFactory(){
        
    }
    
    public static BuisnessFactory getInstance(){
          if (buisnessFactory == null){
            buisnessFactory = new BuisnessFactory();
        }
        return buisnessFactory;
    }
    public SuperBO getBO(BOTypes types){
        switch(types){
            
            case STUDENT:return new StudentBOImpl();
            case PAYMENT:return new PaymentBOImpl();
            case TEACHER:return new TeacherBOImpl();
            case SUBJECT:return new SubjectBOImpl();
            case LOGIN:return new LoginBOImpl();
            case ATTENDANCE:return new AttendanceBOImpl();
            case EXAM:return new ExamBOImpl();
            case RESULT:return new ResultBOImpl();
            case BATCH:return new BatchBOImpl();
            case REGISTRATION:return new RegistrationBOImpl();
            case BATCH_TEACHER:return new Batch_SubjectBOImpl();
            
            default:return null;
        }
    }
}
