/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom;

import java.util.List;
import lk.ijse.student.dto.AttendanceDto;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 */
public interface AttendanceService extends SuperService{
    public boolean addAttendance(AttendanceDto attendanceDto)throws Exception;
    public boolean updateAttendance(AttendanceDto attendanceDto)throws Exception;
    public boolean removeAttendance(String id)throws Exception;
    public AttendanceDto searchAttendance(String id)throws Exception;
    public List<AttendanceDto>getAll()throws Exception;
}
