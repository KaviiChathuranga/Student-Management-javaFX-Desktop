/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.ExamDto;

import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.ExamService;



/**
 *
 * @author Kavindu
 */
public class ExamControler {
    
    public static boolean addExam(ExamDto examDto)throws Exception{
        ExamService service=(ExamService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
        return service.addExam(examDto);    
    }
    public static List<ExamDto> getAll()throws Exception{
       ExamService service=(ExamService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
       return service.getAll(); 
  
    }
    public static ExamDto searchExam(String id)throws Exception{
       ExamService service=(ExamService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
       return service.searchExam(id); 
  
    }
     public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveAttendance(String id) throws Exception{
        ExamService Service = (ExamService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
        return Service.reserve(id);
    }
    
    public static boolean releaseAttendance(String id) throws Exception{
        ExamService Service = (ExamService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.EXAM);
        return Service.release(id);        
    }
}
