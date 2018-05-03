/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.StudentDto;

/**
 *
 * @author Kavindu
 */
public interface StudentBO extends SuperBO{
    public boolean addStudent(StudentDto studentDto)throws Exception;
    public boolean updateStudent(StudentDto studentDto)throws Exception;
    public boolean removeStudent(String id)throws Exception;
    public StudentDto searchStudent(String id)throws Exception;
    public List<StudentDto>getAll()throws Exception;
}
