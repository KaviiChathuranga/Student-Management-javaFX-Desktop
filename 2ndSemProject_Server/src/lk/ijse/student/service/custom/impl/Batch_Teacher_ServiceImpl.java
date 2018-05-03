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
import lk.ijse.student.buisness.custom.Batch_TeacherBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.Batch_TeacherService;

/**
 *
 * @author Kavindu
 */
public class Batch_Teacher_ServiceImpl  extends UnicastRemoteObject implements Batch_TeacherService,SubjectOb{

    private Batch_TeacherBO batch_TeacherBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<Batch_TeacherService> butch_teacher_Book=new ReservstionImpl<>();
    
    public Batch_Teacher_ServiceImpl() throws RemoteException{
        batch_TeacherBO=(Batch_TeacherBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.BATCH_TEACHER);
    }
    
    @Override
    public boolean reserve(Object id) throws Exception {
        return butch_teacher_Book.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    Logger.getLogger(BatchServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public boolean addBatch_Teacher(BatchDto batchDto,List<SubjectDto>subjectDtos) throws Exception {
          boolean result = batch_TeacherBO.addBatch_Teacher(batchDto,subjectDtos);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateBatch_Teacher(Batch_SubjectDTO batch_TeacherDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeBatch_Teacher(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Batch_SubjectDTO> searchBatch_subject(String id) throws Exception {
        return batch_TeacherBO.searchBatch_subject(id);
    }

    @Override
    public List<Batch_SubjectDTO> getAll() throws Exception {
       return batch_TeacherBO.getAll();
    }
    
}
