/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.AttendanceDto;


/**
 *
 * @author Kavindu
 */
public interface AttendanceBO extends SuperBO{
    public boolean addAttendance(AttendanceDto attendanceDto)throws Exception;
    public boolean updateAttendance(AttendanceDto attendanceDto)throws Exception;
    public boolean removeAttendance(String id)throws Exception;
    public AttendanceDto searchAttendance(String id)throws Exception;
    public List<AttendanceDto>getAll()throws Exception;
}
