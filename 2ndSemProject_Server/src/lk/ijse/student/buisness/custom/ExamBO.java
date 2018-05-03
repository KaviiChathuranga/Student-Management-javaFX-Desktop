/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.ExamDto;


/**
 *
 * @author Kavindu
 */
public interface ExamBO extends SuperBO{
    public boolean addExam(ExamDto examDto)throws Exception;
    public boolean updateExam(ExamDto examDto)throws Exception;
    public boolean removeExam(String id)throws Exception;
    public ExamDto searchExam(String id)throws Exception;
    public List<ExamDto>getAll()throws Exception;
}
