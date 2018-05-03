/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.SubjectDto;


/**
 *
 * @author Kavindu
 */
public interface SubjectBO extends SuperBO{
    public boolean addSub(SubjectDto subjectDto)throws Exception;
    public boolean updateSub(SubjectDto subjectDto)throws Exception;
    public boolean removeSub(String id)throws Exception;
    public SubjectDto searchSubject(String id)throws Exception;
    public List<SubjectDto>getAll()throws Exception;
    
    public List<SubjectDto>searchAll(String name)throws Exception;
    public SubjectDto searchSubjectName(String name)throws Exception;
}
