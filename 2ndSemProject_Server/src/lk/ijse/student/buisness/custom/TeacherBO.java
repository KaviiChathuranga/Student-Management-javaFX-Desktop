/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.TeacherDto;


/**
 *
 * @author Kavindu
 */
public interface TeacherBO extends SuperBO{
    public boolean addTeacher(TeacherDto teacherDto)throws Exception;
    public boolean updateTeacher(TeacherDto teacherDto)throws Exception;
    public boolean removeTeacher(String id)throws Exception;
    public TeacherDto searchTeahcer(String id)throws Exception;
    public List<TeacherDto>getAll()throws Exception;
}
