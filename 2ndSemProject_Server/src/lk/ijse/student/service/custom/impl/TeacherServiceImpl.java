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
import lk.ijse.student.buisness.custom.TeacherBO;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.TeacherService;

/**
 *
 * @author Kavindu
 */
public class TeacherServiceImpl extends UnicastRemoteObject implements TeacherService,SubjectOb{
    
    private TeacherBO teacherBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<TeacherService> TeacherBook=new ReservstionImpl<>();
    
     public TeacherServiceImpl()throws RemoteException{
        teacherBO=(TeacherBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.TEACHER);
    }
    
    @Override
    public boolean addTeacher(TeacherDto teacherDto) throws Exception {
        boolean result = teacherBO.addTeacher(teacherDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateTeacher(TeacherDto teacherDto) throws Exception {
         boolean result = false;
        if (TeacherBook.reserve(teacherDto.getTid(), this, true)) {
            result = teacherBO.updateTeacher(teacherDto);
            notifyAllObservers();
            if (TeacherBook.isReservedInternally(teacherDto.getTid())){
                TeacherBook.release(teacherDto.getTid());
            }
        }
        return result;
    }

    @Override
    public boolean removeTeacher(String id) throws Exception {
                 boolean result = false;
        if (TeacherBook.reserve(id, this, true)) {
            result = teacherBO.removeTeacher(id);
            notifyAllObservers();
            if (TeacherBook.isReservedInternally(id)){
                TeacherBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public TeacherDto searchTeahcer(String id) throws Exception {
       return teacherBO.searchTeahcer(id);
    }

    @Override
    public List<TeacherDto> getAll() throws Exception {
        return teacherBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return TeacherBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return TeacherBook.release(id);
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
                    Logger.getLogger(TeacherServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
}
