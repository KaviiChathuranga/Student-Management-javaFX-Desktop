/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;


import java.util.List;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.BatchService;
import lk.ijse.student.service.custom.LoginService;


/**
 *
 * @author Kavindu
 */
public class loginControler {
    public static boolean addLog(LoginDto loginDto)throws Exception{
        LoginService loginService=(LoginService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        return loginService.addLogin(loginDto);
    }
    public static List<LoginDto> getAll()throws Exception{
        LoginService loginService=(LoginService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        return loginService.getAll();
    }
     public static LoginDto searchLog(String id)throws Exception{
        LoginService loginService=(LoginService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        return loginService.searchLogin(id); 
    }
     public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveLog(String Id) throws Exception{
        LoginService Service = (LoginService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        return Service.reserve(Id);
    }
    
    public static boolean releaseLog(String Id) throws Exception{
        LoginService Service = (LoginService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.LOGIN);
        return Service.release(Id);        
    }
}
