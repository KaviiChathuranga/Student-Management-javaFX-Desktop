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
import lk.ijse.student.buisness.custom.BatchBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.BatchService;

/**
 *
 * @author Kavindu
 */
public class BatchServiceImpl extends UnicastRemoteObject implements BatchService,SubjectOb{
    private BatchBO batchBO;
   
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    
    private static ReservstionImpl<BatchService> batchBook=new ReservstionImpl<>();
    
    public BatchServiceImpl()throws RemoteException{
        batchBO=(BatchBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.BATCH);
    }
    
    @Override
    public boolean addBatch(BatchDto batchDto) throws Exception {
        boolean result = batchBO.addBatch(batchDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateBatch(BatchDto batchDto) throws Exception {
               boolean result = false;
        if (batchBook.reserve(batchDto.getBid(), this, true)) {
            result = batchBO.updateBatch(batchDto);
            notifyAllObservers();
            if (batchBook.isReservedInternally(batchDto.getBid())){
                batchBook.release(batchDto.getBid());
            }
        }
        return result;
    }

    @Override
    public boolean removeBatch(String id) throws Exception {
        boolean result = false;
        if (batchBook.reserve(id, this, true)) {
            result = batchBO.removeBatch(id);
            notifyAllObservers();
            if (batchBook.isReservedInternally(id)){
                batchBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public BatchDto searchBatch(String id) throws Exception {
        return batchBO.searchBatch(id);
    }

    @Override
    public List<BatchDto> getAll() throws Exception {
        return batchBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return batchBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
       return batchBook.release(id);
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
    public BatchDto searchNameOfBatch(String name) throws Exception {
        return batchBO.searchNameOfBatch(name);
    }
    
}
