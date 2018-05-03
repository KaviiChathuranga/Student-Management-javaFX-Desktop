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
import lk.ijse.student.buisness.custom.SubjectBO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.SubjectService;

/**
 *
 * @author Kavindu
 */
public class SubjectServiceImpl extends UnicastRemoteObject implements SubjectService,SubjectOb{

    private SubjectBO subjectBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<SubjectService> subjectBook=new ReservstionImpl<>();
    
     public SubjectServiceImpl()throws RemoteException{
        subjectBO=(SubjectBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.SUBJECT);
    }
    
    @Override
    public boolean addSub(SubjectDto subjectDto) throws Exception {
       boolean result = subjectBO.addSub(subjectDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateSub(SubjectDto subjectDto) throws Exception {
                boolean result = false;
        if (subjectBook.reserve(subjectDto.getSubId(), this, true)) {
            result = subjectBO.updateSub(subjectDto);
            notifyAllObservers();
            if (subjectBook.isReservedInternally(subjectDto.getSubId())){
                subjectBook.release(subjectDto.getSubId());
            }
        }
        return result;
    }

    @Override
    public boolean removeSub(String id) throws Exception {
        boolean result = false;
        if (subjectBook.reserve(id, this, true)) {
            result = subjectBO.removeSub(id);
            notifyAllObservers();
            if (subjectBook.isReservedInternally(id)){
                subjectBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public SubjectDto searchSubject(String id) throws Exception {
       return subjectBO.searchSubject(id);
    }

    @Override
    public List<SubjectDto> getAll() throws Exception {
       return subjectBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
       return subjectBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
       return subjectBook.release(id);
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
                    Logger.getLogger(SubjectServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public List<SubjectDto> searchAll(String id) throws Exception {
        return subjectBO.searchAll(id);
    }

    @Override
    public SubjectDto searchSubjectName(String name) throws Exception {
         return subjectBO.searchSubjectName(name);
    }
    
}
