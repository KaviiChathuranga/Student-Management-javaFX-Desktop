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
import lk.ijse.student.buisness.custom.ExamBO;
import lk.ijse.student.dto.ExamDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.ExamService;

/**
 *
 * @author Kavindu
 */
public class ExamServiceImpl extends UnicastRemoteObject implements ExamService,SubjectOb{
    private ExamBO examBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<ExamService> examBook=new ReservstionImpl<>();
    
     public ExamServiceImpl()throws RemoteException{
        examBO=(ExamBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.EXAM);
    }
    
    @Override
    public boolean addExam(ExamDto examDto) throws Exception {
         boolean result = examBO.addExam(examDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateExam(ExamDto examDto) throws Exception {
                      boolean result = false;
        if (examBook.reserve(examDto.getEid(), this, true)) {
            result = examBO.updateExam(examDto);
            notifyAllObservers();
            if (examBook.isReservedInternally(examDto.getEid())){
                examBook.release(examDto.getEid());
            }
        }
        return result;
    }

    @Override
    public boolean removeExam(String id) throws Exception {
        boolean result = false;
        if (examBook.reserve(id, this, true)) {
            result = examBO.removeExam(id);
            notifyAllObservers();
            if (examBook.isReservedInternally(id)){
                examBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public ExamDto searchExam(String id) throws Exception {
        return examBO.searchExam(id);
    }

    @Override
    public List<ExamDto> getAll() throws Exception {
        return examBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
       return examBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return examBook.release(id);
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
                    Logger.getLogger(ExamServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
}
