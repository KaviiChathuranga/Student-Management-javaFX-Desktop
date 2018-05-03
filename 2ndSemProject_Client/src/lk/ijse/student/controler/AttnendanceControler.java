/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.AttendanceDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.AttendanceService;

/**
 *
 * @author Kavindu
 */
public class AttnendanceControler {
    
    
      public static boolean add(AttendanceDto attendanceDto)throws Exception{
          AttendanceService service=(AttendanceService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.ATTENDANCE);
        return service.addAttendance(attendanceDto);
    }

       public static List<AttendanceDto> getAll()throws Exception{
          AttendanceService service=(AttendanceService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.ATTENDANCE);
        return service.getAll();
    }
       
      public static void registerObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.ATTENDANCE);
        Service.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb Service = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.ATTENDANCE);
        Service.unregisterObserver(observer);
    }
    
    public static boolean reserveAttendance(String id) throws Exception{
        AttendanceService Service = (AttendanceService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.ATTENDANCE);
        return Service.reserve(id);
    }
    
    public static boolean releaseAttendance(String id) throws Exception{
        AttendanceService Service = (AttendanceService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.ATTENDANCE);
        return Service.release(id);        
    }
}
