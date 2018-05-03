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
import lk.ijse.student.buisness.custom.StudentBO;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.StudentService;
import lk.ijse.student.observer.SubjectOb;

/**
 *
 * @author Kavindu
 */
public class StudentServiceImpl extends UnicastRemoteObject implements StudentService,SubjectOb{
    private StudentBO studentBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<StudentService> studentBook=new ReservstionImpl<>();
    
    public StudentServiceImpl()throws RemoteException{
        studentBO=(StudentBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.STUDENT);
    }
    
    @Override
    public boolean reserve(Object id) throws Exception {
      return studentBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return studentBook.release(id);
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
                    Logger.getLogger(StudentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public boolean addStudent(StudentDto studentDto) throws Exception {
        boolean result = studentBO.addStudent(studentDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) throws Exception {
        boolean result = false;
        if (studentBook.reserve(studentDto.getSid(), this, true)) {
            result = studentBO.updateStudent(studentDto);
            notifyAllObservers();
            if (studentBook.isReservedInternally(studentDto.getSid())){
                studentBook.release(studentDto.getSid());
            }
        }
        return result;
    }

    @Override
    public boolean removeStudent(String id) throws Exception {
         boolean result = false;
        if (studentBook.reserve(id, this, true)) {
            result = studentBO.removeStudent(id);
            notifyAllObservers();
            if (studentBook.isReservedInternally(id)){
                studentBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public StudentDto searchStudent(String id) throws Exception {
        return studentBO.searchStudent(id);
    }

    @Override
    public List<StudentDto> getAll() throws Exception {
       return studentBO.getAll();
    }
    
}
