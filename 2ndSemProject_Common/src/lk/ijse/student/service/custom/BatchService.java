/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom;

import java.util.List;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 */
public interface BatchService extends SuperService{
    public boolean addBatch(BatchDto batchDto)throws Exception;
    public boolean updateBatch(BatchDto batchDto)throws Exception;
    public boolean removeBatch(String id)throws Exception;
    public BatchDto searchBatch(String id)throws Exception;
    public List<BatchDto>getAll()throws Exception;
    
    public BatchDto searchNameOfBatch(String name)throws Exception;
    
}
