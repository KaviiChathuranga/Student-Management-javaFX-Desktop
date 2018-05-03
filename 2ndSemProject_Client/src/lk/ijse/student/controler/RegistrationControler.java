/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.RegistrationService;



/**
 *
 * @author Kavindu
 */
public class RegistrationControler {
    
     public static boolean addReg(RegistrationDto registrationDto)throws Exception{
        RegistrationService service=(RegistrationService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        return service.addRegistration(registrationDto);    
    }
      public static List<RegistrationDto> getAll()throws Exception{
        RegistrationService service=(RegistrationService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        return service.getAll();    
    }
       public static List<RegistrationDto> getBatchStudent(String id)throws Exception{
        RegistrationService service=(RegistrationService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        return service.getBatchStudent(id);    
    }
        public static RegistrationDto searcherAll(String id)throws Exception{
        RegistrationService service=(RegistrationService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        return service.searchRegistration(id);    
    } 
     public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserve(String id) throws Exception{
        RegistrationService Service = (RegistrationService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        return Service.reserve(id);
    }
    
    public static boolean release(String id) throws Exception{
        RegistrationService Service = (RegistrationService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.REGISTRATION);
        return Service.release(id);        
    }
}
