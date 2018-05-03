/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.repository.custom;

import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.repository.SuperRepository;

/**
 *
 * @author Kavindu
 */
public interface BatchRepository extends SuperRepository<Batch, String>{
//    public BatchDto searchNameOfBatch(String name)throws Exception;
}
