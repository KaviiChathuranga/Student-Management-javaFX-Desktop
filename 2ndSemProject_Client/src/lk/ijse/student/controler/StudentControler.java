/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;


import java.util.List;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.StudentService;
import lk.ijse.student.observer.SubjectOb;

/**
 *
 * @author Kavindu
 */
public class StudentControler {
    public static boolean addStudent(StudentDto studentDto)throws Exception{
        StudentService studentService=(StudentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.addStudent(studentDto);    
    }
    public static boolean updateStudent(StudentDto studentDto)throws Exception{
        StudentService studentService=(StudentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.updateStudent(studentDto);    
    }
     public static List<StudentDto> getAll()throws Exception{
        StudentService studentService=(StudentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.getAll();    
    }
     public static StudentDto search(String id)throws Exception{
        StudentService studentService=(StudentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.searchStudent(id);    
    }

     public static void registerObserver(Observer observer) throws Exception{
        SubjectOb studentService = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        studentService.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb studentService = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        studentService.unregisterObserver(observer);
    }
    
    public static boolean reserveStudent(String sid) throws Exception{
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.reserve(sid);
    }
    
    public static boolean releaseStudent(String sid) throws Exception{
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.release(sid);        
    }
}
