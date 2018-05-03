/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom;

import java.util.List;
import lk.ijse.student.dto.ExamDto;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 */
public interface ExamService extends SuperService{
    public boolean addExam(ExamDto examDto)throws Exception;
    public boolean updateExam(ExamDto examDto)throws Exception;
    public boolean removeExam(String id)throws Exception;
    public ExamDto searchExam(String id)throws Exception;
    public List<ExamDto>getAll()throws Exception;
}
