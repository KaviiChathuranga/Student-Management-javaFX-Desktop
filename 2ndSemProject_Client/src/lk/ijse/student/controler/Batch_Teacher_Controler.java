/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.controler;

import java.util.List;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.proxy.ProxyHandler;
import lk.ijse.student.service.ServiceFactory;
import lk.ijse.student.service.custom.Batch_TeacherService;

/**
 *
 * @author Kavindu
 */
public class Batch_Teacher_Controler {
     public static boolean addBatch(BatchDto batchDto,List<SubjectDto>subjectDtos)throws Exception{
          Batch_TeacherService batchService=(Batch_TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH_TEACHER);
          return batchService.addBatch_Teacher(batchDto,subjectDtos);
    }
     
     public static List<Batch_SubjectDTO> search(String id)throws Exception{
          Batch_TeacherService batchService=(Batch_TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH_TEACHER);
          return batchService.searchBatch_subject(id);
     }
      public static List<Batch_SubjectDTO> getAll()throws Exception{
          Batch_TeacherService batchService=(Batch_TeacherService) ProxyHandler.getInstance().getServiceTypes(ServiceFactory.ServiceTypes.BATCH_TEACHER);
          return batchService.getAll();
    }
}
