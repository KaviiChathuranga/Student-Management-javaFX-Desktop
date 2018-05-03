/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.BatchDto;


/**
 *
 * @author Kavindu
 */
public interface BatchBO extends SuperBO{
    public boolean addBatch(BatchDto batchDto)throws Exception;
    public boolean updateBatch(BatchDto batchDto)throws Exception;
    public boolean removeBatch(String id)throws Exception;
    public BatchDto searchBatch(String id)throws Exception;
    public List<BatchDto>getAll()throws Exception;
    
    public BatchDto searchNameOfBatch(String name)throws Exception;
}
