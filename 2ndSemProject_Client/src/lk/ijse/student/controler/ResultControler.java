/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.PaymentDto;
import lk.ijse.student.dto.ResultDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.PaymentService;
import lk.ijse.student.service.custom.ResultService;


/**
 *
 * @author Kavindu
 */
public class ResultControler {
    
    public static boolean addResult(ResultDto resultDto)throws Exception{
        ResultService service=(ResultService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
        return service.addResult(resultDto);    
    }
     public static boolean updateResult(ResultDto resultDto)throws Exception{
        ResultService service=(ResultService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
        return service.updateResult(resultDto);    
    }
    public static List<ResultDto> getAll()throws Exception{
       ResultService service=(ResultService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
       return service.getAll(); 
    }
      public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveAttendance(String id) throws Exception{
        ResultService Service = (ResultService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
        return Service.reserve(id);
    }
    
    public static boolean releaseAttendance(String id) throws Exception{
        ResultService Service = (ResultService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
        return Service.release(id);        
    }

    public static ResultDto searchResult(String text, String sid)throws Exception {
       ResultService service=(ResultService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.RESULT);
       return service.searchResult(text,sid); 
    }
}
