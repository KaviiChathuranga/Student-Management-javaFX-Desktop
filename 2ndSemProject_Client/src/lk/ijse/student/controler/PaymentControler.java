/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.PaymentDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.PaymentService;

/**
 *
 * @author Kavindu
 */
public class PaymentControler {
    
    public static boolean addPayment(PaymentDto paymentDto)throws Exception{
        PaymentService service=(PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
        return service.addPay(paymentDto);    
    }
    public static List<PaymentDto> getAll()throws Exception{
       PaymentService service=(PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
       return service.getAll(); 
    }
    public static PaymentDto search(String id)throws Exception{
       PaymentService service=(PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
       return service.searchPay(id); 
    }
    public static List<PaymentDto> searchPayment(String bid,String sub,String sid)throws Exception{
       PaymentService service=(PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
       return service.searchPayment(bid,sub,sid); 
    }
    public static List<PaymentDto> checkCount(String bid,String sub,String month)throws Exception{
       PaymentService service=(PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
       return service.checkCount(bid,sub,month); 
    }
    
     public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
        Service.registerObserver(observer);
    }
     
     
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveAttendance(String id) throws Exception{
        PaymentService Service = (PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
        return Service.reserve(id);
    }
    
    public static boolean releaseAttendance(String id) throws Exception{
        PaymentService Service = (PaymentService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.PAYMENT);
        return Service.release(id);        
    }
}
