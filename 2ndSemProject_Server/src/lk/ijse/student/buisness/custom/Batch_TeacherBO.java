/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.SubjectDto;

/**
 *
 * @author Kavindu
 */
public interface Batch_TeacherBO extends SuperBO{
    public boolean addBatch_Teacher(BatchDto batchDto,List<SubjectDto>subjectDtos)throws Exception;
    public boolean updateBatch_Teacher(Batch_SubjectDTO batch_TeacherDTO)throws Exception;
    public boolean removeBatch_Teacher(String id)throws Exception;
    public List<Batch_SubjectDTO> searchBatch_subject(String id)throws Exception;
    public List<Batch_SubjectDTO>getAll()throws Exception;
}
