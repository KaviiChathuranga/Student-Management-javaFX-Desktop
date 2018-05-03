/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.SubjectService;


/**
 *
 * @author Kavindu
 */
public class SubjectControler {
    public static boolean addSubject(SubjectDto subjectDto)throws Exception{
        SubjectService service=(SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return service.addSub(subjectDto);
    }
    public static List<SubjectDto> getAll()throws Exception{
        SubjectService service=(SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return service.getAll();
    }
    public static List<SubjectDto> searchAll(String id)throws Exception{
        SubjectService service=(SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return service.searchAll(id); 
    }
    public static SubjectDto search(String id)throws Exception{
        SubjectService service=(SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return service.searchSubject(id); 
    }
    public static SubjectDto searchSubjectName(String name)throws Exception{
        SubjectService service=(SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return service.searchSubjectName(name); 
    }
       public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveSubject(String id) throws Exception{
        SubjectService Service = (SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return Service.reserve(id);
    }
    
    public static boolean releaseSubject(String id) throws Exception{
        SubjectService Service = (SubjectService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.SUBJECT);
        return Service.release(id);        
    }
}
