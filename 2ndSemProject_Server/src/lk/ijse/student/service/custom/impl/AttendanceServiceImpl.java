/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.student.buisness.BuisnessFactory;
import lk.ijse.student.buisness.custom.AttendanceBO;
import lk.ijse.student.dto.AttendanceDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.AttendanceService;

/**
 *
 * @author Kavindu
 */
public class AttendanceServiceImpl  extends UnicastRemoteObject implements AttendanceService,SubjectOb{
    
    private AttendanceBO attendanceBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<AttendanceService> attendanceBook=new ReservstionImpl<>();
    
    public AttendanceServiceImpl()throws RemoteException{
        attendanceBO=(AttendanceBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.ATTENDANCE);
    }
    
    @Override
    public boolean addAttendance(AttendanceDto attendanceDto) throws Exception {
        boolean result = attendanceBO.addAttendance(attendanceDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateAttendance(AttendanceDto attendanceDto) throws Exception {
       boolean result = false;
        if (attendanceBook.reserve(attendanceDto.getAid(), this, true)) {
            result = attendanceBO.updateAttendance(attendanceDto);
            notifyAllObservers();
            if (attendanceBook.isReservedInternally(attendanceDto.getAid())){
                attendanceBook.release(attendanceDto.getAid());
            }
        }
        return result;
    }

    @Override
    public boolean removeAttendance(String id) throws Exception {
         boolean result = false;
        if (attendanceBook.reserve(id, this, true)) {
            result = attendanceBO.removeAttendance(id);
            notifyAllObservers();
            if (attendanceBook.isReservedInternally(id)){
                attendanceBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public AttendanceDto searchAttendance(String id) throws Exception {
       return attendanceBO.searchAttendance(id);
    }

    @Override
    public List<AttendanceDto> getAll() throws Exception {
        return attendanceBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return attendanceBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
       return attendanceBook.release(id);
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        alObserver.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
      alObserver.remove(observer);
    }

    @Override
    public void notifyAllObservers() throws Exception {
         new Thread(() -> {
            for (Observer observer : alObserver) {
                try {
                    observer.update();
                } catch (Exception ex) {
                    Logger.getLogger(AttendanceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
}
