/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.TeacherService;


/**
 *
 * @author Kavindu
 */
public class TeacherControler {
    
    public static boolean addTeacher(TeacherDto teacherDto)throws Exception{
        TeacherService service=(TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.TEACHER);
        return service.addTeacher(teacherDto);
    }
    
    public static List<TeacherDto> getAll()throws Exception{
        TeacherService service=(TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.TEACHER);
        return service.getAll();
    }
      public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.TEACHER);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.TEACHER);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveAttendance(String id) throws Exception{
        TeacherService Service = (TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.TEACHER);
        return Service.reserve(id);
    }
    
    public static boolean releaseAttendance(String id) throws Exception{
        TeacherService Service = (TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.TEACHER);
        return Service.release(id);        
    }
}
