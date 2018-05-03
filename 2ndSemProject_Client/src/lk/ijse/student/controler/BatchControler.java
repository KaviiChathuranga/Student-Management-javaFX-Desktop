/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.BatchService;


/**
 *
 * @author Kavindu
 */
public class BatchControler {
      public static boolean addBatch(BatchDto batchDto)throws Exception{
          BatchService batchService=(BatchService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        return batchService.addBatch(batchDto);
    }
       public static BatchDto searchNameOfBatch(String name)throws Exception{
          BatchService batchService=(BatchService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        return batchService.searchNameOfBatch(name);
    }
       public static List<BatchDto> getAll()throws Exception{
          BatchService batchService=(BatchService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        return batchService.getAll();
    }
      public static void registerObserver(Observer observer) throws Exception{
        SubjectOb batchService = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        batchService.registerObserver(observer);
    }
    
    public static void unregisterObserver(Observer observer) throws Exception{
        SubjectOb batchService = (SubjectOb) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        batchService.unregisterObserver(observer);
    }
    
    public static boolean reserveBatch(String batchId) throws Exception{
        BatchService batchService = (BatchService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        return batchService.reserve(batchId);
    }
    
    public static boolean releaseBatch(String batchId) throws Exception{
        BatchService batchService = (BatchService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH);
        return batchService.release(batchId);        
    }
}
