/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.repository.custom.impl;


import lk.ijse.student.buisness.custom.BatchBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.repository.SuperREpositoryImpl;
import lk.ijse.student.repository.custom.BatchRepository;

/**
 *
 * @author Kavindu
 */
public class BatchRepositoryImpl extends SuperREpositoryImpl<Batch, String> implements BatchRepository{

    public BatchRepositoryImpl() {
    }

//    @Override
//    public BatchDto searchNameOfBatch(String name) throws Exception {
//        return 
//    }
    
}
